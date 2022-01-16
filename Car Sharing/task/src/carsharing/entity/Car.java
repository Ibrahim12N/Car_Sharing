package carsharing.entity;

public class Car {

    private final int id;
    private final String name;
    private final int companyID;

    public Car(int id, String name, int companyID) {
        this.id = id;
        this.name = name;
        this.companyID = companyID;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getCompanyID() {
        return companyID;
    }



    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", companyID=" + companyID +
                '}';
    }
}