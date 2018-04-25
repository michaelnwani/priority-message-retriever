package org.mnwani;

public class LogNode implements Comparable<LogNode> {
    public String name;
    public String logLevelName;
    public String message;
    public String threadName;
    public int level;
    public long creationTime;
    public LogNode newer;
    public LogNode older;

    public LogNode() {
        this.creationTime = System.currentTimeMillis();
        this.threadName = Thread.currentThread().getName();
    }

    @Override
    public int compareTo(LogNode otherNode) {
        if (this.level < otherNode.level) {
            return -1;
        } else if (this.level > otherNode.level) {
            return 1;
        }

        if (this.creationTime < otherNode.creationTime) {
            return -1;
        } else if (this.creationTime > otherNode.creationTime) {
            return 1;
        }

        return 0;
    }
}
