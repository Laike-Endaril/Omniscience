package com.fantasticsource.omniscience;

import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static com.fantasticsource.omniscience.Omniscience.NAME;

@SideOnly(Side.SERVER)
public class LagDetector implements Runnable
{
    private final DedicatedServer server;
    private final long[] checkTimes = new long[]{1000, 5000, 10000};
    private boolean go = true;

    private LagDetector(DedicatedServer server)
    {
        this.server = server;
    }

    public static boolean init(MinecraftServer server)
    {
        DedicatedServer dedicatedServer = (DedicatedServer) server;
        if (dedicatedServer.getMaxTickTime() > 0L)
        {
            Thread thread1 = new Thread(new LagDetector(dedicatedServer));
            thread1.setName(NAME + "-LagDetector");
            thread1.setDaemon(true);
            thread1.start();
            return true;
        }
        return false;
    }

    public void run()
    {
        while (go && server.isServerRunning())
        {
            long i = server.getCurrentTime();
            long j = MinecraftServer.getCurrentTimeMillis();
            long tickTime = j - i;

            if (tickTime >= checkTimes[2])
            {
                System.out.println(TextFormatting.RED + "One tick has taken " + ((double) tickTime / 1000) + "seconds so far!");
                System.out.println(Debug.memData());

//                  java.lang.ArrayIndexOutOfBoundsException: -1
//                  at java.util.ArrayList.elementData(ArrayList.java:422)
//                  at java.util.ArrayList.get(ArrayList.java:435)
//                  at net.minecraft.profiler.Profiler.endSection(Profiler.java:76)
//                  at net.minecraft.profiler.Profiler.endStartSection(Profiler.java:149)
//                  at net.minecraft.server.MinecraftServer.updateTimeLightAndEntities(MinecraftServer.java:787)
//                  at net.minecraft.server.dedicated.DedicatedServer.updateTimeLightAndEntities(DedicatedServer.java:397)
//                  at net.minecraft.server.MinecraftServer.tick(MinecraftServer.java:668)
//                  at net.minecraft.server.MinecraftServer.run(MinecraftServer.java:526)
//                  at java.lang.Thread.run(Thread.java:748)

                //TODO Need to fix this before re-enabling it!  See stacktrace above!  Might be due to cross-thread dysync in the profiler calls
//                try
//                {
//                    String s = MCTools.getConfigDir() + ".." + File.separator + "logs" + File.separator + Timestamp.getInstance().toString().replaceAll(":", ".") + "_WATCHDOG_PROFILING.log";
//                    System.out.println(s);

//                    BufferedWriter writer = new BufferedWriter(new FileWriter(s));
//                    writer.write(getProfilerResults());
//                    writer.close();
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }

                go = false;
            }
            else if (tickTime >= checkTimes[1])
            {
                System.out.println(TextFormatting.RED + "One tick has taken " + ((double) tickTime / 1000) + "seconds so far!");
                System.out.println(Debug.memData());

                try
                {
                    Thread.sleep(i + checkTimes[2] - j);
                }
                catch (InterruptedException e)
                {
                }
            }
            else if (tickTime >= checkTimes[0])
            {
                System.out.println(TextFormatting.RED + "One tick has taken " + ((double) tickTime / 1000) + "seconds so far!");
                System.out.println(Debug.memData());

                //TODO Need to fix this before re-enabling it!  See stacktrace above!  Might be due to cross-thread dysync in the profiler calls
//                server.profiler.profilingEnabled = true;
//                server.profiler.clearProfiling();

                try
                {
                    Thread.sleep(i + checkTimes[1] - j);
                }
                catch (InterruptedException e)
                {
                }
            }
            else
            {
                try
                {
                    Thread.sleep(i + checkTimes[0] - j);
                }
                catch (InterruptedException e)
                {
                }
            }
        }
    }

    private String getProfilerResults()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("---- LagDetector Profiler Results ----\n");
        stringbuilder.append("// ");
        stringbuilder.append("Enter Witty Comment Here");
        stringbuilder.append("\n\n");
        stringbuilder.append("--- BEGIN PROFILE DUMP ---\n\n");
        appendProfilerResults(0, "root", stringbuilder, server);
        stringbuilder.append("--- END PROFILE DUMP ---\n\n");
        return stringbuilder.toString();
    }

    private void appendProfilerResults(int depth, String sectionName, StringBuilder builder, MinecraftServer server)
    {
        List<Profiler.Result> list = server.profiler.getProfilingData(sectionName);

        for (int i = 1; i < list.size(); i++)
        {
            Profiler.Result profiler$result = list.get(i);
            builder.append(String.format("[%02d] ", depth));

            for (int j = 0; j < depth; j++) builder.append("|   ");

            builder.append(profiler$result.profilerName).append(" - ").append(String.format("%.2f", profiler$result.usePercentage)).append("%/").append(String.format("%.2f", profiler$result.totalUsePercentage)).append("%\n");

            if (!"unspecified".equals(profiler$result.profilerName))
            {
                try
                {
                    this.appendProfilerResults(depth + 1, sectionName + "." + profiler$result.profilerName, builder, server);
                }
                catch (Exception exception)
                {
                    builder.append("[[ EXCEPTION ").append(exception).append(" ]]");
                }
            }
        }
    }
}
