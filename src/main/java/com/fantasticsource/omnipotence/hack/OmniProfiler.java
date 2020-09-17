package com.fantasticsource.omnipotence.hack;

import com.fantasticsource.tools.ReflectionTool;
import com.google.common.collect.Lists;
import net.minecraft.profiler.Profiler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OmniProfiler extends Profiler
{
    protected static final long NORMAL_TICK_TIME_NANOS = 50_000_000;
    protected static final Field PROFILER_PROFILING_MAP_FIELD = ReflectionTool.getField(Profiler.class, "field_76324_e", "profilingMap");

    protected final Map<String, Long> profilingMap;

    public OmniProfiler()
    {
        profilingMap = (Map<String, Long>) ReflectionTool.get(PROFILER_PROFILING_MAP_FIELD, this);
    }

    public List<OmniProfiler.Result> getProfilingData(String profilerName, boolean omni)
    {
        if (!profilingEnabled)
        {
            return Collections.emptyList();
        }
        else
        {
            long i = profilingMap.getOrDefault("root", 0L);
            long j = profilingMap.getOrDefault(profilerName, 0L);
            List<OmniProfiler.Result> list = Lists.newArrayList();

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
                    list.add(new OmniProfiler.Result(s2, d0, d1, d2));
                }
            }

            for (String s3 : profilingMap.keySet())
            {
                profilingMap.put(s3, profilingMap.get(s3) * 999L / 1000L);
            }

            if ((float) k > f)
            {
                list.add(new OmniProfiler.Result("unspecified", (double) ((float) k - f) * 100.0D / (double) k, (double) ((float) k - f) * 100.0D / (double) i, (double) ((float) k - f) / (double) NORMAL_TICK_TIME_NANOS));
            }

            Collections.sort(list);
            list.add(0, new OmniProfiler.Result(profilerName, 100.0D, (double) k * 100.0D / (double) i, (double) k / (double) NORMAL_TICK_TIME_NANOS));
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