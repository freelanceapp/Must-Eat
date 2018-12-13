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


public class RestaurentListAdapter extends RecyclerView.Adapter<RestaurentListAdapter.MyViewHolder> {

    private List<VendorList> vendorLists;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public RestaurentListAdapter(List<VendorList> vendorLists, Context mContext, View.OnClickListener onClickListener) {
        this.vendorLists = vendorLists;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_restaurent_list_a, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.restaurent_name.setText(vendorLists.get(position).getVendorName());
        holder.restaurent_address.setText(vendorLists.get(position).getVendorStreet());
        holder.cardViewItem.setTag(position);
        holder.cardViewItem.setOnClickListener(onClickListener);

        String sImg = Constant.BASE_URL + vendorLists.get(position).getVendorLogo();
        Glide.with(mContext).load(sImg)
                .into(holder.rc_img);

    }

    @Override
    public int getItemCount() {
        return vendorLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView restaurent_name, restaurent_address;
        public ImageView rc_img;
        private CardView cardViewItem;

        public MyViewHolder(View view) {
            super(view);
            cardViewItem = view.findViewById(R.id.cardViewItem);
            restaurent_address = view.findViewById(R.id.restaurent_address);
            restaurent_name = view.findViewById(R.id.restaurent_name);
            rc_img = view.findViewById(R.id.restaurent_img);
        }
    }

}
