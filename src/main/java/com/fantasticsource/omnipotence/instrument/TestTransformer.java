package com.fantasticsource.omnipotence.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class TestTransformer implements ClassFileTransformer
{
    private Class cls;

    TestTransformer(Class cls)
    {
        this.cls = cls;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] bytes)
    {
        if (classBeingRedefined == cls)
        {
            System.out.println("Transforming " + className);


            //TODO Actually transform the class


            return bytes;
        }
        return bytes;
    }
}