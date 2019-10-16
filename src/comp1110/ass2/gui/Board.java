package comp1110.ass2.gui;

import comp1110.ass2.FocusGame;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;


import java.util.ArrayList;
import java.util.Arrays;

import javafx.util.Duration;

import static comp1110.ass2.FocusGame.isPlacementStringValid;
import static javafx.scene.paint.Color.*;

import java.net.URI;
import java.util.List;
import java.util.Random;

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
    private static final double X_SHIFT = 106;

    private final Group shapes = new Group();
    private final Group board = new Group();
//
private final Group controls = new Group();
private TextField textField;

//



    private static final String URI_BASE = "assets/";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("game");
        Group root = new Group();
        Scene scene = new Scene(root, 933, 700);


    /*Initialise dropShadow*/
    private static DropShadow dropShadow;

    {
        dropShadow = new DropShadow();
        dropShadow.setOffsetX(2.0);
        dropShadow.setOffsetY(2.0);
        dropShadow.setColor(Color.color(0, 0, 0, 0.4));
    }
//
    Random rand = new Random();
    int i = rand.nextInt(challenge.length);
    String Challenge = challenge[i];


    // FIXME Task 8: Implement challenges (you may use challenges and assets provided for you in comp1110.ass2.gui.assets: sq-b.png, sq-g.png, sq-r.png & sq-w.png)

    //Written by Mithun Comar
    void makeChallenge(String Challenge){// This block displays the challenge to be implemented for the game.

    String input = null;
    System.out.println(Challenge);
    char[] clg = Challenge.toCharArray();

    for (int j = 0; j<clg.length; j++){

        switch(clg[j]){
            case 'B': input = "sq-b";
            break;
            case 'G': input = "sq-g";
            break;
            case 'R': input = "sq-r";
            break;
            case 'W': input = "sq-w";

        }
        Image image = new Image(getClass().getResource(URI_BASE + input + ".png").toString());//selecting image from the directory
        System.out.println(input);
        ImageView img = new ImageView(image);

        img.setOpacity(0.5);//setting the image to be opaque

        int widthScale = (SQUARE_SIZE);
        int heightScale = (SQUARE_SIZE);
        img.setFitHeight(heightScale);
        img.setFitWidth(widthScale);

        img.setImage(image);
        // placing images in the order [0][1][2]
        //                             [3][4][5]
        //                             [6][7][8]
        //based on the challenge string eg. RRBWBRWGG
        if (j == 0) {

            img.setTranslateX(SQUARE_SIZE * 1);
            img.setTranslateY(SQUARE_SIZE * 3);
        }
        else if (j == 1){
            img.setTranslateX(SQUARE_SIZE * 2);
            img.setTranslateY(SQUARE_SIZE * 3);
        }
        else if(j == 2){
            img.setTranslateX(SQUARE_SIZE * 3);
            img.setTranslateY(SQUARE_SIZE * 3);
        }
        else if (j == 3){
            img.setTranslateX(SQUARE_SIZE * 1);
            img.setTranslateY(SQUARE_SIZE * 4);
        }
        else if(j == 4){
            img.setTranslateX(SQUARE_SIZE * 2);
            img.setTranslateY(SQUARE_SIZE * 4);
        }
        else if(j == 5){
            img.setTranslateX(SQUARE_SIZE * 3);
            img.setTranslateY(SQUARE_SIZE * 4);
        }
        else if (j == 6){
            img.setTranslateX(SQUARE_SIZE * 1);
            img.setTranslateY(SQUARE_SIZE * 5);
        }
        else if(j == 7){
            img.setTranslateX(SQUARE_SIZE * 2);
            img.setTranslateY(SQUARE_SIZE * 5);
        }
        else{
            img.setTranslateX(SQUARE_SIZE * 3);
            img.setTranslateY(SQUARE_SIZE * 5);
        }

        root.getChildren().add(img);
    }

}
// end of task 8

        ArrayList<Character> unplacedPieces = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'));
        Image[] images = new Image[10];
        for (int i = 0; i < 10; i++) {
            images[i] = new Image(getClass().getResource(URI_BASE + unplacedPieces.get(i) + ".png").toString());
        }

        // Creates the board
        ImageView board = new ImageView();
        Image b = new Image(getClass().getResource(URI_BASE + "board.png").toString());
        board.setLayoutX(0 + X_SHIFT);
        board.setLayoutY(0);
        board.setFitWidth(BOARD_WIDTH);
        board.setFitHeight(BOARD_HEIGHT);
        board.setImage(b);


        root.getChildren().add(board);

        for (int i = 0; i < 10; i ++) {
            ImageView piece = new ImageView();
            piece.setLayoutX(42 + X_SHIFT + 2*i*SQUARE_WIDTH);
            if (i > 4) {
                piece.setLayoutY(BOARD_HEIGHT + 2 * SQUARE_HEIGHT);
                piece.setLayoutX(42 + X_SHIFT + 2*(i-5)*SQUARE_WIDTH);
            }
            else {
                piece.setLayoutY(BOARD_HEIGHT + 0.5 * SQUARE_HEIGHT);
            }
            piece.setFitWidth(SQUARE_WIDTH);
            piece.setFitHeight(SQUARE_HEIGHT);
            piece.setImage(images[i]);
            root.getChildren().add(piece);
        }
        scene.setRoot(root);
        scene.setFill(BEIGE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // Generates images representing each shape using placement string
    public class Shapes extends ImageView {
        ImageView shape;
        char piece;
        double x,y;
        int orientation;


        public Shapes(String placement) {
            if (isPlacementStringValid(placement)) {
                this.piece = placement.charAt(0);
                this.x = (double) Character.digit(placement.charAt(1), 10);
                this.y = (double) Character.digit(placement.charAt(2), 10);
                this.orientation = Character.getNumericValue(placement.charAt(3));

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
        }

        public ImageView getShape() {
            return shape;
        }
    }
    class DraggableShapes extends Shapes {
        int homeX, homeY;
        double mouseX, mouseY;

        DraggableShapes(String placement) {
            super(placement);
            }

        }
    }








    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting challenges (each challenge may have just one solution)




    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FocusGame Viewer");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        makeChallenge(Challenge);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
