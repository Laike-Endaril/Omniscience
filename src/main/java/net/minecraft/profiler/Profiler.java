package net.minecraft.profiler;

import com.google.common.collect.Lists;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;
import java.util.function.Supplier;

public class Profiler
{
    private final Stack<String> sectionStack = new Stack<>();
    private final List<Long> timestampList = new ArrayList<>();
    public boolean profilingEnabled;
    private String profilingSection = "";
    private final HashMap<String, Long> profilingMap = new HashMap<>();

    public void clearProfiling()
    {
        profilingMap.clear();
        profilingSection = "";
        sectionStack.clear();
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
        if (profilingEnabled)
        {
            if (profilingSection.equals("")) profilingSection = name;
            else profilingSection += "." + name;

            sectionStack.push(profilingSection);
            timestampList.add(System.nanoTime());
        }
    }

    public void endSection()
    {
        if (profilingEnabled)
        {
            long newTime = System.nanoTime();
            long oldTime = timestampList.remove(timestampList.size() - 1);
            long timeDelta = newTime - oldTime;

            sectionStack.pop();

            profilingMap.put(profilingSection, profilingMap.getOrDefault(profilingSection, 0L) + timeDelta);

            profilingSection = sectionStack.isEmpty() ? "" : sectionStack.peek();
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

    public List<Profiler.Result> getProfilingData(String profilerName)
    {
        if (!profilingEnabled) return Collections.emptyList();


        long rootTimeDelta = profilingMap.getOrDefault("root", 0L);
        long timeDelta = profilingMap.getOrDefault(profilerName, 0L);
        List<Profiler.Result> list = Lists.newArrayList();

        if (!profilerName.isEmpty())
        {
            profilerName = profilerName + ".";
        }

        long sectionTimeDelta = 0;

        for (String s : profilingMap.keySet())
        {
            if (s.length() > profilerName.length() && s.startsWith(profilerName) && s.indexOf(".", profilerName.length() + 1) < 0)
            {
                sectionTimeDelta += profilingMap.get(s);
            }
        }

        long sectionTimeDelta2 = sectionTimeDelta;

        if (sectionTimeDelta < timeDelta) sectionTimeDelta = timeDelta;
        if (rootTimeDelta < sectionTimeDelta) rootTimeDelta = sectionTimeDelta;

        for (String s1 : profilingMap.keySet())
        {
            if (s1.length() > profilerName.length() && s1.startsWith(profilerName) && s1.indexOf(".", profilerName.length() + 1) < 0)
            {
                long l = profilingMap.get(s1);
                double usePercentage = (double) l * 100 / sectionTimeDelta;
                double totalUsePercentage = (double) l * 100 / rootTimeDelta;
                String name = s1.substring(profilerName.length());
                list.add(new Profiler.Result(name, usePercentage, totalUsePercentage));
            }
        }

        if (sectionTimeDelta > sectionTimeDelta2)
        {
            list.add(new Profiler.Result("unspecified", (double) (sectionTimeDelta - sectionTimeDelta2) * 100 / sectionTimeDelta, (double) (sectionTimeDelta - sectionTimeDelta2) * 100 / rootTimeDelta));
        }

        Collections.sort(list);
        list.add(0, new Profiler.Result(profilerName, 100, (double) sectionTimeDelta * 100 / rootTimeDelta));
        return list;
    }

    public String getNameOfLastSection()
    {
        return sectionStack.isEmpty() ? "[UNKNOWN]" : sectionStack.peek();
    }

    public static final class Result implements Comparable<Profiler.Result>
    {
        public double usePercentage;
        public double totalUsePercentage;
        public String profilerName;

        public Result(String profilerName, double usePercentage, double totalUsePercentage)
        {
            this.profilerName = profilerName;
            this.usePercentage = usePercentage;
            this.totalUsePercentage = totalUsePercentage;
        }

        public int compareTo(Profiler.Result p_compareTo_1_)
        {
            if (p_compareTo_1_.usePercentage < usePercentage)
            {
                return -1;
            }
            else
            {
                return p_compareTo_1_.usePercentage > usePercentage ? 1 : p_compareTo_1_.profilerName.compareTo(profilerName);
            }
        }

        @SideOnly(Side.CLIENT)
        public int getColor()
        {
            return (profilerName.hashCode() & 11184810) + 4473924;
        }
    }
}