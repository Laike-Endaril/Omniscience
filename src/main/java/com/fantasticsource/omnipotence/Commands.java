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
        return AQUA + "/omnipotence threads" + WHITE + " - " + I18n.translateToLocalFormatted(Omnipotence.MODID + ".cmd.threads.comment") + "\n" +
                AQUA + "/omnipotence threads <id>" + WHITE + " - " + I18n.translateToLocalFormatted(Omnipotence.MODID + ".cmd.threads.comment2") + "\n" +
                AQUA + "/omnipotence threads <id> stop" + WHITE + " - " + I18n.translateToLocalFormatted(Omnipotence.MODID + ".cmd.threads.comment3");
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
        else if (args.length == 2)
        {
            switch (args[0])
            {
                case "threads":
                    for (long id : Debug.threadIDs()) result.add("" + id);

                    if (partial.length() != 0) result.removeIf(k -> partial.length() > k.length() || !k.substring(0, partial.length()).equalsIgnoreCase(partial));
            }
        }
        else if (args.length == 3)
        {
            switch (args[0])
            {
                case "threads":
                    result.add("stop");

                    if (partial.length() != 0) result.removeIf(k -> partial.length() > k.length() || !k.substring(0, partial.length()).equalsIgnoreCase(partial));
            }
        }
        return result;
    }

    private void subCommand(ICommandSender sender, String[] args)
    {
        String cmd = args[0];
        switch (cmd)
        {
            case "threads":
                if (args.length == 1)
                {
                    for (String s : Debug.threadDataStrings())
                    {
                        notifyCommandListener(sender, this, s);
                    }
                }
                else if (args.length < 4) //2 <= args.length <= 3, as it cannot be 0 or 1 at this point
                {
                    long id = Debug.threadID(args[1]);
                    if (id == -1)
                    {
                        try
                        {
                            id = Long.parseLong(args[1].trim());
                        }
                        catch (NumberFormatException e)
                        {
                            //Just leave id at -1
                        }
                    }

                    if (id == -1)
                    {
                        notifyCommandListener(sender, this, Omnipotence.MODID + ".cmd.threads.notFound", args[1]);
                        return;
                    }

                    Thread thread = Debug.getThread(id);
                    if (thread == null)
                    {
                        notifyCommandListener(sender, this, Omnipotence.MODID + ".cmd.threads.notFound", args[1]);
                        return;
                    }

                    if (args.length == 2)
                    {
                        notifyCommandListener(sender, this, "Thread \"" + thread.getName() + "\" (ID = " + thread.getId() + ") of class \"" + thread.getClass().getName() + "\"");

                        StringBuilder threadGroups = new StringBuilder("Thread Group Lineage is ... ");
                        String[] strings = Debug.threadGroupLineageStrings(thread);
                        threadGroups.append("(").append(strings[0]);
                        for (int i = 1; i < strings.length; i++)
                        {
                            threadGroups.append(" -> ").append(strings[i]);
                        }
                        threadGroups.append(")");


                        notifyCommandListener(sender, this, threadGroups.toString());
                        for (StackTraceElement element : thread.getStackTrace()) notifyCommandListener(sender, this, element.toString());
                    }
                    else
                    {
                        if (args[2].equals("stop"))
                        {
                            thread.stop();
                            notifyCommandListener(sender, this, "Stopping thread " + id + " (" + thread.getName() + ")");
                        }
                        else notifyCommandListener(sender, this, getUsage(sender));
                    }
                }
                else notifyCommandListener(sender, this, getUsage(sender));
                break;

            default:
                notifyCommandListener(sender, this, getUsage(sender));
        }
    }
}
