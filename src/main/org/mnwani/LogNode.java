package main.org.mnwani;

public class LogNode implements Comparable<LogNode> {
    String name;
    String message;
    int level;
    long creationTime;
    LogNode newer;
    LogNode older;

    LogNode() {
        this.creationTime = System.currentTimeMillis();
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
