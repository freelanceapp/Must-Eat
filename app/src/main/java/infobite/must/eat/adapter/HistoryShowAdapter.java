package infobite.must.eat.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.modal.HistoryModel;


public class HistoryShowAdapter extends RecyclerView.Adapter<HistoryShowAdapter.MyViewHolder> {

    ProgressDialog pDialog;
    private List<HistoryModel> restaurentModelList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView h_name,h_address,h_orderid,h_items,h_price,h_delivery_time;
        public ImageView h_img;
        public MyViewHolder(View view) {
            super(view);
            h_name = (TextView) view.findViewById(R.id.h_name);
            h_address = (TextView) view.findViewById(R.id.h_address);
            h_delivery_time = (TextView) view.findViewById(R.id.h_delivered_time);
            h_img = (ImageView)view.findViewById(R.id.h_img);
            h_items = (TextView)view.findViewById(R.id.h_itmes);
            h_orderid = (TextView)view.findViewById(R.id.h_orderid);
            h_price = (TextView)view.findViewById(R.id.h_price);
        }

        @Override
        public void onClick(View v) {

        }
    }


    public HistoryShowAdapter(List<HistoryModel> restaurentModelList, Context context) {
        this.restaurentModelList = restaurentModelList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_history, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HistoryModel historyModel = restaurentModelList.get(position);
        holder.h_name.setText(historyModel.getH_name());
        holder.h_address.setText(historyModel.getH_address());
        holder.h_orderid.setText(historyModel.getH_orderid());
        holder.h_price.setText(historyModel.getH_total_price());
        holder.h_img.setImageResource(historyModel.getH_img1());
        holder.h_delivery_time.setText(historyModel.getH_delivery_time());
        holder.h_items.setText(historyModel.getH_items());

        //Picasso.with(context).load(memoireModel.getMemoire_img1()).placeholder(R.drawable.img1).resize(500,150).error(R.drawable.img1).noFade().into(holder.memoire_img);


    }

    @Override
    public int getItemCount() {
        return restaurentModelList.size();
    }

}
