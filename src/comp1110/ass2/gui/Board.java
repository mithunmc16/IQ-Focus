package comp1110.ass2.gui;

import comp1110.ass2.FocusGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URI;

public class Board extends Application {

    // FIXME Task 7: Implement a basic playable Focus Game in JavaFX that only allows pieces to be placed in valid places

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int SQUARE_SIZE = 60;
    private static final int MARGIN_X = 18;
    private static final int MARGIN_Y = 18;

    private static Group root = new Group();
    private final Group shapes = new Group();
    private final Group board = new Group();

    private static final String URI_BASE = "assets/";
    private static final String BASEBOARD_URI = Board.class.getResource(URI_BASE + "board.png").toString();

    /*The underlying game*/
    FocusGame game;

    /*the state of the shapes*/
    char[] shapeState = new char[10]; //10 shapes all off the screen initially

    /*representation of the shapes */
    class GShape extends ImageView {
        int shapeID;

        GShape(char shape) {
            if(shape > 'j' || shape < 'a'){
                throw new IllegalArgumentException("Bad shape: \"" + shape + "\"");
            }
        }
    }



    // FIXME Task 8: Implement challenges (you may use challenges and assets provided for you in comp1110.ass2.gui.assets: sq-b.png, sq-g.png, sq-r.png & sq-w.png)

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting challenges (each challenge may have just one solution)

    @Override
    public void start(Stage primaryStage) {

    }
}
