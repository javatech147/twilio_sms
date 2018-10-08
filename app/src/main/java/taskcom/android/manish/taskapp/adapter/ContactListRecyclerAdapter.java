package taskcom.android.manish.taskapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import taskcom.android.manish.taskapp.R;
import taskcom.android.manish.taskapp.activity.ContactInfoActivity;
import taskcom.android.manish.taskapp.model.Contact;
import taskcom.android.manish.taskapp.utils.Constants;

public class ContactListRecyclerAdapter extends RecyclerView.Adapter<ContactListRecyclerAdapter.ContactListViewHolder> {
    public ArrayList<Contact> contactArrayList;
    public Context context;


    public ContactListRecyclerAdapter(ArrayList<Contact> contactArrayList, Context context) {
        this.contactArrayList = contactArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_row_contact_list, viewGroup, false);
        return new ContactListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListViewHolder contactListViewHolder, int i) {
        contactListViewHolder.tvName.setText(contactArrayList.get(i).name);
        contactListViewHolder.tvContactNumber.setText(contactArrayList.get(i).number);
    }


    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }

    public class ContactListViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvContactNumber;

        public ContactListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_contact_name);
            tvContactNumber = itemView.findViewById(R.id.tv_contact_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent contactInfoIntent = new Intent(context, ContactInfoActivity.class);
                    contactInfoIntent.putExtra(Constants.KEY_CONTACT, contactArrayList.get(getAdapterPosition()));
                    context.startActivity(contactInfoIntent);
                }
            });
        }
    }
}
