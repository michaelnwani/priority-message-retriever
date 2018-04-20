package main.org.mnwani;

public class LoggerQueue {
    LogNode oldestLowestLevel;
    LogNode newestHighestLevel;
    int size;
    int maxCapacity;

    LoggerQueue(int maxCapacity) {
        this.size = 0;
        this.maxCapacity = maxCapacity;
        setupNewestOldest();
    }

    private void setupNewestOldest() {
        oldestLowestLevel = new LogNode();
        newestHighestLevel = new LogNode();

        oldestLowestLevel.older = null;
        oldestLowestLevel.newer = newestHighestLevel;

        newestHighestLevel.older = oldestLowestLevel;
        newestHighestLevel.newer = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(LogNode logNode) {
        if (size == maxCapacity) {
            // TODO: throw exception
        }
        LogNode newestHighestLevelNode = newestHighestLevel.older;
        // add to queue
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

    public LogNode remove() {
        if (size == 0) {
            // TODO: throw exception or return null
        }
        size--;
        LogNode newestHighestLevelNode = newestHighestLevel.older;
        LogNode temp = newestHighestLevelNode.older;

        newestHighestLevel.older = temp;
        temp.newer = newestHighestLevel;
        return newestHighestLevelNode;
    }
}
