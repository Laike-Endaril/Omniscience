package com.fantasticsource.omnipotence.transforms;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.*;

public class ThreadInitVisitor extends MethodVisitor
{
    //Thanks to https://www.javacodegeeks.com/2012/02/manipulating-java-class-files-with-asm.html

    public ThreadInitVisitor(int api, MethodVisitor mv)
    {
        super(api, mv);
    }

    //This is the point we insert the code. Note that the instructions are added right after
    //the visitCode method of the super class. This ordering is very important.
    @Override
    public void visitCode()
    {
        super.visitCode();


        //The new code ends up being the first bit of code in the method


        //Prints the thread name
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        mv.visitLdcInsn("======= ");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitVarInsn(ALOAD, 3);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitLdcInsn(" =======");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);


        //Prints the stacktrace of the thread's parent
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getStackTrace", "()[Ljava/lang/StackTraceElement;", false);
        mv.visitVarInsn(ASTORE, 8);
        mv.visitVarInsn(ALOAD, 8);
        mv.visitInsn(ARRAYLENGTH);
        mv.visitVarInsn(ISTORE, 9);
        mv.visitInsn(ICONST_0);
        mv.visitVarInsn(ISTORE, 10);
        Label l0 = new Label();
        mv.visitLabel(l0);
        mv.visitFrame(Opcodes.F_APPEND, 3, new Object[]{"[Ljava/lang/StackTraceElement;", Opcodes.INTEGER, Opcodes.INTEGER}, 0, null);
        mv.visitVarInsn(ILOAD, 10);
        mv.visitVarInsn(ILOAD, 9);
        Label l1 = new Label();
        mv.visitJumpInsn(IF_ICMPGE, l1);
        mv.visitVarInsn(ALOAD, 8);
        mv.visitVarInsn(ILOAD, 10);
        mv.visitInsn(AALOAD);
        mv.visitVarInsn(ASTORE, 11);
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(ALOAD, 11);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/Object;)V", false);
        mv.visitIincInsn(10, 1);
        mv.visitJumpInsn(GOTO, l0);
        mv.visitLabel(l1);
        mv.visitFrame(Opcodes.F_CHOP, 3, null, 0, null);


        //Prints an empty line
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "()V", false);


        //Succeeded (simple printline)
//        super.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//        super.visitLdcInsn("Added this printline to Thread.init()");
//        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");


        //Succeeded (set threadQ to parent thread)
//        mv.visitVarInsn(ALOAD, 0);
//        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
//        mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadQ", "Ljava/lang/Thread;");


        //Failed
//        mv.visitFieldInsn(GETSTATIC, "com/fantasticsource/omnipotence/transforms/AgentTest", "injectToThreadInit", "Ljava/util/function/Predicate;");
//        mv.visitInsn(ACONST_NULL);
//        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/function/Predicate", "test", "(Ljava/lang/Object;)Z", true);
//        mv.visitInsn(POP);


        //Failed
//        mv.visitFieldInsn(GETSTATIC, "com/fantasticsource/omnipotence/Debug", "sourceTraceMap", "Ljava/util/LinkedHashMap;");
//        mv.visitVarInsn(ALOAD, 0);
//        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "getAllStackTraces", "()Ljava/util/Map;", false);
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/LinkedHashMap", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", false);
//        mv.visitInsn(POP);


        //Failed
//        mv.visitFieldInsn(GETSTATIC, "com/fantasticsource/omnipotence/transforms/AgentTest", "verboseThreadCreation", "Z");
//        Label l0 = new Label();
//        mv.visitJumpInsn(IFEQ, l0);
//        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getStackTrace", "()[Ljava/lang/StackTraceElement;", false);
//        mv.visitVarInsn(ASTORE, 1);
//        mv.visitVarInsn(ALOAD, 1);
//        mv.visitInsn(ARRAYLENGTH);
//        mv.visitVarInsn(ISTORE, 2);
//        mv.visitInsn(ICONST_0);
//        mv.visitVarInsn(ISTORE, 3);
//        Label l1 = new Label();
//        mv.visitLabel(l1);
//        mv.visitFrame(Opcodes.F_APPEND,3, new Object[] {"[Ljava/lang/StackTraceElement;", Opcodes.INTEGER, Opcodes.INTEGER}, 0, null);
//        mv.visitVarInsn(ILOAD, 3);
//        mv.visitVarInsn(ILOAD, 2);
//        mv.visitJumpInsn(IF_ICMPGE, l0);
//        mv.visitVarInsn(ALOAD, 1);
//        mv.visitVarInsn(ILOAD, 3);
//        mv.visitInsn(AALOAD);
//        mv.visitVarInsn(ASTORE, 4);
//        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//        mv.visitVarInsn(ALOAD, 4);
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/Object;)V", false);
//        mv.visitIincInsn(3, 1);
//        mv.visitJumpInsn(GOTO, l1);
//        mv.visitLabel(l0);
//        mv.visitFrame(Opcodes.F_CHOP,3, null, 0, null);


        //Failed
//        mv.visitFieldInsn(GETSTATIC, "com/fantasticsource/omnipotence/Debug", "sourceTraceMap", "Ljava/util/LinkedHashMap;");
//        mv.visitVarInsn(ALOAD, 0);
//        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getStackTrace", "()[Ljava/lang/StackTraceElement;", false);
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/LinkedHashMap", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", false);
//        mv.visitInsn(POP);


        //Failed
//        mv.visitVarInsn(ALOAD, 0);
//        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getStackTrace", "()[Ljava/lang/StackTraceElement;", false);
//        mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "sourceTrace", "[Ljava/lang/StackTraceElement;");
    }
}
