package com.fantasticsource.omnipotence.client;

import com.fantasticsource.omnipotence.Omnipotence;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.IClientCommand;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static net.minecraft.util.text.TextFormatting.AQUA;
import static net.minecraft.util.text.TextFormatting.WHITE;

public class ClientCommands extends CommandBase implements IClientCommand
{
    @Override
    public String getName()
    {
        return "omnipotenceclient";
    }

    @Override
    public List<String> getAliases()
    {
        ArrayList<String> names = new ArrayList<>();

        names.add("omniclient");

        return names;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }

    @Override
    public boolean allowUsageWithoutPrefix(ICommandSender sender, String message)
    {
        return false;
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return AQUA + "/omnipotenceclient screens" + WHITE + " - " + I18n.translateToLocalFormatted(Omnipotence.MODID + ".cmd.screens.comment");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args)
    {
        if (args.length > 0)
        {
            if (args[0].equals("screens"))
            {
                if (args.length == 2)
                {
                    if (args[1].equals("live"))
                    {
                        ScreenDebug.live = !ScreenDebug.live;
                        if (ScreenDebug.live)
                        {
                            sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Started live GuiScreen debugging"));
                        }
                        else
                        {
                            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Stopped live GuiScreen debugging"));
                        }
                    }
                    else notifyCommandListener(sender, this, getUsage(sender));
                }
                else
                {
                    ScreenDebug.showRecent();
                }
            }
            else notifyCommandListener(sender, this, getUsage(sender));
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        ArrayList<String> strings = new ArrayList<>();

        if (args.length == 1) strings.add("screens");
        else if (args.length == 2) strings.add("live");

        return strings;
    }
}
