package com.fantasticsource.omniscience.hack;

import com.fantasticsource.mctools.ServerTickTimer;
import com.fantasticsource.omniscience.Debug;
import com.fantasticsource.omniscience.Omniscience;
import com.fantasticsource.tools.Tools;
import net.minecraft.profiler.Profiler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class OmniProfiler extends Profiler
{
    public static final long NORMAL_TICK_TIME_NANOS = 50_000_000;
    public static final OmniProfiler INSTANCE = new OmniProfiler();
    public static final String FILENAME = OmniProfiler.class.getSimpleName() + ".java";

    protected final LinkedHashMap<Long, SectionNode> PER_TICK_DATA = new LinkedHashMap<>();
    protected SectionNode currentNode = null;
    protected boolean active = false, debugging = false;
    protected int startingMode = 0;
    protected ArrayList<Predicate<Results>> stoppingCallbacks = new ArrayList<>();
    protected Results lastRunResults = null;

    protected long startTime;
    protected int startGCRuns;

    protected OmniProfiler()
    {
    }

    public void reset()
    {
        PER_TICK_DATA.clear();
        currentNode = null;
        active = false;
        debugging = false;
        startingMode = 0;
        stoppingCallbacks.clear();
        //lastRunResults can be kept
    }

    /**
     * @return human-readable result of this call (success or error)
     * A start can only be queued if the profiler is not already running or starting
     */
    public String startProfiling()
    {
        if (active) return "Profiler is already running";
        if (startingMode > 0) return "Profiler is already starting";

        startingMode = 1;
        return "Starting profiler";
    }

    /**
     * @return human-readable result of this call (success or error)
     * A debug start can only be queued if the profiler is not already running and not already starting in debug mode
     */
    public String startDebugging()
    {
        if (active) return "Profiler is already running";
        if (startingMode > 1) return "Profiler is already starting in debug mode";

        startingMode = 2;
        return "Starting profiler in debug mode";
    }

    /**
     * @return human-readable result of this call (success or error)
     * A stop can be queued if the profiler is already running, or if it is starting (the latter will result in a 1-tick profiler run)
     * Callbacks will be called unless there is an error (eg. different number of calls to startSection and endSection in one tick)
     */
    public String stopProfiling(Predicate<Results> callback)
    {
        if (!active && startingMode == 0) return "Profiler is not running or starting";

        stoppingCallbacks.add(callback);
        return "Stopping profiler";
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void serverTick(TickEvent.ServerTickEvent event)
    {
        if (event.phase != TickEvent.Phase.START) return;

        INSTANCE.tick();
    }

    protected void tick()
    {
        if (active)
        {
            if (startingMode > 0) throw new IllegalStateException("Profiler tried to start while already active!");


            endSection(true);
            if (currentNode != null)
            {
                if (debugging)
                {
                    //TODO
                }

                reset();
                System.err.println("profiler.startSection() was called more times this tick than profiler.endSection()!  Stopping profiling and resetting profiler state!");
                return;
            }


            if (stoppingCallbacks.size() > 0)
            {
                Predicate<Results>[] callbacks = stoppingCallbacks.toArray(new Predicate[0]);
                lastRunResults = new Results(PER_TICK_DATA);

                reset();

                for (Predicate<Results> callback : callbacks) callback.test(lastRunResults);
            }
            else
            {
                currentNode = new SectionNode("OMNIROOT", true);
                PER_TICK_DATA.put(ServerTickTimer.currentTick(), currentNode);
            }
        }


        if (startingMode > 0)
        {
            if (startingMode > 1) debugging = true;

            PER_TICK_DATA.clear();
            currentNode = new SectionNode("OMNIROOT", true);
            PER_TICK_DATA.put(ServerTickTimer.currentTick(), currentNode);

            active = true;
            startingMode = 0;

            startTime = System.nanoTime();
            startGCRuns = Debug.gcRuns();
        }
    }

    @Override
    public void startSection(String name)
    {
        startSection(name, false);
    }

    protected void startSection(String name, boolean force)
    {
        if (active)
        {
            if (!force)
            {
                //Vanilla has a different number of startSection() and endSection() calls
                //TODO replace this filtering because it's incredibly inefficient
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                int index = 1;
                while (stackTrace[index].getFileName().equals(FILENAME)) index++;
                if (stackTrace[index].toString().contains("net.minecraft")) return;
            }


            currentNode = currentNode.children.computeIfAbsent(name, o -> new SectionNode(name, currentNode));
            currentNode.startState = debugging ? new DebugRuntimeState() : new RuntimeState();
        }
    }

    @Override
    public void endSection()
    {
        endSection(false);
    }

    public void endSection(boolean force)
    {
        if (active)
        {
            if (!force)
            {
                //TODO replace this filtering because it's incredibly inefficient
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                int index = 1;
                while (stackTrace[index].getFileName().equals(FILENAME)) index++;
                if (stackTrace[index].toString().contains("net.minecraft")) return;
            }


            if (currentNode == null)
            {
                reset();
                System.err.println("profiler.endSection() was called more times this tick than profiler.startSection()!  Stopping profiling and resetting profiler state!");
                return;
            }

            if (debugging)
            {
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                DebugRuntimeState startState = (DebugRuntimeState) currentNode.startState;
                for (int i = 0; i < Tools.min(stackTrace.length, startState.stackTrace.length); i++)
                {
                    StackTraceElement before = startState.stackTrace[startState.stackTrace.length - 1 - i], after = stackTrace[stackTrace.length - 1 - i];
                    if (before.getFileName().equals(FILENAME) && after.getFileName().equals(FILENAME))
                    {
                        break;
                    }
                    if (before.getClassName().equals(after.getClassName()) && before.getMethodName().equals(after.getMethodName())) continue;


                    System.err.println();
                    System.err.println("Stack mismatch:" + currentNode.fullName + "\n");

                    System.err.println("Before:");
                    boolean found = false;
                    for (StackTraceElement element : startState.stackTrace)
                    {
                        if (found) System.err.println("* " + element);
                        else
                        {
                            System.err.println(element);
                            if (element.equals(before)) found = true;
                        }
                    }

                    System.err.println("\n");
                    System.err.println("After:");
                    found = false;
                    for (StackTraceElement element : stackTrace)
                    {
                        if (found) System.err.println("* " + element);
                        else
                        {
                            System.err.println(element);
                            if (element.equals(after)) found = true;
                        }
                    }

                    System.err.println("\n");
                }
            }

            RuntimeState startState = currentNode.startState;
            currentNode.nanos += System.nanoTime() - startState.nanoTime;
            currentNode.gcRuns += Debug.gcRuns() - startState.gcRuns;
            long dif = Debug.usedMemory() - startState.heapUsage;
            if (dif > 0) currentNode.heapUsage += dif;

            currentNode.startState = null;

            currentNode = currentNode.parent;
        }
    }

    @Override
    public void endStartSection(String name)
    {
        endSection();
        startSection(name);
    }

    public static Results getLastRunResults()
    {
        return INSTANCE.lastRunResults;
    }


    public static class RuntimeState
    {
        public long nanoTime, heapUsage;
        public int gcRuns;

        public RuntimeState()
        {
            this.nanoTime = System.nanoTime();
            this.heapUsage = Debug.usedMemory();
            this.gcRuns = Debug.gcRuns();
        }
    }

    public static class DebugRuntimeState extends RuntimeState
    {
        public StackTraceElement[] stackTrace;

        public DebugRuntimeState()
        {
            super();
            this.stackTrace = Thread.currentThread().getStackTrace();
        }
    }

    public static class SectionNode
    {
        public String fullName;
        public SectionNode parent = null;
        public HashMap<String, SectionNode> children = new HashMap<>();
        public RuntimeState startState = null;
        public long nanos = 0, heapUsage = 0;
        public int gcRuns = 0;

        public SectionNode(String fullName, boolean initStartState)
        {
            this.fullName = fullName;
            if (initStartState) startState = INSTANCE.debugging ? new DebugRuntimeState() : new RuntimeState();
        }

        public SectionNode(String fullName, SectionNode parent)
        {
            this.fullName = parent.fullName + "." + fullName;
            this.parent = parent;
        }
    }

    public static class Results
    {
        public final LinkedHashMap<Long, SectionNode> perTickData;
        public final SectionNode averageData;
        public final int timeSpan, tickSpan;

        public Results(LinkedHashMap<Long, SectionNode> perTickData)
        {
            this.perTickData = (LinkedHashMap<Long, SectionNode>) perTickData.clone();

            timeSpan = (int) ((System.nanoTime() - INSTANCE.startTime) / 1000000);
            Long[] ticks = perTickData.keySet().toArray(new Long[0]);
            tickSpan = (int) (ticks[ticks.length - 1] - ticks[0]);

            averageData = new SectionNode("OMNIROOT", false);
            for (SectionNode rootNode : perTickData.values())
            {
                averageData.nanos += rootNode.nanos;
                averageData.gcRuns += rootNode.gcRuns;
                averageData.heapUsage += rootNode.heapUsage;
            }
        }

        @Override
        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("---- " + Omniscience.NAME + " Profiler Results ----\n\n");
            stringbuilder.append("Time span: ").append(timeSpan).append(" ms\n");
            stringbuilder.append("Tick span: ").append(tickSpan).append(" ticks\n");
            stringbuilder.append("// This is approximately ").append(String.format("%.2f", Tools.min((float) (tickSpan + 1) / ((float) timeSpan / 1000), 20))).append(" ticks per second. It should be 20 ticks per second\n");
            stringbuilder.append("// Garbage collectors ran ").append(Debug.gcRuns() - INSTANCE.startGCRuns).append(" time(s) during profiling\n");
//            stringbuilder.append("// Approximate total heap allocations during profiling - ").append(totalHeapUsage).append("\n\n");
            stringbuilder.append("--- BEGIN PROFILE DUMP ---\n\n");
            stringbuilder.append(perTickData.size()).append(" results\n");
            for (Map.Entry<Long, SectionNode> entry : perTickData.entrySet())
            {
                stringbuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                //TODO
            }
            stringbuilder.append("--- END PROFILE DUMP ---\n\n");
            return stringbuilder.toString();
        }
    }
}