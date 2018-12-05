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
import infobite.must.eat.modal.DayModel;
import infobite.must.eat.modal.HistoryModel;


public class OpenDayAdapter extends RecyclerView.Adapter<OpenDayAdapter.MyViewHolder> {

    ProgressDialog pDialog;
    private List<DayModel> dayModelList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_day,tv_time;
        public MyViewHolder(View view) {
            super(view);
            tv_day = (TextView) view.findViewById(R.id.tv_day);
            tv_time = (TextView) view.findViewById(R.id.tv_time);

        }

        @Override
        public void onClick(View v) {

        }
    }


    public OpenDayAdapter(List<DayModel> restaurentModelList, Context context) {
        this.dayModelList = restaurentModelList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_day, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DayModel historyModel = dayModelList.get(position);
        holder.tv_day.setText(historyModel.getDay());
        holder.tv_time.setText(historyModel.getTime());

        //Picasso.with(context).load(memoireModel.getMemoire_img1()).placeholder(R.drawable.img1).resize(500,150).error(R.drawable.img1).noFade().into(holder.memoire_img);


    }

    @Override
    public int getItemCount() {
        return dayModelList.size();
    }

}
