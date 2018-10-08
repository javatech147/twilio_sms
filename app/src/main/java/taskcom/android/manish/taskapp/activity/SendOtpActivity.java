package taskcom.android.manish.taskapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import taskcom.android.manish.taskapp.R;
import taskcom.android.manish.taskapp.model.Contact;
import taskcom.android.manish.taskapp.rest.ApiClient;
import taskcom.android.manish.taskapp.rest.ApiService;
import taskcom.android.manish.taskapp.utils.Constants;
import taskcom.android.manish.taskapp.utils.Utils;

public class SendOtpActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = SendOtpActivity.class.getSimpleName();
    private Context context;
    private Toolbar toolbar;
    private TextView tvOtpText;
    private TextView tvBtnSendOtp;
    private ImageView ivBackImage;
    private Contact contact;
    private String otpText;
    private static final String FROM = "+18507572855";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        initializeViews();
        contact = (Contact) getIntent().getSerializableExtra(Constants.KEY_CONTACT);
        context = SendOtpActivity.this;
    }

    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar_send_otp_activity);
        ivBackImage = findViewById(R.id.iv_back_image);
        ivBackImage.setVisibility(View.VISIBLE);
        ivBackImage.setOnClickListener(this);

        TextView tvToolbarText = toolbar.findViewById(R.id.tv_toolbar_text);
        tvToolbarText.setText(getString(R.string.txt_send_otp));
        tvOtpText = findViewById(R.id.tv_otp_text);
        otpText = "Hi. Your OTP is : " + Utils.generateSixDigitRandomOtp();
        tvOtpText.setText(otpText);

        tvBtnSendOtp = findViewById(R.id.btn_send_otp);
        tvBtnSendOtp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_image:
                onBackPressed();
                break;

            case R.id.btn_send_otp:
                String to = contact.countryCode + contact.number;
                Utils.log(TAG, "To Phone Number : " + to);

                String credential = "Basic " + Base64.encodeToString(
                        (Constants.ACCOUNT_SID + ":" + Constants.AUTH_TOKEN).getBytes(), Base64.NO_WRAP
                );

                String conentType = "application/json";

                Map<String, String> data = new HashMap<>();
                data.put("From", FROM);
                data.put("To", to);
                data.put("Body", otpText);
                // data.put("Name", "Manish");
                Utils.log(TAG, "Json Data : " + data);
                sendOtp(Constants.ACCOUNT_SID, credential, conentType, data);
                break;

        }
    }

    private void sendOtp(String accountSid, String credential, String contentType, Map<String, String> data) {
        Utils.showProgressDialog(this);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ResponseBody> responseBodyCall = apiService.sendOtp(accountSid, credential, contentType, data);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Utils.dismissProgressDialog();
                if (response != null) {
                    switch (response.code()) {
                        case 201:
                            Utils.toast(context, "Otp Send successfully.");
                            Intent homeIntent = new Intent(context, MainActivity.class);
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(homeIntent);
                            finishAffinity();
                            break;

                        default:
                            try {
                                String errorString = response.errorBody().string();
                                Utils.log(TAG, "Some Error occurs : " + errorString);
                                JSONObject jsonObject = new JSONObject(errorString);
                                String errorMessage = jsonObject.getString("message");
                                Utils.toast(context, "" + errorMessage);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Utils.log(TAG, "onFailure : " + t.getMessage());
                Utils.dismissProgressDialog();
            }
        });
    }
}
