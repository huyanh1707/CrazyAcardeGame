package gameplay;

import constants.Parameter;
import entities.block.Brick;
import entities.block.Grass;
import entities.block.Wall;
import gamelogic.KeyController;
import gamelogic.GameLoop;
import entities.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MapCreate {
    static Canvas canvas;
    static GraphicsContext graphicsContext;
    static Player player;

    public static char[][] myMap;
    public static char[][] mapMatrix;
    private static final List<Entity> boardLayer = new ArrayList<>();
    private static final List<Entity> topLayer = new ArrayList<>();
    private static final List<Entity> midLayer = new ArrayList<>();

    public static int mapWidth;
    public static int mapHeight;
    public static int mapLevel;
    public static int CANVAS_WIDTH;
    public static int CANVAS_HEIGHT;

    public static void initGame(Pane root, Scene scene) {
        canvas = new Canvas();
        root.getChildren().addAll(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        createLevel(1);
        GameLoop.start(graphicsContext);
        KeyController.setOnKeys(scene);
    }

    public static void createLevel(int level) {
        loadMapFile("/levels/Level" + level + ".txt");
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                char c = myMap[i][j];
                addEntity(c, j * Parameter.SCALED_SIZE, i * Parameter.SCALED_SIZE);
            }
        }
        canvas.setHeight(CANVAS_HEIGHT);
        canvas.setWidth(CANVAS_WIDTH);
    }

    public static List<Entity> getBoardLayer() {
        return boardLayer;
    }
    public static List<Entity> getMidLayer() {
        return midLayer;
    }
    public static List<Entity> getTopLayer() {
        return topLayer;
    }


    public static void addEntity(char c, int x, int y) {
        switch (c) {
            case '#':
                boardLayer.add(new Wall(x, y));
                break;
            case '*':
                boardLayer.add(new Grass(x, y));
                topLayer.add(new Brick(x, y));
                break;
            case ' ':
                boardLayer.add(new Grass(x, y));
                break;
            case 'p':
                boardLayer.add(new Grass(x, y));
                player = Player.setPlayer(x, y, false);
                break;
        }
    }

    public static void loadMapFile(String filePath) {
        try {
            URL fileMapPath = MapCreate.class.getResource(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileMapPath.openStream()));
            String data = reader.readLine();
            StringTokenizer tokens = new StringTokenizer(data);
            mapLevel = Integer.parseInt(tokens.nextToken());
            mapHeight = Integer.parseInt(tokens.nextToken());
            mapWidth = Integer.parseInt(tokens.nextToken());
            CANVAS_HEIGHT = mapHeight * Parameter.BLOCK_SIZE;
            CANVAS_WIDTH = mapWidth * Parameter.BLOCK_SIZE;
            myMap = new char[mapHeight][mapWidth];
            mapMatrix = new char[mapHeight][mapWidth];
            for (int i = 0; i < mapHeight; i++) {
                char[] temp = reader.readLine().toCharArray();
                for (int j = 0; j < mapWidth; j++) {
                    myMap[i][j] = temp[j];
                    if ('#' == temp[j] || '*' == temp[j] || 'x' == temp[j]
                            || 'b' == temp[j] || 'f' == temp[j] || 's' == temp[j]
                            || 'w' == temp[j] || 'm' == temp[j] || 'n' == temp[j]) {
                        mapMatrix[i][j] = temp[j];
                    } else {
                        mapMatrix[i][j] = ' ';
                    }
                }
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}
