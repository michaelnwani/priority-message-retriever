package main.org.mnwani;

public class Logger {
    private String name;

    private static int LOG_LEVEL_INFO = 1;
    private static int LOG_LEVEL_WARN = 2;
    private static int LOG_LEVEL_ERROR = 3;

    Logger(String name) {
        this.name = name;
    }

    private void log(int level,
                     String message) {
        LogManager.getInstance().addLog(name, level, message);
    }

    public void info(String message) {
        log(LOG_LEVEL_INFO, message);
    }

    public void warn(String message) {
        log(LOG_LEVEL_WARN, message);
    }

    public void error(String message) {
        log(LOG_LEVEL_ERROR, message);
    }
}
