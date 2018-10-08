package taskcom.android.manish.taskapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import taskcom.android.manish.taskapp.R;
import taskcom.android.manish.taskapp.adapter.OtpSentRecyclerAdapter;
import taskcom.android.manish.taskapp.model.Contact;
import taskcom.android.manish.taskapp.model.ContactMessage;
import taskcom.android.manish.taskapp.model.Message;
import taskcom.android.manish.taskapp.rest.ApiClient;
import taskcom.android.manish.taskapp.rest.ApiService;
import taskcom.android.manish.taskapp.rest.ServerResponseMessageList;
import taskcom.android.manish.taskapp.utils.Constants;
import taskcom.android.manish.taskapp.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtpSentFragment extends Fragment {


    private static final String TAG = OtpSentFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private ArrayList<ContactMessage> contactMessageArrayList;
    private OtpSentRecyclerAdapter adapter;
    private ArrayList<Contact> contactArrayList;

    public OtpSentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_otp_sent, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            contactArrayList = (ArrayList<Contact>) bundle.getSerializable(Constants.KEY_CONTACT_LIST);
        }


        recyclerView = view.findViewById(R.id.recycler_view_otp_sent);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        String credential = "Basic " + Base64.encodeToString(
                (Constants.ACCOUNT_SID + ":" + Constants.AUTH_TOKEN).getBytes(), Base64.NO_WRAP
        );

        String contentType = "application/json";
        getOtpSentList(Constants.ACCOUNT_SID, credential, contentType);
        return view;
    }

    private void getOtpSentList(String accountSid, String auth, String mimeType) {
        Utils.showProgressDialog(getContext());
        contactMessageArrayList = new ArrayList<>();
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ServerResponseMessageList> call = apiService.getOtpSentMessagesList(accountSid, auth, mimeType);
        call.enqueue(new Callback<ServerResponseMessageList>() {
            @Override
            public void onResponse(Call<ServerResponseMessageList> call, Response<ServerResponseMessageList> response) {
                Utils.dismissProgressDialog();
                ArrayList<Message> messageArrayList = response.body().messageArrayList;
                for (Message message : messageArrayList) {
                    String createdDate = message.date_created;
                    String contact = message.to;
                    String otpMessage = message.body;
                    String otp = Utils.getCharactersFromLast(otpMessage, 6);
                    String name = Utils.getNameFromList(contactArrayList, contact);
                    ContactMessage contactMessage = new ContactMessage(name, contact, otp, createdDate);
                    contactMessageArrayList.add(contactMessage);
                    Utils.log(TAG, "Created Date : " + createdDate + "  ---  " + contact + "---" + otp + "--" + name);
                }

                OtpSentRecyclerAdapter otpSentRecyclerAdapter = new OtpSentRecyclerAdapter(contactMessageArrayList, getContext());
                recyclerView.setAdapter(otpSentRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<ServerResponseMessageList> call, Throwable t) {
                Log.d(TAG, "onFailure get : " + t.getMessage());
                Utils.dismissProgressDialog();
            }
        });
    }
}
