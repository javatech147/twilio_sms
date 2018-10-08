package taskcom.android.manish.taskapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import taskcom.android.manish.taskapp.R;
import taskcom.android.manish.taskapp.model.ContactMessage;
import taskcom.android.manish.taskapp.utils.Utils;

public class OtpSentRecyclerAdapter extends RecyclerView.Adapter<OtpSentRecyclerAdapter.OtpSentViewHolder> {

    private static final String TAG = OtpSentRecyclerAdapter.class.getSimpleName();
    private ArrayList<ContactMessage> contactMessageArrayList;
    // Used for set Image -> Picasso
    private Context context;


    public OtpSentRecyclerAdapter(ArrayList<ContactMessage> contactMessageArrayList, Context context) {
        this.contactMessageArrayList = contactMessageArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public OtpSentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_row_otp_sent, viewGroup, false);
        return new OtpSentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtpSentViewHolder otpSentViewHolder, int i) {
        otpSentViewHolder.tvName.setText(contactMessageArrayList.get(i).name);
        otpSentViewHolder.tvContact.setText(contactMessageArrayList.get(i).contact);
        otpSentViewHolder.tvOtp.setText(contactMessageArrayList.get(i).otp);
        //otpSentViewHolder.tvTime.setText(contactMessageArrayList.get(i).otpTime.substring(0, 25));
        otpSentViewHolder.tvTime.setText(Utils.convertUTCTimeToLocalTime(contactMessageArrayList.get(i).otpTime));
    }

    @Override
    public int getItemCount() {
        return contactMessageArrayList.size();
    }

    public class OtpSentViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvContact;
        public TextView tvOtp;
        public TextView tvTime;

        public OtpSentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_otp_contact_name);
            tvContact = itemView.findViewById(R.id.tv_otp_contact_number);
            tvOtp = itemView.findViewById(R.id.tv_otp);
            tvTime = itemView.findViewById(R.id.tv_otp_time);

        }
    }
}
