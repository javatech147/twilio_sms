package taskcom.android.manish.taskapp.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class Message {

    public String sid;
    public String date_created;
    public String date_updated;
    public String date_sent;
    public String account_sid;
    public String to;
    public String from;
    public String messaging_service_sid;
    public String body;
    public String status;
    public String num_segments;
    public String num_media;
    public String direction;
    public String api_version;
    public String price;
    public String price_unit;
    public String error_code;
    public String error_message;
    public String uri;
    public JSONObject subresource_uris;
}
