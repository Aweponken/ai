public class Node {
    public int x;
    public int y;
    public int gScore;
    public int fScore;
    public char value;
    public Node parent;
    public String parentDirection;

    public Node(){}

    public Node(int x, int y, char value) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.gScore = Integer.MAX_VALUE;
        this.fScore = Integer.MAX_VALUE;
    }
}