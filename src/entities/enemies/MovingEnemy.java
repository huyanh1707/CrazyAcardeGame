package entities.enemies;

import constants.Director;
import entities.Player;

import java.util.ArrayList;
import java.util.List;

public class MovingEnemy {
    private MovingEnemy.IQ iq;
    private boolean wallPass;
    private boolean brickPass;
    private Player player;

    public MovingEnemy(MovingEnemy.IQ iq, boolean brickPass, boolean wallPass) {
        this.iq = iq;
        this.wallPass = wallPass;
        this.brickPass = brickPass;
        this.player = Player.getPlayer();
    }

    class Node {
        private int x;
        private int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private boolean movableNode(char[][] matrix, int x, int y) {
        return 0 <= x && x < matrix[0].length && 0 <= y && y < matrix.length && (' ' == matrix[y][x]
                || this.brickPass && '*' == matrix[y][x] || this.wallPass && '#' == matrix[y][x]);
    }

    private boolean validNode(char[][] matrix, int x, int y) {
        return 0 <= x && x < matrix[0].length && 0 <= y && y < matrix.length && '0' != matrix[y][x];
    }

    private List<Node> getNeighborNodes(char[][] matrix, Node node) {
        List<Node> neighbors = new ArrayList();
        if (this.validNode(matrix, node.x - 1, node.y)) {
            neighbors.add(new Node(node.x - 1, node.y));
        }

        if (this.validNode(matrix, node.x + 1, node.y)) {
            neighbors.add(new Node(node.x + 1, node.y));
        }

        if (this.validNode(matrix, node.x, node.y - 1)) {
            neighbors.add(new Node(node.x, node.y - 1));
        }

        if (this.validNode(matrix, node.x, node.y + 1)) {
            neighbors.add(new MovingEnemy.Node(node.x, node.y + 1));
        }

        return neighbors;
    }

    private boolean movableDirection(char[][] matrix, int x, int y, Director direction) {
        boolean move = false;
        switch(direction) {
            case UP:
                move = this.movableNode(matrix, x, y - 1);
                break;
            case DOWN:
                move = this.movableNode(matrix, x, y + 1);
                break;
            case LEFT:
                move = this.movableNode(matrix, x - 1, y);
                break;
            case RIGHT:
                move = this.movableNode(matrix, x + 1, y);
        }

        return move;
    }

    private Director pathFinding(char[][] matrix, int e_x, int e_y, int p_x, int p_y, MovingEnemy.IQ iq) {
        Director direction = null;
        char[][] checkingMap = new char[matrix.length][matrix[0].length];

        for(int i = 0; i < checkingMap.length; ++i) {
            for(int j = 0; j < checkingMap[0].length; ++j) {
                if (this.movableNode(matrix, j, i)) {
                    checkingMap[i][j] = '1';
                } else {
                    checkingMap[i][j] = '0';
                }
            }
        }

        int tracingRange = 0;
        if (iq == MovingEnemy.IQ.MEDIUM || iq == MovingEnemy.IQ.HIGH) {
            tracingRange = 25;
        }

        boolean pathExits = false;
        if (Math.abs((e_x - p_x) * (e_y - p_y)) < tracingRange && iq != MovingEnemy.IQ.LOW) {
            List<MovingEnemy.Node> queue = new ArrayList();
            queue.add(new MovingEnemy.Node(p_x, p_y));

            while(!queue.isEmpty()) {
                MovingEnemy.Node lastNode = (MovingEnemy.Node)queue.remove(0);
                if (lastNode.x - 1 == e_x && lastNode.y == e_y) {
                    direction = Director.RIGHT;
                    pathExits = true;
                    break;
                }

                if (lastNode.x + 1 == e_x && lastNode.y == e_y) {
                    direction = Director.LEFT;
                    pathExits = true;
                    break;
                }

                if (lastNode.x == e_x && lastNode.y - 1 == e_y) {
                    direction = Director.DOWN;
                    pathExits = true;
                    break;
                }

                if (lastNode.x == e_x && lastNode.y + 1 == e_y) {
                    direction = Director.UP;
                    pathExits = true;
                    break;
                }

                try {
                    checkingMap[lastNode.y][lastNode.x] = '0';
                } catch (ArrayIndexOutOfBoundsException var14) {
                }

                List<MovingEnemy.Node> neighbors = this.getNeighborNodes(checkingMap, lastNode);
                queue.addAll(neighbors);
            }
        }

        if (!pathExits) {
            direction = this.randomMoving(matrix, e_x, e_y);
        }

        return direction;
    }

    private Director randomMoving(char[][] matrix, int e_x, int e_y) {
        Director direction;
        do {
            direction = Director.dir[(int)(Math.random() * 4.0D)];
        } while(!this.movableDirection(matrix, e_x, e_y, direction));

        return direction;
    }

    public Director movingDirection(char[][] matrix, int e_x, int e_y) {
        Director direction = null;
        switch(this.iq) {
            case LOW:
                direction = this.randomMoving(matrix, e_x, e_y);
                break;
            case MEDIUM:
                direction = this.pathFinding(matrix, e_x, e_y, this.player.getX_node(), this.player.getY_node(), MovingEnemy.IQ.MEDIUM);
                break;
            case HIGH:
                direction = this.pathFinding(matrix, e_x, e_y, this.player.getX_node(), this.player.getX_node(), MovingEnemy.IQ.MEDIUM);
        }

        return direction;
    }

    public static enum IQ {
        LOW,
        MEDIUM,
        HIGH;

        private IQ() {
        }
    }
}
