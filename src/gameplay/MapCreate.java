package gameplay;

import entities.block.Brick;
import entities.block.Grass;
import entities.block.Wall;
import entities.enemies.*;
import entities.player.Player;
import entities.player.Player2;
import entities.powerup.Portal;
import entities.powerup.PowerupBombs;
import entities.powerup.PowerupFlames;
import entities.powerup.PowerupSpeed;
import gamelogic.KeyController;
import gamelogic.GameLoop;
import entities.*;
import gamelogic.MultiPlayerGameLoop;
import graphics.Parameter;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class MapCreate {
    static Canvas canvas;
    public static GraphicsContext graphicsContext;
    static Player player;
    static Player2 player2;

    public static int mapWidth;
    public static int mapHeight;
    public static int mapLevel;
    public static int CANVAS_WIDTH;
    public static int CANVAS_HEIGHT;
    public static boolean pause1 = false;
    public static boolean pause2 = false;
    public static char[][] myMap;
    public static char[][] mapMatrix;

    public static int currentLevel = 1;
    public static int gameScore = 0;

    private static final List<Entity> boardLayer = new ArrayList<>();
    private static final List<Entity> topLayer = new ArrayList<>();
    private static final List<Entity> midLayer = new ArrayList<>();
    private static final List<Enemy> enemyLayer = new ArrayList<>();

    public static Label score;
    public static Label life;
    public static Label bombs;
    public static Label range;
    public static Label speed;
    public static Label levels;
    public static Label enemies;
    public static ImageView loseImage;

    public static void initGame(Pane root, Scene scene) {
        pause1 = false;
        canvas = new Canvas();
        initLabel();
        loseImage = new ImageView();
        root.getChildren().addAll(canvas, score, life, bombs, levels, enemies, range, speed, loseImage);
        graphicsContext = canvas.getGraphicsContext2D();
        createLevel(currentLevel);
        GameLoop.start(graphicsContext);
        KeyController.setOnKeys(scene);
    }

    public static void initMultiPlayerGame(Pane root, Scene scene) {
        pause2 = false;
        canvas = new Canvas();
        root.getChildren().addAll(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        createMultiPlayerLevel();
        MultiPlayerGameLoop.multiplayerStart(graphicsContext);
        KeyController.setOnKeys(scene);
    }

    public static void createLevel(int level) {
        clearMap();
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

    public static void createMultiPlayerLevel() {
        clearMap();
        loadMapFile("/levels/MultiPlayerMap.txt");
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                char c = myMap[i][j];
                addEntity(c, j * Parameter.SCALED_SIZE, i * Parameter.SCALED_SIZE);
            }
        }
        canvas.setHeight(CANVAS_HEIGHT);
        canvas.setWidth(CANVAS_WIDTH);
    }

    public static void removeEntity() {
        for (int i = 0; i < midLayer.size(); i++) {
            if (midLayer.get(i).isRemoved()) {
                midLayer.remove(i);
                --i;
            }
        }

        for (int i = 0; i < topLayer.size(); i++) {
            if (topLayer.get(i).isRemoved()) {
                topLayer.remove(i);
                --i;
            }
        }
        for (int i = 0; i < enemyLayer.size(); i++) {
            if (enemyLayer.get(i).isRemoved()) {
                gameScore += enemyLayer.get(i).getScore();
                enemyLayer.remove(i);
                --i;
            }
        }
    }

    public static void nextLevel() {
        if (currentLevel <= 5) {
            currentLevel += 1;
        } else {
            currentLevel = 1;
        }

        createLevel(currentLevel);
        player.resetBombList();
    }

    public static void resetLevel() {
        currentLevel = 1;
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

    public static List<Enemy> getEnemyLayer() {
        return enemyLayer;
    }


    public static void addEntity(char c, int x, int y) {
        switch (c) {
            // background and Player
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
            case 'q':
                boardLayer.add(new Grass(x, y));
                player2 = Player2.setPlayer(x, y, false);
                break;
            //enemies
            case '1':
                boardLayer.add(new Grass(x, y));
                enemyLayer.add(new Balloon(x, y));
                break;
            case '2':
                boardLayer.add(new Grass(x, y));
                enemyLayer.add(new Oneal(x, y));
                break;
            case '3':
                boardLayer.add(new Grass(x, y));
                enemyLayer.add(new Doll(x, y));
                break;
            case '4':
                boardLayer.add(new Grass(x, y));
                enemyLayer.add(new Kondoria(x, y));
                break;
            // Powerup item
            case 'x':
                boardLayer.add(new Grass(x, y));
                midLayer.add(new Portal(x, y));
                topLayer.add(new Brick(x, y));
                break;
            case 'b':
                boardLayer.add(new Grass(x, y));
                midLayer.add(new PowerupBombs(x,y));
                topLayer.add(new Brick(x, y));
                break;
            case 'f':
                boardLayer.add(new Grass(x, y));
                midLayer.add(new PowerupFlames(x,y));
                topLayer.add(new Brick(x, y));
                break;
            case 's':
                boardLayer.add(new Grass(x, y));
                midLayer.add(new PowerupSpeed(x,y));
                topLayer.add(new Brick(x, y));
                break;
        }
    }

    public static void loadMapFile(String filePath) {
        try {
            URL fileMapPath = MapCreate.class.getResource(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(fileMapPath).openStream()));
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

    public static void clearMap() {
        graphicsContext.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        enemyLayer.clear();
        topLayer.clear();
        midLayer.clear();
        boardLayer.clear();
    }

    public static Entity getFixedEntityAt(int x, int y) {
        for (Entity entity : boardLayer) {
            if (entity instanceof Wall && entity.getX_pos() == x && entity.getY_pos() == y) {
                return entity;
            }
        }
        for (Entity entity : topLayer) {
            if (entity instanceof Brick && entity.getX_pos() == x && entity.getY_pos() == y) {
                return entity;
            }
        }
        return null;
    }

    private static void initLabel() {
        levels = new Label("Level");
        score = new Label("Score");
        life = new Label("Life");
        bombs = new Label("Bomb");
        range = new Label("Range");
        speed = new Label("Speed");
        enemies = new Label("Enemies");
        levels.setFont(new Font("Berlin Sans FB", 17));
        score.setFont(new Font("Berlin Sans FB", 17));
        life.setFont(new Font("Berlin Sans FB", 17));
        bombs.setFont(new Font("Berlin Sans FB", 17));
        range.setFont(new Font("Berlin Sans FB", 17));
        speed.setFont(new Font("Berlin Sans FB", 17));
        enemies.setFont(new Font("Berlin Sans FB", 17));
        levels.setLayoutX(885); levels.setLayoutY(130);
        score.setLayoutX(885); score.setLayoutY(175);
        life.setLayoutX(885); life.setLayoutY(220);
        bombs.setLayoutX(885); bombs.setLayoutY(265);
        range.setLayoutX(885); range.setLayoutY(310);
        speed.setLayoutX(885); speed.setLayoutY(355);
        enemies.setLayoutX(885); enemies.setLayoutY(400);
    }

    public static void updateLabel() {
        levels.setText("Level: " + currentLevel);
        life.setText("Life: " + Player.getPlayer().getLifeCount());
        bombs.setText("Bomb: " + Player.getPlayer().getRemainBombs());
        score.setText("Score: " + gameScore);
        speed.setText("Speed: " + Player.getPlayer().getSpeed());
        range.setText("Range: " + Player.getPlayer().getBombRadius());
        enemies.setText("Left: " + getEnemyLayer().size());
    }

    public static void LoseEffect() {
//        MapCreate.pause1 = true;
        Image image = new Image("/gameview/lose.png");
        MapCreate.loseImage.setImage(image);
        loseImage.setLayoutX(300);
        loseImage.setLayoutY(280);
    }
}
