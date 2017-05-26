import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Ben on 5/25/2017.
 */
public class window extends Application{
    public Menu startMenu, resumeMenu, newGameMenu;
    public void start(Stage primaryStage) throws Exception
    {
        //background picture
        InputStream is = Files.newInputStream(Paths.get("resources/test.jpg"));
        Image img = new Image(is);
        is.close();
        ImageView imgView = new ImageView(img);

        //main pane
        final int x = 1080, y = 720;
        Pane root = new Pane();
        root.setPrefSize(x, y);

        //give pane background image
        startMenu = new Menu(1);
        resumeMenu = new Menu(2);
      //  root.getChildren().addAll(gMenu, imgView);
        root.getChildren().addAll(imgView, startMenu, resumeMenu);

        //use start until game has been started, then use resume
        resumeMenu.setVisible(false);

        // create scene
        Scene scene1 = new Scene(root);

        //nested ifs for generic func keys, from test/game projects for reference checks for imput then respondes depending on input
        scene1.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ESCAPE)
            {
                if(resumeMenu.isVisible() == false)
                {
                    //if input = escape do below
                    FadeTransition ft1 = new FadeTransition(Duration.seconds(0.3), resumeMenu);
                    ft1.setFromValue(0);
                    ft1.setToValue(1);
                    resumeMenu.setVisible(true);
                    ft1.play();
                }
                else
                {
                    //IGNORE FOR NOW ...does nothing yet
//                    FadeTransition ft2 = new FadeTransition(Duration.seconds(0.5), startMenu);
//                    ft2.setFromValue(1);
//                    ft2.setFromValue(0);
//                    ft2.setOnFinished(e1 -> startMenu.setVisible(false));
//                    ft2.play();
                }
            }
        });

        //primaryStage set, prob will be moved
        primaryStage.setScene(scene1);
        primaryStage.show();


    }
    public class Menu extends Parent
    {
        public Menu(int y)
        {
            int x = y;
            //declares all 3 menus one will be main menu, 2nd will be options, 3d will be create new game
            //will probably make a 4th for inside options if im not lazy
            VBox m1 = new VBox(15);
            VBox m2 = new VBox(15);
            VBox m3 = new VBox(15);

            //setting menu1/2 to same y, different x for transition fade
            m1.setTranslateX(80);
            m1.setTranslateY(200);
            m2.setTranslateX(280);
            m2.setTranslateY(400);

            //start game button
            menuButton btStart = new menuButton("New Game");
            btStart.setOnMouseClicked(e ->{
                FadeTransition ft = new FadeTransition(Duration.seconds(0.0), startMenu);
                ft.setFromValue(1);
                ft.setFromValue(0);
                ft.setOnFinished(evt ->{
                    startMenu.setVisible(false);
                });
                ft.play();
            });
            menuButton btExit = new menuButton("Exit");
            btExit.setOnMouseClicked(e->{
                System.exit(0);
            });
            menuButton btResume = new menuButton("Resume");
            btResume.setOnMouseClicked(e->{
                FadeTransition ft = new FadeTransition(Duration.seconds(0.0), resumeMenu);
                ft.setFromValue(1);
                ft.setFromValue(0);
                ft.setOnFinished(evt ->{
                    resumeMenu.setVisible(false);
                });
                ft.play();
            });
            //creates fade over the background
            Rectangle bg = new Rectangle(1080, 720);
            bg.setFill(Color.GRAY);
            bg.setOpacity(0.4);
            if(x == 1)
            {
                m1.getChildren().addAll(btStart, btExit);
            }
            if(x == 2)
            {
                m1.getChildren().addAll(btResume, btExit);
            }
            this.getChildren().addAll(bg, m1);

        }
    }
    public static class menuButton extends StackPane
    {
        //new text object, kinda unneeded but i want be thorough in making my buttons really fancy
        Text name;
        public menuButton(String n)
        {
            //make text object look fancy
            name = new Text(n);
            name.setFill(Color.BLACK);

            //Use rectangles as buttons so you can do fancy fade :)
            Rectangle rec = new Rectangle(200, 35);
            rec.setOpacity(0.6);
            rec.setFill(Color.BLUE);
            rec.setEffect(new GaussianBlur(3.5));

            //location of buttons
            this.setAlignment(Pos.CENTER);
            this.setRotate(-.5);
            getChildren().addAll(rec, name);

            //highlight when entered
                this.setOnMouseEntered(e->{
                    rec.setTranslateX(10);
                    rec.setFill(Color.WHITE);
                    name.setTranslateX(10);
                    name.setFill(Color.BLACK);
                });
                this.setOnMouseExited(e->{
                    //resets on exit
                    rec.setTranslateX(0);
                    rec.setFill(Color.BLUE);
                    name.setTranslateX(0);
                    name.setFill(Color.WHITE);
                });
                //click effects to simulate depression on click
            DropShadow d = new DropShadow(50, Color.WHITE);
            d.setInput(new Glow());
            setOnMousePressed(e-> setEffect(d));
            setOnMouseReleased(e-> setEffect(null));

        }
    }
    public static void main (String args[])
    {
        launch(args);
    }
}
