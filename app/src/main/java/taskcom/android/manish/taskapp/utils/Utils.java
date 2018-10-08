package taskcom.android.manish.taskapp.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import taskcom.android.manish.taskapp.model.Contact;
import taskcom.android.manish.taskapp.model.Message;

public class Utils {
    private static ProgressDialog progressDialog;

    public static void log(String tag, String message) {
        Log.d(String.valueOf(tag), String.valueOf(message));
    }


    public static void toast(Context conext, String message) {
        Toast.makeText(conext, "" + message, Toast.LENGTH_SHORT).show();
    }


    public static int generateSixDigitRandomOtp() {
        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000);
        return randomNumber;
    }


    public static String getNameFromList(ArrayList<Contact> contactArrayList, String phone) {
        Utils.log("TEST", "Phone to check : " + phone);
        for (Contact contact : contactArrayList) {
            String phoneFromContact = contact.countryCode + contact.number.trim();
            Utils.log("TEST", "Phone from contact : " + phoneFromContact);
            if (phoneFromContact.equals(phone.trim())) {
                return contact.name;
            }
        }
        return null;
    }

    public static String getCharactersFromLast(String word, int noOfCharactersFromLast) {
        if (word.length() == noOfCharactersFromLast) {
            return word;
        } else if (word.length() > noOfCharactersFromLast) {
            return word.substring(word.length() - noOfCharactersFromLast);
        } else {
            // whatever is appropriate in this case
            throw new IllegalArgumentException("word has less than " + noOfCharactersFromLast + " characters!");
        }
    }

    public static ProgressDialog showProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("");
        progressDialog.setMessage("please wait ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }

    public static void dismissProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    // a - AM or PM
    // hh - Hour in 12 Hr format.
    public static String convertUTCTimeToLocalTime(String dateInUTC) {
        java.text.DateFormat dateFormatUTC = new SimpleDateFormat("EE, dd MMM yyyy HH:mm:ss Z");
        dateFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date utcDate = dateFormatUTC.parse(dateInUTC);
            java.text.DateFormat currentDateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a"); // a for AM/PM
            String localTimeZone = Calendar.getInstance().getTimeZone().getID();
            currentDateFormat.setTimeZone(TimeZone.getTimeZone(localTimeZone));
            String localeDate = currentDateFormat.format(utcDate);
            return localeDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
