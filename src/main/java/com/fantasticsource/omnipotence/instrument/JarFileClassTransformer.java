package com.fantasticsource.omnipotence.instrument;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarFileClassTransformer implements ClassFileTransformer
{
    //Thanks to https://stackoverflow.com/questions/35424320/how-do-i-replace-a-class-with-new-one-with-java-instrumentation

    private String jarFileName;
    protected Map<String, InputStream> classStreamMap = new HashMap<>();
    static Instrumentation instrumentation = null;

    public JarFileClassTransformer(String jarFileName)
    {
        this.jarFileName = jarFileName;

        File file = new File(jarFileName);
        System.out.println("Jar file '" + this.jarFileName + "' " + (file.exists() ? "exists" : "doesn't exist!"));

        if (file.exists())
        {
            try
            {
                JarFile jarFile = new JarFile(file);
                Enumeration e = jarFile.entries();

                while (e.hasMoreElements())
                {
                    JarEntry je = (JarEntry) e.nextElement();
                    if (je.isDirectory() || !je.getName().endsWith(".class")) continue;

                    // -6 because of .class
                    String jarClassName = je.getName().substring(0, je.getName().length() - 6);
                    jarClassName = jarClassName.replace('/', '.');
                    System.out.println("Adding class " + jarClassName);
                    this.classStreamMap.put(jarClassName, jarFile.getInputStream(je));
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer)
    {
        System.out.println("Transforming.......................................................................................");

        if (classStreamMap.containsKey(className.replace("/", ".")))
        {
            System.out.format("\n==> Found %s to replace with the existing version\n", className);
            try
            {
                Class c = loader.loadClass(className.replace("/", "."));
                System.out.println("Existing class: " + c);
                InputStream is = classStreamMap.get(className.replace("/", "."));

                byte[] content = new byte[is.available()];
                is.read(content);

                System.out.println("Original class version: " + ((classfileBuffer[6] & 0xff) << 8 | (classfileBuffer[7] & 0xff)));
                System.out.println("Redefined class version: " + ((content[6] & 0xff) << 8 | (content[7] & 0xff)));

                System.out.println("Original bytecode: " + new String(classfileBuffer));
                System.out.println("Redefined byte code: " + new String(content));
                ClassDefinition cd = new ClassDefinition(c, content);
                instrumentation.redefineClasses(cd);

                return content;
            }
            catch (Throwable e)
            {
                e.printStackTrace();
            }
        }
        return classfileBuffer;
    }
}
