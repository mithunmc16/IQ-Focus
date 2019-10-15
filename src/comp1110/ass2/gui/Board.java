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
import javafx.util.Duration;

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

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int SQUARE_SIZE = 60;
    private static final int MARGIN_X = 18;
    private static final int MARGIN_Y = 18;
    private static final int ROWS = 4;
    private static final int COLUMNS = 8;

    private static Group root = new Group();
    private final Group shapes = new Group();
    private final Group board = new Group();
//
private final Group controls = new Group();
private TextField textField;

//



    private static final String URI_BASE = "assets/";
    private static final String BASEBOARD_URI = Board.class.getResource(URI_BASE + "board.png").toString();

    /*The underlying game*/
    FocusGame game;

    /*the state of the shapes*/
    char[] shapeState = new char[10]; //10 shapes all off the screen initially

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


    /*representation of the shapes */
    class GShape extends ImageView {
        char piece;

        /**
         * Construct a playing piece
         *
         * @param piece The piece to be created, represented by a letter
         */

        GShape(char piece) {
            if (piece > 'j' || piece < 'a') {
                throw new IllegalArgumentException("Bad piece: \"" + piece + "\"");
            }
            this.piece = piece;
            setFitWidth(SQUARE_SIZE);
            setFitHeight(SQUARE_SIZE);
            setImage(new Image(Board.class.getResource(URI_BASE + piece + ".png").toString()));

            //Set the height and width depending on which shape it is
            switch (piece) {
                case 'a':
                    setFitWidth(SQUARE_SIZE * 3);
                    setFitHeight(SQUARE_SIZE * 2);

                case 'b':
                    setFitWidth(SQUARE_SIZE * 4);
                    setFitHeight(SQUARE_SIZE * 2);

                case 'c':
                    setFitWidth(SQUARE_SIZE * 4);
                    setFitHeight(SQUARE_SIZE * 2);

                case 'd':
                    setFitWidth(SQUARE_SIZE * 3);
                    setFitHeight(SQUARE_SIZE * 2);

                case 'e':
                    setFitWidth(SQUARE_SIZE * 3);
                    setFitHeight(SQUARE_SIZE * 2);

                case 'f':
                    setFitWidth(SQUARE_SIZE * 3);
                    setFitHeight(SQUARE_SIZE);

                case 'g':
                    setFitWidth(SQUARE_SIZE * 3);
                    setFitHeight(SQUARE_SIZE * 2);

                case 'h':
                    setFitWidth(SQUARE_SIZE * 3);
                    setFitHeight(SQUARE_SIZE * 3);

                case 'i':
                    setFitWidth(SQUARE_SIZE * 2);
                    setFitHeight(SQUARE_SIZE * 2);

                case 'j':
                    setFitWidth(SQUARE_SIZE * 4);
                    setFitHeight(SQUARE_SIZE * 2);

            }



            setEffect(dropShadow);
        }

        // Create draggable pieces
        class DraggabblePiece extends GShape {
            int homeX, homeY;
            double mouseX, mouseY;
            int orientation;

            DraggabblePiece(char tile) {
                super(tile);
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
