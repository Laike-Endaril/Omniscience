package com.fantasticsource.omniscience.hack;

import com.fantasticsource.mctools.ServerTickTimer;
import com.fantasticsource.omniscience.Debug;
import com.fantasticsource.omniscience.Omniscience;
import com.fantasticsource.tools.Tools;
import net.minecraft.profiler.Profiler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import sun.misc.SharedSecrets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class OmniProfiler extends Profiler
{
    public static final OmniProfiler INSTANCE = new OmniProfiler();
    public static final String[] VALID_MODES = new String[]{"total", "average", "peak"};

    protected static final String ROOT_NAME = "OMNIROOT";
    protected static final long NORMAL_TICK_TIME_NANOS = 50_000_000;
    protected static final String FILENAME = OmniProfiler.class.getSimpleName() + ".java";

    protected final LinkedHashMap<Long, SectionNode> PER_TICK_DATA = new LinkedHashMap<>();
    protected SectionNode currentNode = null;
    protected boolean active = false, debugging = false;
    protected int startingMode = 0;
    protected ArrayList<Predicate<Results>> stoppingCallbacks = new ArrayList<>();
    protected Results lastRunResults = null;

    protected long startTime, startHeapAllocated;
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
                currentNode = new SectionNode();
                PER_TICK_DATA.put(ServerTickTimer.currentTick(), currentNode);
            }
        }


        if (startingMode > 0)
        {
            if (startingMode > 1) debugging = true;

            PER_TICK_DATA.clear();
            currentNode = new SectionNode();
            PER_TICK_DATA.put(ServerTickTimer.currentTick(), currentNode);

            active = true;
            startingMode = 0;

            startTime = System.nanoTime();
            startGCRuns = Debug.gcRuns();
            startHeapAllocated = Debug.cumulativeServerThreadHeapAllocations();
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
                //Vanilla calls endSection() more times than it calls startSection()...
                if (SharedSecrets.getJavaLangAccess().getStackTraceElement(new Throwable(), 2).getClassName().substring(0, 14).equals("net.minecraft."))
                {
                    return;
                }
            }


            if (debugging)
            {
                if (!Thread.currentThread().getName().equals("Server thread"))
                {
                    reset();
                    System.err.println("profiler.startSection() was called from somewhere besides the server thread!  Stopping profiling and resetting profiler state!");
                    Tools.printStackTrace();
                    return;
                }
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
                //Vanilla calls endSection() more times than it calls startSection()...
                if (SharedSecrets.getJavaLangAccess().getStackTraceElement(new Throwable(), 2).getClassName().substring(0, 14).equals("net.minecraft."))
                {
                    return;
                }
            }


            if (currentNode == null)
            {
                reset();
                System.err.println("profiler.endSection() was called more times this tick than profiler.startSection()!  Stopping profiling and resetting profiler state!");
                return;
            }


            if (debugging)
            {
                if (!Thread.currentThread().getName().equals("Server thread"))
                {
                    reset();
                    System.err.println("profiler.endSection() was called from somewhere besides the server thread!  Stopping profiling and resetting profiler state!");
                    Tools.printStackTrace();
                    return;
                }


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
            currentNode.heapAllocated += Debug.cumulativeServerThreadHeapAllocations() - startState.heapAllocated;

            currentNode.startState = null;

            currentNode = currentNode.parent;
        }
    }

    @Override
    public void endStartSection(String name)
    {
        if (active)
        {
            //Vanilla calls endSection() more times than it calls startSection()...
            if (SharedSecrets.getJavaLangAccess().getStackTraceElement(new Throwable(), 1).getClassName().substring(0, 14).equals("net.minecraft."))
            {
                return;
            }

            endSection();
            startSection(name);
        }
    }

    public static Results getLastRunResults()
    {
        return INSTANCE.lastRunResults;
    }


    public static class RuntimeState
    {
        public long nanoTime, heapAllocated;
        public int gcRuns;

        public RuntimeState()
        {
            this.nanoTime = System.nanoTime();
            this.heapAllocated = Debug.cumulativeServerThreadHeapAllocations();
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
        public String fullName, mode = null;
        public SectionNode parent = null;
        public HashMap<String, SectionNode> children = new HashMap<>();
        public RuntimeState startState = null;
        public long nanos = 0, heapAllocated = 0;
        public int gcRuns = 0, divisor = 1;

        public SectionNode()
        {
            this(ROOT_NAME, true);
        }

        public SectionNode(String fullName, boolean initStartState)
        {
            this.fullName = fullName;
            if (initStartState) startState = INSTANCE.debugging ? new DebugRuntimeState() : new RuntimeState();
        }

        public SectionNode(String name, SectionNode parent)
        {
            this.fullName = parent.fullName + "." + name;
            this.parent = parent;
            this.mode = parent.mode;
            this.divisor = parent.divisor;
        }

        public SectionNode(String mode)
        {
            this(mode, INSTANCE.lastRunResults.perTickData);
        }

        public SectionNode(String mode, LinkedHashMap<Long, SectionNode> perTickData)
        {
            this(ROOT_NAME, false);
            this.mode = mode;

            if (!Tools.contains(VALID_MODES, mode)) throw new IllegalArgumentException("Mode is invalid: " + mode);

            switch (mode)
            {
                case "total":
                    for (SectionNode root : perTickData.values()) addRecursive(root);
                    break;


                case "average":
                    for (SectionNode root : perTickData.values()) addRecursive(root);
                    divisor = perTickData.size();
                    break;


                case "peak":
                    for (SectionNode root : perTickData.values()) maxRecursive(root);
                    break;
            }
        }


        public SectionNode addRecursive(SectionNode other)
        {
            if (!fullName.equals(other.fullName)) throw new IllegalArgumentException("Names should match, but don't (" + fullName + " vs " + other.fullName + ")");

            nanos += other.nanos;
            heapAllocated += other.heapAllocated;
            gcRuns += other.gcRuns;

            for (Map.Entry<String, SectionNode> entry : other.children.entrySet())
            {
                String name = entry.getKey();
                SectionNode node = entry.getValue();
                children.computeIfAbsent(entry.getKey(), o -> new SectionNode(name, this)).addRecursive(node);
            }

            return this;
        }

        public SectionNode maxRecursive(SectionNode other)
        {
            if (!fullName.equals(other.fullName)) throw new IllegalArgumentException("Names should match, but don't (" + fullName + " vs " + other.fullName + ")");

            nanos = Tools.max(nanos, other.nanos);
            heapAllocated = Tools.max(heapAllocated, other.heapAllocated);
            gcRuns = Tools.max(gcRuns, other.gcRuns);

            for (Map.Entry<String, SectionNode> entry : other.children.entrySet())
            {
                String name = entry.getKey();
                SectionNode node = entry.getValue();
                children.computeIfAbsent(entry.getKey(), o -> new SectionNode(name, this)).maxRecursive(node);
            }

            return this;
        }


        @Override
        public String toString()
        {
            StringBuilder stringBuilder = new StringBuilder();
            switch (mode)
            {
                case "total":
                    if (parent == null) stringBuilder.append("--- START OF TOTALED RESULTS ---\n\n");
                    stringBuilder.append("todo\n"); //TODO
                    if (parent == null) stringBuilder.append("\n--- END OF TOTALED RESULTS ---");
                    return stringBuilder.toString();

                case "average":
                    if (parent == null) stringBuilder.append("--- START OF AVERAGED RESULTS ---\n\n");
                    stringBuilder.append("todo\n"); //TODO
                    if (parent == null) stringBuilder.append("\n--- END OF AVERAGED RESULTS ---");
                    return stringBuilder.toString();

                case "peak":
                    if (parent == null) stringBuilder.append("--- START OF PEAK RESULTS ---\n\n");
                    stringBuilder.append("todo\n"); //TODO
                    if (parent == null) stringBuilder.append("\n--- END OF PEAK RESULTS ---");
                    return stringBuilder.toString();

                default:
                    throw new IllegalStateException("This should never happen!");
            }
        }
    }

    public static class Results
    {
        public final LinkedHashMap<Long, SectionNode> perTickData;
        public final int timeSpan;
        public final long heapAllocated;

        public Results(LinkedHashMap<Long, SectionNode> perTickData)
        {
            this.perTickData = (LinkedHashMap<Long, SectionNode>) perTickData.clone();

            timeSpan = (int) ((System.nanoTime() - INSTANCE.startTime) / 1000000);
            heapAllocated = Debug.cumulativeServerThreadHeapAllocations() - INSTANCE.startHeapAllocated;
        }

        @Override
        public String toString()
        {
            return toString("average");
        }

        public String toString(String mode)
        {
            return "\n---- " + Omniscience.NAME + " Profiler Results ----\n\n" +
                    "Time span: " + timeSpan + " ms\n" +
                    "Tick span: " + perTickData.size() + " ticks\n" +
                    "Ticks per second: " + String.format("%.2f", (float) (perTickData.size()) / ((float) timeSpan / 1000)) + " (should be ~20)\n" +
                    new SectionNode(mode.toLowerCase(), perTickData) + "\n";
        }
    }
}