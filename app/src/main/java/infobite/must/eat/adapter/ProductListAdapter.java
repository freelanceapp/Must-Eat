package infobite.must.eat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorProduct;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

    private String strQuantity = "";
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
        holder.llItem.setTag(position);
        holder.llItem.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tv_menu_name, tv_menu_price, tvQuantity;
        private LinearLayout llItem;
        private ImageView imgSubtract, imgAdd;

        public MyViewHolder(View view) {
            super(view);
            tv_menu_name = view.findViewById(R.id.tv_menu_name);
            tv_menu_price = view.findViewById(R.id.tv_menu_price);
            tvQuantity = view.findViewById(R.id.tvQuantity);
            llItem = view.findViewById(R.id.llItem);
            imgSubtract = view.findViewById(R.id.imgSubtract);
            imgAdd = view.findViewById(R.id.imgAdd);

            imgAdd.setOnClickListener(this);
            imgSubtract.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imgAdd:
                    int quantityA = Integer.parseInt(tvQuantity.getText().toString());
                    if (quantityA < 6) {
                        quantityA += 1;
                        strQuantity = String.valueOf(quantityA);
                        tvQuantity.setText(String.valueOf(quantityA));
                    }
                    break;
                case R.id.imgSubtract:
                    int quantityB = Integer.parseInt(tvQuantity.getText().toString());
                    if (quantityB > 1) {
                        quantityB = quantityB - 1;
                        tvQuantity.setText(String.valueOf(quantityB));
                        strQuantity = String.valueOf(quantityB);
                    }
                    break;
            }
        }
    }

    public String getQuantity() {
        return strQuantity;
    }
}
