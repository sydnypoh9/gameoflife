package a_robber_in_the_net_Ee;

/**
 * Created by Ben on 5/17/2017.
 */
public class player {
    private int money, lifeT, salary;
    private String name;
    public player()
    {

    }
    public player(int m, int l, int s, String n)
    {
        money = m;
        lifeT = l;
        salary = s;
        name = n;
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
