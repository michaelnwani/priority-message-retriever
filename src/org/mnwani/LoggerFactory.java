package org.mnwani;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Static factory used to create Logger instances according to their enclosing class.
 * Only one logger instance will be returned per class.
 * @author Michael Nwani
 */
public class LoggerFactory {
    public static ConcurrentMap<String, Logger> loggerMap = new ConcurrentHashMap<>();

    private static Logger getLogger(String name) {
        Logger logger = loggerMap.get(name);
        if (logger != null) {
            return logger;
        } else {
            Logger newInstance = new Logger(name);
            Logger oldInstance = loggerMap.putIfAbsent(name, newInstance);
            return oldInstance == null ? newInstance : oldInstance;
        }
    }

    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }
}
