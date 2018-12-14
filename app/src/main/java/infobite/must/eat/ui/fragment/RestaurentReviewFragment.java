package infobite.must.eat.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.adapter.ReviewAdapter;
import infobite.must.eat.modal.User;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorDetailMainModal;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorReview;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.BaseFragment;

public class RestaurentReviewFragment extends BaseFragment implements View.OnClickListener {

    private float userRating = 0;
    private View view;
    private RecyclerView review_list;
    private ReviewAdapter reviewAdapter;
    private List<VendorReview> reviewModelArrayList = new ArrayList<>();
    private Dialog dialogReview;

    public RestaurentReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_restaurent_review, container, false);
        init();
        return view;
    }

    private void init() {
        mContext = getActivity();
        review_list = view.findViewById(R.id.review_list);
        view.findViewById(R.id.tvAddReview).setOnClickListener(this);

        if (getArguments() == null)
            return;
        Bundle bundle = getArguments();
        String strVendorDetail = bundle.getString("vendor_detail");
        Gson gson = new Gson();
        VendorDetailMainModal mainModal = gson.fromJson(strVendorDetail, VendorDetailMainModal.class);
        reviewModelArrayList.addAll(mainModal.getReview());

        reviewAdapter = new ReviewAdapter(reviewModelArrayList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        review_list.setLayoutManager(mLayoutManager);
        review_list.setItemAnimator(new DefaultItemAnimator());
        review_list.setAdapter(reviewAdapter);
        reviewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvAddReview:
                reviewDialog();
                break;
        }
    }

    private void reviewDialog() {
        dialogReview = new Dialog(mContext);
        dialogReview.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogReview.setContentView(R.layout.dialog_review);

        dialogReview.setCanceledOnTouchOutside(true);
        dialogReview.setCancelable(true);
        if (dialogReview.getWindow() != null)
            dialogReview.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ((TextView) dialogReview.findViewById(R.id.tvUsername)).setText(User.getUser().getUser().getUserFullname());
        ((RatingBar) dialogReview.findViewById(R.id.ratingbar)).
                setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> userRating = rating);

        dialogReview.findViewById(R.id.btnSubmit).setOnClickListener(v -> {
            dialogReview.dismiss();
            Alerts.show(mContext, String.valueOf(userRating));
        });

        Window window = dialogReview.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        dialogReview.show();
    }
}
