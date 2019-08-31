package com.fantasticsource.omnipotence.client;

import com.fantasticsource.omnipotence.Network;
import com.fantasticsource.tools.TrigLookupTable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.pathfinding.Path;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class PathVisualizer
{
    public static LinkedHashMap<EntityPlayerMP, ArrayList<Integer>> pathTrackedEntities = new LinkedHashMap<>(); //Used server-side only
    public static LinkedHashMap<Integer, Path> entityPaths = new LinkedHashMap<>(); //Used client-side only

    private static TrigLookupTable trig = new TrigLookupTable(512);

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void entityRender(RenderLivingEvent.Post event)
    {
        if (event.getRenderer().getRenderManager().renderOutlines) return;

        EntityLivingBase entity = event.getEntity();
        if (!(entity instanceof EntityLiving)) return;
        if (!entityPaths.containsKey(entity.getEntityId())) return;

        Path path = entityPaths.get(entity.getEntityId());

        GlStateManager.disableLighting();
        GlStateManager.disableTexture2D();
        if (path == null)
        {
            GlStateManager.color(1, 0, 0, 1);
            GlStateManager.pushMatrix();
            GlStateManager.translate(event.getX(), event.getY() + entity.height / 2, event.getZ());


            GlStateManager.glBegin(GL11.GL_LINE_STRIP);

            double radius = 1;
            for (double theta = 0; theta < Math.PI * 2 + (Math.PI * 2 / 32); theta += Math.PI * 2 / 32)
            {
                GlStateManager.glVertex3f((float) (radius * trig.cos(theta)), 0, (float) (radius * -trig.sin(theta)));
            }

            GlStateManager.glEnd();


            GlStateManager.popMatrix();
        }
        else
        {
            GlStateManager.color(0, 1, 0, 1);
            GlStateManager.pushMatrix();
            GlStateManager.translate(event.getX(), event.getY() + entity.height / 2, event.getZ());


            GlStateManager.glBegin(GL11.GL_LINE_STRIP);

            double radius = 1;
            for (double theta = 0; theta < Math.PI * 2 + (Math.PI * 2 / 32); theta += Math.PI * 2 / 32)
            {
                GlStateManager.glVertex3f((float) (radius * trig.cos(theta)), 0, (float) (radius * -trig.sin(theta)));
            }

            GlStateManager.glEnd();


            GlStateManager.popMatrix();
        }
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void clientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event)
    {
        entityPaths.clear();
    }

    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END)
        {
            for (Object object : pathTrackedEntities.entrySet().toArray())
            {
                Map.Entry<EntityPlayerMP, ArrayList<Integer>> entry = (Map.Entry<EntityPlayerMP, ArrayList<Integer>>) object;
                EntityPlayerMP player = entry.getKey();

                if (!player.world.playerEntities.contains(player)) pathTrackedEntities.remove(player);
                else
                {
                    LinkedHashMap<Integer, Path> data = new LinkedHashMap<>();
                    ArrayList<Integer> ids = entry.getValue();
                    for (int i : ids.toArray(new Integer[0]))
                    {
                        Entity entity = player.world.getEntityByID(i);
                        if (!(entity instanceof EntityLiving))
                        {
                            ids.remove(i);
                            if (ids.size() == 0) pathTrackedEntities.remove(player);
                            continue;
                        }

                        data.put(i, ((EntityLiving) entity).getNavigator().getPath());
                    }

                    Network.WRAPPER.sendTo(new Network.PathPacket(data), player);
                }
            }
        }
    }
}
