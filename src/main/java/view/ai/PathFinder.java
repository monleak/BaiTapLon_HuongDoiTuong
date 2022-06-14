package view.ai;

import states.PlayState;
import view.entity.Entity;
import view.main.GamePanel;
import view.title.TileMapObj;
import java.util.ArrayList;

/**
 * Tìm đường đi ngắn nhất trên bản đồ.
 *
 */
public class PathFinder {
    private final GamePanel gp;
    private PlayState ps;
    private Node[][] node;
    private final ArrayList<Node> openList;
    private final ArrayList<Node> pathList;
    private Node startNode, goalNode, currentNode;

    boolean goalReached = false;
    int step = 0;
    int startX, startY, goalX, goalY;
    boolean isInit;

    public PathFinder(GamePanel gp, PlayState ps) {
        this.gp = gp;
        this.ps = ps;
        initialNodes();

        openList = new ArrayList<>();
        pathList = new ArrayList<>();
    }

    private void initialNodes () {
        node = new Node[gp.maxWorldRow][gp.maxWorldCol];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            node[row][col] = new Node(row, col);

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    private void resetNodes () {
        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            node[row][col].open = false;
            node[row][col].checked = false;
            node[row][col].solid = false;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }

        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    private void getCost(Node node) {
        int xDistance = Math.abs(node.column * gp.titleSize - startNode.column * gp.titleSize);
        int yDistance = Math.abs(node.row * gp.titleSize - - startNode.row * gp.titleSize);
        node.gCost = xDistance + yDistance;

        xDistance = Math.abs(node.column * gp.titleSize - goalNode.column * gp.titleSize);
        yDistance = Math.abs(node.row * gp.titleSize - goalNode.row * gp.titleSize);
        node.hCost = xDistance + yDistance;

        node.fCost = node.gCost + node.hCost;
    }

    private void trackThePath () {
        Node current = goalNode;

        while (current != startNode) {
            pathList.add(0, current);
            current = current.parent;
        }
    }

    private void openNode (Node node) {
        if (!node.open && !node.checked && !node.solid) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    /**
     * Đặt các giá trị start và goal.
     * Gọi 1 lần trước khi dùng serach()
     *
     * @param startX
     * @param startY
     * @param goalX
     * @param goalY
     * @param entity
     */
    public void setNodes (int startX, int startY, int goalX, int goalY, Entity entity) {
        isInit = true;
        resetNodes();

        this.startX = startX;
        this.startY = startY;
        this.goalX = goalX;
        this.goalY = goalY;

        int startRow = startX / gp.titleSize;
        int startCol = startY / gp.titleSize;

        int goalRow = goalX / gp.titleSize;
        int goalCol = goalY / gp.titleSize;

        startNode = node[startRow][startCol];
        currentNode = startNode;
        goalNode = node[goalRow][goalCol];
        openList.add(currentNode);

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

            if (TileMapObj.event_blocks[row * TileMapObj.height + col] != null)  {
                node[col][row].solid = true;
            }

            getCost(node[row][col]);

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }

        }
    }

    /**
     * Tìm đường đi ngắn nhất từ startNode đến goalNode.
     * Thuật toán: A* ( khá giống dijkstra nhưng ưu tiên tìm những nút có h(x) thấp hơn )
     *
     * Sử dụng:
     *  - Kết tập trong đối tượng cần tìm đường đi.
     *  - Cần gọi setNode() để set điểm đầu và đich của đường đi trước.
     *  - Gọi pathFindet.search() trong parent.update().
     *
     * @return
     */
    public boolean search () {
        if (isInit)
            while (!goalReached && step < 1000) {
                int col = currentNode.column;
                int row = currentNode.row;

                currentNode.checked = true;
                openList.remove(currentNode);

                if (col - 1 >= 0 )
                    openNode(node[col-1][row]);
                if (col + 1 < gp.maxWorldCol)
                    openNode(node[col+1][row]);
                if (row + 1 < gp.maxWorldRow)
                    openNode(node[col][row+1]);
                if (row - 1 >= 0 )
                    openNode(node[col][row - 1]);

                int bestNodeIndex = 0;
                int bestNodeFCost = 999;
                for (int i = 0; i < openList.size(); i++ ) {
                    if(openList.get(i).fCost < bestNodeFCost) {
                        bestNodeIndex = i;
                        bestNodeFCost = openList.get(i).fCost;
                    }
                    else if (openList.get(i).fCost == bestNodeFCost) {
                        if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                            bestNodeIndex = i;
                        }
                    }
                }

                if (openList.size() == 0) break;

                currentNode = openList.get(bestNodeIndex);

                if (currentNode == goalNode) {
                    goalReached = true;
                    trackThePath();
                }
                step++;
            }
        return goalReached;
    }

    /**
     * Trả về đường đi ngắn nhất từ startNode đến goalNode.
     * Sử dụng: sau khi gọi setNode() và serach().
     *
     * @return
     */
    public ArrayList<Node> getPathList() {
        return pathList;
    }
}
