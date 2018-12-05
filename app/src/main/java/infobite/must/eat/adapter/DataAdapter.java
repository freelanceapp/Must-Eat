package infobite.must.eat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.modal.ProductDetails;

/**
 * Created by Dell on 12/1/2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ProductViewHolder> {

    private Context ctx;
    private List<ProductDetails> product;

    public DataAdapter(Context ctx, List<ProductDetails> product) {
        this.ctx = ctx;
        this.product = product;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.recylerestaurantdesign, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        ProductDetails getData = product.get(position);

        holder.image.setImageDrawable(ctx.getResources().getDrawable(Integer.parseInt(String.valueOf(getData.getImage()))));
        holder.title.setText(getData.getTitle());
        holder.subtitle.setText(getData.getSubtitle());
        holder.more.setText(getData.getMore());
        //holder.imagetwo.setImageDrawable(ctx.getResources().getDrawable(Integer.parseInt(String.valueOf(getData.getImagetwo()))));

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView image, imagetwo;
        TextView title, subtitle, more;

        public ProductViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            more = itemView.findViewById(R.id.more);
            imagetwo = itemView.findViewById(R.id.imagetwo);
        }
    }
}
