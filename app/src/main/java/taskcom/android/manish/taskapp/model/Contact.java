package taskcom.android.manish.taskapp.model;

import java.io.Serializable;

public class Contact implements Serializable {
    public int id;
    public String name;
    public String number;
    public String countryCode;
    public String image;

    public Contact(int id, String name, String number, String countryCode) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.countryCode = countryCode;
    }
}
