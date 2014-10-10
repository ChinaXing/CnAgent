package com.cn.jvm.tool.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by ChinaXing on 14-10-10.
 */
public class ChinaTransformer implements ClassFileTransformer {
    private String className;
    private String methodName;
    private String enhanceCode;

    public ChinaTransformer(String className, String method, String enhanceCode) {
        this.className = className;
        this.methodName = method;
        this.enhanceCode = enhanceCode;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!className.equals(this.className.replace(".", "/"))) {
            return classfileBuffer;
        }
        try {
            System.out.println("begin transform => " + className);
            ClassPool classPool = ClassPool.getDefault();
            CtClass ctClass = classPool.get(this.className);
            CtMethod ctMethod = ctClass.getDeclaredMethod(methodName);
            ctMethod.insertBefore(enhanceCode);
            System.out.println("transform ok !");
            return ctClass.toBytecode();
        } catch (Throwable e) {
            System.out.println("error when transform : " + e.getMessage());
            e.printStackTrace();
            return classfileBuffer;
        }
    }
}
