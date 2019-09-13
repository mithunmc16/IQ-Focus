package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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

    private static final int ROWS = 4;
    private static final int COLUMNS = 8;
    //
    public static final int PIECE_IMAGE_SIZE = 3*SQUARE_SIZE;
    //
    private static final double ROW_HEIGHT = SQUARE_SIZE ;// rotate 90 degree
    private static final int VIEWER_HEIGHT = 480;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group pieces = new Group(); // new group for pieces
    private final Group controls = new Group();
    private final Group block = new Group();
    private TextField textField;

      /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        root.getChildren().clear();
        root.getChildren().add(controls);
        char shape = placement.charAt(0);
        int rotation = Character.getNumericValue(placement.charAt(3));
        Image image = new Image(getClass().getResource(URI_BASE + shape + ".png").toString());
        ImageView img = new ImageView();
        img.setImage(image);
        int r = rotation;
        if(r >= 4)r -= 4;
        img.setRotate(r * 90);
        if(rotation >= 4)img.setScaleY(-1);
        root.getChildren().add(img);

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
