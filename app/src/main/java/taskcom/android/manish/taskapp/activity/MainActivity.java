package taskcom.android.manish.taskapp.activity;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import taskcom.android.manish.taskapp.R;
import taskcom.android.manish.taskapp.adapter.ContactListTabAdapter;
import taskcom.android.manish.taskapp.fragments.ContactListFragment;
import taskcom.android.manish.taskapp.fragments.OtpSentFragment;
import taskcom.android.manish.taskapp.interfaces.DataSender;
import taskcom.android.manish.taskapp.model.Contact;
import taskcom.android.manish.taskapp.utils.Constants;
import taskcom.android.manish.taskapp.utils.Utils;

public class MainActivity extends AppCompatActivity implements DataSender {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ContactListTabAdapter adapter;
    private OtpSentFragment otpSentFragment;
    private ContactListFragment contactListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setSupportActionBar(toolbar);
        adapter = new ContactListTabAdapter(getSupportFragmentManager());
        contactListFragment = new ContactListFragment();
        otpSentFragment = new OtpSentFragment();

        adapter.addTab(contactListFragment, getString(R.string.txt_all_contacts));
        adapter.addTab(otpSentFragment, getString(R.string.txt_sent));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar_main_activity);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setMessage(R.string.txt_exit_app_alert).setPositiveButton(R.string.txt_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.super.onBackPressed();
            }
        }).setNegativeButton(R.string.txt_no, null).show();
    }

    //Get contactArrayList from ContactListFragment
    @Override
    public void onDataSend(ArrayList<Contact> contactArrayList) {
        // Now send this contactArrayList to OtpSentFragment
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_CONTACT_LIST, contactArrayList);
        otpSentFragment.setArguments(bundle);
    }
}
