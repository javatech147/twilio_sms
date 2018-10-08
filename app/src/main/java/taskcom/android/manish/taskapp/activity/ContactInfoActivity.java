package taskcom.android.manish.taskapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import taskcom.android.manish.taskapp.R;
import taskcom.android.manish.taskapp.model.Contact;
import taskcom.android.manish.taskapp.utils.Constants;

public class ContactInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvContactName;
    private TextView tvContactNumber;
    private ImageView ivBackImage;
    private TextView tvBtnSendMessage;
    private Contact contact;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        initializeViews();
        setSupportActionBar(toolbar);
        contact = (Contact) getIntent().getSerializableExtra(Constants.KEY_CONTACT);
        if (contact != null) {
            tvContactName.setText(contact.name);
            tvContactNumber.setText(contact.number);
        }
    }

    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar_contact_info_activity);
        ivBackImage = toolbar.findViewById(R.id.iv_back_image);
        TextView tvToolbarText = toolbar.findViewById(R.id.tv_toolbar_text);
        tvToolbarText.setText(getString(R.string.txt_contact_info));

        ivBackImage.setVisibility(View.VISIBLE);
        ivBackImage.setOnClickListener(this);
        tvContactName = findViewById(R.id.tv_info_contact_name);
        tvContactNumber = findViewById(R.id.tv_info_contact_number);
        tvBtnSendMessage = findViewById(R.id.btn_send_message);
        tvBtnSendMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_message:
                Intent sendOtpIntent = new Intent(this, SendOtpActivity.class);
                sendOtpIntent.putExtra(Constants.KEY_CONTACT, contact);
                startActivity(sendOtpIntent);
                break;

            case R.id.iv_back_image:
                onBackPressed();
                break;
        }
    }
}