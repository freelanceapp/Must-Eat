package infobite.must.eat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorReview;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    private List<VendorReview> reviewModelList;
    private Context context;

    public ReviewAdapter(List<VendorReview> reviewModelList, Context context) {
        this.reviewModelList = reviewModelList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_review, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_review_name.setText("Demo");
        holder.tv_review_comment.setText("Abc");
        holder.tv_review_date.setText(reviewModelList.get(position).getReviewDate());

        String strRate = reviewModelList.get(position).getReviewRate();
        float rating = Float.parseFloat(strRate);
        holder.rb_review.setRating(rating);
    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tv_review_name, tv_review_comment, tv_review_date, rb_raview;
        public RatingBar rb_review;

        public MyViewHolder(View view) {
            super(view);
            tv_review_name = (TextView) view.findViewById(R.id.tv_review_name);
            tv_review_comment = (TextView) view.findViewById(R.id.tv_review_comment);
            tv_review_date = (TextView) view.findViewById(R.id.tv_review_date);
            rb_review = (RatingBar) view.findViewById(R.id.rb_review_number);
        }

        @Override
        public void onClick(View v) {

        }
    }


}
