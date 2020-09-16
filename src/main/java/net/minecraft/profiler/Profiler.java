package net.minecraft.profiler;

import com.google.common.collect.Lists;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

public class Profiler
{
    public static final long NORMAL_TICK_TIME_NANOS = 50_000_000;

    private final ArrayList<String> sectionList = new ArrayList<>();
    private final ArrayList<Long> timestampList = new ArrayList<>();
    public boolean profilingEnabled;
    private String profilingSection = "";
    private final HashMap<String, Long> profilingMap = new HashMap<>();


    public void clearProfiling()
    {
        profilingMap.clear();
        profilingSection = "";
        sectionList.clear();
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
            if (!profilingSection.isEmpty())
            {
                profilingSection = profilingSection + ".";
            }

            profilingSection = profilingSection + name;
            sectionList.add(profilingSection);
            timestampList.add(System.nanoTime());
        }
    }


    public void endSection()
    {
        if (profilingEnabled)
        {
            long i = System.nanoTime();
            long j = timestampList.remove(timestampList.size() - 1);
            sectionList.remove(sectionList.size() - 1);
            long k = i - j;

            if (profilingMap.containsKey(profilingSection))
            {
                profilingMap.put(profilingSection, profilingMap.get(profilingSection) + k);
            }
            else
            {
                profilingMap.put(profilingSection, k);
            }

            profilingSection = sectionList.isEmpty() ? "" : sectionList.get(sectionList.size() - 1);
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
        return sectionList.isEmpty() ? "[UNKNOWN]" : sectionList.get(sectionList.size() - 1);
    }


    public List<Profiler.Result> getProfilingData(String profilerName)
    {
        if (!profilingEnabled)
        {
            return Collections.emptyList();
        }
        else
        {
            long i = profilingMap.getOrDefault("root", 0L);
            long j = profilingMap.getOrDefault(profilerName, 0L);
            List<Profiler.Result> list = Lists.newArrayList();

            if (!profilerName.isEmpty())
            {
                profilerName = profilerName + ".";
            }

            long k = 0L;

            for (String s : profilingMap.keySet())
            {
                if (s.length() > profilerName.length() && s.startsWith(profilerName) && s.indexOf(".", profilerName.length() + 1) < 0)
                {
                    k += profilingMap.get(s);
                }
            }

            float f = (float) k;

            if (k < j)
            {
                k = j;
            }

            if (i < k)
            {
                i = k;
            }

            for (String s1 : profilingMap.keySet())
            {
                if (s1.length() > profilerName.length() && s1.startsWith(profilerName) && s1.indexOf(".", profilerName.length() + 1) < 0)
                {
                    long l = profilingMap.get(s1);
                    double d0 = (double) l * 100.0D / (double) k;
                    double d1 = (double) l * 100.0D / (double) i;
                    double d2 = (double) l / (double) NORMAL_TICK_TIME_NANOS;
                    String s2 = s1.substring(profilerName.length());
                    list.add(new Profiler.Result(s2, d0, d1, d2));
                }
            }

            for (String s3 : profilingMap.keySet())
            {
                profilingMap.put(s3, profilingMap.get(s3) * 999L / 1000L);
            }

            if ((float) k > f)
            {
                list.add(new Profiler.Result("unspecified", (double) ((float) k - f) * 100.0D / (double) k, (double) ((float) k - f) * 100.0D / (double) i, (double) ((float) k - f) / (double) NORMAL_TICK_TIME_NANOS));
            }

            Collections.sort(list);
            list.add(0, new Profiler.Result(profilerName, 100.0D, (double) k * 100.0D / (double) i, (double) k / (double) NORMAL_TICK_TIME_NANOS));
            return list;
        }
    }


    public static final class Result implements Comparable<Result>
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

        public int compareTo(Result p_compareTo_1_)
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