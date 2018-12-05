package infobite.must.eat.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import infobite.must.eat.R;
import infobite.must.eat.adapter.HistoryShowAdapter;
import infobite.must.eat.adapter.ReviewAdapter;
import infobite.must.eat.modal.ReviewModel;

public class RestaurentReviewFragment extends Fragment {
    public View view;

    RecyclerView review_list;
    ReviewAdapter reviewAdapter;
    ArrayList<ReviewModel> reviewModelArrayList = new ArrayList<>();
    public RestaurentReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_restaurent_review, container, false);
        review_list = (RecyclerView)view.findViewById(R.id.review_list);

        for (int i = 0 ; i < 20 ; i++) {
            ReviewModel reviewModel = new ReviewModel();
            reviewModel.setReview_name("Devid");
            reviewModel.setReview_comment("KFjerivb lkvhwe lkvhw iwh ");
            reviewModel.setReview_date("nov 20, 2018");
            reviewModelArrayList.add(reviewModel);
        }

        reviewAdapter = new ReviewAdapter(reviewModelArrayList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        //GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        review_list.setLayoutManager(mLayoutManager);
        review_list.setItemAnimator(new DefaultItemAnimator());
        review_list.setAdapter(reviewAdapter);

        return view;
    }


}
