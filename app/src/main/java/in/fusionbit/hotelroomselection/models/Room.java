package in.fusionbit.hotelroomselection.models;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private List<Adult> adultList = new ArrayList<>();

    private List<Child> childList = new ArrayList<>();

    public List<Adult> getAdultList() {
        return adultList;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        String childDetails = "Child Details: \n";
        String adultDetails = "Adult Details: \n";

        for (Child child : childList) {
            sb.append("Age = ");
            sb.append(String.valueOf(child.getAge()));
            sb.append("\n");
        }
        childDetails = childDetails + sb;

        for (Adult adult : adultList) {
            sb2.append("Age = ");
            sb2.append(String.valueOf(adult.getAge()));
            sb2.append("\n");
        }
        adultDetails = adultDetails + sb2;

        return adultDetails + childDetails;

    }
}
