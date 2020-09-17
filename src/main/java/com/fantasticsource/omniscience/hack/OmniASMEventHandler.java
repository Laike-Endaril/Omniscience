package com.fantasticsource.omniscience.hack;

import net.minecraft.profiler.Profiler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.ASMEventHandler;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.lang.reflect.Method;

public class OmniASMEventHandler extends ASMEventHandler
{
    protected ModContainer modContainer;

    @Deprecated
    public OmniASMEventHandler(Object target, Method method, ModContainer owner) throws Exception
    {
        this(target, method, owner, false);
    }

    public OmniASMEventHandler(Object target, Method method, ModContainer owner, boolean isGeneric) throws Exception
    {
        super(target, method, owner, isGeneric);

        modContainer = owner;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void invoke(Event event)
    {
        //Omni start
        Profiler profiler = Thread.currentThread().getName().equals("Server thread") ? FMLCommonHandler.instance().getMinecraftServerInstance().profiler : null;
        if (profiler != null) profiler.startSection("@Subscribe " + event.getClass().getSimpleName() + "(" + modContainer.getName() + ")");
        //Omni end

        super.invoke(event);

        if (profiler != null) profiler.endSection(); //Omni
    }
}