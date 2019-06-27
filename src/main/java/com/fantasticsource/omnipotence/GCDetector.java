package com.fantasticsource.omnipotence;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

public class GCDetector
{
    //TODO Should be able to replace this with a listener for garbage collector notifications; see Test class

    private static boolean active = false, wanted = false;

    private GCDetector()
    {
    }

    public static void start()
    {
        wanted = true;
        if (!active)
        {
            active = true;
            new GCDetector();
        }
    }

    public static void stop()
    {
        wanted = false;
    }

    @Override
    public void finalize()
    {
        if (wanted)
        {
            new GCDetector();
            MinecraftForge.EVENT_BUS.post(new GCEvent());
        }
        else active = false;
    }

    public class GCEvent extends Event
    {
        private GCEvent()
        {
        }
    }
}
