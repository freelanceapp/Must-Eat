package infobite.must.eat.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorProduct;
import infobite.must.eat.ui.activities.MenuQuntityActivity;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

    private List<VendorProduct> productList;
    private Context context;
    private View.OnClickListener onClickListener;

    public ProductListAdapter(List<VendorProduct> productList, Context context, View.OnClickListener onClickListener) {
        this.productList = productList;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_menu_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_menu_name.setText(productList.get(position).getProductName());
        holder.tv_menu_price.setText(productList.get(position).getProductFullRate());
        holder.tv_add_btn.setTag(position);
        holder.tv_add_btn.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tv_menu_name, tv_menu_price, tv_add_btn;

        public MyViewHolder(View view) {
            super(view);
            tv_menu_name = (TextView) view.findViewById(R.id.tv_menu_name);
            tv_menu_price = (TextView) view.findViewById(R.id.tv_menu_price);
            tv_add_btn = (TextView) view.findViewById(R.id.add_btn);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, MenuQuntityActivity.class);
            context.startActivity(intent);
        }
    }

}
