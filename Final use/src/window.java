import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
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
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by Ben on 5/25/2017.
 */
public class window extends Application{
    public Menu startMenu, resumeMenu, newGameMenu, game;
    public ArrayList<player> plist = new ArrayList<player>();
    public void start(Stage primaryStage) throws Exception
    {
        //background picture
        InputStream is = Files.newInputStream(Paths.get("resources/test.jpg"));
        Image img = new Image(is);
        is.close();
        ImageView imgView = new ImageView(img);
        InputStream is1 = Files.newInputStream(Paths.get("resources/board.jpg"));
        Image img1 = new Image(is);
        is1.close();
        ImageView imgView1 = new ImageView(img);

        //main pane
        final int x = 1080, y = 884;
        Pane root = new Pane();
        root.setPrefSize(x, y);

        //give pane background image
        //create all the menus that will take in an int to determine what buttons they will have
        //i realize i could polymorphism this instead of using numbers becuase it creates a bunch of usless data but i'm lazy
        startMenu = new Menu(1);
        resumeMenu = new Menu(2);
        newGameMenu = new Menu(3);
        game = new Menu(4);

        root.getChildren().addAll(imgView, imgView1, startMenu, resumeMenu, newGameMenu, game);


        //use start until game has been started, then use resume
        resumeMenu.setVisible(false);
        newGameMenu.setVisible(false);
        game.setVisible(false);
        imgView1.setVisible(false);

        // create scene
        Scene scene1 = new Scene(root);

        //nested ifs for generic func keys, from test/game projects for reference checks for imput then respondes depending on input
        scene1.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ESCAPE)
            {
                if(resumeMenu.isVisible() == false && startMenu.isVisible() == false)
                {
                    //if input = escape do below
                    System.out.println("test");
                    FadeTransition ft1 = new FadeTransition(Duration.seconds(0.1), resumeMenu);
                    ft1.setFromValue(0);
                    ft1.setToValue(1);
                    resumeMenu.setVisible(true);

                    ft1.play();
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
            Pane m4 = new Pane();


            //setting menu1/2 to same y, different x for transition fade
            m1.setTranslateX(80);
            m1.setTranslateY(200);
            m2.setTranslateX(80);
            m2.setTranslateY(200);
            m3.setTranslateX(500);
            m3.setTranslateY(400);

            //new game button
            menuButton btNewGame = new menuButton("New Game");
            btNewGame.setOnMouseClicked(e ->{
                FadeTransition ft = new FadeTransition(Duration.seconds(0.5), startMenu);
                ft.setFromValue(1);
                ft.setFromValue(0);
                ft.setOnFinished(evt ->{
                    startMenu.setVisible(false);
                });
                ft.play();
                newGameMenu.setVisible(true);
            });
            menuButton btExit = new menuButton("Exit");
            btExit.setOnMouseClicked(e->{
                System.exit(0);
            });
            menuButton btResume = new menuButton("Resume");
            btResume.setOnMouseClicked(e->{
                FadeTransition ft = new FadeTransition(Duration.seconds(0.5), resumeMenu);
                ft.setFromValue(1);
                ft.setFromValue(0);
                ft.setOnFinished(evt ->{
                    resumeMenu.setVisible(false);
                });
                ft.play();
            });
            menuButton btStart = new menuButton("Start Game");
            btStart.setOnMouseClicked(e->{
                this.getChildren().remove(m3);
                this.getChildren().add(m4);

            });
            menuButton btFour = new menuButton("Four Players");
            menuButton btThree = new menuButton("Three players");
            menuButton btTwo = new menuButton("Two players");
            btTwo.setOnMouseClicked(e->{
                boolean[] startGameVis = {false};
                m3.getChildren().removeAll(btFour, btTwo, btThree);
                m3.setTranslateY(m3.getTranslateY()+50);
                player p1 = new player();
                player p2 = new player();
                plist.add(p1);
                plist.add(p2);
                TextField p1name = new TextField("Enter player 1 name");
                p1name.setTranslateX(500);
                p1name.setTranslateY(400);
                TextField p2name = new TextField("Enter player 2 name");
                p2name.setTranslateX(500);
                p2name.setTranslateY(400);
                this.getChildren().addAll(p1name);
                p1name.setOnKeyPressed(e1->{
                    if(e1.getCode() == KeyCode.ENTER)
                    {
                        String name = p1name.getText();
                        p1.setName(name);
                        p1name.setVisible(false);
                        this.getChildren().addAll(p2name);
                    }
                });
                p2name.setOnKeyPressed(e1->{
                    if(e1.getCode() == KeyCode.ENTER)
                    {
                        String name = p2name.getText();
                        p2.setName(name);
                        p2name.setVisible(false);
                        this.getChildren().remove(m3);
                        m4.setVisible(true);
                    }
                });
            });

            //creates fade over the background
            Rectangle bg = new Rectangle(1080, 884);
            bg.setFill(Color.GRAY);
            bg.setOpacity(0.4);

            //uses imputed int to determine what buttons to add
            if(x == 1)
            {
                m1.getChildren().addAll(btNewGame, btExit);
                this.getChildren().addAll(bg, m1);
            }
            else if(x == 2)
            {
                m2.getChildren().addAll(btResume, btExit);
                this.getChildren().remove(m1);
                this.getChildren().addAll(bg, m2);
            }
            else if (x == 3)
            {
                m3.getChildren().addAll(btTwo, btThree, btFour);
                this.getChildren().remove(m2);
                this.getChildren().addAll(m3);
            }
            else if(x == 4)
            {
                m4.getChildren().add(btStart);
            }


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