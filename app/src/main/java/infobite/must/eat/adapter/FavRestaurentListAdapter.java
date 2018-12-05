package infobite.must.eat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import infobite.must.eat.R;
import infobite.must.eat.modal.HomeRestaurentModel;


public class FavRestaurentListAdapter extends RecyclerView.Adapter<FavRestaurentListAdapter.SingleItemRowHolder> {

    private ArrayList<HomeRestaurentModel> itemsList;
    private Context mContext;

    public FavRestaurentListAdapter(Context context, ArrayList<HomeRestaurentModel> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_fav_home_restaurent, null);
            SingleItemRowHolder mh = new SingleItemRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        HomeRestaurentModel singleItem = itemsList.get(i);

            holder.restaurent_name.setText(singleItem.getHr_namne());

            holder.restaurent_img.setImageResource(singleItem.getHr_img1());

           // Picasso.with(mContext).load("http://62.210.110.20:100"+singleItem.getImg()).placeholder(R.drawable.img11).resize(300, 300).into(holder.itemImage);


            holder.restaurent_address.setText(singleItem.getHr_address());

       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/

    }
    @Override
    public int getItemCount() {

        //return 4;
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView restaurent_address,restaurent_name;

        protected ImageView restaurent_img;


        public SingleItemRowHolder(final View view) {
            super(view);

            this.restaurent_address = (TextView) view.findViewById(R.id.restaurent_address);
            this.restaurent_img = (ImageView) view.findViewById(R.id.restaurent_img);
            this.restaurent_name = (TextView) view.findViewById(R.id.restaurent_name);

        }
    }




}
