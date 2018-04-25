package test.org.mnwani;

import org.mnwani.Logger;
import org.mnwani.LogReader;
import org.mnwani.LoggerFactory;

public class LoggerTest {
    public static void main(String[] args) {
        Logger infoLogger = LoggerFactory.getLogger(LoggerTest.class);
        infoLogger.info("abc");

        Logger warnLogger = LoggerFactory.getLogger(LoggerTest.class);
        warnLogger.warn("def");

        Logger errorLogger = LoggerFactory.getLogger(LoggerTest.class);
        errorLogger.error("jkl");

        infoLogger.info("ghi");

        infoLogger.info("{} {}", "123", "456");

        LogReader logReader = new LogReader();
        System.out.println(logReader.get().contains("jkl"));
        System.out.println(logReader.get().contains("def"));
        System.out.println(logReader.get().contains("123 456"));
        System.out.println(logReader.get().contains("ghi"));
        System.out.println(logReader.get().contains("abc"));

        warnLogger.warn("warning A");
        warnLogger.warn("warning B");
        errorLogger.error("error A");
        warnLogger.warn("warning C");

        System.out.println(logReader.get().contains("error A"));
        System.out.println(logReader.get().contains("warning C"));
        System.out.println(logReader.get().contains("warning B"));
        System.out.println(logReader.get().contains("warning A"));

        try {
            errorLogger.error("{} need one optional parameter per curly braces");
        } catch (Exception ex) {
            System.out.println("IllegalStateException expected: " + (ex.getClass() == IllegalStateException.class));
        }

        try {
            errorLogger.error("should not have optional parameters without curly brace pairs",
                            "replace string");
        } catch (Exception ex) {
            System.out.println("IllegalStateException expected: " + (ex.getClass() == IllegalStateException.class));
        }
    }

}
