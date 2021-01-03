package com.fantasticsource.omniscience;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class GCMessager
{
    protected static boolean initialized = false;
    protected static int prevGCRuns;
    protected static long prevGCTime;

    public static void init(FMLServerStartedEvent event)
    {
        System.out.println(TextFormatting.LIGHT_PURPLE + "Starting GCMessager");
        if (!initialized)
        {
            MinecraftForge.EVENT_BUS.register(GCMessager.class);
            initialized = true;
        }
        prevGCRuns = Debug.gcRuns();
        prevGCTime = Debug.gcTime();
    }

    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event)
    {
        int gcRuns = Debug.gcRuns();
        if (gcRuns > prevGCRuns)
        {
            long gcTime = Debug.gcTime();
            System.out.println(TextFormatting.LIGHT_PURPLE + "Garbage collector(s) ran " + (gcRuns - prevGCRuns) + " time(s) within the last server tick, spending ~" + (gcTime - prevGCTime) + "ms");
            System.out.println(TextFormatting.LIGHT_PURPLE + "After GC... " + Debug.memData());
            prevGCRuns = gcRuns;
            prevGCTime = gcTime;
        }
    }
}
