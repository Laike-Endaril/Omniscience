package com.fantasticsource.omnipotence.client;

import com.fantasticsource.omnipotence.Network;
import com.fantasticsource.tools.ReflectionTool;
import com.fantasticsource.tools.TrigLookupTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class PathVisualizer
{
    private static Field renderManagerRenderOutlinesField = ReflectionTool.getField(RenderManager.class, "field_178639_r", "renderOutlines");

    public static LinkedHashMap<EntityPlayerMP, ArrayList<Integer>> pathTrackedEntities = new LinkedHashMap<>(); //Used server-side only
    public static LinkedHashMap<Integer, Path> entityPaths = new LinkedHashMap<>(); //Used client-side only

    private static TrigLookupTable trig = new TrigLookupTable(512);

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void entityRender(RenderLivingEvent.Post event) throws IllegalAccessException
    {
        EntityLivingBase entity = event.getEntity();
        if (!(entity instanceof EntityLiving)) return;
        if (!entityPaths.containsKey(entity.getEntityId())) return;

        if ((boolean) renderManagerRenderOutlinesField.get(event.getRenderer().getRenderManager())) return;


        GlStateManager.disableLighting();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth(3);


        Path path = entityPaths.get(entity.getEntityId());
        renderPath(path, event, entity);
        GlStateManager.disableDepth();
        renderPath(path, event, entity);
        GlStateManager.enableDepth();


        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.glLineWidth(1);
    }

    @SideOnly(Side.CLIENT)
    private static void renderPath(Path path, RenderLivingEvent.Post event, Entity entity)
    {
        if (path == null || path.getCurrentPathLength() == 0)
        {
            //Basic "has no path" indicator
            GlStateManager.pushMatrix();
            GlStateManager.translate(event.getX(), event.getY() + entity.height / 2, event.getZ());

            GlStateManager.color(1, 0, 0, 1);
            renderSphereWireframe(0.25, 16);

            GlStateManager.popMatrix();
        }
        else
        {
            //Basic "has path" indicator
            GlStateManager.pushMatrix();
            GlStateManager.translate(event.getX(), event.getY() + entity.height / 2, event.getZ());

            GlStateManager.color(0, 1, 0, 1);
            renderSphereWireframe(0.25, 16);

            GlStateManager.popMatrix();


            //Path render
            PathPoint point;
            GlStateManager.pushMatrix();
            EntityPlayer player = Minecraft.getMinecraft().player;

            double partialTick = event.getPartialRenderTick();
            double dx = player.prevPosX + (player.posX - player.prevPosX) * partialTick;
            double dy = player.prevPosY + (player.posY - player.prevPosY) * partialTick;
            double dz = player.prevPosZ + (player.posZ - player.prevPosZ) * partialTick;

            GlStateManager.translate(0.5 - dx, 0.5 - dy, 0.5 - dz);


            //Previous path points
            GlStateManager.color(0.7f, 0.7f, 0.7f, 1);
            GlStateManager.glBegin(GL11.GL_LINE_STRIP);
            int i = 0;
            for (; i < path.getCurrentPathIndex(); i++)
            {
                point = path.getPathPointFromIndex(i);
                GlStateManager.glVertex3f(point.x, point.y, point.z);
            }
            GlStateManager.glEnd();


            if (i < path.getCurrentPathLength())
            {
                //In-progress path line
                if (i > 0)
                {
                    GlStateManager.color(0, 0, 1, 1);
                    GlStateManager.glBegin(GL11.GL_LINE_STRIP);
                    point = path.getPathPointFromIndex(i - 1);
                    GlStateManager.glVertex3f(point.x, point.y, point.z);
                    point = path.getPathPointFromIndex(i);
                    GlStateManager.glVertex3f(point.x, point.y, point.z);
                    GlStateManager.glEnd();
                }


                //Lead-in line
                GlStateManager.color(0, 1, 1, 1);
                GlStateManager.glBegin(GL11.GL_LINE_STRIP);
                GlStateManager.glVertex3f((float) (event.getX() + dx - 0.5), (float) (event.getY() + dy), (float) (event.getZ() + dz - 0.5));
                point = path.getPathPointFromIndex(i);
                GlStateManager.glVertex3f(point.x, point.y, point.z);
                GlStateManager.glEnd();


                //Remaining path lines
                GlStateManager.color(1, 0.8f, 0, 1);
                GlStateManager.glBegin(GL11.GL_LINE_STRIP);
                for (; i < path.getCurrentPathLength(); i++)
                {
                    point = path.getPathPointFromIndex(i);
                    GlStateManager.glVertex3f(point.x, point.y, point.z);
                }
                GlStateManager.glEnd();
            }


            GlStateManager.popMatrix();
        }
    }

    @SideOnly(Side.CLIENT)
    private static void renderSphereWireframe(double radius, int granularity)
    {
        GlStateManager.glBegin(GL11.GL_LINE_STRIP);
        for (double theta = 0; theta < Math.PI * 2; theta += Math.PI * 2 / granularity)
        {
            GlStateManager.glVertex3f((float) (radius * trig.cos(theta)), 0, (float) (radius * -trig.sin(theta)));
        }
        for (double theta = 0; theta < Math.PI * 2; theta += Math.PI * 2 / granularity)
        {
            GlStateManager.glVertex3f((float) (radius * trig.cos(theta)), (float) (radius * -trig.sin(theta)), 0);
        }
        GlStateManager.glVertex3f((float) radius, 0, 0);
        GlStateManager.glEnd();

        GlStateManager.glBegin(GL11.GL_LINE_STRIP);
        for (double theta = 0; theta < Math.PI * 2; theta += Math.PI * 2 / granularity)
        {
            GlStateManager.glVertex3f(0, (float) (radius * trig.cos(theta)), (float) (radius * -trig.sin(theta)));
        }
        GlStateManager.glVertex3f(0, (float) radius, 0);
        GlStateManager.glEnd();
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
                            ids.remove((Integer) i);
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
