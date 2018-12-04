package infobite.must.eat.Fragment;

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
import infobite.must.eat.modal.HistoryModel;


public class HistoryFragment extends Fragment {
    public View view;
    RecyclerView history_list;
    ArrayList<HistoryModel> historyModelArrayList = new ArrayList<>();
    HistoryShowAdapter showAdapter;
    public HistoryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_history, container, false);
        //tooltext.setText("Order History");
        history_list = (RecyclerView)view.findViewById(R.id.history_list);

        for (int i = 0 ; i < 10 ; i++) {
            HistoryModel historyModel = new HistoryModel();
            historyModel.setH_address("Restaurent Address");
            historyModel.setH_name("Restaurent Name");
            historyModel.setH_items("1* item name");
            historyModel.setH_img1(R.drawable.bg_food);
            historyModel.setH_delivery_time("Dec. 15, 2018 at 11:00 AM");
            historyModel.setH_orderid("ABCD1234");
            historyModel.setH_total_price("$ 228.45");
            historyModelArrayList.add(historyModel);
        }


        showAdapter = new HistoryShowAdapter(historyModelArrayList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        //GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        history_list.setLayoutManager(mLayoutManager);
        history_list.setItemAnimator(new DefaultItemAnimator());
        history_list.setAdapter(showAdapter);
        return view;
    }


}
