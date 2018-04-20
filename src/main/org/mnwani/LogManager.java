package main.org.mnwani;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LogManager {
    private static LoggerQueue loggerQueue;
    private static LogManager instance;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Prevents initialization
     */
    private LogManager() {
    }

    void addLog(String name,
                int level,
                String message) {
        LogNode logNode = new LogNode();
        logNode.name = name;
        logNode.message = message;
        logNode.level = level;
        loggerQueue.add(logNode);
    }

    String getLog() {
        LogNode logNode = loggerQueue.remove();
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(logNode.creationTime),
                                                         ZoneId.systemDefault());
        return dateTime.format(formatter) + " " +
                logNode.name + " " +
                logNode.level + ": " +
                logNode.message;
    }

    public static synchronized LogManager getInstance() {
        if (instance == null) {
            instance = new LogManager();
            loggerQueue = new LoggerQueue(100);
        }
        return instance;
    }
}
