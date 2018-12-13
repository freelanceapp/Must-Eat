package infobite.must.eat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.api_modal.offer_response.Coupon;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorReview;
import infobite.must.eat.modal.default_modal.OffersModel;


public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder> {

    private List<Coupon> reviewModelList;
    private Context context;

    public OffersAdapter(List<Coupon> reviewModelList, Context context) {
        this.reviewModelList = reviewModelList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_offers, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       // holder.offer_img.setImageResource(reviewModelList.get(position).getCoupon_image());

        Glide.with(context).load(Constant.BASE_URL+reviewModelList.get(position).getCouponImage())
                .into(holder.offer_img);

    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView offer_img;

        public MyViewHolder(View view) {
            super(view);
            offer_img = (ImageView) view.findViewById(R.id.coupan_img);

        }

        @Override
        public void onClick(View v) {

        }
    }


}
