package taskcom.android.manish.taskapp.rest;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import taskcom.android.manish.taskapp.model.Message;

public class ServerResponseMessageList {

    //@Element(name = "first_page_uri")
    @SerializedName("first_page_uri")
    private String firstPageUri;

    //@Element(name = "end")
    @SerializedName("end")
    private int noOfMessages;

    //@Element(name = "messages")
    @SerializedName("messages")
    public ArrayList<Message> messageArrayList;
}
