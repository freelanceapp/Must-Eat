package infobite.must.eat.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.modal.default_modal.MenuListModel;
import infobite.must.eat.ui.activities.MenuQuntityActivity;


public class ManuListShowAdapter extends RecyclerView.Adapter<ManuListShowAdapter.MyViewHolder> {

    ProgressDialog pDialog;
    private List<MenuListModel> menuListModelList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_menu_name,tv_menu_price,tv_add_btn;
        public MyViewHolder(View view) {
            super(view);
            tv_menu_name = (TextView) view.findViewById(R.id.tv_menu_name);
            tv_menu_price = (TextView) view.findViewById(R.id.tv_menu_price);
            tv_add_btn = (TextView) view.findViewById(R.id.add_btn);
            tv_add_btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(context,MenuQuntityActivity.class);
            context.startActivity(intent);
        }
    }


    public ManuListShowAdapter(List<MenuListModel> menuListModelList, Context context) {
        this.menuListModelList = menuListModelList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_menu_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MenuListModel menuListModel = menuListModelList.get(position);
        holder.tv_menu_name.setText(menuListModel.getMenu_name());
        holder.tv_menu_price.setText(menuListModel.getMenu_price());


        //Picasso.with(context).load(memoireModel.getMemoire_img1()).placeholder(R.drawable.img1).resize(500,150).error(R.drawable.img1).noFade().into(holder.memoire_img);


    }

    @Override
    public int getItemCount() {
        return menuListModelList.size();
    }

}
