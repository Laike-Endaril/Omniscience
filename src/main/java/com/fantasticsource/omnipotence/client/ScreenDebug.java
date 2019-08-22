package com.fantasticsource.omnipotence.client;

import com.fantasticsource.tools.Timestamp;
import com.fantasticsource.tools.datastructures.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;

public class ScreenDebug
{
    public static boolean live = false;

    private static ArrayList<Pair<Timestamp, GuiScreen>> recent = new ArrayList<>();

    public static void showRecent()
    {
        for (Pair<Timestamp, GuiScreen> pair : recent)
        {
            Timestamp ts = pair.getKey();
            Timestamp now = Timestamp.getInstance();

            double dif = (double) (now.getInstant().toEpochMilli() - ts.getInstant().toEpochMilli()) / 1000;

            GuiScreen screen = pair.getValue();
            EntityPlayer player = Minecraft.getMinecraft().player;
            if (player != null)
            {
                player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + ts.toString(false, true, false) + TextFormatting.GOLD + "(" + dif + " secs ago)" + TextFormatting.WHITE + " ..."));
                player.sendMessage(new TextComponentString(TextFormatting.GREEN + (screen == null ? "null" : screen.getClass().getName())));
            }
        }
    }

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END)
        {
            GuiScreen current = Minecraft.getMinecraft().currentScreen;
            if (recent.size() == 0 || current != recent.get(recent.size() - 1).getValue())
            {
                Pair<Timestamp, GuiScreen> pair = new Pair<>(Timestamp.getInstance(), current);
                recent.add(pair);
                while (recent.size() > 20) recent.remove(0);

                if (live)
                {
                    GuiScreen screen = pair.getValue();
                    Timestamp ts = pair.getKey();
                    EntityPlayer player = Minecraft.getMinecraft().player;
                    if (player != null)
                    {
                        player.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + ts.toString(false, true, false) + TextFormatting.WHITE + " ..."));
                        player.sendMessage(new TextComponentString(TextFormatting.GREEN + (screen == null ? "null" : screen.getClass().getName())));
                    }
                }
            }
        }
    }
}
