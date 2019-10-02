package comp1110.ass2.gui;

import comp1110.ass2.FocusGame;
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
//The below code is written by Mithun Comar
public class Viewer extends Application {

    /* board layout */
    private static final int SQUARE_SIZE = 60;
    private static final int VIEWER_WIDTH = 720;
    private static final int ROWS = 4;
    private static final int COLUMNS = 8;
    private static final int VIEWER_HEIGHT = 480;
    private static final String URI_BASE = "assets/";
    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField textField;


    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {

        System.out.println((FocusGame.isPlacementStringValid(placement)));// to check if placement is valid
        root.getChildren().clear();

        root.getChildren().add(controls);

        //output image only of placement string is valid && well formed

        if (FocusGame.isPlacementStringValid(placement) && FocusGame.isPlacementStringWellFormed(placement)) {

            int X = ((VIEWER_WIDTH - (COLUMNS * SQUARE_SIZE))/4);//X and Y coordinates initialised
            int Y = ((VIEWER_HEIGHT - (ROWS * SQUARE_SIZE))/4);

            for (int i = 0; i < placement.length(); i += 4) {
                String placements = placement.substring(i, i + 4);//

                char obj = placements.charAt(0);
                int x = Character.getNumericValue(placements.charAt(1));
                int y = Character.getNumericValue(placements.charAt(2));
                int rotation = Character.getNumericValue(placements.charAt(3));

                Image image = new Image(getClass().getResource(URI_BASE + obj + ".png").toString());

                ImageView img = new ImageView();
                img.setImage(image);
//rotating images based on character 3
                int r = rotation;

                img.setRotate(r * 90);

                // hardcoding height and width of the images.
                int width;
                int height;


                if (obj == 'b' || obj == 'c' || obj == 'j') {
                    width = 4;
                } else if (obj == 'a' || obj == 'd' || obj == 'e' || obj == 'f' || obj == 'g' || obj == 'h') {
                    width = 3;
                }else {
                    width = 2;
                }


                if (obj == 'h') {
                    height = 3;
                } else if (obj == 'a' || obj == 'b' || obj == 'c' || obj == 'e' || obj == 'g' || obj == 'j'||obj=='d' || obj == 'i') {
                    height = 2;
                } else {
                    height = 1;
                }

//scaling image to fit in the stage.
                int widthScale = (width * SQUARE_SIZE);
                int heightScale = (height * SQUARE_SIZE);

                img.setFitHeight(heightScale);
                img.setFitWidth(widthScale);

                if (rotation % 2 == 0 || height == width) {

                    img.setTranslateX(SQUARE_SIZE * (x-1) + X);
                    img.setTranslateY(SQUARE_SIZE * y + Y);
                } else {

                    img.setTranslateX(SQUARE_SIZE * (x - 1) + X - (((double) (Math.abs(height - width)) / 2) * SQUARE_SIZE));
                    img.setTranslateY(SQUARE_SIZE * y + Y + (((double) (Math.abs(height - width)) / 2) * SQUARE_SIZE));
                }


                root.getChildren().add(img);
            }
        }
    }
//FIXME Task 4
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