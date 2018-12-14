package infobite.must.eat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.modal.CartItemDetailModal;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorItemCategory;


public class CheckoutItemAdapter extends RecyclerView.Adapter<CheckoutItemAdapter.MyViewHolder> {

    private List<CartItemDetailModal> itemCategories;
    private Context context;
    private View.OnClickListener onClickListener;

    public CheckoutItemAdapter(List<CartItemDetailModal> itemCategories, Context context, View.OnClickListener onClickListener) {
        this.itemCategories = itemCategories;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_checkout_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvQuantity.setText(itemCategories.get(position).getProductQuantity());
        holder.tvItemName.setText(itemCategories.get(position).getProductName());
        holder.tvPrice.setText(itemCategories.get(position).getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return itemCategories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvQuantity, tvItemName, tvPrice;

        public MyViewHolder(View view) {
            super(view);
            tvQuantity = view.findViewById(R.id.tvQuantity);
            tvItemName = view.findViewById(R.id.tvItemName);
            tvPrice = view.findViewById(R.id.tvPrice);
        }
    }
}
