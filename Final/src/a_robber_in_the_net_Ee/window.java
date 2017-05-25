package a_robber_in_the_net_Ee;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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

            //setting menu1/2 to same location
            m1.setTranslateX(80);
            m1.setTranslateY(200);
            m2.setTranslateX(80);
            m2.setTranslateY(200);
        }
    }
    public static void main (String args[])
    {
        launch(args);
    }
}
