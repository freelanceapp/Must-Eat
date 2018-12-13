package infobite.must.eat.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.api_modal.vendor_list.VendorList;
import infobite.must.eat.modal.default_modal.ProductDetails;

/**
 * Created by Dell on 12/1/2018.
 */

public class ListGridAdapter extends RecyclerView.Adapter<ListGridAdapter.ProductViewHolder> {

    private Context ctx;
    private List<VendorList> product;
    private String strViewType;
    private View.OnClickListener onClickListener;

    public ListGridAdapter(Context ctx, List<VendorList> product, String strViewType, View.OnClickListener onClickListener) {
        this.ctx = ctx;
        this.product = product;
        this.strViewType = strViewType;
        this.onClickListener = onClickListener;
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

        VendorList getData = product.get(position);

        holder.title.setText(getData.getVendorName());
        holder.subtitle.setText(getData.getVendorStreet());
        holder.ratingbar.setRating(getData.getReviewRate());

        holder.cardViewItem.setTag(position);
        holder.cardViewItem.setOnClickListener(onClickListener);
        holder.ll_more.setTag(position);
        holder.ll_more.setOnClickListener(onClickListener);

        String sImg = Constant.BASE_URL + getData.getVendorLogo();
        Glide.with(ctx).load(sImg)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cardViewItem;
        private ImageView image;
        private LinearLayout ll_more;
        private TextView title, subtitle;
        private RatingBar ratingbar;

        public ProductViewHolder(View itemView) {
            super(itemView);

            cardViewItem = itemView.findViewById(R.id.cardViewItem);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            ll_more = itemView.findViewById(R.id.ll_more);
            ratingbar = itemView.findViewById(R.id.ratingbar);
        }

        @Override
        public void onClick(View view) {
            /*Intent i = new Intent(ctx, RestaurentMenuActivity.class);
            ctx.startActivity(i);*/
        }
    }
}
