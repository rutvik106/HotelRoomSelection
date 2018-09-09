package in.fusionbit.hotelroomselection.models;

public class Adult {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws IllegalArgumentException {
        if (age < 18) {
            throw new IllegalArgumentException("age should be above 18");
        }
        this.age = age;
    }
}
