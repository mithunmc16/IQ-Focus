package comp1110.ass2.gui;

import comp1110.ass2.FocusGame;
import comp1110.ass2.Orientation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.Scene;


import java.lang.reflect.Array;
import java.util.*;

import javafx.util.Duration;

import static comp1110.ass2.FocusGame.*;
import static javafx.scene.paint.Color.*;

import java.net.URI;

public class Board extends Application {


    private static String[] challenge = {
            "RRRBWBBRB",
            "RWWRRRWWW",
            "BGGWGGRWB",
            "WRRWRRGWW",
            "GWRGWWGGG",
            "GRWGRWWWW",
            "RGGRGGRRB",
            "GGGRGRBBB",
            "RGGGGRBGG",
            "BBBWRWGGG",
            "WBWWWWRWG",
            "BBGRWBRRB",
            "WWRGWWGGW",
            "WRRRRRWWW",
            "WWWWRWBBB",
            "GBBRRRBBG",
            "BRRBWRBBB",
            "GGGWGWGGB",
            "GWRGGWGGG",
            "WWRGWRWWR",
            "WWRGGWGGW",
            "RBGWBBGWR",
            "BGWBWRBBB",
            "BGRWWWWWW",
            "WBWWBWGGG",
            "WGBWGBWGB",
            "BBBGGGWWW",
            "GBBGWWGBB",
            "BWGGWGGWB",
            "WBWGGBWGW",
            "GWGGWBGGG",
            "BGGWGGGWB",
            "GWWGWBGGG",
            "GBGWWBWWG",
            "GWWBWWGBG",
            "BGBWWWWBW",
            "GGWWGGBWG",
            "WBWWWWGGG",
            "BBBBGGBGB",
            "BBBWWWBGB",
            "WGGBGGGBW",
            "GBGWWWBGB",
            "BWGWWWGWB",
            "BGBBGGWBB",
            "BGBBWBBGB",
            "WWGBBWWBW",
            "WBWGGBBGW",
            "BWWBWGBWW",
            "RRRRRRRRR",
            "RRRRRWRWW",
            "RWWWRWWWR",
            "BWBBWBBBB",
            "BWWBBBBWB",
            "WWBWWBWWB",
            "BWBBBBWWW",
            "BBBBWBBWB",
            "BBBBWBBBB",
            "BBBWWBWBB",
            "WBBWBWWBW",
            "BBBWWBBBB",
            "WGGWWGWWW",
            "GGGGWGWWG",
            "GGWWGGWWG",
            "WBBWWBWWW",
            "BBBBWWBBB",
            "WBBBWWBWW",
            "WWRWRWWWR",
            "RRWWRWWRR",
            "WWWRRWWRR",
            "WGGGGGGGW",
            "WGGGGGWWW",
            "GGGGGGGGG",
            "GGRBGRBBR",
            "BGBGBRGRR",
            "WBWWRWWGW",
            "GGBRRGBRG",
            "GWWWRWWWB",
            "BBRWBBGWB",
            "BGWWBGWWW",
            "GWBGGWWGG",
            "BGGBGBBGG",
            "GGRGGRRRR",
            "GWGGWRGGG",
            "RRWGRRGGR",
            "GRRGGGRRG",
            "WRWWRWGWG",
            "RGGRWGGRR",
            "GGGGGGRWR",
            "GGGRRRWRW",
            "GRGWRRRWG",
            "RBRRBBRRB",
            "RRBWBRWWR",
            "WBWWRWWBW",
            "WWWRBRRBR",
            "RWWRWBWWB",
            "RBRRBRRBR",
            "WWWWWWWWW",
            "RRBBBBGGB",
            "GGGBBGRBG",
            "GBGRRRBRB",
            "GBRGBRGGG",
            "GBWRBBRRG",
            "WWBWGWWWR",
            "BGGRWBBGG",
            "BGRBGRGGG",
            "BRWRRRWRB",
            "BBBRRBWRB",
            "BWRBBWWBB",
            "RBBRBBRRR",
            "RWWBWWBBR",
            "RBBRWBRRB",
            "BBRRRRBBR",
            "RBBBWWBWR",
            "BWRBWRBWR",
            "BBBWRWRRR",
            "BBBRWBWRB",
            "RWBWWWBWR",
            "WRBWRBWRB",
            "BWRBRWBWR",
            "BRBBRBBWB",
    };


    // FIXME Task 7: Implement a basic playable Focus Game in JavaFX that only allows pieces to be placed in valid places


    private static final int BOARD_WIDTH = 720;
    private static final int BOARD_HEIGHT = 463;
    private static final double SQUARE_WIDTH = 70;
    private static final double SQUARE_HEIGHT = 70;
    private static final double X_SHIFT = 80;
    private static final long ROTATION_THRESHOLD = 50; // Allow rotation every 50 ms

    private final Group root = new Group();
    private Group game = new Group();

    //
    private final Group controls = new Group();
    private final Slider difficulty = new Slider();
//


    private static final String URI_BASE = "assets/";
    private static final String[] placements = new String[10];

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Game");
        Scene scene = new Scene(root, 933, 700);


        // Creates the board
        generateBoard();

        for (ImageView i : makeChallenge(Challenge)) {
            root.getChildren().add(i);
        }
        ;
        // Generates the draggable pieces
        generatePieces();
        makeControls();

        root.getChildren().add(controls);

        scene.setRoot(root);
        scene.setFill(BEIGE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public double nearestX(double x) {
        double[] distances = new double[9];
        double[] xCoordinates = new double[9];
        for (int i = 0; i < 9; i++) {
            xCoordinates[i] = (42 + X_SHIFT + i * SQUARE_WIDTH);
            distances[i] = Math.abs(x - xCoordinates[i]);
        }
        double distance = (distances[0]);
        double X = 0;
        for (int j = 0; j < 9; j++)
            if (distance > (distances[j])) {
                distance = distances[j];
                X = xCoordinates[j];
            }
        if (X == 0) {
            return 42 + X_SHIFT;
        } else {
            return X;
        }

    }

    public double nearestY(double y) {
        double[] distances = new double[5];
        double[] yCoordinates = new double[5];
        for (int i = 0; i < 5; i++) {
            yCoordinates[i] = (87 + i * SQUARE_HEIGHT);
            distances[i] = Math.abs(y - yCoordinates[i]);
        }
        double distance = distances[0];
        double Y = 0;
        for (int j = 0; j < 5; j++) {
            if (distance > distances[j]) {
                distance = distances[j];
                Y = yCoordinates[j];
            }
        }
        if (Y == 0) {
            return 87;
        } else {
            return Y;
        }
    }


    // Generates images representing each shape using placement string
    public class Shapes extends ImageView {
        ImageView shape;
        char piece;
        double x, y;
        int orientation;


        public Shapes(char piece, double x, double y, int orientation) {

            {
                Image s1 = new Image(getClass().getResource(URI_BASE + piece + orientation + ".png").toString());

                this.setLayoutX(42 + X_SHIFT + x * SQUARE_WIDTH);
                this.setLayoutY(87 + y * SQUARE_HEIGHT);

                int WIDTH = 0;
                int HEIGHT = 0;
                if (piece == 'a' || piece == 'd' || piece == 'e' || piece == 'f' || piece == 'g' || piece == 'h') {
                    WIDTH = 3;
                } else if (piece == 'b' || piece == 'c' || piece == 'j') {
                    WIDTH = 4;
                } else {
                    WIDTH = 2;
                }
                if (piece == 'a' || piece == 'b' || piece == 'c' || piece == 'd' || piece == 'e' || piece == 'g'
                        || piece == 'i' || piece == 'j') {
                    HEIGHT = 2;
                } else if (piece == 'f') {
                    HEIGHT = 1;
                } else {
                    HEIGHT = 3;
                }
                switch (orientation % 2) {
                    case 0:
                        this.setFitWidth(WIDTH * SQUARE_WIDTH);
                        this.setFitHeight(HEIGHT * SQUARE_HEIGHT);
                        break;
                    case 1:
                        this.setFitWidth(HEIGHT * SQUARE_WIDTH);
                        this.setFitHeight(WIDTH * SQUARE_HEIGHT);
                }
                this.setImage(s1);
            }

        }

        public ImageView getShape() {
            return shape;
        }

        public ImageView setShape(char piece, double x, double y, int orientation) {
            Shapes newShape = new Shapes(piece, x, y, orientation);
            return newShape;
        }
    }

    class DraggableShapes extends Shapes {
        double homeX, homeY;
        double mouseX, mouseY;
        Image[] images = new Image[4];
        long lastRotationTime = System.currentTimeMillis();
        String placement;

        DraggableShapes(char piece, double x, double y, int orientation) {
            super(piece, x, y, orientation);
            homeX = this.getLayoutX();
            homeY = this.getLayoutY();

            for (int i = 0; i < 4; i++) {
                images[i] = new Image(getClass().getResource(URI_BASE + piece + i + ".png").toString());
            }

            this.setOnMousePressed(event -> {
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
            });
            this.toFront();

            this.setOnMouseDragged(event -> {
                double mouseMovedX = event.getSceneX() - mouseX;
                double mouseMovedY = event.getSceneY() - mouseY;

                this.setLayoutX(this.getLayoutX() + mouseMovedX);
                this.setLayoutY(this.getLayoutY() + mouseMovedY);

                mouseX = event.getSceneX();
                mouseY = event.getSceneY();

            });
            this.setOnScroll(event -> {
                if (System.currentTimeMillis() - lastRotationTime > ROTATION_THRESHOLD) {
                    lastRotationTime = System.currentTimeMillis();

                    this.orientation = (this.orientation + 1) % 4;
                    double setX = this.getLayoutX();
                    double setY = this.getLayoutY();
                    this.setImage(images[this.orientation]);

                    this.setLayoutX(setX);
                    this.setLayoutY(setY);

                    int WIDTH = 0;
                    int HEIGHT = 0;
                    if (piece == 'a' || piece == 'd' || piece == 'e' || piece == 'f' || piece == 'g' || piece == 'h') {
                        WIDTH = 3;
                    } else if (piece == 'b' || piece == 'c' || piece == 'j') {
                        WIDTH = 4;
                    } else {
                        WIDTH = 2;
                    }
                    if (piece == 'a' || piece == 'b' || piece == 'c' || piece == 'd' || piece == 'e' || piece == 'g'
                            || piece == 'i' || piece == 'j') {
                        HEIGHT = 2;
                    } else if (piece == 'f') {
                        HEIGHT = 1;
                    } else {
                        HEIGHT = 3;
                    }
                    switch (this.orientation % 2) {
                        case 0:
                            this.setFitWidth(WIDTH * SQUARE_WIDTH);
                            this.setFitHeight(HEIGHT * SQUARE_HEIGHT);
                            break;
                        case 1:
                            this.setFitWidth(HEIGHT * SQUARE_WIDTH);
                            this.setFitHeight(WIDTH * SQUARE_HEIGHT);
                    }
                    event.consume();
                }
            });
            this.setOnMouseReleased(event -> {
                int WIDTH = 0;
                int HEIGHT = 0;
                if (piece == 'a' || piece == 'd' || piece == 'e' || piece == 'f' || piece == 'g' || piece == 'h') {
                    WIDTH = 3;
                } else if (piece == 'b' || piece == 'c' || piece == 'j') {
                    WIDTH = 4;
                } else {
                    WIDTH = 2;
                }
                if (piece == 'a' || piece == 'b' || piece == 'c' || piece == 'd' || piece == 'e' || piece == 'g'
                        || piece == 'i' || piece == 'j') {
                    HEIGHT = 2;
                } else if (piece == 'f') {
                    HEIGHT = 1;
                } else {
                    HEIGHT = 3;
                }
                switch (this.orientation % 2) {
                    case 0:
                        this.setFitWidth(WIDTH * SQUARE_WIDTH);
                        this.setFitHeight(HEIGHT * SQUARE_HEIGHT);
                        break;
                    case 1:
                        this.setFitWidth(HEIGHT * SQUARE_WIDTH);
                        this.setFitHeight(WIDTH * SQUARE_HEIGHT);
                        break;
                }
                String xCoord = Integer.toString((int) (((nearestX(this.getLayoutX())) - (X_SHIFT + 42)) / SQUARE_WIDTH));
                String yCoord = Integer.toString((int) (((nearestY(this.getLayoutY())) - 87) / SQUARE_HEIGHT));

                this.placement = piece + xCoord + yCoord + this.orientation;
                System.out.println(this.placement);

                addPlacement(this.placement);

                List<String> list = new ArrayList<String>();
                for(String s : placements) {
                    if(s != null && s.length() > 0) {
                        list.add(s);
                    }}
                System.out.println(String.join("",list));
                if (isPlacementStringValid(String.join("",list))) {
                    if (this.getLayoutX() > 868 || this.getLayoutX() < 106) {
                        this.setLayoutX(homeX);
                        this.setLayoutY(homeY);
                        this.setFitWidth(SQUARE_WIDTH);
                        this.setFitHeight(SQUARE_HEIGHT);
                        removePlacement(this.placement);
                    }
                    if (this.getLayoutY() > BOARD_HEIGHT) {
                        this.setLayoutX(homeX);
                        this.setLayoutY(homeY);
                        this.setFitWidth(SQUARE_WIDTH);
                        this.setFitHeight(SQUARE_HEIGHT);
                        removePlacement(this.placement);
                    }
                    this.setLayoutX(nearestX(this.getLayoutX()));
                    this.setLayoutY(nearestY(this.getLayoutY()));

                } else {
                    this.setLayoutX(homeX);
                    this.setLayoutY(homeY);
                    this.setFitWidth(SQUARE_WIDTH);
                    this.setFitHeight(SQUARE_HEIGHT);
                    removePlacement(this.placement);
                }
                this.toFront();

                String placementString = String.join("",placements);
            });
        }

        ;
    }

    //
    Random rand = new Random();
    int i = rand.nextInt(challenge.length);
    String Challenge = challenge[i];


    // FIXME Task 8: Implement challenges (you may use challenges and assets provided for you in comp1110.ass2.gui.assets: sq-b.png, sq-g.png, sq-r.png & sq-w.png)

    ImageView[] makeChallenge(String Challenge) { // This block displays the challenge to be implemented for the game.

        String input = null;
        ImageView[] challengeArray = new ImageView[9];
        char[] clg = Challenge.toCharArray();

        for (int j = 0; j < clg.length; j++) {

            switch (clg[j]) {
                case 'B':
                    input = "sq-b";
                    break;
                case 'G':
                    input = "sq-g";
                    break;
                case 'R':
                    input = "sq-r";
                    break;
                case 'W':
                    input = "sq-w";

            }
            Image image = new Image(getClass().getResource(URI_BASE + input + ".png").toString()); //selecting image from the directory
            ImageView img = new ImageView(image);

            img.setOpacity(0.5); //setting the image to be opaque

            img.setFitHeight(SQUARE_HEIGHT);
            img.setFitWidth(SQUARE_WIDTH);

            // placing images in the order [0][1][2]
            //                             [3][4][5]
            //                             [6][7][8]
            // based on the challenge string eg. RRBWBRWGG

            if (j == 0) {

                img.setTranslateX(42 + X_SHIFT + SQUARE_WIDTH * 3);
                img.setTranslateY(87 + SQUARE_HEIGHT * 1);
            } else if (j == 1) {
                img.setTranslateX(42 + X_SHIFT + SQUARE_WIDTH * 4);
                img.setTranslateY(87 + SQUARE_HEIGHT * 1);
            } else if (j == 2) {
                img.setTranslateX(42 + X_SHIFT + SQUARE_WIDTH * 5);
                img.setTranslateY(87 + SQUARE_HEIGHT * 1);
            } else if (j == 3) {
                img.setTranslateX(42 + X_SHIFT + SQUARE_WIDTH * 3);
                img.setTranslateY(87 + SQUARE_HEIGHT * 2);
            } else if (j == 4) {
                img.setTranslateX(42 + X_SHIFT + SQUARE_WIDTH * 4);
                img.setTranslateY(87 + SQUARE_HEIGHT * 2);
            } else if (j == 5) {
                img.setTranslateX(42 + X_SHIFT + SQUARE_WIDTH * 5);
                img.setTranslateY(87 + SQUARE_HEIGHT * 2);
            } else if (j == 6) {
                img.setTranslateX(42 + X_SHIFT + SQUARE_WIDTH * 3);
                img.setTranslateY(87 + SQUARE_HEIGHT * 3);
            } else if (j == 7) {
                img.setTranslateX(42 + X_SHIFT + SQUARE_WIDTH * 4);
                img.setTranslateY(87 + SQUARE_HEIGHT * 3);
            } else {
                img.setTranslateX(42 + X_SHIFT + SQUARE_WIDTH * 5);
                img.setTranslateY(87 + SQUARE_HEIGHT * 3);
            }
            img.setImage(image);
            challengeArray[j] = img;
        }
        return challengeArray;
    }

    // end of task 8
    private void restartGame() {
        try {
            Random rand = new Random();
            int i = rand.nextInt((int) challenge.length / 3);
            String Challenge = challenge[i + ((int) difficulty.getValue() - 1) * 40];
            root.getChildren().clear();

            generateBoard();
            generatePieces();
            root.getChildren().add(controls);
            for (int j = 0; j < 10; j ++) {
                placements[j] = null;
            }

            for (ImageView image : makeChallenge(Challenge)) {
                root.getChildren().add(image);
            }
            ;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Platform.exit();

        }
    }
    void addPlacement(String placement) {
        switch (placement.charAt(0)) {
            case 'a':
                placements[0] = placement;
                break;
            case 'b':
                placements[1] = placement;
                break;
            case 'c':
                placements[2] = placement;
                break;
            case 'd':
                placements[3] = placement;
                break;
            case 'e':
                placements[4] = placement;
                break;
            case 'f':
                placements[5] = placement;
                break;
            case 'g':
                placements[6] = placement;
                break;
            case 'h':
                placements[7] = placement;
                break;
            case 'i':
                placements[8] = placement;
                break;
            case 'j':
                placements[9] = placement;
                break;
        }
    }
    void removePlacement(String placement) {
        switch (placement.charAt(0)) {
            case 'a':
                placements[0] = null;
                break;
            case 'b':
                placements[1] = null;
                break;
            case 'c':
                placements[2] = null;
                break;
            case 'd':
                placements[3] = null;
                break;
            case 'e':
                placements[4] = null;
                break;
            case 'f':
                placements[5] = null;
                break;
            case 'g':
                placements[6] = null;
                break;
            case 'h':
                placements[7] = null;
                break;
            case 'i':
                placements[8] = null;
                break;
            case 'j':
                placements[9] = null;
                break;
        }
    }

    private void makeControls() {
        Button button = new Button("Restart");
        button.setLayoutX(BOARD_WIDTH + X_SHIFT + 30);
        button.setLayoutY(BOARD_HEIGHT + 130);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                restartGame();
            }
        });
        controls.getChildren().add(button);
        difficulty.setMin(1);
        difficulty.setMax(3);
        difficulty.setValue(0);
        difficulty.setShowTickLabels(true);
        difficulty.setShowTickMarks(true);
        difficulty.setMajorTickUnit(1);
        difficulty.setMinorTickCount(0);
        difficulty.setSnapToTicks(true);

        difficulty.setLayoutX(BOARD_WIDTH + X_SHIFT - 20);
        difficulty.setLayoutY(BOARD_HEIGHT + 87);
        difficulty.toFront();
        controls.getChildren().add(difficulty);

        final Label difficultyCaption = new Label("Difficulty:");
        difficultyCaption.setTextFill(Color.GREY);
        difficultyCaption.setLayoutX(BOARD_WIDTH + X_SHIFT - 20);
        difficultyCaption.setLayoutY(BOARD_HEIGHT + 65);
        controls.getChildren().add(difficultyCaption);
    }

    void generatePieces() {
        Shapes[] start = new Shapes[10];
        ArrayList<Character> unplacedPieces = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'));
        for (int i = 0; i < 10; i++) {
            if (i > 4) {
                double x = 2 * (i - 5);
                double y = ((BOARD_HEIGHT - 87) / SQUARE_HEIGHT) + 2;
                start[i] = new DraggableShapes(unplacedPieces.get(i), x, y, 0);
            } else {
                double x = 2 * i;
                double y = ((BOARD_HEIGHT - 87) / SQUARE_HEIGHT) + 0.5;
                start[i] = new DraggableShapes(unplacedPieces.get(i), x, y, 0);
            }
        }
        // Adds draggable pieces to board
        for (Shapes ds : start) {
            ds.setFitWidth(SQUARE_WIDTH);
            ds.setFitHeight(SQUARE_HEIGHT);
            root.getChildren().add(ds);
        }
    }



    void generateBoard() {
        ImageView board = new ImageView();
        Image b = new Image(getClass().getResource(URI_BASE + "board.png").toString());
        board.setLayoutX(0 + X_SHIFT);
        board.setLayoutY(0);
        board.setFitWidth(BOARD_WIDTH);
        board.setFitHeight(BOARD_HEIGHT);
        board.setImage(b);

        root.getChildren().add(board);
    }

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting challenges (each challenge may have just one solution)


}

