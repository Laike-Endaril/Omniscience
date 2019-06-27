package com.fantasticsource.omnipotence;

import com.fantasticsource.tools.datastructures.SortableTable;
import com.sun.management.GarbageCollectorMXBean;
import com.sun.management.ThreadMXBean;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.util.ArrayList;

public class Debug
{
    private static ThreadMXBean threadBean;
    private static GarbageCollectorMXBean gcBean; //TODO

    public static void init()
    {
        threadBean = ((ThreadMXBean) ManagementFactory.getThreadMXBean());
        threadBean.setThreadAllocatedMemoryEnabled(true);
        threadBean.setThreadCpuTimeEnabled(true);
//        threadBean.setThreadContentionMonitoringEnabled(true);
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
}
