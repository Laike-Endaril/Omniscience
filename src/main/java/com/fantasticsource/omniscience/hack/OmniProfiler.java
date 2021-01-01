package com.fantasticsource.omniscience.hack;

import com.fantasticsource.mctools.ServerTickTimer;
import com.fantasticsource.omniscience.Debug;
import net.minecraft.profiler.Profiler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class OmniProfiler extends Profiler
{
    public static final OmniProfiler INSTANCE = new OmniProfiler();

    protected static final LinkedHashMap<Long, SectionNode> PER_TICK_DATA = new LinkedHashMap<>();
    protected static SectionNode currentNode = null;
    protected static boolean starting = false, active = false;
    protected static ArrayList<Runnable> stoppingCallbacks = new ArrayList<>();

    protected OmniProfiler()
    {
    }

    public static void reset()
    {
        PER_TICK_DATA.clear();
        currentNode = null;
        stoppingCallbacks.clear();
        starting = false;
        active = false;
    }

    /**
     * @return human-readable result of this call (success or error)
     * A start can only be queued if the profiler is not already running or starting
     */
    public String startProfiling()
    {
        if (starting) return "Profiler is already starting";
        if (active) return "Profiler is already running";

        starting = true;
        return "Starting profiler";
    }

    /**
     * @return human-readable result of this call (success or error)
     * A stop can be queued if the profiler is already running, or if it is starting (the latter will result in a 1-tick profiler run)
     * Callbacks will be called unless there is an error (eg. different number of calls to startSection and endSection in one tick)
     */
    public String stopProfiling(Runnable callback)
    {
        if (!active && !starting) return "Profiler is not running or starting";

        stoppingCallbacks.add(callback);
        return "Stopping profiler";
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void serverTick(TickEvent.ServerTickEvent event)
    {
        if (event.phase != TickEvent.Phase.START) return;

        if (active)
        {
            INSTANCE.endSection();
            if (currentNode != null)
            {
                reset();
                throw new IllegalStateException("profiler.startSection() was called more times this tick than profiler.endSection()!  Stopping profiling and resetting profiler state!  The stacktrace from this is probably not pointing at the actual issue!");
            }

            if (stoppingCallbacks.size() > 0)
            {
                Runnable[] callbacks = stoppingCallbacks.toArray(new Runnable[0]);

                active = false;
                stoppingCallbacks.clear();

                for (Runnable callback : callbacks) callback.run();
            }
        }

        if (!active && starting)
        {
            PER_TICK_DATA.clear();
            currentNode = new SectionNode(true);
            PER_TICK_DATA.put(ServerTickTimer.currentTick(), currentNode);

            active = true;
            starting = false;
        }
    }

    @Override
    public void startSection(String name)
    {
        if (active)
        {
            currentNode = currentNode.children.computeIfAbsent(name, o -> new SectionNode(currentNode));
            currentNode.startState = new RuntimeState();
        }
    }

    public void endSection()
    {
        if (active)
        {
            if (currentNode == null)
            {
                reset();
                throw new IllegalStateException("profiler.endSection() was called more times this tick than profiler.startSection()!  Stopping profiling and resetting profiler state!  The stacktrace from this is probably not pointing at the actual issue!");
            }
            else
            {
                RuntimeState startState = currentNode.startState;
                currentNode.nanos += System.nanoTime() - startState.nanoTime;
                currentNode.gcRuns += Debug.gcRuns() - startState.gcRuns;
                long dif = Debug.usedMemory() - startState.heapUsage;
                if (dif > 0) currentNode.heapUsage += dif;

                currentNode.startState = null;

                currentNode = currentNode.parent;
            }
        }
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

    public static class SectionNode
    {
        public SectionNode parent = null;
        public HashMap<String, SectionNode> children = new HashMap<>();
        public RuntimeState startState = null;
        public long nanos = 0, heapUsage = 0;
        public int gcRuns = 0;

        public SectionNode(boolean initStartState)
        {
            if (initStartState) startState = new RuntimeState();
        }

        public SectionNode(SectionNode parent)
        {
            this.parent = parent;
        }
    }
}