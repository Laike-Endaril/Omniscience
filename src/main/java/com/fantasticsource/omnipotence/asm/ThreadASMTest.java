package com.fantasticsource.omnipotence.asm;

import org.objectweb.asm.*;

import java.io.*;

public class ThreadASMTest
{

    public static class ModifierMethodWriter extends MethodVisitor
    {
        private String methodName;

        public ModifierMethodWriter(int api, MethodVisitor mv, String methodName)
        {
            super(api, mv);
            this.methodName = methodName;
        }

        //This is the point we insert the code. Note that the instructions are added right after
        //the visitCode method of the super class. This ordering is very important.
        @Override
        public void visitCode()
        {
            super.visitCode();
            super.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            super.visitLdcInsn("TEST");
            super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        }
    }

    //Our class modifier class visitor. It delegate all calls to the super class
    //Only makes sure that it returns our MethodVisitor for every method
    public static class ModifierClassWriter extends ClassVisitor
    {
        private int api;

        public ModifierClassWriter(int api, ClassWriter cv)
        {
            super(api, cv);
            this.api = api;
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
        {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

            if (name.equals("init") && desc.equals("(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;JLjava/security/AccessControlContext;Z)V"))
            {
                return new ModifierMethodWriter(api, mv, name);
            }

            return mv;
        }
    }

    public static void main(String[] args) throws IOException
    {
        InputStream in = Thread.class.getResourceAsStream("/java/lang/Thread.class");
        ClassReader classReader = new ClassReader(in);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        //Wrap the ClassWriter with our custom ClassVisitor
        ModifierClassWriter mcw = new ModifierClassWriter(Opcodes.ASM4, cw);
        classReader.accept(mcw, 0);

        //Write the output to a class file
        File outputDir = new File("asmtest");
        outputDir.mkdirs();
        DataOutputStream dout = new DataOutputStream(new FileOutputStream(new File(outputDir, "Thread.class")));
        dout.write(cw.toByteArray());
    }
}
