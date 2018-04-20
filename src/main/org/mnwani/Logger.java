package main.org.mnwani;

public class Logger {
    private String name;

    private static int LOG_LEVEL_INFO = 1;
    private static int LOG_LEVEL_WARN = 2;
    private static int LOG_LEVEL_ERROR = 3;

    Logger(String name) {
        this.name = name;
    }

    private void log(int level, String message, String... messageParams) {
        LogManager.getInstance().addLog(name, level, message, messageParams);
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
