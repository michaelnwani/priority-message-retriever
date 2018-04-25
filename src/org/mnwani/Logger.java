package org.mnwani;

import java.util.HashMap;

/**
 * Custom logging implementation which should be instantiated via {@link LoggerFactory}'s getLogger method
 * @author Michael Nwani
 */
public class Logger {
    private String name;
    private static HashMap<Integer, String> logLevelMap;
    private static final int LOG_LEVEL_INFO = 1;
    private static final int LOG_LEVEL_WARN = 2;
    private static final int LOG_LEVEL_ERROR = 3;

    private static final String LOG_LEVEL_NAME_INFO = "INFO";
    private static final String LOG_LEVEL_NAME_WARN = "WARN";
    private static final String LOG_LEVEL_NAME_ERROR = "ERROR";

    static {
        logLevelMap = new HashMap<>();
        logLevelMap.putIfAbsent(LOG_LEVEL_INFO, LOG_LEVEL_NAME_INFO);
        logLevelMap.putIfAbsent(LOG_LEVEL_WARN, LOG_LEVEL_NAME_WARN);
        logLevelMap.putIfAbsent(LOG_LEVEL_ERROR, LOG_LEVEL_NAME_ERROR);
    }

    Logger(String name) {
        this.name = name;
    }

    private void log(int level, String message, String... messageParams) {
        LogManager.getInstance().addLog(name,
                                        level,
                                        logLevelMap.get(level),
                                        message,
                                        messageParams);
    }

    /**
     * Attempts to add an INFO-priority level log to the queue
     * @param message a log message
     * @param messageParams optional log arguments to match curly braces
     */
    public void info(String message, String... messageParams) {
        log(LOG_LEVEL_INFO, message, messageParams);
    }

    /**
     * Attempts to add an WARN-priority level log to the queue
     * @param message a log message
     * @param messageParams optional log arguments to match curly braces
     */
    public void warn(String message, String... messageParams) {
        log(LOG_LEVEL_WARN, message, messageParams);
    }

    /**
     * Attempts to add an ERROR-priority level log to the queue
     * @param message a log message
     * @param messageParams optional log arguments to match curly braces
     */
    public void error(String message, String... messageParams) {
        log(LOG_LEVEL_ERROR, message, messageParams);
    }
}
