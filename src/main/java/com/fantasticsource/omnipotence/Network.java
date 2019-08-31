package com.fantasticsource.omnipotence;

import com.fantasticsource.omnipotence.client.PathVisualizer;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Network
{
    public static final SimpleNetworkWrapper WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(Omnipotence.MODID);

    private static int discriminator = 0;

    public static void init()
    {
        WRAPPER.registerMessage(PathPacketHandler.class, PathPacket.class, discriminator++, Side.CLIENT);
    }


    public static class PathPacket implements IMessage
    {
        LinkedHashMap<Integer, Path> data;

        public PathPacket() //Required; probably for when the packet is received
        {
        }

        public PathPacket(LinkedHashMap<Integer, Path> data)
        {
            this.data = data;
        }


        @Override
        public void toBytes(ByteBuf buf)
        {
            buf.writeInt(data.size());
            for (Map.Entry<Integer, Path> entry : data.entrySet())
            {
                buf.writeInt(entry.getKey());
                Path path = entry.getValue();
                if (path == null) buf.writeInt(0);
                else
                {
                    int size = path.getCurrentPathLength();
                    buf.writeInt(size);

                    for (int i = 0; i < size; i++)
                    {
                        PathPoint point = path.getPathPointFromIndex(i);
                        buf.writeInt(point.x);
                        buf.writeInt(point.y);
                        buf.writeInt(point.z);
                    }
                }
            }
        }

        @Override
        public void fromBytes(ByteBuf buf)
        {
            data = new LinkedHashMap<>();
            for (int i = buf.readInt(); i > 0; i--)
            {
                int id = buf.readInt();
                ArrayList<PathPoint> pathPoints = new ArrayList<>();
                for (int i2 = buf.readInt(); i2 > 0; i2--)
                {
                    pathPoints.add(new PathPoint(buf.readInt(), buf.readInt(), buf.readInt()));
                }

                data.put(id, pathPoints.size() == 0 ? null : new Path(pathPoints.toArray(new PathPoint[0])));
            }
        }
    }

    public static class PathPacketHandler implements IMessageHandler<PathPacket, IMessage>
    {
        @Override
        public IMessage onMessage(PathPacket packet, MessageContext ctx)
        {
            if (ctx.side == Side.CLIENT)
            {
                Minecraft mc = Minecraft.getMinecraft();
                mc.addScheduledTask(() ->
                {
                    for (int i : PathVisualizer.entityPaths.keySet().toArray(new Integer[0]))
                    {
                        if (!packet.data.containsKey(i)) PathVisualizer.entityPaths.remove(i);
                    }

                    for (Map.Entry<Integer, Path> entry : packet.data.entrySet())
                    {
                        PathVisualizer.entityPaths.put(entry.getKey(), entry.getValue());
                    }
                });
            }

            return null;
        }
    }
}
