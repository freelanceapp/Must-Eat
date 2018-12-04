package infobite.must.eat.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import infobite.must.eat.R;
import infobite.must.eat.modal.RestaurentModel;


public class RestaurentShowAdapter extends RecyclerView.Adapter<RestaurentShowAdapter.MyViewHolder> {

    ProgressDialog pDialog;
    private ArrayList<RestaurentModel> restaurentModelList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView rc_name, rc_type, rc_offer;
        public ImageView rc_img;
        public RatingBar rc_rate;
        public MyViewHolder(View view) {
            super(view);
            rc_name = (TextView) view.findViewById(R.id.rc_name);
            rc_offer = (TextView) view.findViewById(R.id.rc_offer);
            rc_type = (TextView) view.findViewById(R.id.rc_type);
            rc_img = (ImageView)view.findViewById(R.id.rc_img);
            rc_rate = (RatingBar)view.findViewById(R.id.rc_rate);
        }

        @Override
        public void onClick(View v) {

        }
    }


    public RestaurentShowAdapter(ArrayList<RestaurentModel> restaurentModelList, Context context) {
        this.restaurentModelList = restaurentModelList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_restaurant, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RestaurentModel restaurentModel = restaurentModelList.get(position);
        holder.rc_name.setText(restaurentModel.getR_name());
        holder.rc_type.setText(restaurentModel.getR_type());
        holder.rc_offer.setText(restaurentModel.getR_discount());
        holder.rc_img.setImageResource(restaurentModel.getR_img1());

        //Picasso.with(context).load(memoireModel.getMemoire_img1()).placeholder(R.drawable.img1).resize(500,150).error(R.drawable.img1).noFade().into(holder.memoire_img);


    }

    @Override
    public int getItemCount() {
        return restaurentModelList.size();
    }

}
