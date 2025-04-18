package group31.classes.Membership;

public class Membership {
    private String customer;
    private int cost = 10;
    private String description = "Gives access to Gym and Workout classes";


    //constructors
    public Membership() {
        this.customer = "missing";
    }

    public Membership(String customer) {
        this.customer = customer;
    }

    //getters and setters
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
