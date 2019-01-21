package pages;

public class Dog extends Animal{

    public Dog (String name) {
        setName(name);
    }

    public void bark() {
        System.out.println(getClass() + " " + getName() + "is barking....");
    }
}
