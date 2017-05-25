/**
 * Created by Ben on 5/15/2017.
 */
public class gameBoard {
    public int gbx, gby;
    payDay pd1;

    public gameBoard()
    {
        gbx = 0;
        gby = 0;
        pd1 = null;
    }
    public gameBoard(int xC, int yC)
    {
        gbx = xC;
        gby = yC;
    }

    public gameBoard(int xC, int yC, payDay pd)
    {
        gbx = xC;
        gby = yC;
        pd1 = pd;
    }

    @Override
    public String toString() {
        return "gameBoard{" +
                "x=" + gbx +
                ", y=" + gby +
                ", gs=" + pd1 +
                '}';
    }
}
