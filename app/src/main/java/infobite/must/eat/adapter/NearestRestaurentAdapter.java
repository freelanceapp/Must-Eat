package infobite.must.eat.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.api_modal.vendor_list.VendorList;
import infobite.must.eat.modal.api_modal.vendor_list.VendorOpeningClosingTime;


public class NearestRestaurentAdapter extends RecyclerView.Adapter<NearestRestaurentAdapter.MyViewHolder> {

    private List<VendorList> vendorLists;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public NearestRestaurentAdapter(List<VendorList> vendorLists, Context mContext, View.OnClickListener onClickListener) {
        this.vendorLists = vendorLists;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_restaurant, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.rc_name.setText(vendorLists.get(position).getVendorName());
        float rate = vendorLists.get(position).getReviewRate();
        holder.rc_rate.setRating(rate);
        holder.cardViewItem.setTag(position);
        holder.cardViewItem.setOnClickListener(onClickListener);

        List<VendorOpeningClosingTime> times = new ArrayList<>(vendorLists.get(position).getVendorOpeningClosingTime());
        String strDay = getCurrentDay();
        int hour = getHour(getTime());
        for (int i = 0; i < times.size(); i++) {
            if (strDay.equalsIgnoreCase(times.get(i).getWeek())) {
                int startHourB = getHour(times.get(i).getStart());
                int endHourB = getHour(times.get(i).getEnd());

                if (hour >= startHourB && hour <= endHourB) {
                    holder.rc_type.setText("OPEN");
                    holder.rc_type.setTextColor(mContext.getResources().getColor(R.color.colorGreen));
                } else {
                    holder.rc_type.setText("CLOSE");
                    holder.rc_type.setTextColor(mContext.getResources().getColor(R.color.colorRed));
                }
            }
        }

        String sImg = Constant.BASE_URL + vendorLists.get(position).getVendorLogo();
        Glide.with(mContext).load(sImg)
                .into(holder.rc_img);

    }

    private int getHour(String strTime) {
        String[] strH = strTime.split(":");
        if (strH[0].isEmpty()) {
            strH[0] = "0";
        }
        return Integer.parseInt(strH[0]);
    }

    @SuppressLint("SimpleDateFormat")
    private String getCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        return sdf.format(d);
    }

    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date());
    }

    @Override
    public int getItemCount() {
        return vendorLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView rc_name, rc_type, rc_offer;
        public ImageView rc_img;
        public RatingBar rc_rate;
        private CardView cardViewItem;

        public MyViewHolder(View view) {
            super(view);
            cardViewItem = view.findViewById(R.id.cardViewItem);
            rc_name = view.findViewById(R.id.rc_name);
            rc_offer = view.findViewById(R.id.rc_offer);
            rc_type = view.findViewById(R.id.rc_type);
            rc_img = view.findViewById(R.id.rc_img);
            rc_rate = view.findViewById(R.id.rc_rate);
        }
    }

}
