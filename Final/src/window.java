import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.awt.Image;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Ben on 5/25/2017.
 */
public class window extends Application{
    public Menu gMenu;
    public void start(Stage primaryStage) throws Exception
    {
        //background picture
        InputStream is = Files.newInputStream(Paths.get("resources/login.jpg"));
        javafx.scene.image.Image i = new javafx.scene.image.Image(is);
        is.close();
        ImageView iView = new ImageView(i);

        //main pane
        final int x = 1080, y = 720;
        Pane root = new Pane();
        root.setPrefSize(x, y);

        //give pane background image
        gMenu = new Menu();
        root.getChildren().addAll(iView, gMenu);

        // create scene
        Scene scene1 = new Scene(root);

        //nested ifs for generic func keys, from test/game projects for reference
        scene1.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ESCAPE)
            {
                if(gMenu.isVisible() == false)
                {
                    FadeTransition ft1 = new FadeTransition(Duration.seconds(0.5), gMenu);
                    ft1.setFromValue(0);
                    ft1.setToValue(1);
                }
                else
                {
                    FadeTransition ft2 = new FadeTransition(Duration.seconds(0.5), gMenu);
                    ft2.setFromValue(1);
                    ft2.setFromValue(0);
                    ft2.setOnFinished(evt -> gMenu.setVisible(false));
                    ft2.play();
                }
            }
        });

        //primaryStage set, prob will be moved
        primaryStage.setScene(scene1);
        primaryStage.show();


    }
    public class Menu extends Parent
    {
        public Menu()
        {
            //declares all 3 menus one will be main menu, 2nd will be options, 3d will be create new game
            //will probably make a 4th for inside options if im not lazy
            VBox m1 = new VBox(15);
            VBox m2 = new VBox(15);
            VBox m3 = new VBox(15);

            //setting menu1/2 to same y, different x for transition fade
            m1.setTranslateX(80);
            m1.setTranslateY(200);
            m2.setTranslateX(280);
            m2.setTranslateY(200);

            menuButton btStart = new menuButton("WoooooW");
            btStart.setOnMouseClicked(e ->{
                FadeTransition ft = new FadeTransition(Duration.seconds(0.0), gMenu);
                ft.setFromValue(1);
                ft.setFromValue(0);
                ft.setOnFinished(evt ->{
                    gMenu.setVisible(false);
                    ft.play();
                });
            });
            Rectangle bg = new Rectangle(1080, 720);
            m1.getChildren().addAll(btStart);
            getChildren().addAll(bg, m1);

        }
    }
    public static class menuButton extends StackPane
    {
        //new text object, kinda unneeded but i want be thorough
        Text name;
        public menuButton(String n)
        {
            //make text object look fancy
            name = new Text(n);
            name.setFill(Color.WHITE);

            //Use rectangles to set the button to
            Rectangle rec = new Rectangle(200, 35);
            rec.setOpacity(0.6);
            rec.setFill(Color.BLACK);
            rec.setEffect(new GaussianBlur(3.5));
            this.setAlignment(Pos.CENTER);
            this.setRotate(-.5);
            getChildren().addAll(rec, name);


        }
    }
    public static void main (String args[])
    {
        launch(args);
    }
}
