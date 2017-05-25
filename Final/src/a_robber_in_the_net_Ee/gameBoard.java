package a_robber_in_the_net_Ee;

/**
 * Created by Ben on 5/15/2017.
 */
public class gameBoard {
    public int gbx, gby;
    gameSquare gs;

    public gameBoard()
    {
        gbx = 0;
        gby = 0;
        gs = null;
    }
    public gameBoard(int xC, int yC)
    {
        gbx = xC;
        gby = yC;
    }

    public gameBoard(int xC, int yC, gameSquare gsC)
    {
        gbx = xC;
        gby = yC;
        gs = gsC;
    }

    @Override
    public String toString() {
        return "gameBoard{" +
                "x=" + gbx +
                ", y=" + gby +
                ", gs=" + gs +
                '}';
    }
}
