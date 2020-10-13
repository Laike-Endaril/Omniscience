package com.fantasticsource.omniscience;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

public class GCMessager
{
    protected static boolean initialized = false;
    protected static int prevGCRuns = 0;
    protected static long prevGCTime = 0;

    public static void init(FMLServerStartedEvent event)
    {
        System.out.println(TextFormatting.LIGHT_PURPLE + "Starting GCMessager");
        if (!initialized)
        {
            MinecraftForge.EVENT_BUS.register(GCMessager.class);
            initialized = true;
        }
        for (GarbageCollectorMXBean gcBean : ManagementFactory.getGarbageCollectorMXBeans())
        {
            prevGCRuns += gcBean.getCollectionCount();
            prevGCTime += gcBean.getCollectionTime();
        }
    }

    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event)
    {
        int gcRuns = 0;
        long gcTime = 0;
        for (GarbageCollectorMXBean gcBean : ManagementFactory.getGarbageCollectorMXBeans())
        {
            gcRuns += gcBean.getCollectionCount();
            gcTime += gcBean.getCollectionTime();
        }
        if (gcRuns > prevGCRuns)
        {
            System.out.println(TextFormatting.LIGHT_PURPLE + "Garbage collector(s) ran " + (gcRuns - prevGCRuns) + " time(s) within the last server tick, spending ~" + (gcTime - prevGCTime) + "ms");
            System.out.println(TextFormatting.LIGHT_PURPLE + "After GC... " + Debug.memData());
            prevGCRuns = gcRuns;
            prevGCTime = gcTime;
        }
    }
}
