package main.org.mnwani;

public class LogReader {
    public String get() {
        return LogManager.getInstance().getLog();
    }
}
