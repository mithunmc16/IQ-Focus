package comp1110.ass2.gui;

import comp1110.ass2.FocusGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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
   // private static final String BASEBOARD_URI = Board.class.getResource(URI_BASE + "board.png").toString();

    /*The underlying game*/
    FocusGame game;

    /*the state of the shapes*/
    char[] shapeState = new char[10]; //10 shapes all off the screen initially

    /*Initialise dropShadow*/
    private static DropShadow dropShadow;{
        dropShadow = new DropShadow();
        dropShadow.setOffsetX(2.0);
        dropShadow.setOffsetY(2.0);
        dropShadow.setColor(Color.color(0,0,0,0.4));
    }

    /*representation of the shapes */
    class GShape extends ImageView {
        char piece;

        /**
         * Construct a playing piece
         * @param piece The piece to be created, represented by a letter
         */

        GShape(char piece) {
            if(piece > 'j' || piece < 'a'){
                throw new IllegalArgumentException("Bad piece: \"" + piece + "\"");
            }
            this.piece = piece;
            setFitWidth(SQUARE_SIZE);
            setFitHeight(SQUARE_SIZE);
            setImage(new Image(Board.class.getResource(URI_BASE + piece + ".png").toString()));

            //Set the height and width depending on which shape it is
            switch (piece) {
                case 'a':
                    setFitWidth(SQUARE_SIZE*3);
                    setFitHeight(SQUARE_SIZE*2);

                case 'b':
                    setFitWidth(SQUARE_SIZE*4);
                    setFitHeight(SQUARE_SIZE*2);

                case 'c':
                    setFitWidth(SQUARE_SIZE*4);
                    setFitHeight(SQUARE_SIZE*2);

                case 'd':
                    setFitWidth(SQUARE_SIZE*3);
                    setFitHeight(SQUARE_SIZE*2);

                case 'e':
                    setFitWidth(SQUARE_SIZE*3);
                    setFitHeight(SQUARE_SIZE*2);

                case 'f':
                    setFitWidth(SQUARE_SIZE*3);
                    setFitHeight(SQUARE_SIZE);

                case 'g':
                    setFitWidth(SQUARE_SIZE*3);
                    setFitHeight(SQUARE_SIZE*2);

                case 'h':
                    setFitWidth(SQUARE_SIZE*3);
                    setFitHeight(SQUARE_SIZE*3);

                case 'i':
                    setFitWidth(SQUARE_SIZE*2);
                    setFitHeight(SQUARE_SIZE*2);

                case 'j':
                    setFitWidth(SQUARE_SIZE*4);
                    setFitHeight(SQUARE_SIZE*2);

            }

            setEffect(dropShadow);
        }

        // Create draggable pieces
        class DraggabblePiece extends GShape {
            int homeX, homeY;
            double mouseX, mouseY;
            int orientation;

            DraggabblePiece(char tile){
                super(tile);
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
