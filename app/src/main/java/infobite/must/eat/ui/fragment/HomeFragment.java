package infobite.must.eat.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import infobite.must.eat.R;
import infobite.must.eat.adapter.FavRestaurentListAdapter;
import infobite.must.eat.adapter.RecyclerViewDataAdapter;
import infobite.must.eat.adapter.SectionListDataAdapter;
import infobite.must.eat.modal.HomeRestaurentModel;
import infobite.must.eat.modal.SectionDataModel;
import infobite.must.eat.ui.activities.RestaurantsActivity;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private View view;
    private RecyclerView recommendation_list, most_list, fav_list;
    private TextView popular_btn, recommendation_btn, fav_btn;
    private ArrayList<HomeRestaurentModel> homeRestaurentModelArrayList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        recommendation_list = (RecyclerView) view.findViewById(R.id.recommendation_list);
        most_list = (RecyclerView) view.findViewById(R.id.most_list);
        fav_list = (RecyclerView) view.findViewById(R.id.fav_list);
        popular_btn = (TextView) view.findViewById(R.id.popular_btn);
        recommendation_btn = (TextView) view.findViewById(R.id.recommendation_btn);
        fav_btn = (TextView) view.findViewById(R.id.fav_btn);
        //tooltext.setText("Home");

        for (int i = 0; i < 16; i++) {
            HomeRestaurentModel homeRestaurentModel = new HomeRestaurentModel();
            homeRestaurentModel.setHr_img1(R.drawable.bg_food);
            homeRestaurentModel.setHr_namne("Apna");
            homeRestaurentModel.setHr_address("Vijay nagar");
            homeRestaurentModelArrayList.add(homeRestaurentModel);
        }

        SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(getActivity(), homeRestaurentModelArrayList);
        recommendation_list.setHasFixedSize(true);
        recommendation_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recommendation_list.setAdapter(itemListDataAdapter);

        most_list.setHasFixedSize(true);
        most_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        most_list.setAdapter(itemListDataAdapter);

        FavRestaurentListAdapter favRestaurentListAdapter = new FavRestaurentListAdapter(getActivity(), homeRestaurentModelArrayList);
        fav_list.setHasFixedSize(true);
        fav_list.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false));
        fav_list.setAdapter(favRestaurentListAdapter);

        popular_btn.setOnClickListener(this);
        fav_btn.setOnClickListener(this);
        recommendation_btn.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recommendation_btn:
                Intent intent = new Intent(getActivity(), RestaurantsActivity.class);
                startActivity(intent);
                break;

            case R.id.popular_btn:
                Intent intent1 = new Intent(getActivity(), RestaurantsActivity.class);
                startActivity(intent1);
                break;

            case R.id.fav_btn:
                Intent intent2 = new Intent(getActivity(), RestaurantsActivity.class);
                startActivity(intent2);
                break;

        }
    }
}
