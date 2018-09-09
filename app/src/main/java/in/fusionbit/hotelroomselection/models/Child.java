package in.fusionbit.hotelroomselection.models;

public class Child {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws IllegalArgumentException {
        if (age > 18) {
            throw new IllegalArgumentException("age should be below 18");
        }
        this.age = age;
    }
}
