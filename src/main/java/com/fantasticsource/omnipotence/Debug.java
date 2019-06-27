package com.fantasticsource.omnipotence;

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
        result.startSorting(3, false);

        ThreadInfo threadInfo;
        for (long id : threadMXBean.getAllThreadIds())
        {
            threadInfo = threadMXBean.getThreadInfo(id);
            result.add(id, threadInfo.getThreadName(), threadMXBean.getThreadCpuTime(id), threadMXBean.getThreadAllocatedBytes(id));
        }

        return result;
    }

    public static String[] getThreadDataStrings()
    {
        return getThreadData().toString().split("\r\n");
    }
}
