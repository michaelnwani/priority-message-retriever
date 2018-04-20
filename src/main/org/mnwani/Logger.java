package main.org.mnwani;

import java.util.HashMap;

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

    public void info(String message, String... messageParams) {
        log(LOG_LEVEL_INFO, message, messageParams);
    }

    public void warn(String message, String... messageParams) {
        log(LOG_LEVEL_WARN, message, messageParams);
    }

    public void error(String message, String... messageParams) {
        log(LOG_LEVEL_ERROR, message, messageParams);
    }
}
