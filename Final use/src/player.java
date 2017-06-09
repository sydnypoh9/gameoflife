import java.util.ArrayList;

/**
 * Created by Ben on 5/17/2017.
 */
public class player {
    private int money, lifeT, salary;
    private ArrayList<Integer> loc = new ArrayList<>();
    private String name;
    public player()
    {
        money = 0;
        lifeT = 0;
        salary = 0;
        name = null;
    }
    public player(String n)
    {
        money = 0;
        lifeT = 0;
        salary = 0;
        name = n;
    }
    public player(int m, int l, int s, String n)
    {
        money = m;
        lifeT = l;
        salary = s;
        name = n;
        for(int i = 0; i < 130; i++)
        {
            loc.add(i, i+1);
        }
    }
    public int getMoney() {
        return money;
    }

    public int getLifeT() {
        return lifeT;
    }

    public int getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setLifeT(int lifeT) {
        this.lifeT = lifeT;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setName(String name) {
        if(name.length() > 16)
        {
            name = name.substring(0, 15);
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "player{" +
                "money=" + money +
                ", lifeT=" + lifeT +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                '}';
    }
}
