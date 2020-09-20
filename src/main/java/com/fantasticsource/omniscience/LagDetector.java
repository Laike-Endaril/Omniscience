package com.fantasticsource.omniscience;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

    public static boolean init(FMLServerStartedEvent event, MinecraftServer server)
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
}
