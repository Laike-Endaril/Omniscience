package com.fantasticsource.omniscience;

import com.fantasticsource.omniscience.hack.OmniProfiler;
import com.fantasticsource.tools.Tools;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import org.apache.commons.io.IOUtils;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CommandDebug extends CommandBase
{
    public String getName()
    {
        return "debug";
    }

    public int getRequiredPermissionLevel()
    {
        return 3;
    }

    public String getUsage(ICommandSender sender)
    {
        return "commands.debug.usage";
    }

    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 1) throw new WrongUsageException("commands.debug.usage");

        if (args[0].equals("start"))
        {
            notifyCommandListener(sender, this, OmniProfiler.INSTANCE.startProfiling());
        }
        else if (args[0].equals("startdebug"))
        {
            notifyCommandListener(sender, this, OmniProfiler.INSTANCE.startDebugging());
        }
        else if (args[0].equals("stop"))
        {
            notifyCommandListener(sender, this, OmniProfiler.INSTANCE.stopProfiling(results ->
            {
                String profilerResults = results.toString();
                saveProfilerResults(server, profilerResults);
                if (sender instanceof EntityPlayerMP) sendProfilerResults((EntityPlayerMP) sender, profilerResults);
                return true;
            }));
        }
        else throw new WrongUsageException(getUsage(sender));
    }

    private void saveProfilerResults(MinecraftServer server, String profilerResults)
    {
        File file = new File(server.getFile("debug"), "profile-results-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + ".txt");
        file.getParentFile().mkdirs();
        Writer writer = null;
        try
        {
            writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            writer.write(profilerResults);
        }
        catch (Throwable throwable)
        {
            System.err.println("Could not save profiler results to " + file + throwable);
        }
        finally
        {
            IOUtils.closeQuietly(writer);
        }
    }

    private void sendProfilerResults(EntityPlayerMP player, String profilerResults)
    {
        for (String s : Tools.fixedSplit(profilerResults, "\n"))
        {
            player.sendMessage(new TextComponentString(s));
        }
    }

    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, "start", "stop") : Collections.emptyList();
    }
}