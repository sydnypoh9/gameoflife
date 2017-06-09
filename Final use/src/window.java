import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.text.Position;
import java.awt.*;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.ArrayList;

/**
 * Created by Ben on 5/25/2017.
 */
public class window extends Application{
    private Menu startMenu, resumeMenu, newGameMenu, game;
    private ArrayList<player> plist = new ArrayList<player>();
    private player p1, p2, p3, p4;
    private boolean turn = false, run = false, terriblewayofdoingthis = true, terriblewayofdoingthis2 = true;
    private KeyFrame frame1;
    private Scene scene1, tutorial;
    private ImageView imgView, imgView1;
    private Pane root, root2;
    private VBox p1SB, p2SB, p3SB, p4SB, scoreBM = new VBox(100);
    private Circle test = new Circle(15);
    private Rectangle r;
    private int place = 1, rollint = 0;
    private final int q1x = 300,q2x = 720, q1y = 100, tip = 300;


    public void start(Stage primaryStage) throws Exception
    {
        //background picture
        InputStream is = Files.newInputStream(Paths.get("resources/test.jpg"));
        Image img = new Image(is);
        is.close();
        imgView = new ImageView(img);
        InputStream is1 = Files.newInputStream(Paths.get("resources/board.jpg"));
        Image img1 = new Image(is1);
        is1.close();
        imgView1 = new ImageView(img1);

        //rectangle and circle city
        r= new Rectangle(120, 884);
        r.setFill(Color.WHITE);
        test.setTranslateX(901);
        test.setTranslateY(397);
         menuButton spin = new menuButton("Tip: Click the spin button \nto spin the wheel!!", 200, 200);
        spin.setTranslateY(tip);
        spin.setTranslateX(tip);
        menuButton btRoll = new menuButton("Roll", 50, 20, Color.GREY);
        btRoll.setOnMouseClicked(e->
        {
            if(rollint == 0)
            {
                rollint = (int)(Math.random()*10)+1;
                menuButton youGot = new menuButton("You rolled a: " + rollint, 200, 200);
                youGot.setTranslateX(tip); youGot.setTranslateY(tip);
                root.getChildren().add(youGot);
                youGot.setOnMouseEntered(e1->{
                    root.getChildren().remove(youGot);
                });
            }
            else
            {
                System.out.println("Stop pressing the button");
            }
        });
        btRoll.setTranslateX(700);
        btRoll.setTranslateY(383);

        //main pane
        final int x = 1080, y = 884;
        root = new Pane();
        root.setPrefSize(x, y);

        //give pane background image
        //create all the menus that will take in an int to determine what buttons they will have
        //i realize i could polymorphism this instead of using numbers becuase it creates a bunch of usless data but i'm lazy
        startMenu = new Menu(1);
        resumeMenu = new Menu(2);
        newGameMenu = new Menu(3);
        game = new Menu(4);
            //everything is everywhere i give up commenting
        root.getChildren().addAll(imgView, imgView1, startMenu, resumeMenu, newGameMenu, game);
        scene1 = new Scene(root);
        //do at some point
        tutorial = new Scene(root2= new Pane());
        root2.setPrefSize(680, 200);

        //use start until game has been started, then use resume
        resumeMenu.setVisible(false);
        newGameMenu.setVisible(false);
        game.setVisible(false);
        root.setVisible(true);
        imgView1.setVisible(false);

        ArrayList<gameBoard> collegeA = new ArrayList<gameBoard>();
        collegeA.add(new gameBoard(932, 545));
        collegeA.add(new gameBoard(947, 596));
        collegeA.add(new gameBoard(998, 596));
        collegeA.add(new gameBoard(1011, 543));
        collegeA.add(new gameBoard(1011, 498));
        collegeA.add(new gameBoard(1011, 452));
        collegeA.add(new gameBoard(1011, 407));
        collegeA.add(new gameBoard(1011, 361));
        collegeA.add(new gameBoard(1011, 316));
        collegeA.add(new gameBoard(1011, 268));
        collegeA.add(new gameBoard(1011, 227));
        collegeA.add(new gameBoard(1010, 168));

        ArrayList<gameBoard> workA = new ArrayList<gameBoard>();
        workA.add(new gameBoard(932, 305));
        workA.add(new gameBoard(930, 248));
        workA.add(new gameBoard(949, 190));
        workA.add(new gameBoard(1010, 168));





        frame1 = new KeyFrame(Duration.seconds(1), e1 -> {
            if(terriblewayofdoingthis)
            {
                setScoreBoard();
                root.getChildren().addAll(r, scoreBM, test);
                terriblewayofdoingthis = false;
            }
            if (turn == true)
            {
               if(place <= plist.size())
               {
                   System.out.println("why are you running");
                   place++;
               }
               else
               {
                   place = 1;
               }
                turn = false;
            }
            else
            {
                if (plist.size() == 2)
                {
                    if(place == 1)
                    {
                        if(terriblewayofdoingthis2 == true)
                        {
                            menuButton College = new menuButton("Go to college?",125, 125, Color.WHITE);
                            College.setTranslateX(q1x);
                            College.setTranslateY(q1y);
                            menuButton work = new menuButton("Go to work?",125, 125, Color.WHITE);
                            work.setTranslateX(q2x);
                            work.setTranslateY(q1y);
                            root.getChildren().addAll(College, work, btRoll);
                            College.setOnMouseClicked(e->{
                                root.getChildren().removeAll(College, work);
                            });
                            work.setOnMouseClicked(e->{
                                root.getChildren().removeAll(College, work);
                            });
                            terriblewayofdoingthis2 = false;
                        }
                        if(rollint == 0)
                        {
                            System.out.println("roll int in if " + rollint);

                        }
                        else
                        {
                            System.out.println("roll int in else " + rollint);
                            test.setTranslateX(collegeA.get(rollint-1).getGbx());
                            test.setTranslateY(collegeA.get(rollint-1).getGby());
                            p1.setLoc(rollint-1);
                            turn = true;
                            rollint = 0;
                        }

                    }
                    if(place == 2)
                    {
                        System.out.println(rollint);
                        System.out.println("place 2");
                    }
               // System.out.println( plist);
                }
                else if(plist.size() == 3)
                {
                   //System.out.println( plist);
                }
                else  if(plist.size()== 4)
                {
                //    System.out.println( plist);
                }
                else
                {
                    System.exit(0);
                }
                turn = true;
            }

        });
        final Timeline tline = new Timeline(frame1);
        tline.setCycleCount(Timeline.INDEFINITE);
        // create scene



        //nested ifs for generic func keys, from test/game projects for reference checks for imput then respondes depending on input
        scene1.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ESCAPE)
            {
                if(resumeMenu.isVisible() == false && startMenu.isVisible() == false)
                {
                    //if input = escape do below
                    //System.out.println("test");
                    FadeTransition ft1 = new FadeTransition(Duration.seconds(0.1), resumeMenu);
                    ft1.setFromValue(0);
                    ft1.setToValue(1);
                    resumeMenu.setVisible(true);

                    ft1.play();
                }
            }
        });
        scene1.setOnMouseClicked(event -> {
            //System.out.println("testclick");
            if (run == true) {
                root.getChildren().removeAll(newGameMenu, startMenu, imgView);
                imgView1.setVisible(true);
                tline.play();
            }
        });

        //primaryStage set, prob will be moved
        primaryStage.setScene(scene1);
    //    primaryStage.setScene(tutorial);
        primaryStage.show();
    }

    public void setScoreBoard()
    {
        //in my defense i know how bad this is but im tired :)
        //pls dont hate me robinetto-ben
        int temp = plist.size();
        if(temp == 2)
        {
            if(terriblewayofdoingthis == false)
            {
                scoreBM.getChildren().removeAll(p1SB, p2SB);
            }
            p1SB = new VBox(5);
            Text player1 = new Text("Player 1:");
            Text name = new Text("Name: " + p1.getName());
            Text money = new Text("Money: " + String.valueOf(p1.getMoney()));
            Text LifeC = new Text("LifeCards: " + String.valueOf(p1.getLifeT()));
            Text salery = new Text("Salary: " + String.valueOf(p1.getSalary()));
            p1SB.getChildren().addAll(player1, name, money, LifeC, salery);
            p2SB = new VBox(5);
            Text player2 = new Text("Player 2:");
            Text name2 = new Text("Name: " + p2.getName());
            Text money2 = new Text("Money: " + String.valueOf(p2.getMoney()));
            Text LifeC2 = new Text("LifeCards: " + String.valueOf(p2.getLifeT()));
            Text salery2 = new Text("Salary: " + String.valueOf(p2.getSalary()));
            p2SB.getChildren().addAll(player2, name2, money2, LifeC2, salery2);
            scoreBM.getChildren().addAll(p1SB, p2SB);
        }
        if(temp == 3)
        {
            if(terriblewayofdoingthis == false)
            {
                scoreBM.getChildren().removeAll(p1SB, p2SB, p3SB);
            }
            p1SB = new VBox(5);
            Text player1 = new Text("Player 1:");
            Text name = new Text("Name: " + p1.getName());
            Text money = new Text("Money: " + String.valueOf(p1.getMoney()));
            Text LifeC = new Text("LifeCards: " + String.valueOf(p1.getLifeT()));
            Text salery = new Text("Salary: " + String.valueOf(p1.getSalary()));
            p1SB.getChildren().addAll(player1, name, money, LifeC, salery);
            p2SB = new VBox(5);
            Text player2 = new Text("Player 2:");
            Text name2 = new Text("Name: " + p2.getName());
            Text money2 = new Text("Money: " + String.valueOf(p2.getMoney()));
            Text LifeC2 = new Text("LifeCards: " + String.valueOf(p2.getLifeT()));
            Text salery2 = new Text("Salary: " + String.valueOf(p2.getSalary()));
            p2SB.getChildren().addAll(player2, name2, money2, LifeC2, salery2);
            p3SB = new VBox(5);
            Text player3 = new Text("Player 3:");
            Text name3 = new Text("Name: " + p3.getName());
            Text money3 = new Text("Money: " + String.valueOf(p3.getMoney()));
            Text LifeC3 = new Text("LifeCards: " + String.valueOf(p3.getLifeT()));
            Text salery3 = new Text("Salary: " + String.valueOf(p3.getSalary()));
            p3SB.getChildren().addAll(player3, name3, money3, LifeC3, salery3);
            scoreBM.getChildren().addAll(p1SB, p2SB, p3SB);
        }
        if(temp == 4)
        {
            if(terriblewayofdoingthis == false)
            {
                scoreBM.getChildren().removeAll(p1SB, p2SB, p3SB, p4SB);
            }
            p1SB = new VBox(5);
            Text player1 = new Text("Player 1:");
            Text name = new Text("Name: " + p1.getName());
            Text money = new Text("Money: " + String.valueOf(p1.getMoney()));
            Text LifeC = new Text("LifeCards: " + String.valueOf(p1.getLifeT()));
            Text salery = new Text("Salary: " + String.valueOf(p1.getSalary()));
            p1SB.getChildren().addAll(player1, name, money, LifeC, salery);
            p2SB = new VBox(5);
            Text player2 = new Text("Player 2:");
            Text name2 = new Text("Name: " + p2.getName());
            Text money2 = new Text("Money: " + String.valueOf(p2.getMoney()));
            Text LifeC2 = new Text("LifeCards: " + String.valueOf(p2.getLifeT()));
            Text salery2 = new Text("Salary: " + String.valueOf(p2.getSalary()));
            p2SB.getChildren().addAll(player2, name2, money2, LifeC2, salery2);
            p3SB = new VBox(5);
            Text player3 = new Text("Player 3:");
            Text name3 = new Text("Name: " + p3.getName());
            Text money3 = new Text("Money: " + String.valueOf(p3.getMoney()));
            Text LifeC3 = new Text("LifeCards: " + String.valueOf(p3.getLifeT()));
            Text salery3 = new Text("Salary: " + String.valueOf(p3.getSalary()));
            p3SB.getChildren().addAll(player3, name3, money3, LifeC3, salery3);
            p4SB = new VBox(5);
            Text player4 = new Text("Player 4:");
            Text name4 = new Text("Name: " + p4.getName());
            Text money4 = new Text("Money: " + String.valueOf(p1.getMoney()));
            Text LifeC4 = new Text("LifeCards: " + String.valueOf(p1.getLifeT()));
            Text salery4 = new Text("Salary: " + String.valueOf(p1.getSalary()));
            p4SB.getChildren().addAll(player4, name4, money4, LifeC4, salery4);
            scoreBM.getChildren().addAll(p1SB, p2SB, p3SB, p4SB);
            //see above comment
        }
    }



    public class Menu extends Parent
    {
        //declares all 3 menus one will be main menu, 2nd will be options, 3d will be create new game
        //will probably make a 4th for inside options if im not lazy

        int i = 0;
        VBox m1 = new VBox(15);
        VBox m2 = new VBox(15);
        VBox m3 = new VBox(15);
        VBox m4 = new VBox(15);

        public Menu(int y)
        {
            int x = y;
            //declares all 3 menus one will be main menu, 2nd will be options, 3d will be create new game
            //will probably make a 4th for inside options if im not lazy



            //setting menu1/2 to same y, different x for transition fade
            m1.setTranslateX(80);
            m1.setTranslateY(200);
            m2.setTranslateX(80);
            m2.setTranslateY(200);
            m3.setTranslateX(80);
            m3.setTranslateY(200);
            m4.setTranslateX(80);
            m4.setTranslateY(200);

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
                this.getChildren().remove(m4);

            });
            menuButton btTutorial = new menuButton("Tutorial!");
            btTutorial.setOnKeyPressed(e->{

            });

            menuButton btTwo = new menuButton("Two players");
            menuButton btFour = new menuButton("Four Players");
            menuButton btThree = new menuButton("Three players");
            btFour.setOnMouseClicked(e-> {
                m3.getChildren().removeAll(btFour, btTwo, btThree);
                m3.setTranslateY(m3.getTranslateY()+50);
                p1 = new player();
                p2 = new player();
                p3 = new player();
                p4 = new player();
                plist.add(p1);
                plist.add(p2);
                plist.add(p3);
                plist.add(p4);
                TextField p1name = new TextField("Enter player 1 name");
                TextField p2name = new TextField("Enter player 2 name");
                TextField p3name = new TextField("Enter player 3 name");
                TextField p4name = new TextField("Enter player 4 name");
                m3.getChildren().addAll(p1name, p2name, p3name, p4name);
                p1name.setOnKeyPressed(e1->{
                    if(e1.getCode() == KeyCode.ENTER)
                    {
                        String name = p1name.getText();
                        p1.setName(name);
                        p1name.setVisible(false);

                    }
                });
                p2name.setOnKeyPressed(e1->{
                    if(e1.getCode() == KeyCode.ENTER)
                    {
                        String name = p2name.getText();
                        p2.setName(name);
                        p2name.setVisible(false);
                    }
                });
                p3name.setOnKeyPressed(e1->{
                    if(e1.getCode() == KeyCode.ENTER)
                    {
                        String name = p3name.getText();
                        p3.setName(name);
                        p3name.setVisible(false);

                    }
                });
                p4name.setOnKeyPressed(e1->{
                    if(e1.getCode() == KeyCode.ENTER)
                    {
                        String name = p4name.getText();
                        p4.setName(name);
                        p4name.setVisible(false);
                        run = true;
                        m3.getChildren().removeAll(p1name,p2name, p3name, p4name);
                        game.setVisible(true);

                    }
                });

            });
            btThree.setOnMouseClicked(e->{
                m3.getChildren().removeAll(btFour, btTwo, btThree);
                m3.setTranslateY(m3.getTranslateY()+50);
                p1 = new player();
                p2 = new player();
                p3 = new player();
                plist.add(p1);
                plist.add(p2);
                plist.add(p3);
                TextField p1name = new TextField("Enter player 1 name");
                TextField p2name = new TextField("Enter player 2 name");
                TextField p3name = new TextField("Enter player 3 name");
                m3.getChildren().addAll(p1name, p2name, p3name);
                p1name.setOnKeyPressed(e1->{
                    if(e1.getCode() == KeyCode.ENTER)
                    {
                        String name = p1name.getText();
                        p1.setName(name);
                        p1name.setVisible(false);

                    }
                });
                p2name.setOnKeyPressed(e1->{
                    if(e1.getCode() == KeyCode.ENTER)
                    {
                        String name = p2name.getText();
                        p2.setName(name);
                        p2name.setVisible(false);
                    }
                });
                p3name.setOnKeyPressed(e1->{
                    if(e1.getCode() == KeyCode.ENTER)
                    {
                        String name = p3name.getText();
                        p3.setName(name);
                        run = true;
                        m3.getChildren().removeAll(p1name,p2name, p3name);
                        game.setVisible(true);
                    }
                });

            });
            btTwo.setOnMouseClicked(e->{
                m3.getChildren().removeAll(btFour, btTwo, btThree);
                m3.setTranslateY(m3.getTranslateY()+50);
                p1 = new player();
                p2 = new player();
                plist.add(p1);
                plist.add(p2);
                TextField p1name = new TextField("Enter player 1 name");
                TextField p2name = new TextField("Enter player 2 name");
                m3.getChildren().addAll(p1name, p2name);
                p1name.setOnKeyPressed(e1->{
                    if(e1.getCode() == KeyCode.ENTER)
                    {
                        String name = p1name.getText();
                        p1.setName(name);
                        p1name.setVisible(false);

                    }
                });
                p2name.setOnKeyPressed(e1->{
                    if(e1.getCode() == KeyCode.ENTER)
                    {
                        String name = p2name.getText();
                        p2.setName(name);
                         run = true;
                         m3.getChildren().removeAll(p1name,p2name);
                        game.setVisible(true);
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
                m1.getChildren().addAll(btNewGame,btTutorial, btExit);
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
                this.getChildren().add(m4);

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
        public menuButton(String n, int x, int y)
        {
            //make text object look fancy
            name = new Text(n);
            name.setFill(Color.BLACK);

            //Use rectangles as buttons so you can do fancy fade :)
            Rectangle rec = new Rectangle(x, y);
            rec.setOpacity(0.6);
            rec.setFill(Color.WHITE);

            //location of buttons
            this.setAlignment(Pos.CENTER);
            this.setRotate(-.5);
            getChildren().addAll(rec, name);

            //highlight when entered

            //click effects to simulate depression on click


        }
        public menuButton(String n, int x, int y, Color c)
        {
            int xmove = x;
            int ymove = y;
            Color cchange = c;
            //make text object look fancy
            name = new Text(n);
            name.setFill(Color.BLACK);

            //Use rectangles as buttons so you can do fancy fade :)
            Rectangle rec = new Rectangle(xmove, ymove);
            rec.setOpacity(0.6);
            rec.setFill(cchange);
            rec.setEffect(new GaussianBlur(3.5));

            //location of buttons
            this.setAlignment(Pos.CENTER);
            this.setRotate(-.5);
            getChildren().addAll(rec, name);

            //highlight when entered
//            this.setOnMouseEntered(e->{
//                rec.setTranslateX(10);
//                rec.setFill(Color.YELLOW);
//                name.setTranslateX(10);
//                name.setFill(Color.WHITE);
//            });
//            this.setOnMouseExited(e->{
//                //resets on exit
//                rec.setTranslateX(0);
//                rec.setFill(cchange);
//                name.setTranslateX(0);
//                name.setFill(Color.BLACK);
//            });
            //click effects to simulate depression on click
            DropShadow d = new DropShadow(50, Color.BLACK);
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
