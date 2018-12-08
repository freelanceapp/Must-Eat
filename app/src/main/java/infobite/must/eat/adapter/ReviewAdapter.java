package infobite.must.eat.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.modal.default_modal.ReviewModel;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    ProgressDialog pDialog;
    private List<ReviewModel> reviewModelList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_review_name,tv_review_comment,tv_review_date,rb_raview;
        public RatingBar rb_review;
        public MyViewHolder(View view) {
            super(view);
            tv_review_name = (TextView) view.findViewById(R.id.tv_review_name);
            tv_review_comment = (TextView) view.findViewById(R.id.tv_review_comment);
            tv_review_date = (TextView) view.findViewById(R.id.tv_review_date);
            rb_review = (RatingBar)view.findViewById(R.id.rb_review_number);

        }

        @Override
        public void onClick(View v) {

        }
    }


    public ReviewAdapter(List<ReviewModel> reviewModelList, Context context) {
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
        ReviewModel reviewModel = reviewModelList.get(position);
        holder.tv_review_name.setText(reviewModel.getReview_name());
        holder.tv_review_comment.setText(reviewModel.getReview_comment());
        holder.tv_review_date.setText(reviewModel.getReview_date());


        //Picasso.with(context).load(memoireModel.getMemoire_img1()).placeholder(R.drawable.img1).resize(500,150).error(R.drawable.img1).noFade().into(holder.memoire_img);


    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

}
