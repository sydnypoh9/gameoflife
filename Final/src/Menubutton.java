import javafx.scene.control.Button;
import java.awt.*;

/**
 * Created by Ben on 5/18/2017.
 */
public class Menubutton {
    final int menuDefaultx = 200, menuDefaulty = 100;
    Button b;
    public Menubutton(String s)
    {
        Rectangle rec = new Rectangle(menuDefaultx, menuDefaulty);
        b = new Button(s);
        b.equals(rec);

    }
    public Menubutton(String s, int x, int y)
    {
        Rectangle rec = new Rectangle(x, y);
        b = new Button(s);
        b.equals(rec);
}
    public Button getB()
    {
        return b;
    }
}
