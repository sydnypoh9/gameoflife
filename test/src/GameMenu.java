import com.sun.scenario.effect.Offset;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameMenu extends Application {
    public Menu gameMenu;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Pane root = new Pane();
        root.setPrefSize(1920, 1080);

        InputStream is = Files.newInputStream(Paths.get("res/1.jpg"));
        Image img = new Image(is);
        is.close();

        ImageView imgView = new ImageView(img);
        gameMenu = new Menu();

        root.getChildren().addAll(imgView, gameMenu);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ESCAPE)
            {
                if(!gameMenu.isVisible())
                {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.7), gameMenu);
                    ft.setFromValue(0);
                    ft.setToValue(1);


                    gameMenu.setVisible(true);
                    ft.play();
                }
                else
                {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.7), gameMenu);
                    ft.setFromValue(1);
                    ft.setFromValue(0);
                    ft.setOnFinished(evt -> gameMenu.setVisible(false));
                    ft.play();
                }
            }

        });
     //   primaryStage.setTitle("Please don't change the size of this window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public class Menu extends Parent {
        public Menu()
        {
            VBox menu1 = new VBox(10);
            VBox menu2 = new VBox(10);
            VBox menu3 = new VBox(10);

            menu1.setTranslateX(100);
            menu1.setTranslateY(200);

            menu2.setTranslateX(100);
            menu2.setTranslateY(200);

            menu3.setTranslateX(100);
            menu3.setTranslateY(200);

            final int offset = 400;
            menu2.setTranslateX(offset);

            MenuButton btnResume = new MenuButton("Resume");
            btnResume.setOnMouseClicked(event -> {
                FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
                ft.setToValue(1);
                ft.setToValue(0);
                ft.setOnFinished(evt -> this.setVisible(false));
                ft.play();
            });
            MenuButton btnOptions = new MenuButton("Options");
            btnOptions.setOnMouseClicked(event -> {

                getChildren().add(menu2);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu2);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });
            MenuButton btnexit = new MenuButton("Exit");
            btnexit.setOnMouseClicked(event -> {
                System.exit(0);
            });
            MenuButton btnBack = new MenuButton("Back");
            btnBack.setOnMouseClicked(event -> {
                getChildren().addAll(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu2);
                tt.setToX(menu2.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt1.setToX((menu2.getTranslateX()));

                tt.play();
                tt1.play();
                tt.setOnFinished(evt -> {
                    getChildren().remove(menu2);
                });

            });

            MenuButton btnStart = new MenuButton("Start Game");
            btnStart.setOnMouseClicked(event -> {
                FadeTransition ft = new FadeTransition(Duration.seconds(0.0), gameMenu);
                ft.setFromValue(1);
                ft.setFromValue(0);
                ft.setOnFinished(evt -> gameMenu.setVisible(false));
                ft.play();
            });

            MenuButton btnSound = new MenuButton("Sound");
            MenuButton btnVideo = new MenuButton("Video");

            menu1.getChildren().addAll(btnStart, btnOptions, btnexit);
            menu2.getChildren().addAll(btnSound, btnVideo, btnBack);
            Rectangle bg = new Rectangle(1920, 1080);
            bg.setFill(Color.GREY);
            bg.setOpacity(0.4);
            getChildren().addAll(bg, menu1);
            // menu3.getChildren().addAll(btnResume, btnOptions, btnexit);
        }
    }
    private static class MenuButton extends StackPane
    {
        private Text text;
        public MenuButton(String name)
            {
                text = new Text(name);
                text.setFont(text.getFont().font(20));
                text.setFill(Color.WHITE);

                Rectangle bg = new Rectangle(200, 30);
                bg.setOpacity(0.6);
                bg.setFill(Color.BLACK);
            bg.setEffect(new GaussianBlur(3.5));

            this.setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(bg, text);

            this.setOnMouseEntered(event -> {
                bg.setTranslateX(10);
                text.setTranslateX(10);
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });
            this.setOnMouseExited(event -> {
                bg.setTranslateX(0);
                text.setTranslateX(0);
                bg.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });
            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());
            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));
        }
    }
    public static void main (String args[])
    {
        launch(args);
    }
}
