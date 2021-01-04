package sample;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    public ImageView menuView;

    public int flag = 0;

    private FadeTransition fadeIn = new FadeTransition(Duration.millis(3000));
    private FadeTransition fadeIn2 = new FadeTransition(Duration.millis(150));
    private FadeTransition fadeIn3 = new FadeTransition(Duration.millis(200));

    public void initialize() {
        fadeIn.setNode(screenScrollPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setCycleCount(1);
        fadeIn.setAutoReverse(false);

        fadeIn2.setNode(textImage);
        fadeIn2.setFromValue(0.2);
        fadeIn2.setToValue(1.0);
        fadeIn2.setCycleCount(1);
        fadeIn2.setAutoReverse(false);

        fadeIn3.setNode(knobLight);
        fadeIn3.setFromValue(0.2);
        fadeIn3.setToValue(1.0);
        fadeIn3.setCycleCount(1);
        fadeIn3.setAutoReverse(false);


    }

    @FXML
    private void handleButton(ActionEvent event) {
        if (event.getSource().equals(powerButton)) {
            if (flag == 0) {

                (new Thread(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(400);
                        textImage.setImage(new Image(getClass().getResourceAsStream("assets/brand-logo-on.png")));
                        fadeIn2.playFromStart();
                        TimeUnit.MILLISECONDS.sleep(250);
                        textImage.setImage(new Image(getClass().getResourceAsStream("assets/brand-logo-off.png")));
                        TimeUnit.MILLISECONDS.sleep(80);
                        textImage.setImage(new Image(getClass().getResourceAsStream("assets/brand-logo-on.png")));
                        fadeIn2.playFromStart();
                        TimeUnit.MILLISECONDS.sleep(200);
                        textImage.setImage(new Image(getClass().getResourceAsStream("assets/brand-logo-off.png")));
                        TimeUnit.MILLISECONDS.sleep(100);
                        textImage.setImage(new Image(getClass().getResourceAsStream("assets/brand-logo-on.png")));
                        TimeUnit.MILLISECONDS.sleep(100);
                        textImage.setImage(new Image(getClass().getResourceAsStream("assets/brand-logo-off.png")));
                        TimeUnit.MILLISECONDS.sleep(150);
                        textImage.setImage(new Image(getClass().getResourceAsStream("assets/brand-logo-on.png")));
                        fadeIn2.playFromStart();
                        knobLight.setImage(new Image(getClass().getResourceAsStream("assets/volume-indicator-on.png")));

                        fadeIn2.playFromStart();
                        fadeIn3.playFromStart();
                        screenScrollPane.setVisible(true);
                        fadeIn.playFromStart();
                        TimeUnit.MILLISECONDS.sleep(4000);

                        for (int i=0;screenScrollPane.getVvalue()<0.45;i++){
                                double degrees = i;
                                double radians = Math.toRadians(degrees);
                                TimeUnit.MILLISECONDS.sleep(1);
                                screenScrollPane.setVvalue(screenScrollPane.getVvalue() + Math.sin(radians/1000));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })).start();

                flag = 1;

            } else {
                textImage.setImage(new Image(getClass().getResourceAsStream("assets/brand-logo-off.png")));
                knobLight.setImage(new Image(getClass().getResourceAsStream("assets/volume-indicator-off.png")));
                screenScrollPane.setVisible(false);
                flag = 0;
                screenScrollPane.setVvalue(0);
            }
        }

        // Sztuczne testowe przesuwanie ekraniku fiku miku
        // screenScrollPane.setVvalue(screenScrollPane.getVvalue() + screenScrollPane.getVmax() / 3);
        // System.out.println(screenScrollPane.getVvalue());
    }
    @FXML
    public void handleButton2(MouseEvent event){
        (new Thread(() -> {
            try {

                TimeUnit.MILLISECONDS.sleep(1000);
                for (int i=0;screenScrollPane.getVvalue()<0.9;i++){
                    double degrees = i;
                    double radians = Math.toRadians(degrees);
                    TimeUnit.MILLISECONDS.sleep(1);
                    screenScrollPane.setVvalue(screenScrollPane.getVvalue() + Math.sin(radians/1000));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();
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
