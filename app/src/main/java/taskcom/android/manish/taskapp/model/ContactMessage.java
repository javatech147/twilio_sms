package taskcom.android.manish.taskapp.model;

public class ContactMessage {
    public String name;
    public String contact;
    public String otp;
    public String otpTime;

    public ContactMessage(String name, String contact, String otp, String otpTime) {
        this.name = name;
        this.contact = contact;
        this.otp = otp;
        this.otpTime = otpTime;
    }
}
