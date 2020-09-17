package com.fantasticsource.omnipotence;

import com.fantasticsource.mctools.ServerTickTimer;
import com.fantasticsource.omnipotence.client.ClientCommands;
import com.fantasticsource.omnipotence.client.PathVisualizer;
import com.fantasticsource.omnipotence.client.ScreenDebug;
import com.fantasticsource.omnipotence.hack.OmniEventBus;
import com.fantasticsource.omnipotence.hack.OmniProfiler;
import com.fantasticsource.tools.ReflectionTool;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.lang.reflect.Field;

@Mod(modid = Omnipotence.MODID, name = Omnipotence.NAME, version = Omnipotence.VERSION, dependencies = "required-after:fantasticlib@[1.12.2.037,)", acceptableRemoteVersions = "*")
public class Omnipotence
{
    protected static final Field MINECRAFT_FORGE_EVENT_BUS_FIELD = ReflectionTool.getField(MinecraftForge.class, "EVENT_BUS");
    protected static final Field MINECRAFT_SERVER_PROFILER_FIELD = ReflectionTool.getField(MinecraftServer.class, "field_71304_b", "profiler");

    public static final String MODID = "omnipotence";
    public static final String NAME = "Omnipotence";
    public static final String VERSION = "1.12.2.000k";

    static
    {
        ReflectionTool.set(MINECRAFT_FORGE_EVENT_BUS_FIELD, null, new OmniEventBus());


        MinecraftForge.EVENT_BUS.register(Omnipotence.class);
        MinecraftForge.EVENT_BUS.register(ServerTickTimer.class);
        MinecraftForge.EVENT_BUS.register(PathVisualizer.class);
        Debug.init();
        Network.init();

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
        {
            ClientCommandHandler.instance.registerCommand(new ClientCommands());
            MinecraftForge.EVENT_BUS.register(ScreenDebug.class);
            PathVisualizer.renderManagerRenderOutlinesField = ReflectionTool.getField(RenderManager.class, "field_178639_r", "renderOutlines");
        }
    }

    @SubscribeEvent
    public static void saveConfig(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(MODID)) ConfigManager.sync(MODID, Config.Type.INSTANCE);
    }

    @Mod.EventHandler
    public static void serverAboutToStart(FMLServerAboutToStartEvent event)
    {
        ReflectionTool.set(MINECRAFT_SERVER_PROFILER_FIELD, event.getServer(), new OmniProfiler());
    }

    @Mod.EventHandler
    public static void serverStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new Commands());
        event.registerServerCommand(new CommandDebug());

        System.out.println(Debug.memData());
    }

    @Mod.EventHandler
    public static void serverStarted(FMLServerStartedEvent event)
    {
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        if (server.isDedicatedServer())
        {
            if (LagDetector.init(server)) System.out.println("Starting LagDetector");
            else System.out.println("NOT starting LagDetector; the watchdog timeout setting is 0");
        }
        else System.out.println("NOT starting LagDetector; we are not running in dedicated server mode");
    }
}
