package infobite.must.eat.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import infobite.must.eat.R;
import infobite.must.eat.adapter.CategoryManuAdapter;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.default_modal.CategoryManuModel;

public class RestaurentMenuFragment extends Fragment {
    public View view;
    public RecyclerView menu_category_list;
    CategoryManuAdapter adapter;
    FragmentManager fragmentManager;
    ArrayList<CategoryManuModel> categoryManuModelArrayList = new ArrayList<>();
    public RestaurentMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_restaurent_menu, container, false);
        menu_category_list = (RecyclerView)view.findViewById(R.id.menu_category_list);

        fragmentManager = getActivity().getSupportFragmentManager();
        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.menu_frame, new MenuListFragment(),
                            Constant.MenuListFragment).commit();
        }

        for (int i = 0 ; i <10 ; i++) {
            CategoryManuModel categoryManuModel = new CategoryManuModel();
            categoryManuModel.setCategoryMenu("Special Offers");
            categoryManuModelArrayList.add(categoryManuModel);
        }
        adapter = new CategoryManuAdapter(categoryManuModelArrayList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        //GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        menu_category_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        //menu_category_list.setLayoutManager(mLayoutManager);
        menu_category_list.setItemAnimator(new DefaultItemAnimator());
        menu_category_list.setAdapter(adapter);

        return view;
    }


}
