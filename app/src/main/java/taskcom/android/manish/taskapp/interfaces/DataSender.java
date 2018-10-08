package taskcom.android.manish.taskapp.interfaces;

import java.util.ArrayList;

import taskcom.android.manish.taskapp.model.Contact;

public interface DataSender{
        public void onDataSend(ArrayList<Contact> contactArrayList);
    }