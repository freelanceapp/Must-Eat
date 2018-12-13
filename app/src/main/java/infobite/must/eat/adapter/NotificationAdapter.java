package infobite.must.eat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.api_modal.notification_response.Notification;
import infobite.must.eat.modal.api_modal.offer_response.Coupon;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private List<Notification> notificationList;
    private Context context;

    public NotificationAdapter(List<Notification> notificationList, Context context) {
        this.notificationList = notificationList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_notification, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       // holder.offer_img.setImageResource(reviewModelList.get(position).getCoupon_image());

        holder.title.setText(notificationList.get(position).getNotificationTitle());
        holder.text.setText(notificationList.get(position).getNotificationMessage());
        holder.tv_date.setText(notificationList.get(position).getNotificationDate());


       /* Glide.with(context).load(Constant.BASE_URL+reviewModelList.get(position).getCouponImage())
                .into(holder.offer_img);*/

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView image;
        public TextView title,tv_date,text;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.title);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            text = (TextView) view.findViewById(R.id.text);

        }

        @Override
        public void onClick(View v) {

        }
    }


}
