package com.fantasticsource.omnipotence;

import com.fantasticsource.tools.datastructures.ExplicitPriorityQueue;
import com.fantasticsource.tools.datastructures.SortableTable;
import com.sun.management.ThreadMXBean;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

public class Debug
{
    private static ThreadMXBean threadMXBean;

    public static void init()
    {
        threadMXBean = ((ThreadMXBean) ManagementFactory.getThreadMXBean());
        threadMXBean.setThreadAllocatedMemoryEnabled(true);
        threadMXBean.setThreadCpuTimeEnabled(true);
//        threadMXBean.setThreadContentionMonitoringEnabled(true);
    }


    public static SortableTable getThreadData()
    {
        //Table is thread ID (long), thread name (string), thread cpu (long), thread RAM (long)
        SortableTable result = new SortableTable(Long.class, String.class, Long.class, Long.class);

        ExplicitPriorityQueue<ThreadInfo> queue = new ExplicitPriorityQueue<>(threadMXBean.getThreadCount());

        ThreadInfo threadInfo;
        for (long l : threadMXBean.getAllThreadIds())
        {
            threadInfo = threadMXBean.getThreadInfo(l);
            queue.add(threadInfo, Long.MAX_VALUE - threadMXBean.getThreadAllocatedBytes(l));
        }

        threadInfo = queue.poll();
        while (threadInfo != null)
        {
            long l = threadInfo.getThreadId();
            result.add(l, threadInfo.getThreadName(), threadMXBean.getThreadCpuTime(l), threadMXBean.getThreadAllocatedBytes(l));
            threadInfo = queue.poll();
        }

        return result;
    }

    public static String[] getThreadDataStrings()
    {
        return getThreadData().toString().split("\r\n");
    }
}
