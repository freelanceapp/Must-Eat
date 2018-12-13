package infobite.must.eat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import infobite.must.eat.R;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorOpenCloseTime;


public class OpenDayAdapter extends RecyclerView.Adapter<OpenDayAdapter.MyViewHolder> {

    private List<VendorOpenCloseTime> dayModelList;
    private Context context;

    public OpenDayAdapter(List<VendorOpenCloseTime> restaurentModelList, Context context) {
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

        String strDays = capitalize(dayModelList.get(position).getWeek());
        holder.tv_day.setText(strDays);
        String strStart = dayModelList.get(position).getStart();
        String strEnd = dayModelList.get(position).getEnd();
        holder.tv_time.setText(strStart + " - " + strEnd);
    }

    private String capitalize(String capString) {
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()) {
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }
        return capMatcher.appendTail(capBuffer).toString();
    }

    @Override
    public int getItemCount() {
        return dayModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_day, tv_time;

        public MyViewHolder(View view) {
            super(view);
            tv_day = (TextView) view.findViewById(R.id.tv_day);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
