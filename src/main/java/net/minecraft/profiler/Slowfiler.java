package net.minecraft.profiler;

import com.google.common.collect.Lists;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.function.Supplier;

public class Slowfiler
{
    public static final long NORMAL_TICK_TIME_NANOS = 50_000_000;

    public boolean profilingEnabled;

    protected String sectionName = null;
    protected Stack<String> sectionNames = new Stack<>();
    protected Stack<Long> sectionTimestamps = new Stack<>();
    protected HashMap<String, Long> sectionNanoTimes = new HashMap<>();

    public void clearProfiling()
    {
        sectionName = null;
        sectionNames.clear();
        sectionTimestamps.clear();
        sectionNanoTimes.clear();
    }

    public void func_194340_a(Supplier<String> p_194340_1_)
    {
        startSection(p_194340_1_.get());
    }

    public void startSection(Class<?> profiledClass)
    {
        startSection(profiledClass.getSimpleName());
    }

    public void startSection(String name)
    {
        //TODO Apparently either startSection or endSection is very slow?
        if (profilingEnabled)
        {
            sectionName = sectionName == null ? name : sectionName + "." + name;
            sectionNames.push(sectionName);

            sectionTimestamps.push(System.nanoTime());
        }
    }

    public void endSection()
    {
        //TODO Apparently either startSection or endSection is very slow?
        if (profilingEnabled)
        {
            sectionNanoTimes.put(sectionName, sectionNanoTimes.getOrDefault(sectionName, 0L) + System.nanoTime() - sectionTimestamps.pop());
            sectionName = sectionNames.pop();
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_194339_b(Supplier<String> p_194339_1_)
    {
        endSection();
        func_194340_a(p_194339_1_);
    }

    public void endStartSection(String name)
    {
        endSection();
        startSection(name);
    }


    public String getNameOfLastSection()
    {
        return sectionName == null ? "[UNKNOWN]" : sectionName;
    }


    public List<Slowfiler.Result> getProfilingData(String targetSectionName)
    {
        if (!profilingEnabled) return Collections.emptyList();


        long rootNanos = sectionNanoTimes.getOrDefault("root", 0L);
        long mainSectionNanos = sectionNanoTimes.getOrDefault(targetSectionName, 0L);

        List<Slowfiler.Result> list = Lists.newArrayList();

        if (!targetSectionName.isEmpty()) targetSectionName = targetSectionName + ".";

        long sectionNanos = 0;

        for (String key : sectionNanoTimes.keySet())
        {
            if (key.length() > targetSectionName.length() && key.startsWith(targetSectionName) && key.indexOf(".", targetSectionName.length() + 1) < 0)
            {
                sectionNanos += sectionNanoTimes.get(key);
            }
        }

        long subSectionNanosSum = sectionNanos;

        if (sectionNanos < mainSectionNanos) sectionNanos = mainSectionNanos;
        if (rootNanos < sectionNanos) rootNanos = sectionNanos;

        for (String key : sectionNanoTimes.keySet())
        {
            if (key.length() > targetSectionName.length() && key.startsWith(targetSectionName) && key.indexOf(".", targetSectionName.length() + 1) < 0)
            {
                String name = key.substring(targetSectionName.length());
                double difTimes100 = sectionNanoTimes.get(key) * 100;
                list.add(new Slowfiler.Result(name, difTimes100 / sectionNanos, difTimes100 / rootNanos, difTimes100 / NORMAL_TICK_TIME_NANOS));
            }
        }

        if (sectionNanos > subSectionNanosSum)
        {
            double difTimes100 = (sectionNanos - subSectionNanosSum) * 100;
            list.add(new Slowfiler.Result("unspecified", difTimes100 / sectionNanos, difTimes100 / rootNanos, difTimes100 / rootNanos));
        }

        Collections.sort(list);
        double difTimes100 = sectionNanos * 100;
        list.add(0, new Slowfiler.Result(targetSectionName, difTimes100 / sectionNanos, difTimes100 / rootNanos, difTimes100 / rootNanos));
        return list;
    }


    public static final class Result implements Comparable<Slowfiler.Result>
    {
        public String profilerName;
        public double usePercentage, totalUsePercentage, tickUsePercentage;

        public Result(String profilerName, double usePercentage, double totalUsePercentage, double tickUsePercentage)
        {
            this.profilerName = profilerName;
            this.usePercentage = usePercentage;
            this.totalUsePercentage = totalUsePercentage;
            this.tickUsePercentage = tickUsePercentage;
        }

        public int compareTo(Slowfiler.Result p_compareTo_1_)
        {
            if (p_compareTo_1_.usePercentage < usePercentage) return -1;
            if (p_compareTo_1_.usePercentage > usePercentage) return 1;
            return p_compareTo_1_.profilerName.compareTo(profilerName);
        }

        @SideOnly(Side.CLIENT)
        public int getColor()
        {
            return (profilerName.hashCode() & 11184810) + 4473924;
        }
    }
}
