package main.org.mnwani;

public class Solution {
    static void println(Object o) {
        System.out.println(o);
    }

    public static void main(String[] args) {
        Class<?> clazz = Logger.class;
        println(clazz.getName());

        Logger infoLogger = LoggerFactory.getLogger(Solution.class);
        infoLogger.info("abc");

        Logger warnLogger = LoggerFactory.getLogger(Solution.class);
        warnLogger.warn("def");

        Logger errorLogger = LoggerFactory.getLogger(Solution.class);
        errorLogger.error("jkf");

        infoLogger.info("ghi");

        infoLogger.info("{} {}", "123", "456");

        LogReader logReader = new LogReader();
        println(logReader.get());
        println(logReader.get());
        println(logReader.get());
        println(logReader.get());
        println(logReader.get());
    }
}
