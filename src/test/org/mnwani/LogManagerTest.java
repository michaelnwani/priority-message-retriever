package test.org.mnwani;

import main.org.mnwani.LogManager;

public class LogManagerTest {
    public static void main(String[] args) {
        LogManager logManager = LogManager.getInstance();
        logManager.setLoggerQueueMaxCapacity(5);

        System.out.println(logManager.getLog() == null);
    }
}
