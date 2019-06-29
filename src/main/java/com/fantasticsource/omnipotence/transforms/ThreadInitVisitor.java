package com.fantasticsource.omnipotence.transforms;

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


        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
        mv.visitFieldInsn(PUTFIELD, "java/lang/Thread", "threadQ", "Ljava/lang/Thread;");


        super.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        super.visitLdcInsn("SUCCESS");
        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
    }
}
