package infobite.must.eat.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import infobite.must.eat.R;
import infobite.must.eat.adapter.RecyclerViewDataAdapter;
import infobite.must.eat.modal.HomeRestaurentModel;
import infobite.must.eat.modal.SectionDataModel;


public class HomeFragment extends Fragment {
    public View view;
    RecyclerView home_list;
    ArrayList<SectionDataModel> allSampleData = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        home_list = (RecyclerView)view.findViewById(R.id.home_list);
        //tooltext.setText("Home");

        for(int i = 0 ; i < 2 ; i++) {
            SectionDataModel dm = new SectionDataModel();
            dm.setHeaderTitle("Recommendations for you");
            dm.setButtonName("View All");
            ArrayList<HomeRestaurentModel> singleItem = new ArrayList<HomeRestaurentModel>();
            for (int k = 0; k < 5; k++) {
                HomeRestaurentModel singleItemModel = new HomeRestaurentModel();
                singleItemModel.setHr_img1(R.drawable.bg_food);
                singleItemModel.setHr_namne("Restaurent Name");
                singleItemModel.setHr_address("Address of the restaurant");
                singleItem.add(singleItemModel);
                //singleItem.add(new SingleItemModel(listen.get(j).getName() , " " , R.drawable.img12));
            }
            dm.setHomeRestaurentModelArrayList(singleItem);
            allSampleData.add(dm);


            SectionDataModel dm1 = new SectionDataModel();
            dm1.setHeaderTitle("Most Popular");
            dm1.setButtonName("View All");
            ArrayList<HomeRestaurentModel> singleItem11 = new ArrayList<HomeRestaurentModel>();
            for (int k = 0; k < 5; k++) {
                HomeRestaurentModel singleItemModel1 = new HomeRestaurentModel();
                singleItemModel1.setHr_img1(R.drawable.bg_food);
                singleItemModel1.setHr_namne("Restaurent Name");
                singleItemModel1.setHr_address("Address of the restaurant");
                singleItem11.add(singleItemModel1);
                //singleItem.add(new SingleItemModel(listen.get(j).getName() , " " , R.drawable.img12));
            }
            dm1.setHomeRestaurentModelArrayList(singleItem11);
            allSampleData.add(dm1);

        }
        home_list.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(getActivity(), allSampleData);
        home_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        home_list.setAdapter(adapter);

        return view;
    }



}
