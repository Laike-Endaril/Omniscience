package com.fantasticsource.omnipotence;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static net.minecraft.util.text.TextFormatting.AQUA;
import static net.minecraft.util.text.TextFormatting.WHITE;

public class Commands extends CommandBase
{
    @Override
    public String getName()
    {
        return "omnipotence";
    }

    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return AQUA + "/omnipotence threads" + WHITE + " - " + I18n.translateToLocalFormatted(Omnipotence.MODID + ".cmd.threads.comment");
    }

    public void execute(MinecraftServer server, ICommandSender sender, String[] args)
    {
        if (args.length == 0) sender.getCommandSenderEntity().sendMessage(new TextComponentString(getUsage(sender)));
        else
        {
            subCommand(sender, args);
        }
    }

    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        ArrayList<String> result = new ArrayList<>();

        String partial = args[args.length - 1];
        if (args.length == 1)
        {
            result.add("threads");

            if (partial.length() != 0) result.removeIf(k -> partial.length() > k.length() || !k.substring(0, partial.length()).equalsIgnoreCase(partial));
        }
        return result;
    }

    private void subCommand(ICommandSender sender, String[] args)
    {
        String cmd = args[0];
        switch (cmd)
        {
            case "threads":
                for (String s : Debug.getThreadDataStrings())
                {
                    notifyCommandListener(sender, this, s);
                }
                break;
            default:
                notifyCommandListener(sender, this, getUsage(sender));
                break;
        }
    }
}
