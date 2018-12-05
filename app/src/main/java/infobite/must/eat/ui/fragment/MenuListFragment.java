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
import infobite.must.eat.adapter.CategoryManuAdapter;
import infobite.must.eat.adapter.ManuListShowAdapter;
import infobite.must.eat.modal.MenuListModel;

public class MenuListFragment extends Fragment {

    RecyclerView rv_menu_list;
    public View view;
    ManuListShowAdapter manuListShowAdapter;
    ArrayList<MenuListModel> menuListModelArrayList = new ArrayList<>();

    public MenuListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menu_list, container, false);
        rv_menu_list = (RecyclerView)view.findViewById(R.id.rv_menu_list);

        for (int i = 0 ; i < 20 ; i ++) {
            MenuListModel model = new MenuListModel();
            model.setMenu_name("Chicken Soup");
            model.setMenu_price("50");
            menuListModelArrayList.add(model);
        }

        manuListShowAdapter = new ManuListShowAdapter(menuListModelArrayList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        //GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        //rv_menu_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        rv_menu_list.setLayoutManager(mLayoutManager);
        rv_menu_list.setItemAnimator(new DefaultItemAnimator());
        rv_menu_list.setAdapter(manuListShowAdapter);

        return view;
    }

}
