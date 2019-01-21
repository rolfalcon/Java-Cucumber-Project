package pages;

import java.sql.SQLOutput;

public abstract class Animal {

    private String name;

    public Animal() {
        this.name = "stupid";
    }

    public Animal(String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void walk () {
        System.out.println(getClass() + " " + getName() + "is walking....");

    }

    public void sleep () {
        System.out.println(getClass() + " " + getName() + "is sleeping....");
    }

    public void eat (String food) {
        System.out.println(getClass() + " " + getName() + "is eating "+ food);
    }
}
