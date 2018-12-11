package infobite.must.eat.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.adapter.ReviewAdapter;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorDetailMainModal;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorReview;
import infobite.must.eat.modal.default_modal.ReviewModel;

public class RestaurentReviewFragment extends Fragment {

    private View view;
    private RecyclerView review_list;
    private ReviewAdapter reviewAdapter;
    private List<VendorReview> reviewModelArrayList = new ArrayList<>();

    public RestaurentReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_restaurent_review, container, false);
        init();
        return view;
    }

    private void init() {
        review_list = (RecyclerView) view.findViewById(R.id.review_list);

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


}
