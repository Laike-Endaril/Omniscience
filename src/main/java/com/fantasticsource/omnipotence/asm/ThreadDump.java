package com.fantasticsource.omnipotence.asm;

import org.objectweb.asm.*;

public class ThreadDump implements Opcodes
{

    public static byte[] dump()
    {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(52, ACC_PUBLIC + ACC_SUPER, "java/lang/Thread", null, "java/lang/Object", new String[]{"java/lang/Runnable"});

        cw.visitInnerClass("java/lang/Thread$1", null, null, ACC_STATIC);

        cw.visitInnerClass("java/lang/Thread$Caches", "java/lang/Thread", "Caches", ACC_PRIVATE + ACC_STATIC);

        cw.visitInnerClass("java/lang/Thread$State", "java/lang/Thread", "State", ACC_PUBLIC + ACC_FINAL + ACC_STATIC + ACC_ENUM);

        cw.visitInnerClass("java/lang/Thread$UncaughtExceptionHandler", "java/lang/Thread", "UncaughtExceptionHandler", ACC_PUBLIC + ACC_STATIC + ACC_ABSTRACT + ACC_INTERFACE);

        cw.visitInnerClass("java/lang/Thread$WeakClassKey", "java/lang/Thread", "WeakClassKey", ACC_STATIC);

        cw.visitInnerClass("java/lang/ThreadLocal$ThreadLocalMap", "java/lang/ThreadLocal", "ThreadLocalMap", ACC_STATIC);

        {
            fv = cw.visitField(ACC_PRIVATE + ACC_VOLATILE, "name", "Ljava/lang/String;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "priority", "I", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "threadQ", "Ljava/lang/Thread;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "eetop", "J", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "single_step", "Z", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "daemon", "Z", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "stillborn", "Z", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "target", "Ljava/lang/Runnable;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "group", "Ljava/lang/ThreadGroup;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "contextClassLoader", "Ljava/lang/ClassLoader;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "inheritedAccessControlContext", "Ljava/security/AccessControlContext;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_STATIC, "threadInitNumber", "I", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(0, "threadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(0, "inheritableThreadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "stackSize", "J", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "nativeParkEventPointer", "J", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE, "tid", "J", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_STATIC, "threadSeqNumber", "J", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_VOLATILE, "threadStatus", "I", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_VOLATILE, "parkBlocker", "Ljava/lang/Object;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_VOLATILE, "blocker", "Lsun/nio/ch/Interruptible;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "blockerLock", "Ljava/lang/Object;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "MIN_PRIORITY", "I", null, 1);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "NORM_PRIORITY", "I", null, 5);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "MAX_PRIORITY", "I", null, 10);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL + ACC_STATIC, "EMPTY_STACK_TRACE", "[Ljava/lang/StackTraceElement;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL + ACC_STATIC, "SUBCLASS_IMPLEMENTATION_PERMISSION", "Ljava/lang/RuntimePermission;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_VOLATILE, "uncaughtExceptionHandler", "Ljava/lang/Thread$UncaughtExceptionHandler;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_STATIC + ACC_VOLATILE, "defaultUncaughtExceptionHandler", "Ljava/lang/Thread$UncaughtExceptionHandler;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(0, "threadLocalRandomSeed", "J", null, null);
            {
                av0 = fv.visitAnnotation("Lsun/misc/Contended;", true);
                av0.visit("value", "tlr");
                av0.visitEnd();
            }
            fv.visitEnd();
        }
        {
            fv = cw.visitField(0, "threadLocalRandomProbe", "I", null, null);
            {
                av0 = fv.visitAnnotation("Lsun/misc/Contended;", true);
                av0.visit("value", "tlr");
                av0.visitEnd();
            }
            fv.visitEnd();
        }
        {
            fv = cw.visitField(0, "threadLocalRandomSecondarySeed", "I", null, null);
            {
                av0 = fv.visitAnnotation("Lsun/misc/Contended;", true);
                av0.visit("value", "tlr");
                av0.visitEnd();
            }
            fv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_STATIC + ACC_NATIVE, "registerNatives", "()V", null, null);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_STATIC + ACC_SYNCHRONIZED, "nextThreadNum", "()I", null, null);
            mv.visitCode();
            mv.visitFieldInsn(GETSTATIC, "java/lang/Thread", "threadInitNumber", "I");
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_1);
            mv.visitInsn(IADD);
            mv.visitFieldInsn(PUTSTATIC, "java/lang/Thread", "threadInitNumber", "I");
            mv.visitInsn(IRETURN);
            mv.visitMaxs(3, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_STATIC + ACC_SYNCHRONIZED, "nextThreadID", "()J", null, null);
            mv.visitCode();
            mv.visitFieldInsn(GETSTATIC, "java/lang/Thread", "threadSeqNumber", "J");
            mv.visitInsn(LCONST_1);
            mv.visitInsn(LADD);
            mv.visitInsn(DUP2);
            mv.visitFieldInsn(PUTSTATIC, "java/lang/Thread", "threadSeqNumber", "J");
            mv.visitInsn(LRETURN);
            mv.visitMaxs(4, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(0, "blockedOn", "(Lsun/nio/ch/Interruptible;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "blockerLock", "Ljava/lang/Object;");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ASTORE, 2);
            mv.visitInsn(MONITORENTER);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "blocker", "Lsun/nio/ch/Interruptible;");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(MONITOREXIT);
            mv.visitLabel(l1);
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 3, new Object[]{"java/lang/Thread", "sun/nio/ch/Interruptible", "java/lang/Object"}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(MONITOREXIT);
            mv.visitLabel(l3);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC + ACC_NATIVE, "currentThread", "()Ljava/lang/Thread;", null, null);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC + ACC_NATIVE, "yield", "()V", null, null);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC + ACC_NATIVE, "sleep", "(J)V", null, new String[]{"java/lang/InterruptedException"});
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "sleep", "(JI)V", null, new String[]{"java/lang/InterruptedException"});
            mv.visitCode();
            mv.visitVarInsn(LLOAD, 0);
            mv.visitInsn(LCONST_0);
            mv.visitInsn(LCMP);
            Label l0 = new Label();
            mv.visitJumpInsn(IFGE, l0);
            mv.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("timeout value is negative");
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ILOAD, 2);
            Label l1 = new Label();
            mv.visitJumpInsn(IFLT, l1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitLdcInsn(999999);
            Label l2 = new Label();
            mv.visitJumpInsn(IF_ICMPLE, l2);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("nanosecond timeout value out of range");
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitLdcInsn(500000);
            Label l3 = new Label();
            mv.visitJumpInsn(IF_ICMPGE, l3);
            mv.visitVarInsn(ILOAD, 2);
            Label l4 = new Label();
            mv.visitJumpInsn(IFEQ, l4);
            mv.visitVarInsn(LLOAD, 0);
            mv.visitInsn(LCONST_0);
            mv.visitInsn(LCMP);
            mv.visitJumpInsn(IFNE, l4);
            mv.visitLabel(l3);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(LLOAD, 0);
            mv.visitInsn(LCONST_1);
            mv.visitInsn(LADD);
            mv.visitVarInsn(LSTORE, 0);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(LLOAD, 0);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "sleep", "(J)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(4, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE, "init", "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;J)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(LLOAD, 4);
            mv.visitInsn(ACONST_NULL);
            mv.visitInsn(ICONST_1);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "init", "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;JLjava/security/AccessControlContext;Z)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(8, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE, "init", "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;JLjava/security/AccessControlContext;Z)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 3);
            Label l0 = new Label();
            mv.visitJumpInsn(IFNONNULL, l0);
            mv.visitTypeInsn(NEW, "java/lang/NullPointerException");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("name cannot be null");
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/NullPointerException", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "name", "Ljava/lang/String;");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
            mv.visitVarInsn(ASTORE, 8);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "getSecurityManager", "()Ljava/lang/SecurityManager;", false);
            mv.visitVarInsn(ASTORE, 9);
            mv.visitVarInsn(ALOAD, 1);
            Label l1 = new Label();
            mv.visitJumpInsn(IFNONNULL, l1);
            mv.visitVarInsn(ALOAD, 9);
            Label l2 = new Label();
            mv.visitJumpInsn(IFNULL, l2);
            mv.visitVarInsn(ALOAD, 9);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/SecurityManager", "getThreadGroup", "()Ljava/lang/ThreadGroup;", false);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_APPEND, 2, new Object[]{"java/lang/Thread", "java/lang/SecurityManager"}, 0, null);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitJumpInsn(IFNONNULL, l1);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getThreadGroup", "()Ljava/lang/ThreadGroup;", false);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ThreadGroup", "checkAccess", "()V", false);
            mv.visitVarInsn(ALOAD, 9);
            Label l3 = new Label();
            mv.visitJumpInsn(IFNULL, l3);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "isCCLOverridden", "(Ljava/lang/Class;)Z", false);
            mv.visitJumpInsn(IFEQ, l3);
            mv.visitVarInsn(ALOAD, 9);
            mv.visitFieldInsn(GETSTATIC, "java/lang/Thread", "SUBCLASS_IMPLEMENTATION_PERMISSION", "Ljava/lang/RuntimePermission;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/SecurityManager", "checkPermission", "(Ljava/security/Permission;)V", false);
            mv.visitLabel(l3);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ThreadGroup", "addUnstarted", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "group", "Ljava/lang/ThreadGroup;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "isDaemon", "()Z", false);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "daemon", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getPriority", "()I", false);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "priority", "I");
            mv.visitVarInsn(ALOAD, 9);
            Label l4 = new Label();
            mv.visitJumpInsn(IFNULL, l4);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "isCCLOverridden", "(Ljava/lang/Class;)Z", false);
            Label l5 = new Label();
            mv.visitJumpInsn(IFEQ, l5);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getContextClassLoader", "()Ljava/lang/ClassLoader;", false);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "contextClassLoader", "Ljava/lang/ClassLoader;");
            Label l6 = new Label();
            mv.visitJumpInsn(GOTO, l6);
            mv.visitLabel(l5);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "contextClassLoader", "Ljava/lang/ClassLoader;");
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "contextClassLoader", "Ljava/lang/ClassLoader;");
            mv.visitLabel(l6);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 6);
            Label l7 = new Label();
            mv.visitJumpInsn(IFNULL, l7);
            mv.visitVarInsn(ALOAD, 6);
            Label l8 = new Label();
            mv.visitJumpInsn(GOTO, l8);
            mv.visitLabel(l7);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/lang/Thread"});
            mv.visitMethodInsn(INVOKESTATIC, "java/security/AccessController", "getContext", "()Ljava/security/AccessControlContext;", false);
            mv.visitLabel(l8);
            mv.visitFrame(Opcodes.F_FULL, 9, new Object[]{"java/lang/Thread", "java/lang/ThreadGroup", "java/lang/Runnable", "java/lang/String", Opcodes.LONG, "java/security/AccessControlContext", Opcodes.INTEGER, "java/lang/Thread", "java/lang/SecurityManager"}, 2, new Object[]{"java/lang/Thread", "java/security/AccessControlContext"});
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "inheritedAccessControlContext", "Ljava/security/AccessControlContext;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "target", "Ljava/lang/Runnable;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "priority", "I");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "setPriority", "(I)V", false);
            mv.visitVarInsn(ILOAD, 7);
            Label l9 = new Label();
            mv.visitJumpInsn(IFEQ, l9);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "inheritableThreadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitJumpInsn(IFNULL, l9);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "inheritableThreadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/ThreadLocal", "createInheritedMap", "(Ljava/lang/ThreadLocal$ThreadLocalMap;)Ljava/lang/ThreadLocal$ThreadLocalMap;", false);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "inheritableThreadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitLabel(l9);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(LLOAD, 4);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "stackSize", "J");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "nextThreadID", "()J", false);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "tid", "J");
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 10);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PROTECTED, "clone", "()Ljava/lang/Object;", null, new String[]{"java/lang/CloneNotSupportedException"});
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/CloneNotSupportedException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/CloneNotSupportedException", "<init>", "()V", false);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "daemon", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "stillborn", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "inheritableThreadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadStatus", "I");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitTypeInsn(NEW, "java/lang/Object");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "blockerLock", "Ljava/lang/Object;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitInsn(ACONST_NULL);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitLdcInsn("Thread-");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "nextThreadNum", "()I", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            mv.visitInsn(LCONST_0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "init", "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;J)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(6, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/Runnable;)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "daemon", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "stillborn", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "inheritableThreadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadStatus", "I");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitTypeInsn(NEW, "java/lang/Object");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "blockerLock", "Ljava/lang/Object;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitLdcInsn("Thread-");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "nextThreadNum", "()I", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            mv.visitInsn(LCONST_0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "init", "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;J)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(6, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(0, "<init>", "(Ljava/lang/Runnable;Ljava/security/AccessControlContext;)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "daemon", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "stillborn", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "inheritableThreadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadStatus", "I");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitTypeInsn(NEW, "java/lang/Object");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "blockerLock", "Ljava/lang/Object;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitLdcInsn("Thread-");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "nextThreadNum", "()I", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            mv.visitInsn(LCONST_0);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(ICONST_0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "init", "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;JLjava/security/AccessControlContext;Z)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(8, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "daemon", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "stillborn", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "inheritableThreadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadStatus", "I");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitTypeInsn(NEW, "java/lang/Object");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "blockerLock", "Ljava/lang/Object;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitLdcInsn("Thread-");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "nextThreadNum", "()I", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            mv.visitInsn(LCONST_0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "init", "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;J)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(6, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/String;)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "daemon", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "stillborn", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "inheritableThreadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadStatus", "I");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitTypeInsn(NEW, "java/lang/Object");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "blockerLock", "Ljava/lang/Object;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitInsn(ACONST_NULL);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitInsn(LCONST_0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "init", "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;J)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(6, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/ThreadGroup;Ljava/lang/String;)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "daemon", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "stillborn", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "inheritableThreadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadStatus", "I");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitTypeInsn(NEW, "java/lang/Object");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "blockerLock", "Ljava/lang/Object;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitInsn(ACONST_NULL);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(LCONST_0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "init", "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;J)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(6, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/Runnable;Ljava/lang/String;)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "daemon", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "stillborn", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "inheritableThreadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadStatus", "I");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitTypeInsn(NEW, "java/lang/Object");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "blockerLock", "Ljava/lang/Object;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(LCONST_0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "init", "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;J)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(6, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "daemon", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "stillborn", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "inheritableThreadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadStatus", "I");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitTypeInsn(NEW, "java/lang/Object");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "blockerLock", "Ljava/lang/Object;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(LCONST_0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "init", "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;J)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(6, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;J)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "daemon", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "stillborn", "Z");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "inheritableThreadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadStatus", "I");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitTypeInsn(NEW, "java/lang/Object");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "blockerLock", "Ljava/lang/Object;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(LLOAD, 4);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "init", "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;J)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(6, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_SYNCHRONIZED, "start", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, "java/lang/Throwable");
            Label l3 = new Label();
            Label l4 = new Label();
            mv.visitTryCatchBlock(l3, l0, l4, null);
            Label l5 = new Label();
            Label l6 = new Label();
            Label l7 = new Label();
            mv.visitTryCatchBlock(l5, l6, l7, "java/lang/Throwable");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "threadStatus", "I");
            Label l8 = new Label();
            mv.visitJumpInsn(IFEQ, l8);
            mv.visitTypeInsn(NEW, "java/lang/IllegalThreadStateException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalThreadStateException", "<init>", "()V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l8);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "group", "Ljava/lang/ThreadGroup;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ThreadGroup", "add", "(Ljava/lang/Thread;)V", false);
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ISTORE, 1);
            mv.visitLabel(l3);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "start0", "()V", false);
            mv.visitInsn(ICONST_1);
            mv.visitVarInsn(ISTORE, 1);
            mv.visitLabel(l0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitJumpInsn(IFNE, l1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "group", "Ljava/lang/ThreadGroup;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ThreadGroup", "threadStartFailed", "(Ljava/lang/Thread;)V", false);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{Opcodes.INTEGER}, 0, null);
            Label l9 = new Label();
            mv.visitJumpInsn(GOTO, l9);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 2);
            mv.visitJumpInsn(GOTO, l9);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l5);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitJumpInsn(IFNE, l6);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "group", "Ljava/lang/ThreadGroup;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ThreadGroup", "threadStartFailed", "(Ljava/lang/Thread;)V", false);
            mv.visitLabel(l6);
            mv.visitFrame(Opcodes.F_APPEND, 2, new Object[]{Opcodes.TOP, "java/lang/Throwable"}, 0, null);
            Label l10 = new Label();
            mv.visitJumpInsn(GOTO, l10);
            mv.visitLabel(l7);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 4);
            mv.visitLabel(l10);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l9);
            mv.visitFrame(Opcodes.F_CHOP, 2, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_NATIVE, "start0", "()V", null, null);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "run", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "target", "Ljava/lang/Runnable;");
            Label l0 = new Label();
            mv.visitJumpInsn(IFNULL, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "target", "Ljava/lang/Runnable;");
            mv.visitMethodInsn(INVOKEINTERFACE, "java/lang/Runnable", "run", "()V", true);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE, "exit", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "group", "Ljava/lang/ThreadGroup;");
            Label l0 = new Label();
            mv.visitJumpInsn(IFNULL, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "group", "Ljava/lang/ThreadGroup;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ThreadGroup", "threadTerminated", "(Ljava/lang/Thread;)V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "group", "Ljava/lang/ThreadGroup;");
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "target", "Ljava/lang/Runnable;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "inheritableThreadLocals", "Ljava/lang/ThreadLocal$ThreadLocalMap;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "inheritedAccessControlContext", "Ljava/security/AccessControlContext;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "blocker", "Lsun/nio/ch/Interruptible;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "uncaughtExceptionHandler", "Ljava/lang/Thread$UncaughtExceptionHandler;");
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_DEPRECATED, "stop", "()V", null, null);
            {
                av0 = mv.visitAnnotation("Ljava/lang/Deprecated;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "getSecurityManager", "()Ljava/lang/SecurityManager;", false);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            Label l0 = new Label();
            mv.visitJumpInsn(IFNULL, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "checkAccess", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
            mv.visitJumpInsn(IF_ACMPEQ, l0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETSTATIC, "sun/security/util/SecurityConstants", "STOP_THREAD_PERMISSION", "Ljava/lang/RuntimePermission;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/SecurityManager", "checkPermission", "(Ljava/security/Permission;)V", false);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/lang/SecurityManager"}, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "threadStatus", "I");
            Label l1 = new Label();
            mv.visitJumpInsn(IFEQ, l1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "resume", "()V", false);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitTypeInsn(NEW, "java/lang/ThreadDeath");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/ThreadDeath", "<init>", "()V", false);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "stop0", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_SYNCHRONIZED + ACC_DEPRECATED, "stop", "(Ljava/lang/Throwable;)V", null, null);
            {
                av0 = mv.visitAnnotation("Ljava/lang/Deprecated;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V", false);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "interrupt", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            Label l4 = new Label();
            mv.visitTryCatchBlock(l3, l4, l2, null);
            Label l5 = new Label();
            mv.visitTryCatchBlock(l2, l5, l2, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
            Label l6 = new Label();
            mv.visitJumpInsn(IF_ACMPEQ, l6);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "checkAccess", "()V", false);
            mv.visitLabel(l6);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "blockerLock", "Ljava/lang/Object;");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitInsn(MONITORENTER);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "blocker", "Lsun/nio/ch/Interruptible;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitJumpInsn(IFNULL, l3);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "interrupt0", "()V", false);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEINTERFACE, "sun/nio/ch/Interruptible", "interrupt", "(Ljava/lang/Thread;)V", true);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitInsn(MONITOREXIT);
            mv.visitLabel(l1);
            mv.visitInsn(RETURN);
            mv.visitLabel(l3);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/lang/Object"}, 0, null);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitInsn(MONITOREXIT);
            mv.visitLabel(l4);
            Label l7 = new Label();
            mv.visitJumpInsn(GOTO, l7);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitInsn(MONITOREXIT);
            mv.visitLabel(l5);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l7);
            mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "interrupt0", "()V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "interrupted", "()Z", null, null);
            mv.visitCode();
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
            mv.visitInsn(ICONST_1);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "isInterrupted", "(Z)Z", false);
            mv.visitInsn(IRETURN);
            mv.visitMaxs(2, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "isInterrupted", "()Z", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "isInterrupted", "(Z)Z", false);
            mv.visitInsn(IRETURN);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_NATIVE, "isInterrupted", "(Z)Z", null, null);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_DEPRECATED, "destroy", "()V", null, null);
            {
                av0 = mv.visitAnnotation("Ljava/lang/Deprecated;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/NoSuchMethodError");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/NoSuchMethodError", "<init>", "()V", false);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_NATIVE, "isAlive", "()Z", null, null);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_DEPRECATED, "suspend", "()V", null, null);
            {
                av0 = mv.visitAnnotation("Ljava/lang/Deprecated;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "checkAccess", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "suspend0", "()V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_DEPRECATED, "resume", "()V", null, null);
            {
                av0 = mv.visitAnnotation("Ljava/lang/Deprecated;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "checkAccess", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "resume0", "()V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL, "setPriority", "(I)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "checkAccess", "()V", false);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitIntInsn(BIPUSH, 10);
            Label l0 = new Label();
            mv.visitJumpInsn(IF_ICMPGT, l0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitInsn(ICONST_1);
            Label l1 = new Label();
            mv.visitJumpInsn(IF_ICMPGE, l1);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>", "()V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getThreadGroup", "()Ljava/lang/ThreadGroup;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ASTORE, 2);
            Label l2 = new Label();
            mv.visitJumpInsn(IFNULL, l2);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ThreadGroup", "getMaxPriority", "()I", false);
            Label l3 = new Label();
            mv.visitJumpInsn(IF_ICMPLE, l3);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ThreadGroup", "getMaxPriority", "()I", false);
            mv.visitVarInsn(ISTORE, 1);
            mv.visitLabel(l3);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/lang/ThreadGroup"}, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitInsn(DUP_X1);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "priority", "I");
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "setPriority0", "(I)V", false);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(4, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL, "getPriority", "()I", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "priority", "I");
            mv.visitInsn(IRETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_SYNCHRONIZED, "setName", "(Ljava/lang/String;)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "checkAccess", "()V", false);
            mv.visitVarInsn(ALOAD, 1);
            Label l0 = new Label();
            mv.visitJumpInsn(IFNONNULL, l0);
            mv.visitTypeInsn(NEW, "java/lang/NullPointerException");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("name cannot be null");
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/NullPointerException", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "name", "Ljava/lang/String;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "threadStatus", "I");
            Label l1 = new Label();
            mv.visitJumpInsn(IFEQ, l1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "setNativeName", "(Ljava/lang/String;)V", false);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL, "getName", "()Ljava/lang/String;", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "name", "Ljava/lang/String;");
            mv.visitInsn(ARETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL, "getThreadGroup", "()Ljava/lang/ThreadGroup;", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "group", "Ljava/lang/ThreadGroup;");
            mv.visitInsn(ARETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "activeCount", "()I", null, null);
            mv.visitCode();
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getThreadGroup", "()Ljava/lang/ThreadGroup;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ThreadGroup", "activeCount", "()I", false);
            mv.visitInsn(IRETURN);
            mv.visitMaxs(1, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "enumerate", "([Ljava/lang/Thread;)I", null, null);
            mv.visitCode();
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getThreadGroup", "()Ljava/lang/ThreadGroup;", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ThreadGroup", "enumerate", "([Ljava/lang/Thread;)I", false);
            mv.visitInsn(IRETURN);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_NATIVE + ACC_DEPRECATED, "countStackFrames", "()I", null, null);
            {
                av0 = mv.visitAnnotation("Ljava/lang/Deprecated;", true);
                av0.visitEnd();
            }
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_SYNCHRONIZED, "join", "(J)V", null, new String[]{"java/lang/InterruptedException"});
            mv.visitCode();
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitVarInsn(LSTORE, 3);
            mv.visitInsn(LCONST_0);
            mv.visitVarInsn(LSTORE, 5);
            mv.visitVarInsn(LLOAD, 1);
            mv.visitInsn(LCONST_0);
            mv.visitInsn(LCMP);
            Label l0 = new Label();
            mv.visitJumpInsn(IFGE, l0);
            mv.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("timeout value is negative");
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_APPEND, 2, new Object[]{Opcodes.LONG, Opcodes.LONG}, 0, null);
            mv.visitVarInsn(LLOAD, 1);
            mv.visitInsn(LCONST_0);
            mv.visitInsn(LCMP);
            Label l1 = new Label();
            mv.visitJumpInsn(IFNE, l1);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "isAlive", "()Z", false);
            Label l3 = new Label();
            mv.visitJumpInsn(IFEQ, l3);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(LCONST_0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "wait", "(J)V", false);
            mv.visitJumpInsn(GOTO, l2);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "isAlive", "()Z", false);
            mv.visitJumpInsn(IFEQ, l3);
            mv.visitVarInsn(LLOAD, 1);
            mv.visitVarInsn(LLOAD, 5);
            mv.visitInsn(LSUB);
            mv.visitVarInsn(LSTORE, 7);
            mv.visitVarInsn(LLOAD, 7);
            mv.visitInsn(LCONST_0);
            mv.visitInsn(LCMP);
            Label l4 = new Label();
            mv.visitJumpInsn(IFGT, l4);
            mv.visitJumpInsn(GOTO, l3);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{Opcodes.LONG}, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(LLOAD, 7);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "wait", "(J)V", false);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitVarInsn(LLOAD, 3);
            mv.visitInsn(LSUB);
            mv.visitVarInsn(LSTORE, 5);
            mv.visitJumpInsn(GOTO, l1);
            mv.visitLabel(l3);
            mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(4, 9);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_SYNCHRONIZED, "join", "(JI)V", null, new String[]{"java/lang/InterruptedException"});
            mv.visitCode();
            mv.visitVarInsn(LLOAD, 1);
            mv.visitInsn(LCONST_0);
            mv.visitInsn(LCMP);
            Label l0 = new Label();
            mv.visitJumpInsn(IFGE, l0);
            mv.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("timeout value is negative");
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ILOAD, 3);
            Label l1 = new Label();
            mv.visitJumpInsn(IFLT, l1);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitLdcInsn(999999);
            Label l2 = new Label();
            mv.visitJumpInsn(IF_ICMPLE, l2);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("nanosecond timeout value out of range");
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitLdcInsn(500000);
            Label l3 = new Label();
            mv.visitJumpInsn(IF_ICMPGE, l3);
            mv.visitVarInsn(ILOAD, 3);
            Label l4 = new Label();
            mv.visitJumpInsn(IFEQ, l4);
            mv.visitVarInsn(LLOAD, 1);
            mv.visitInsn(LCONST_0);
            mv.visitInsn(LCMP);
            mv.visitJumpInsn(IFNE, l4);
            mv.visitLabel(l3);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(LLOAD, 1);
            mv.visitInsn(LCONST_1);
            mv.visitInsn(LADD);
            mv.visitVarInsn(LSTORE, 1);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "join", "(J)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(4, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL, "join", "()V", null, new String[]{"java/lang/InterruptedException"});
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(LCONST_0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "join", "(J)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "dumpStack", "()V", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/Exception");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("Stack trace");
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Exception", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception", "printStackTrace", "()V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL, "setDaemon", "(Z)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "checkAccess", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "isAlive", "()Z", false);
            Label l0 = new Label();
            mv.visitJumpInsn(IFEQ, l0);
            mv.visitTypeInsn(NEW, "java/lang/IllegalThreadStateException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalThreadStateException", "<init>", "()V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "daemon", "Z");
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL, "isDaemon", "()Z", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "daemon", "Z");
            mv.visitInsn(IRETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL, "checkAccess", "()V", null, null);
            mv.visitCode();
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "getSecurityManager", "()Ljava/lang/SecurityManager;", false);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            Label l0 = new Label();
            mv.visitJumpInsn(IFNULL, l0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/SecurityManager", "checkAccess", "(Ljava/lang/Thread;)V", false);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/lang/SecurityManager"}, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "toString", "()Ljava/lang/String;", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getThreadGroup", "()Ljava/lang/ThreadGroup;", false);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            Label l0 = new Label();
            mv.visitJumpInsn(IFNULL, l0);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitLdcInsn("Thread[");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getName", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitLdcInsn(",");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getPriority", "()I", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
            mv.visitLdcInsn(",");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ThreadGroup", "getName", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitLdcInsn("]");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/lang/ThreadGroup"}, 0, null);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitLdcInsn("Thread[");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getName", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitLdcInsn(",");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getPriority", "()I", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
            mv.visitLdcInsn(",]");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            mv.visitInsn(ARETURN);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "getContextClassLoader", "()Ljava/lang/ClassLoader;", null, null);
            {
                av0 = mv.visitAnnotation("Lsun/reflect/CallerSensitive;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "contextClassLoader", "Ljava/lang/ClassLoader;");
            Label l0 = new Label();
            mv.visitJumpInsn(IFNONNULL, l0);
            mv.visitInsn(ACONST_NULL);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "getSecurityManager", "()Ljava/lang/SecurityManager;", false);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            Label l1 = new Label();
            mv.visitJumpInsn(IFNULL, l1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "contextClassLoader", "Ljava/lang/ClassLoader;");
            mv.visitMethodInsn(INVOKESTATIC, "sun/reflect/Reflection", "getCallerClass", "()Ljava/lang/Class;", false);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/ClassLoader", "checkClassLoaderPermission", "(Ljava/lang/ClassLoader;Ljava/lang/Class;)V", false);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/lang/SecurityManager"}, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "contextClassLoader", "Ljava/lang/ClassLoader;");
            mv.visitInsn(ARETURN);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "setContextClassLoader", "(Ljava/lang/ClassLoader;)V", null, null);
            mv.visitCode();
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "getSecurityManager", "()Ljava/lang/SecurityManager;", false);
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 2);
            Label l0 = new Label();
            mv.visitJumpInsn(IFNULL, l0);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitTypeInsn(NEW, "java/lang/RuntimePermission");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("setContextClassLoader");
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimePermission", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/SecurityManager", "checkPermission", "(Ljava/security/Permission;)V", false);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/lang/SecurityManager"}, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "contextClassLoader", "Ljava/lang/ClassLoader;");
            mv.visitInsn(RETURN);
            mv.visitMaxs(4, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC + ACC_NATIVE, "holdsLock", "(Ljava/lang/Object;)Z", null, null);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "getStackTrace", "()[Ljava/lang/StackTraceElement;", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
            Label l0 = new Label();
            mv.visitJumpInsn(IF_ACMPEQ, l0);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "getSecurityManager", "()Ljava/lang/SecurityManager;", false);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            Label l1 = new Label();
            mv.visitJumpInsn(IFNULL, l1);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(GETSTATIC, "sun/security/util/SecurityConstants", "GET_STACK_TRACE_PERMISSION", "Ljava/lang/RuntimePermission;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/SecurityManager", "checkPermission", "(Ljava/security/Permission;)V", false);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/lang/SecurityManager"}, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "isAlive", "()Z", false);
            Label l2 = new Label();
            mv.visitJumpInsn(IFNE, l2);
            mv.visitFieldInsn(GETSTATIC, "java/lang/Thread", "EMPTY_STACK_TRACE", "[Ljava/lang/StackTraceElement;");
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(ICONST_1);
            mv.visitTypeInsn(ANEWARRAY, "java/lang/Thread");
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(AASTORE);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "dumpThreads", "([Ljava/lang/Thread;)[[Ljava/lang/StackTraceElement;", false);
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(ICONST_0);
            mv.visitInsn(AALOAD);
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 3);
            Label l3 = new Label();
            mv.visitJumpInsn(IFNONNULL, l3);
            mv.visitFieldInsn(GETSTATIC, "java/lang/Thread", "EMPTY_STACK_TRACE", "[Ljava/lang/StackTraceElement;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l3);
            mv.visitFrame(Opcodes.F_APPEND, 2, new Object[]{"[[Ljava/lang/StackTraceElement;", "[Ljava/lang/StackTraceElement;"}, 0, null);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_CHOP, 3, null, 0, null);
            mv.visitTypeInsn(NEW, "java/lang/Exception");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Exception", "<init>", "()V", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception", "getStackTrace", "()[Ljava/lang/StackTraceElement;", false);
            mv.visitInsn(ARETURN);
            mv.visitMaxs(4, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "getAllStackTraces", "()Ljava/util/Map;", "()Ljava/util/Map<Ljava/lang/Thread;[Ljava/lang/StackTraceElement;>;", null);
            mv.visitCode();
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "getSecurityManager", "()Ljava/lang/SecurityManager;", false);
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 0);
            Label l0 = new Label();
            mv.visitJumpInsn(IFNULL, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETSTATIC, "sun/security/util/SecurityConstants", "GET_STACK_TRACE_PERMISSION", "Ljava/lang/RuntimePermission;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/SecurityManager", "checkPermission", "(Ljava/security/Permission;)V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETSTATIC, "sun/security/util/SecurityConstants", "MODIFY_THREADGROUP_PERMISSION", "Ljava/lang/RuntimePermission;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/SecurityManager", "checkPermission", "(Ljava/security/Permission;)V", false);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/lang/SecurityManager"}, 0, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "getThreads", "()[Ljava/lang/Thread;", false);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "dumpThreads", "([Ljava/lang/Thread;)[[Ljava/lang/StackTraceElement;", false);
            mv.visitVarInsn(ASTORE, 2);
            mv.visitTypeInsn(NEW, "java/util/HashMap");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitInsn(ARRAYLENGTH);
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/HashMap", "<init>", "(I)V", false);
            mv.visitVarInsn(ASTORE, 3);
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ISTORE, 4);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_FULL, 5, new Object[]{"java/lang/SecurityManager", "[Ljava/lang/Thread;", "[[Ljava/lang/StackTraceElement;", "java/util/Map", Opcodes.INTEGER}, 0, new Object[]{});
            mv.visitVarInsn(ILOAD, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitInsn(ARRAYLENGTH);
            Label l2 = new Label();
            mv.visitJumpInsn(IF_ICMPGE, l2);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitInsn(AALOAD);
            mv.visitVarInsn(ASTORE, 5);
            mv.visitVarInsn(ALOAD, 5);
            Label l3 = new Label();
            mv.visitJumpInsn(IFNULL, l3);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitInsn(AALOAD);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
            mv.visitInsn(POP);
            mv.visitLabel(l3);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitIincInsn(4, 1);
            mv.visitJumpInsn(GOTO, l1);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitMaxs(3, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_STATIC, "isCCLOverridden", "(Ljava/lang/Class;)Z", "(Ljava/lang/Class<*>;)Z", null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitLdcInsn(Type.getType("Ljava/lang/Thread;"));
            Label l0 = new Label();
            mv.visitJumpInsn(IF_ACMPNE, l0);
            mv.visitInsn(ICONST_0);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitFieldInsn(GETSTATIC, "java/lang/Thread$Caches", "subclassAuditsQueue", "Ljava/lang/ref/ReferenceQueue;");
            mv.visitFieldInsn(GETSTATIC, "java/lang/Thread$Caches", "subclassAudits", "Ljava/util/concurrent/ConcurrentMap;");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "processQueue", "(Ljava/lang/ref/ReferenceQueue;Ljava/util/concurrent/ConcurrentMap;)V", false);
            mv.visitTypeInsn(NEW, "java/lang/Thread$WeakClassKey");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETSTATIC, "java/lang/Thread$Caches", "subclassAuditsQueue", "Ljava/lang/ref/ReferenceQueue;");
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread$WeakClassKey", "<init>", "(Ljava/lang/Class;Ljava/lang/ref/ReferenceQueue;)V", false);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitFieldInsn(GETSTATIC, "java/lang/Thread$Caches", "subclassAudits", "Ljava/util/concurrent/ConcurrentMap;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/concurrent/ConcurrentMap", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true);
            mv.visitTypeInsn(CHECKCAST, "java/lang/Boolean");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 2);
            Label l1 = new Label();
            mv.visitJumpInsn(IFNONNULL, l1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "auditSubclass", "(Ljava/lang/Class;)Z", false);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
            mv.visitVarInsn(ASTORE, 2);
            mv.visitFieldInsn(GETSTATIC, "java/lang/Thread$Caches", "subclassAudits", "Ljava/util/concurrent/ConcurrentMap;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/concurrent/ConcurrentMap", "putIfAbsent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
            mv.visitInsn(POP);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_APPEND, 2, new Object[]{"java/lang/Thread$WeakClassKey", "java/lang/Boolean"}, 0, null);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z", false);
            mv.visitInsn(IRETURN);
            mv.visitMaxs(4, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_STATIC, "auditSubclass", "(Ljava/lang/Class;)Z", "(Ljava/lang/Class<*>;)Z", null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/Thread$1");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread$1", "<init>", "(Ljava/lang/Class;)V", false);
            mv.visitMethodInsn(INVOKESTATIC, "java/security/AccessController", "doPrivileged", "(Ljava/security/PrivilegedAction;)Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "java/lang/Boolean");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z", false);
            mv.visitInsn(IRETURN);
            mv.visitMaxs(3, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_STATIC + ACC_NATIVE, "dumpThreads", "([Ljava/lang/Thread;)[[Ljava/lang/StackTraceElement;", null, null);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_STATIC + ACC_NATIVE, "getThreads", "()[Ljava/lang/Thread;", null, null);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "getId", "()J", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "tid", "J");
            mv.visitInsn(LRETURN);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "getState", "()Ljava/lang/Thread$State;", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "threadStatus", "I");
            mv.visitMethodInsn(INVOKESTATIC, "sun/misc/VM", "toThreadState", "(I)Ljava/lang/Thread$State;", false);
            mv.visitInsn(ARETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "setDefaultUncaughtExceptionHandler", "(Ljava/lang/Thread$UncaughtExceptionHandler;)V", null, null);
            mv.visitCode();
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "getSecurityManager", "()Ljava/lang/SecurityManager;", false);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            Label l0 = new Label();
            mv.visitJumpInsn(IFNULL, l0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitTypeInsn(NEW, "java/lang/RuntimePermission");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("setDefaultUncaughtExceptionHandler");
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimePermission", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/SecurityManager", "checkPermission", "(Ljava/security/Permission;)V", false);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/lang/SecurityManager"}, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(PUTSTATIC, "java/lang/Thread", "defaultUncaughtExceptionHandler", "Ljava/lang/Thread$UncaughtExceptionHandler;");
            mv.visitInsn(RETURN);
            mv.visitMaxs(4, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "getDefaultUncaughtExceptionHandler", "()Ljava/lang/Thread$UncaughtExceptionHandler;", null, null);
            mv.visitCode();
            mv.visitFieldInsn(GETSTATIC, "java/lang/Thread", "defaultUncaughtExceptionHandler", "Ljava/lang/Thread$UncaughtExceptionHandler;");
            mv.visitInsn(ARETURN);
            mv.visitMaxs(1, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "getUncaughtExceptionHandler", "()Ljava/lang/Thread$UncaughtExceptionHandler;", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "uncaughtExceptionHandler", "Ljava/lang/Thread$UncaughtExceptionHandler;");
            Label l0 = new Label();
            mv.visitJumpInsn(IFNULL, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "uncaughtExceptionHandler", "Ljava/lang/Thread$UncaughtExceptionHandler;");
            Label l1 = new Label();
            mv.visitJumpInsn(GOTO, l1);
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "java/lang/Thread", "group", "Ljava/lang/ThreadGroup;");
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/lang/Thread$UncaughtExceptionHandler"});
            mv.visitInsn(ARETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "setUncaughtExceptionHandler", "(Ljava/lang/Thread$UncaughtExceptionHandler;)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "checkAccess", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "uncaughtExceptionHandler", "Ljava/lang/Thread$UncaughtExceptionHandler;");
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE, "dispatchUncaughtException", "(Ljava/lang/Throwable;)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getUncaughtExceptionHandler", "()Ljava/lang/Thread$UncaughtExceptionHandler;", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/lang/Thread$UncaughtExceptionHandler", "uncaughtException", "(Ljava/lang/Thread;Ljava/lang/Throwable;)V", true);
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_STATIC, "processQueue", "(Ljava/lang/ref/ReferenceQueue;Ljava/util/concurrent/ConcurrentMap;)V", "(Ljava/lang/ref/ReferenceQueue<Ljava/lang/Class<*>;>;Ljava/util/concurrent/ConcurrentMap<+Ljava/lang/ref/WeakReference<Ljava/lang/Class<*>;>;*>;)V", null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ref/ReferenceQueue", "poll", "()Ljava/lang/ref/Reference;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ASTORE, 2);
            Label l1 = new Label();
            mv.visitJumpInsn(IFNULL, l1);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/concurrent/ConcurrentMap", "remove", "(Ljava/lang/Object;)Ljava/lang/Object;", true);
            mv.visitInsn(POP);
            mv.visitJumpInsn(GOTO, l0);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/lang/ref/Reference"}, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_NATIVE, "setPriority0", "(I)V", null, null);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_NATIVE, "stop0", "(Ljava/lang/Object;)V", null, null);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_NATIVE, "suspend0", "()V", null, null);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_NATIVE, "resume0", "()V", null, null);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_NATIVE, "interrupt0", "()V", null, null);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_NATIVE, "setNativeName", "(Ljava/lang/String;)V", null, null);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
            mv.visitCode();
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "registerNatives", "()V", false);
            mv.visitInsn(ICONST_0);
            mv.visitTypeInsn(ANEWARRAY, "java/lang/StackTraceElement");
            mv.visitFieldInsn(PUTSTATIC, "java/lang/Thread", "EMPTY_STACK_TRACE", "[Ljava/lang/StackTraceElement;");
            mv.visitTypeInsn(NEW, "java/lang/RuntimePermission");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("enableContextClassLoaderOverride");
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimePermission", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitFieldInsn(PUTSTATIC, "java/lang/Thread", "SUBCLASS_IMPLEMENTATION_PERMISSION", "Ljava/lang/RuntimePermission;");
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 0);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
