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
    public static final long NORMAL_TICK_TIME_NANOS = 50_000_000;

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
        if (args.length < 1)
        {
            throw new WrongUsageException("commands.debug.usage");
        }
        else
        {
            if ("start".equals(args[0]))
            {
                if (args.length != 1)
                {
                    throw new WrongUsageException("commands.debug.usage");
                }

                notifyCommandListener(sender, this, "commands.debug.start");
                server.enableProfiling();
                OmniProfiler.SectionNode root = ((OmniProfiler) server.profiler).root;
                root.startState = null;
                root.children.clear();
            }
            else
            {
                if (!"stop".equals(args[0]))
                {
                    throw new WrongUsageException("commands.debug.usage");
                }

                if (args.length != 1)
                {
                    throw new WrongUsageException("commands.debug.usage");
                }

                if (!server.profiler.profilingEnabled)
                {
                    throw new CommandException("commands.debug.notStarted");
                }

                String profilerResults = getProfilerResults(timeSpan, tickSpan, server);
                server.profiler.profilingEnabled = false;
                server.profi

                saveProfilerResults(server, profilerResults);

                notifyCommandListener(sender, this, "commands.debug.stop", String.format("%.2f", (float) timeSpan / 1000.0F), tickSpan);

                if (sender instanceof EntityPlayerMP) sendProfilerResults((EntityPlayerMP) sender, profilerResults);
            }
        }
    }

    private void saveProfilerResults(MinecraftServer server, String profilerResults)
    {
        File file1 = new File(server.getFile("debug"), "profile-results-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + ".txt");
        file1.getParentFile().mkdirs();
        Writer writer = null;

        try
        {
            writer = new OutputStreamWriter(new FileOutputStream(file1), StandardCharsets.UTF_8);
            writer.write(profilerResults);
        }
        catch (Throwable throwable)
        {
            LOGGER.error("Could not save profiler results to {}", file1, throwable);
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

    private String getProfilerResults(long timeSpan, int tickSpan, MinecraftServer server)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("---- " + Omniscience.NAME + " Profiler Results ----\n\n");
        stringbuilder.append("Time span: ").append(timeSpan).append(" ms\n");
        stringbuilder.append("Tick span: ").append(tickSpan).append(" ticks\n");
        stringbuilder.append("// This is approximately ").append(String.format("%.2f", Tools.min((float) (tickSpan + 1) / ((float) timeSpan / 1000), 20))).append(" ticks per second. It should be 20 ticks per second\n");
        stringbuilder.append("// Garbage collectors ran ").append(GCMessager.prevGCRuns - profileStartGCRuns).append(" time(s) during profiling\n");
        stringbuilder.append("// Approximate total heap allocations during profiling - ").append(totalHeapUsage).append("\n\n");
        stringbuilder.append("--- BEGIN PROFILE DUMP ---\n\n");
        //TODO
        stringbuilder.append("--- END PROFILE DUMP ---\n\n");
        return stringbuilder.toString();
    }

    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, "start", "stop") : Collections.emptyList();
    }
}