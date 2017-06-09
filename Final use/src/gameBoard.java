/**
 * Created by Ben on 5/15/2017.
 */
public class gameBoard {
    public int gbx, gby;
    payDay pd1;
    Stop stp1;
    LifeCard lfc1;
    loseMoney lmoney;

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
       this.gbx = xC;
        this.gby = yC;
        this.pd1 = pd;
    }
    public gameBoard(int xC, int yC, Stop s)
    {
        this.gbx = xC;
        this.gby = yC;
        this.stp1 = s;
    }
    public gameBoard(int xC, int yC, LifeCard lc)
    {
        this.gbx = xC;
        this.gby = yC;
        this.lfc1 = lc;
    }

    public int getGbx() {
        return gbx;
    }

    public int getGby() {
        return gby;
    }

    public gameBoard(int xC, int yC, int lose)
    {
        this.gbx = xC;
        this.gby = yC;
        this.lmoney = new loseMoney(lose);

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
