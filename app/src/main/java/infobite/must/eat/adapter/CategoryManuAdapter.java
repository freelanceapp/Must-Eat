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
import infobite.must.eat.modal.default_modal.CategoryManuModel;


public class CategoryManuAdapter extends RecyclerView.Adapter<CategoryManuAdapter.MyViewHolder> {

    ProgressDialog pDialog;
    private List<CategoryManuModel> restaurentModelList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_category_menu;
        public MyViewHolder(View view) {
            super(view);
            tv_category_menu = (TextView) view.findViewById(R.id.tv_category_menu);
        }

        @Override
        public void onClick(View v) {

        }
    }


    public CategoryManuAdapter(List<CategoryManuModel> restaurentModelList, Context context) {
        this.restaurentModelList = restaurentModelList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_restaurent_menu, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CategoryManuModel historyModel = restaurentModelList.get(position);
        holder.tv_category_menu.setText(historyModel.getCategoryMenu());
        //Picasso.with(context).load(memoireModel.getMemoire_img1()).placeholder(R.drawable.img1).resize(500,150).error(R.drawable.img1).noFade().into(holder.memoire_img);
    }
    @Override
    public int getItemCount() {
        return restaurentModelList.size();
    }

}
