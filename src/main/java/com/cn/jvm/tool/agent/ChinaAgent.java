package com.cn.jvm.tool.agent;

import java.lang.instrument.Instrumentation;

/**
 * Created by ChinaXing on 14-10-10.
 */
public class ChinaAgent {
    public static void agentmain(String agentArgs, Instrumentation inst) {

        if (null == agentArgs || agentArgs.length() == 0) {
            help();
            return;
        }

        System.out.println("agent args : " + agentArgs);
        int whiteSpacePos = agentArgs.indexOf("|");
        String classNameMethod = agentArgs.substring(0, whiteSpacePos);
        String enhanceCode = agentArgs.substring(whiteSpacePos + 1).replace("$", " ");

        String className = classNameMethod.split("::")[0];
        String method = classNameMethod.split("::")[1];

        System.out.println("target Class is : " + className);
        System.out.println("target Method is : " + method);
        System.out.println("enhance Code is : " + enhanceCode);

        ChinaTransformer transformer = new ChinaTransformer(className, method, enhanceCode);
        inst.addTransformer(transformer, true);
        try {
            Class clazz = ClassLoader.getSystemClassLoader().loadClass(className);
            inst.retransformClasses(clazz);
        } catch (Exception e) {
            System.out.println("Error when reTransform : " + e.getMessage());
        }
    }

    public static void premain(String agentArgs, Instrumentation inst) {
        agentmain(agentArgs, inst);
    }

    private static void help() {
        System.out.println("arguments : className::methodName enhanceCode");
        System.out.println("eg. => ");
        System.out.println(" com.alibaba.otter.canal.sink.entry.EntryEventSink::sink|filterTransactionEntry$=$false;");
        System.out.println();
        System.out.println("where :");
        System.out.println("ClassName   :  com.alibaba.otter.canal.sink.entry.EntryEventSink");
        System.out.println("MethodName  :  sink");
        System.out.println("EnhanceCode :  filterTransactionEntry = false;");
    }
}
