package com.fantasticsource.omnipotence;

import com.fantasticsource.tools.ReflectionTool;
import com.google.common.collect.ImmutableList;
import net.minecraftforge.fml.common.eventhandler.ASMEventHandler;
import net.minecraftforge.fml.common.eventhandler.IEventListener;
import net.minecraftforge.fml.common.eventhandler.ListenerList;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Timing
{
    public static void init() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException
    {
        Method listenerListGetInstanceMethod = ReflectionTool.getMethod(ListenerList.class, "getInstance");

        Field listenerListMaxSizeField = ReflectionTool.getField(ListenerList.class, "maxSize");
        int buses = (int) listenerListMaxSizeField.get(null);

        Field listenerListAllListsField = ReflectionTool.getField(ListenerList.class, "allLists");
        for (ListenerList listenerList : ((ImmutableList<ListenerList>) listenerListAllListsField.get(null)))
        {
            for (int i = 0; i < buses; i++)
            {
                for (IEventListener listener : listenerList.getListeners(i))
                {
                    if (listener instanceof ASMEventHandler)
                    {
                        Method invokeMethod = ReflectionTool.getMethod(listener.getClass(), "invoke");
                        System.out.println(invokeMethod.getDeclaringClass().getName());
                    }
                }

                Object listenerListInst = listenerListGetInstanceMethod.invoke(listenerList, i);
            }
        }
    }
}
