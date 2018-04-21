package test.org.mnwani;

import main.org.mnwani.LogNode;
import main.org.mnwani.LoggerQueue;

public class LoggerQueueTest {
    public static void main(String[] args) throws InterruptedException {
        LoggerQueue loggerQueue = LoggerQueue.getInstance(5);

        LogNode nodeA = new LogNode();
        nodeA.level = 1;

        Thread.sleep(1000);

        LogNode nodeB = new LogNode();
        nodeB.level = 1;

        Thread.sleep(1000);

        LogNode nodeC = new LogNode();
        nodeC.level = 1;

        System.out.println(nodeA.compareTo(nodeB) < 0);
        System.out.println(nodeA.compareTo(nodeC) < 0);
        System.out.println(nodeB.compareTo(nodeC) < 0);
        System.out.println(nodeC.compareTo(nodeA) > 0);
        System.out.println(nodeC.compareTo(nodeB) > 0);

        try {
            loggerQueue.remove();
        } catch (Exception ex) {
            System.out.println("IllegalStateException expected: " + (ex.getClass() == IllegalStateException.class));
        }

        System.out.println(loggerQueue.isEmpty());

        loggerQueue.add(nodeA);
        loggerQueue.add(nodeB);
        loggerQueue.add(nodeC);

        System.out.println(loggerQueue.remove() == nodeC);
        System.out.println(loggerQueue.remove() == nodeB);
        System.out.println(loggerQueue.remove() == nodeA);

        LogNode nodeD = new LogNode();
        LogNode nodeE = new LogNode();

        loggerQueue.add(nodeA);
        loggerQueue.add(nodeB);
        loggerQueue.add(nodeC);
        loggerQueue.add(nodeD);
        loggerQueue.add(nodeE);

        System.out.println(loggerQueue.isAtCapacity());

        LogNode nodeF = new LogNode();
        try {
            loggerQueue.add(nodeF);
        } catch (Exception ex) {
            System.out.println("IllegalStateException expected: " + (ex.getClass() == IllegalStateException.class));
        }

        try {
            loggerQueue.setMaxCapacity(3);
        } catch (Exception ex) {
            System.out.println("IllegalArgumentException expected: " + (ex.getClass() == IllegalArgumentException.class));
        }

        try {
            loggerQueue.setMaxCapacity(0);
        } catch (Exception ex) {
            System.out.println("IllegalArgumentException expected: " + (ex.getClass() == IllegalArgumentException.class));
        }
    }
}
