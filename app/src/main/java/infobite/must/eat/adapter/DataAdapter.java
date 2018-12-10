package infobite.must.eat.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.modal.default_modal.ProductDetails;
import infobite.must.eat.ui.activities.RestaurentMenuActivity;

/**
 * Created by Dell on 12/1/2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ProductViewHolder> {

    private Context ctx;
    private List<ProductDetails> product;
    private String strViewType;

    public DataAdapter(Context ctx, List<ProductDetails> product, String strViewType) {
        this.ctx = ctx;
        this.product = product;
        this.strViewType = strViewType;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view;
        if (strViewType.equals("list")) {
            view = inflater.inflate(R.layout.row_list_items, null);
        } else {
            view = inflater.inflate(R.layout.row_grid_items, null);
        }

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        ProductDetails getData = product.get(position);

        holder.image.setImageDrawable(ctx.getResources().getDrawable(Integer.parseInt(String.valueOf(getData.getImage()))));
        holder.title.setText(getData.getTitle());
        holder.subtitle.setText(getData.getSubtitle());
        //holder.more.setText(getData.getMore());
        //holder.imagetwo.setImageDrawable(ctx.getResources().getDrawable(Integer.parseInt(String.valueOf(getData.getImagetwo()))));

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cardViewItem;
        ImageView image, imagetwo;
        LinearLayout ll_more;
        TextView title, subtitle;

        public ProductViewHolder(View itemView) {
            super(itemView);

            cardViewItem = itemView.findViewById(R.id.cardViewItem);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            ll_more = itemView.findViewById(R.id.ll_more);
            imagetwo = itemView.findViewById(R.id.imagetwo);

            ll_more.setOnClickListener(this);
            cardViewItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(ctx, RestaurentMenuActivity.class);
            ctx.startActivity(i);
        }
    }
}
