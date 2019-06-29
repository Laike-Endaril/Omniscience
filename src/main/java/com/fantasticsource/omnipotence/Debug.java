package com.fantasticsource.omnipotence;

import com.fantasticsource.tools.datastructures.SortableTable;
import com.sun.management.GarbageCollectorMXBean;
import com.sun.management.ThreadMXBean;
import org.objectweb.asm.util.ASMifier;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.util.ArrayList;

public class Debug
{
    private static ThreadMXBean threadBean;
    private static RuntimeMXBean runtimeBean;
    private static GarbageCollectorMXBean gcBean; //TODO

    public static void init()
    {
        threadBean = ((ThreadMXBean) ManagementFactory.getThreadMXBean());
        threadBean.setThreadAllocatedMemoryEnabled(true);
        threadBean.setThreadCpuTimeEnabled(true);
//        threadBean.setThreadContentionMonitoringEnabled(true);

        runtimeBean = ManagementFactory.getRuntimeMXBean();
    }


    public static int threadCount()
    {
        return threadBean.getThreadCount();
    }

    public static long[] threadIDs()
    {
        return threadBean.getAllThreadIds();
    }

    public static ArrayList<String> threadNames()
    {
        ArrayList<String> names = new ArrayList<>();
        for (long id : threadIDs()) names.add(threadBean.getThreadInfo(id).getThreadName());
        return names;
    }

    public static long threadID(String name)
    {
        ThreadInfo info;
        for (long id : threadIDs())
        {
            info = threadBean.getThreadInfo(id);
            if (info.getThreadName().equals(name)) return id;
        }

        return -1;
    }

    public static String threadName(long id)
    {
        ThreadInfo info = threadBean.getThreadInfo(id);
        return info == null ? null : info.getThreadName();
    }

    public static ArrayList<ThreadGroup> threadGroupLineage()
    {
        return threadGroupLineage(Thread.currentThread());
    }

    public static ArrayList<ThreadGroup> threadGroupLineage(Thread thread)
    {
        ArrayList<ThreadGroup> result = new ArrayList<>();

        ThreadGroup group = thread.getThreadGroup();
        while (group != null)
        {
            result.add(group);
            group = group.getParent();
        }

        return result;
    }

    public static String[] threadGroupLineageStrings()
    {
        return threadGroupLineageStrings(Thread.currentThread());
    }

    public static String[] threadGroupLineageStrings(Thread thread)
    {
        ArrayList<ThreadGroup> groups = threadGroupLineage(thread);
        String[] result = new String[groups.size()];
        int size = result.length;

        for (int i = 0; i < size; i++)
        {
            result[i] = groups.get(size - 1 - i).getName();
        }

        return result;
    }

    public static Thread[] getThreads()
    {
        ThreadGroup group = Thread.currentThread().getThreadGroup();

        for (ThreadGroup parent = group.getParent(); parent != null; parent = group.getParent())
        {
            group = parent;
        }

        Thread[] threads = new Thread[threadCount()];
        group.enumerate(threads);
        return threads;
    }

    public static Thread getThread(long id)
    {
        for (Thread thread : getThreads())
        {
            if (thread.getId() == id) return thread;
        }
        return null;
    }

    public static SortableTable threadData()
    {
        //Table is thread ID (long), thread name (string), thread cpu (long), thread RAM (long)
        SortableTable result = new SortableTable(Long.class, String.class, Long.class, Long.class);
        result.startSorting(3, false);

        ThreadInfo threadInfo;
        for (long id : threadBean.getAllThreadIds())
        {
            threadInfo = threadBean.getThreadInfo(id);
            result.add(id, threadInfo.getThreadName(), threadBean.getThreadCpuTime(id), threadBean.getThreadAllocatedBytes(id));
        }

        return result;
    }

    public static String[] threadDataStrings()
    {
        return threadData().toString().split("\r\n");
    }

    public static void printASM(String fullClassname) throws Exception
    {
        ASMifier.main(new String[]{fullClassname});
    }

    public static int processID()
    {
        String s = runtimeBean.getName();
        return Integer.parseInt(s.substring(0, s.indexOf("@")));
    }

    public static String getPath(Class cls)
    {
        return cls.getResource(cls.getSimpleName() + ".class").toString();
    }
}
