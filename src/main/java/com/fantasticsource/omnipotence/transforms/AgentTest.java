package com.fantasticsource.omnipotence.transforms;

import com.fantasticsource.omnipotence.Debug;
import sun.management.Agent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.ArrayList;

public class AgentTest extends Agent
{
    //Thanks to...
    //https://stackoverflow.com/questions/11898566/tutorials-about-javaagents
    //https://bukkit.org/threads/tutorial-extreme-beyond-reflection-asm-replacing-loaded-classes.99376/
    //https://www.baeldung.com/java-instrumentation
    //https://stackoverflow.com/questions/19009583/difference-between-redefine-and-retransform-in-javaagent


    public static void premain(String agentArgs, Instrumentation instrumentation)
    {
        //Called when using -javaagent JVM argument, eg. java -javaagent <agent jarname> -jar <main jarname>
        //Executes before the main jar, thus the method name
        //Requires proper manifest entries (see build.gradle)

        System.out.println("premain ==============================================================================================================================");
        submain(agentArgs, instrumentation);
    }

    public static void agentmain(String agentArgs, Instrumentation instrumentation)
    {
        //Called at any time during runtime...I'll try this out later
        //TODO https://stackoverflow.com/questions/1277219/starting-a-java-agent-after-program-start

        System.out.println("agentmain =============================================================================================================================");
        submain(agentArgs, instrumentation);
    }

    private static void submain(String agentArgs, Instrumentation instrumentation)
    {
        ArrayList<Class> transformedClasses = new ArrayList<>();
        for (Class cls : instrumentation.getAllLoadedClasses())
        {
            if (cls.getSimpleName().equals("Thread"))
            {
                instrumentation.addTransformer(new TestTransformer(cls), true);
                transformedClasses.add(cls);
            }
        }


        //Retransform already-loaded classes in memory
        try
        {
            instrumentation.retransformClasses(transformedClasses.toArray(new Class[0]));
        }
        catch (UnmodifiableClassException e)
        {
            e.printStackTrace();
        }


        //Test
        System.out.println(Debug.sourceTrace(Thread.currentThread()) == null ? "Not yet..." : "Ultimate success!");
    }
}
