package com.fantasticsource.omnipotence;

import com.fantasticsource.omnipotence.client.PathVisualizer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nullable;
import java.util.*;

import static net.minecraft.util.text.TextFormatting.AQUA;
import static net.minecraft.util.text.TextFormatting.WHITE;

public class Commands extends CommandBase
{
    private static ArrayList<String> subcommands = new ArrayList<>();

    static
    {
        subcommands.addAll(Arrays.asList("threads", "nbt", "memory", "entities", "pathing"));
    }


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
        return subUsage("");
    }

    public String subUsage(String subcommand)
    {
        if (!subcommands.contains(subcommand)) return AQUA + "/omnipotence <threads | nbt | memory | entities | pathing>";

        switch (subcommand)
        {
            case "threads":
                return AQUA + "/omnipotence threads" + WHITE + " - " + I18n.translateToLocalFormatted(Omnipotence.MODID + ".cmd.threads.comment")
                        + "\n" + AQUA + "/omnipotence threads <id>" + WHITE + " - " + I18n.translateToLocalFormatted(Omnipotence.MODID + ".cmd.threads.comment2")
                        + "\n" + AQUA + "/omnipotence threads <id> stop" + WHITE + " - " + I18n.translateToLocalFormatted(Omnipotence.MODID + ".cmd.threads.comment3");

            case "nbt":
                return AQUA + "/omnipotence nbt hand" + WHITE + " - " + I18n.translateToLocalFormatted(Omnipotence.MODID + ".cmd.nbt.comment")
                        + "\n" + AQUA + "/omnipotence nbt self" + WHITE + " - " + I18n.translateToLocalFormatted(Omnipotence.MODID + ".cmd.nbt.comment2")
                        + "\n" + AQUA + "/omnipotence nbt nearestentity" + WHITE + " - " + I18n.translateToLocalFormatted(Omnipotence.MODID + ".cmd.nbt.comment3");

            default:
                return AQUA + "/omnipotence " + subcommand + WHITE + " - " + I18n.translateToLocalFormatted(Omnipotence.MODID + ".cmd." + subcommand + ".comment");
        }
    }

    @Override
    public List<String> getAliases()
    {
        ArrayList<String> names = new ArrayList<>();

        names.add("omni");

        return names;
    }

    public void execute(MinecraftServer server, ICommandSender sender, String[] args)
    {
        if (args.length == 0) sender.getCommandSenderEntity().sendMessage(new TextComponentString(subUsage("")));
        else subCommand(sender, args);
    }

    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        ArrayList<String> result = new ArrayList<>();

        String partial = args[args.length - 1];
        switch (args.length)
        {
            case 1:
                result.addAll(subcommands);
                if (partial.length() != 0) result.removeIf(k -> partial.length() > k.length() || !k.substring(0, partial.length()).equalsIgnoreCase(partial));
                break;

            case 2:
                switch (args[0])
                {
                    case "threads":
                        for (long id : Debug.threadIDs()) result.add("" + id);

                        if (partial.length() != 0) result.removeIf(k -> partial.length() > k.length() || !k.substring(0, partial.length()).equalsIgnoreCase(partial));
                        break;

                    case "nbt":
                        result.add("hand");
                        result.add("self");
                        result.add("nearestentity");

                        if (partial.length() != 0) result.removeIf(k -> partial.length() > k.length() || !k.substring(0, partial.length()).equalsIgnoreCase(partial));
                        break;
                }
                break;

            case 3:
                switch (args[0])
                {
                    case "threads":
                        result.add("stop");

                        if (partial.length() != 0) result.removeIf(k -> partial.length() > k.length() || !k.substring(0, partial.length()).equalsIgnoreCase(partial));
                        break;
                }
                break;
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
                        notifyCommandListener(sender, this, Omnipotence.MODID + ".error.threads.notFound", args[1]);
                        return;
                    }

                    Thread thread = Debug.getThread(id);
                    if (thread == null)
                    {
                        notifyCommandListener(sender, this, Omnipotence.MODID + ".error.threads.notFound", args[1]);
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
                        else notifyCommandListener(sender, this, subUsage(cmd));
                    }
                }
                else notifyCommandListener(sender, this, subUsage(cmd));
                break;


            case "nbt":
                switch (args.length)
                {
                    case 1:
                        notifyCommandListener(sender, this, "hand");
                        notifyCommandListener(sender, this, "self");
                        notifyCommandListener(sender, this, "nearestentity");
                        break;

                    case 2:
                        if (!(sender instanceof EntityPlayerMP))
                        {
                            notifyCommandListener(sender, this, Omnipotence.MODID + ".error.nbt.notPlayer");
                            return;
                        }

                        switch (args[1])
                        {
                            case "hand":
                                notifyCommandListener(sender, this, "");
                                notifyCommandListener(sender, this, ((EntityPlayerMP) sender).getHeldItemMainhand().getDisplayName());
                                notifyNBT(sender, ((EntityPlayerMP) sender).getHeldItemMainhand().writeToNBT(new NBTTagCompound()));
                                notifyCommandListener(sender, this, "");
                                break;

                            case "self":
                                notifyCommandListener(sender, this, "");
                                notifyCommandListener(sender, this, sender.getName());
                                notifyNBT(sender, ((EntityPlayerMP) sender).writeToNBT(new NBTTagCompound()));
                                notifyCommandListener(sender, this, "");
                                break;

                            case "nearestentity":
                                EntityPlayerMP player = (EntityPlayerMP) sender;
                                Entity entity = player.world.findNearestEntityWithinAABB(Entity.class, player.getEntityBoundingBox().grow(100), player);
                                if (entity != null)
                                {
                                    notifyCommandListener(sender, this, "");
                                    notifyCommandListener(sender, this, entity.getName() + " @ " + entity.posX + ", " + entity.posY + ", " + entity.posZ);
                                    notifyNBT(sender, entity.writeToNBT(new NBTTagCompound()));
                                    notifyCommandListener(sender, this, "");
                                }
                                else notifyCommandListener(sender, this, Omnipotence.MODID + ".error.noEntityFound");
                                break;

                            default:
                        }
                        break;
                }
                break;


            case "memory":
                notifyCommandListener(sender, this, "omnipotence.literal1", Debug.memData());
                break;


            case "entities":
                int players;
                LinkedHashMap<Class, int[]> classCounts = new LinkedHashMap<>();
                for (WorldServer world : FMLCommonHandler.instance().getMinecraftServerInstance().worlds)
                {
                    sender.sendMessage(new TextComponentString(""));

                    players = world.playerEntities.size();
                    sender.sendMessage(new TextComponentString(world.getWorldInfo().getWorldName() + " (" + world.provider.getDimension() + "): " + world.loadedEntityList.size() + " (" + players + (players == 1 ? " player)" : " players)")));

                    classCounts.clear();
                    for (Entity entity : world.loadedEntityList)
                    {
                        classCounts.computeIfAbsent(entity.getClass(), o -> new int[]{0})[0]++;
                    }

                    int max = 0;
                    Class maxClass = null;
                    for (Map.Entry<Class, int[]> entry : classCounts.entrySet())
                    {
                        if (entry.getValue()[0] > max)
                        {
                            max = entry.getValue()[0];
                            maxClass = entry.getKey();
                        }
                    }

                    if (max > 0) sender.sendMessage(new TextComponentString("Highest count: " + maxClass.getName() + " (" + max + ")"));
                }
                break;


            case "pathing":
                EntityPlayerMP player = (EntityPlayerMP) sender;
                Entity entity = player.world.findNearestEntityWithinAABB(EntityLiving.class, player.getEntityBoundingBox().grow(100), player);
                if (entity != null)
                {
                    BlockPos pos = entity.getPosition();
                    ArrayList<Integer> trackedEntities = PathVisualizer.pathTrackedEntities.get(player);
                    if (trackedEntities == null || !trackedEntities.contains(entity.getEntityId()))
                    {
                        FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> PathVisualizer.pathTrackedEntities.computeIfAbsent(player, o -> new ArrayList<>()).add(entity.getEntityId()));
                        notifyCommandListener(sender, this, Omnipotence.MODID + ".cmd.pathing.start", entity.getDisplayName(), pos.getX(), pos.getY(), pos.getZ());
                    }
                    else
                    {
                        trackedEntities.remove(entity.getEntityId());
                        notifyCommandListener(sender, this, Omnipotence.MODID + ".cmd.pathing.stop", entity.getDisplayName(), pos.getX(), pos.getY(), pos.getZ());
                    }
                }
                else notifyCommandListener(sender, this, Omnipotence.MODID + ".error.noEntityFound");
                break;


            default:
                notifyCommandListener(sender, this, subUsage(cmd));
        }
    }

    private void notifyNBT(ICommandSender sender, NBTTagCompound compound)
    {
        char[] chars = compound.toString().toCharArray();
        String current = "";
        String indent = "";
        for (int i = 0; i < chars.length; i++)
        {
            char c = chars[i];
            switch (c)
            {
                case '{':
                case '[':
                    if (!current.equals("")) notifyCommandListener(sender, this, indent + current);
                    notifyCommandListener(sender, this, indent + c);
                    current = "";
                    indent += " ";
                    break;

                case '}':
                case ']':
                    if (!current.equals("")) notifyCommandListener(sender, this, indent + current);
                    indent = indent.substring(0, indent.length() - 1);
                    notifyCommandListener(sender, this, indent + c + (i + 1 < chars.length && chars[i + 1] == ',' ? ',' : ""));
                    current = "";
                    break;

                case ',':
                    if (!current.equals("")) notifyCommandListener(sender, this, indent + current + c);
                    current = "";
                    break;

                default:
                    current += c;
            }
        }
    }
}
