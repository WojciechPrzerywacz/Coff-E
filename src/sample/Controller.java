package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Controller {

    final public double MIN_ROTATE_VALUE = 60, MAX_ROTATE_VALUE = 360;

    double knobCurrentRotate, mouseCurrentX;
    @FXML
    public ToggleButton powerButton;
    public ImageView textImage;
    public ImageView knobLight;

    public int flag=0;

    @FXML
    private void handleButton(ActionEvent event) throws IOException {

        if (event.getSource().equals(powerButton)){
            if(flag==0) {
                textImage.setImage(new Image(getClass().getResourceAsStream("brand-logo-on.png")));
                knobLight.setImage(new Image(getClass().getResourceAsStream("volume-indicator-on.png")));
                flag = 1;
            }else {
                textImage.setImage(new Image(getClass().getResourceAsStream("brand-logo-off.png")));
                knobLight.setImage(new Image(getClass().getResourceAsStream("volume-indicator-off.png")));
                flag = 0;
            }
        }

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

}
