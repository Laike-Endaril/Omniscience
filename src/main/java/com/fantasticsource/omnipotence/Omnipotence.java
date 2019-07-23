package com.fantasticsource.omnipotence;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Omnipotence.MODID, name = Omnipotence.NAME, version = Omnipotence.VERSION, dependencies = "required-after:fantasticlib@[1.12.2.021a,)", acceptableRemoteVersions = "*")
public class Omnipotence
{
    public static final String MODID = "omnipotence";
    public static final String NAME = "Omnipotence";
    public static final String VERSION = "1.12.2.000d";

    static
    {
        MinecraftForge.EVENT_BUS.register(Omnipotence.class);
        Debug.init();

//        try
//        {
//            Timing.init();
//        }
//        catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException e)
//        {
//            MCTools.crash(e, 1000, true);
//        }

//        Test.installGCMonitoring();

//        GCDetector.start();
    }

    @SubscribeEvent
    public static void saveConfig(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(MODID)) ConfigManager.sync(MODID, Config.Type.INSTANCE);
    }

//    @SubscribeEvent(priority = EventPriority.HIGHEST)
//    public static void test(TickEvent.ServerTickEvent event)
//    {
//        System.out.println(Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory());
//        System.out.println(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed());
//        Debug.threadData();
//    }

//    @SubscribeEvent(priority = EventPriority.HIGHEST)
//    public static void test(WorldEvent.Load event)
//    {
//        System.out.println(MODID);
//    }

    @Mod.EventHandler
    public static void serverStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new Commands());
    }
}
