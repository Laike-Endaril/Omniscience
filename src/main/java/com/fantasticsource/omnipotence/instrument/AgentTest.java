package com.fantasticsource.omnipotence.instrument;

import sun.management.Agent;

import java.lang.instrument.Instrumentation;

public class AgentTest extends Agent
{
    public static void premain(String agentArgs, Instrumentation inst)
    {
        System.out.println("premain ==============================================================================================================================");
    }

    public static void agentmain(String agentArgs, Instrumentation inst)
    {
        System.out.println("agentmain =============================================================================================================================");
    }
}
