package test.org.mnwani;

import main.org.mnwani.Logger;
import main.org.mnwani.LoggerFactory;

public class LoggerFactoryTest {
    public static void main(String[] args) {
        Logger logger1 = LoggerFactory.getLogger(LoggerFactoryTest.class);
        Logger logger2 = LoggerFactory.getLogger(LoggerFactoryTest.class);
        Logger logger3 = LoggerFactory.getLogger(LoggerFactoryTest.class);

        System.out.println(LoggerFactory.loggerMap.size() == 1);
    }
}
