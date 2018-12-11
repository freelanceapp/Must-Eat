package infobite.must.eat.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorItemCategory;
import infobite.must.eat.modal.default_modal.CategoryManuModel;


public class CategoryManuAdapter extends RecyclerView.Adapter<CategoryManuAdapter.MyViewHolder> {

    private List<VendorItemCategory> itemCategories;
    private Context context;
    private View.OnClickListener onClickListener;

    public CategoryManuAdapter(List<VendorItemCategory> itemCategories, Context context, View.OnClickListener onClickListener) {
        this.itemCategories = itemCategories;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_restaurent_menu, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_category_menu.setText(itemCategories.get(position).getCategoryName());
        holder.tv_category_menu.setTag(position);
        holder.tv_category_menu.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return itemCategories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_category_menu;

        public MyViewHolder(View view) {
            super(view);
            tv_category_menu = (TextView) view.findViewById(R.id.tv_category_menu);
        }
    }
}
