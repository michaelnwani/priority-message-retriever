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
                String logLevelName,
                String message,
                String... messageParams) {

        int msgParamsIdx = 0;
        LogNode logNode = new LogNode();
        logNode.name = name;
        logNode.logLevelName = logLevelName;

        StringBuilder stringBuilder = new StringBuilder(message.length());
        for (int i = 0; i < message.length(); i++) {
            if (i < message.length()-1 && message.charAt(i) == '{' && message.charAt(i+1) == '}') {
                if (msgParamsIdx == messageParams.length) {
                    throw new IllegalArgumentException("Number of parentheses and optional params must match");
                }

                stringBuilder.append(messageParams[msgParamsIdx++]);
                i++;
                continue;
            }
            stringBuilder.append(message.charAt(i));
        }

        logNode.message = stringBuilder.toString();
        logNode.level = level;
        loggerQueue.add(logNode);
    }

    String getLog() {
        LogNode logNode = loggerQueue.remove();
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(logNode.creationTime),
                                                         ZoneId.systemDefault());
        return dateTime.format(formatter) + " " +
                "[" + logNode.threadName + "] " +
                logNode.name + " " +
                logNode.logLevelName + ": " +
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
