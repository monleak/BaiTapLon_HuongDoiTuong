package view.ai;

/**
 * Node là một ô trong bản đồ, dùng để tìm đường đi (trong class PathFinder).
 *
 * @see view.ai.PathFinder
 */
public class Node {
    public int row;
    public int column;

    public Node parent = null;
    public int gCost;
    public int hCost;
    public int fCost;
    public boolean solid;
    boolean open;
    boolean checked;

    public Node (int col, int row) {
        this.column = col;
        this.row = row;
    }

    @Override
    public String toString() {
        return "Node{" +
                "row=" + row +
                ", column=" + column +
//                ", parent=" + parent +
                ", gCost=" + gCost +
                ", hCost=" + hCost +
                ", fCost=" + fCost +
                ", solid=" + solid +
                ", open=" + open +
                ", checked=" + checked +
                '}';
    }
}
