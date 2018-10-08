package taskcom.android.manish.taskapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import taskcom.android.manish.taskapp.R;
import taskcom.android.manish.taskapp.adapter.ContactListRecyclerAdapter;
import taskcom.android.manish.taskapp.db.SampleDatabase;
import taskcom.android.manish.taskapp.interfaces.DataSender;
import taskcom.android.manish.taskapp.model.Contact;
import taskcom.android.manish.taskapp.utils.Constants;
import taskcom.android.manish.taskapp.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment {


    private RecyclerView recyclerView;
    private ContactListRecyclerAdapter adapter;
    private ArrayList<Contact> contactArrayList;
    private DataSender dataSender;

    public ContactListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataSender = (DataSender) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        initializeViews(view);
        SampleDatabase sampleDatabase = new SampleDatabase(getContext());
        contactArrayList = sampleDatabase.getContactArrayList();
        dataSender.onDataSend(contactArrayList);

        adapter = new ContactListRecyclerAdapter(contactArrayList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void initializeViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_all_contacts);
    }


}
