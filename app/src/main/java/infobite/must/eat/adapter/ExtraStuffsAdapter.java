package infobite.must.eat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import infobite.must.eat.R;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorOpenCloseTime;
import infobite.must.eat.modal.default_modal.ExtraStuffItemsModal;


public class ExtraStuffsAdapter extends RecyclerView.Adapter<ExtraStuffsAdapter.MyViewHolder> {

    private String strItemPrice = "0.0";
    private List<ExtraStuffItemsModal> items;
    private Context context;
    private View.OnClickListener checkedChangeListener;

    public ExtraStuffsAdapter(List<ExtraStuffItemsModal> items, Context context, View.OnClickListener checkedChangeListener) {
        this.items = items;
        this.context = context;
        this.checkedChangeListener = checkedChangeListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_extra_stuff_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ExtraStuffItemsModal stuffItemsModal = items.get(position);
        holder.checkboxItem.setText(stuffItemsModal.getItem());
        holder.tvPrice.setText(stuffItemsModal.getPrice());

        holder.checkboxItem.setTag(position);
        holder.checkboxItem.setOnClickListener(checkedChangeListener);
/*
        holder.checkboxItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                stuffItemsModal.setSelected(isChecked);
                if (isChecked) {
                    strItemPrice = stuffItemsModal.getPrice();
                } else {
                    strItemPrice = "-" + stuffItemsModal.getPrice();
                }
            }
        });
*/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvPrice;
        private CheckBox checkboxItem;

        public MyViewHolder(View view) {
            super(view);
            checkboxItem = view.findViewById(R.id.checkboxItem);
            tvPrice = view.findViewById(R.id.tvPrice);
        }
    }

    public String getItemPrice() {
        return strItemPrice;
    }

}
