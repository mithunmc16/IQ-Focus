package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import comp1110.ass2.FocusGame;
import java.util.ArrayList;
import javafx.scene.image.ImageView;

/**
 * A very simple viewer for piece placements in the IQ-Focus game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int SQUARE_SIZE = 60;
    private static final int VIEWER_WIDTH = 720;
    //
    public static final int PIECE_IMAGE_SIZE = 3*SQUARE_SIZE;
    //
    private static final double ROW_HEIGHT = SQUARE_SIZE ;// rotate 90 degree
    private static final int VIEWER_HEIGHT = 480;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group pieces = new Group(); // new group for pieces
    private final Group controls = new Group();
    private TextField textField;

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        boolean validPlacement;
        int n = 0;  //number of pieces contained within placement string
        int orientation;
        int position;
        int indexStart;
        String piecePlacement;
        ArrayList<ImageView> arrPiece = new ArrayList<>();
        validPlacement = FocusGame.isPlacementStringWellFormed(placement);
        if (validPlacement) {
            pieces.getChildren().clear();
            n = placement.length() / 5;
            for (int i = 0; i < n; i++) {
                indexStart = i * 5;
                piecePlacement = placement.substring(indexStart, indexStart + 3);
                arrPiece.add(new ImageView(Viewer.class.getResource(URI_BASE + piecePlacement.charAt(1) + ".png").toString()));

                if (piecePlacement.charAt(1) == 'a')
                    orientation = piecePlacement.charAt(2) - 'A';
                else {
                    if (piecePlacement.charAt(2) >= 'k') {
                        arrPiece.get(i).setScaleY(-1);
                        orientation = piecePlacement.charAt(2) - 'k';

                    } else {
                        orientation = piecePlacement.charAt(2) - 'a';

                    }
                }
                arrPiece.get(i).setRotate(90 * orientation);
                arrPiece.get(i).setFitHeight(PIECE_IMAGE_SIZE);
                arrPiece.get(i).setFitWidth(PIECE_IMAGE_SIZE);

                position = piecePlacement.charAt(0) - 'a';
                double row = -1;
                double col = -1;
                if (position >= 0 && position <= 5) {
                    row = 0;
                    col = (position) * SQUARE_SIZE;
                }
                }
                //arrPiece.get(i).setLayoutX(col - );
                //arrPiece.get(i).setLayoutY(row - );
            }
            pieces.getChildren().addAll(arrPiece);
            // FIXME Task 4: implement the simple placement viewer
        }
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FocusGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
