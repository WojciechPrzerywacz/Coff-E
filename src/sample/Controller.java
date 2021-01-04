package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Controller {

    final public double MIN_ROTATE_VALUE = 0, MAX_ROTATE_VALUE = 360;
    public ScrollPane screenScrollPane;
    public ImageView screenFirstLayer;
    double xOffset, yOffset;
    public AnchorPane backgroundAnchorPane;

    double knobCurrentRotate, mouseCurrentX;
    @FXML
    public ToggleButton powerButton;
    public ImageView textImage;
    public ImageView knobLight;

    public int flag = 0;

    @FXML
    private void handleButton(ActionEvent event) {
        if (event.getSource().equals(powerButton)) {
            if (flag == 0) {
                textImage.setImage(new Image(getClass().getResourceAsStream("assets/brand-logo-on.png")));
                knobLight.setImage(new Image(getClass().getResourceAsStream("assets/volume-indicator-on.png")));
                screenScrollPane.setVisible(true);
                flag = 1;
            } else {
                textImage.setImage(new Image(getClass().getResourceAsStream("assets/brand-logo-off.png")));
                knobLight.setImage(new Image(getClass().getResourceAsStream("assets/volume-indicator-off.png")));
                screenScrollPane.setVisible(false);
                flag = 0;
            }
        }

        // Sztuczne testowe przesuwanie ekraniku fiku miku
        // screenScrollPane.setVvalue(screenScrollPane.getVvalue() + screenScrollPane.getVmax() / 3);
        // System.out.println(screenScrollPane.getVvalue());
    }

    public void setRotate(MouseEvent event) {
        knobCurrentRotate = knobLight.getRotate();
        mouseCurrentX = event.getSceneX();
    }

    public void controlKnob(MouseEvent event) {
        double rotation = knobCurrentRotate + (event.getSceneX() - mouseCurrentX);
        rotation = rotation < MIN_ROTATE_VALUE ? MIN_ROTATE_VALUE : Math.min(rotation, MAX_ROTATE_VALUE);
        knobLight.setRotate(rotation);
    }

    public void savePositionOffset(MouseEvent event) {
        xOffset = backgroundAnchorPane.getScene().getWindow().getX() - event.getScreenX();
        yOffset = backgroundAnchorPane.getScene().getWindow().getY() - event.getScreenY();
    }

    public void moveWindow(MouseEvent event) {
        // backgroundAnchorPane.getScene().getWindow().setX(event.getScreenX() + xOffset);
        // backgroundAnchorPane.getScene().getWindow().setY(event.getScreenY() + yOffset);
    }

    public void doNotScroll(ScrollEvent event) {
        event.consume();
    }
}
