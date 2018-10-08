package taskcom.android.manish.taskapp.db;

import android.app.Activity;
import android.content.Context;
import android.view.animation.AnimationUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import taskcom.android.manish.taskapp.adapter.ContactListRecyclerAdapter;
import taskcom.android.manish.taskapp.model.Contact;
import taskcom.android.manish.taskapp.utils.Utils;

public class SampleDatabase {
    private Context context;
    private static final String TAG = SampleDatabase.class.getSimpleName();

    public SampleDatabase(Context context) {
        this.context = context;
    }

    public String getContactJson() {
        String jsonString = null;
        try {
            InputStream is = context.getAssets().open("contact_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return jsonString;
    }

    public ArrayList<Contact> getContactArrayList() {
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        String contactArrayJson = getContactJson();
        //Utils.log(TAG, "Contact JSON : " + contactArrayJson);
        try {
            JSONObject jsonObject = new JSONObject(contactArrayJson);
            JSONArray jsonArray = jsonObject.getJSONArray("contacts");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject contactJson = jsonArray.getJSONObject(i);
                int id = contactJson.getInt("id");
                String name = contactJson.getString("name");
                String contactNumber = contactJson.getString("contact");
                String countryCode = contactJson.getString("country_code");
                Contact contact = new Contact(id, name, contactNumber, countryCode);
                contactArrayList.add(contact);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return contactArrayList;
    }
}
