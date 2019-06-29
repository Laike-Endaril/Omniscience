package com.fantasticsource.omnipotence.transforms;

import org.objectweb.asm.util.Printer;

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
            System.out.println("Transforming " + className + " (" + bytes.length + " bytes)");



            StringBuilder builder = new StringBuilder();
            for (byte b : bytes)
            {
                builder.append(Printer.OPCODES[b & 0xFF]).append("\n");
            }
            System.out.println(builder);


            return bytes;
        }
        return bytes;
    }
}