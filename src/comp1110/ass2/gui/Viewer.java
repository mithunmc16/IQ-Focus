package comp1110.ass2.gui;

import com.sun.glass.ui.View;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
        Scene scene = new Scene(new Group());
        boolean validPlacement;
        int n = 0;  //number of pieces contained within placement string
        char orientation = placement.charAt(3);
        int position;
        int indexStart;
        char tile = placement.charAt(0);
        String piecePlacement;
        ArrayList<ImageView> arrPiece = new ArrayList<>();
        Image img = new Image(Viewer.class.getResource(URI_BASE + tile + ".png").toString());


        switch (orientation) {
            case '0':

                ImageView i1 = new ImageView(img);
                i1.setRotate(0);
                root.getChildren().addAll(i1);
                Stage stage = new Stage();
                scene.setRoot(root);
                stage.setScene(scene);
                stage.show();
                break;

            case '1':

                ImageView i2 = new ImageView(img);
                i2.setRotate(90);
                root.getChildren().addAll(i2);
                Stage stage1 = new Stage();
                stage1.setScene(scene);
                stage1.show();
                break;
            case '2':

                ImageView i3 = new ImageView(img);
                i3.setRotate(180);
                root.getChildren().addAll(i3);
                Stage stage2 = new Stage();
                stage2.setScene(scene);
                stage2.show();
                break;

            case '3':

                ImageView i4 = new ImageView(img);
                i4.setRotate(270);
                root.getChildren().addAll(i4);
                Stage stage3 = new Stage();
                stage3.setScene(scene);
                stage3.show();
                break;


            // validPlacement = FocusGame.isPlacementStringWellFormed(placement);
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
