package main.org.mnwani;

/**
 * A singleton class which stores and fetches {@link LogNode} log messages
 * in order of highest priority -> lowest priority
 * Priority is determined by log level (e.g. ERROR > WARN > INFO) followed by earliest insertion time
 * @author Michael Nwani
 */
public class LoggerQueue {
    private static LoggerQueue instance;
    private static final LogNode oldestLowestLevel = new LogNode();
    private static final LogNode newestHighestLevel = new LogNode();
    private int size;
    private int maxCapacity;

    LoggerQueue(int maxCapacity) {
        this.size = 0;
        this.maxCapacity = maxCapacity;
        setupNewestOldest();
    }

    private synchronized void setupNewestOldest() {
        oldestLowestLevel.older = null;
        oldestLowestLevel.newer = newestHighestLevel;

        newestHighestLevel.older = oldestLowestLevel;
        newestHighestLevel.newer = null;
    }

    public synchronized void setMaxCapacity(int maxCapacity) {
        if (this.size > maxCapacity) {
            throw new IllegalArgumentException("current queue size " + size +
                                               " is greater than the attempted max capacity of " +
                                               maxCapacity);
        } else if (maxCapacity < 1) {
            throw new IllegalArgumentException("max capacity must be >= 1");
        }
        this.maxCapacity = maxCapacity;
    }

    /**
     *
     * @return whether or not the queue is currently at max capacity
     */
    public synchronized boolean isAtCapacity() {
        return size == maxCapacity;
    }

    /**
     *
     * @return whether or not the queue is currently empty
     */
    public synchronized boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds a message to the queue in the form of a {@link LogNode}
     * If the node to be added is of a higher priority than the current highest-priority node,
     * it becomes the new highest-priority node in the list, while the previous node is made second-highest priority.
     * Otherwise, the node is walked up from the end of the list (lowest-priority node) to the front of the list,
     * until the queue finds its proper point of insertion. This is O(n) in the worst case.
     * @param logNode represents the log message to be added
     */
    public synchronized void add(LogNode logNode) {
        if (size == maxCapacity) {
            throw new IllegalStateException("add() can't be called while queue is full");
        }

        LogNode newestHighestLevelNode = newestHighestLevel.older;
        if (logNode.compareTo(newestHighestLevelNode) >= 0) {
            newestHighestLevelNode.newer = logNode;
            logNode.older = newestHighestLevelNode;
            logNode.newer = newestHighestLevel;
            newestHighestLevel.older = logNode;
        } else {
            LogNode oldestLowestLevelNode = oldestLowestLevel.newer;

            if (logNode.compareTo(oldestLowestLevelNode) >= 0) {
                LogNode temp = oldestLowestLevelNode.newer;
                // can be optimized to walk forward from the oldest node
                // while walking backwards from the newest node to reduce average time complexity
                while (logNode.compareTo(temp) >= 0) {
                    temp = temp.newer;
                }
                oldestLowestLevelNode = temp.older;
                logNode.newer = oldestLowestLevelNode.newer;
                oldestLowestLevelNode.newer.older = logNode;
                oldestLowestLevelNode.newer = logNode;
                logNode.older = oldestLowestLevelNode;
            } else {
                logNode.older = oldestLowestLevel;
                logNode.newer = oldestLowestLevelNode;
                oldestLowestLevelNode.older = logNode;
            }
        }
        size++;
    }

    /**
     *
     * @return the highest-priority {@link LogNode} in the queue
     * @throws IllegalStateException if the queue is currently empty
     */
    public synchronized LogNode remove() {
        if (size == 0) {
            throw new IllegalStateException("remove() can't be called while queue is empty");
        }
        size--;
        LogNode newestHighestLevelNode = newestHighestLevel.older;
        LogNode temp = newestHighestLevelNode.older;

        newestHighestLevel.older = temp;
        temp.newer = newestHighestLevel;
        return newestHighestLevelNode;
    }

    /**
     *
     * @param maxCapacity the max size of the queue
     * @return the LoggerQueue singleton instance
     */
    public static LoggerQueue getInstance(int maxCapacity) {
        if (instance == null) {
            instance = new LoggerQueue(maxCapacity);
        }
        return instance;
    }
}
