package infobite.must.eat.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.adapter.CategoryManuAdapter;
import infobite.must.eat.adapter.ProductListAdapter;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorDetailMainModal;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorItemCategory;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorProduct;
import infobite.must.eat.utils.BaseFragment;

public class RestaurentMenuFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private RecyclerView menu_category_list, rv_menu_list;
    private CategoryManuAdapter adapter;
    private ProductListAdapter productListAdapter;
    private List<VendorItemCategory> itemCategories = new ArrayList<>();
    private List<VendorProduct> vendorProducts = new ArrayList<>();

    public RestaurentMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_restaurent_menu, container, false);
        init();
        return view;
    }

    private void init() {
        mContext = getActivity();
        if (getArguments() == null)
            return;
        Bundle bundle = getArguments();
        String strVendorDetail = bundle.getString("vendor_detail");
        Gson gson = new Gson();
        VendorDetailMainModal mainModal = gson.fromJson(strVendorDetail, VendorDetailMainModal.class);
        itemCategories.addAll(mainModal.getCategory());
        vendorProducts.addAll(mainModal.getProduct());

        menu_category_list = (RecyclerView) view.findViewById(R.id.menu_category_list);
        rv_menu_list = (RecyclerView) view.findViewById(R.id.rv_menu_list);
        setCategoryRecyclerview();
        setProductRecyclerview();
    }

    private void setCategoryRecyclerview() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        mLayoutManager.scrollToPositionWithOffset(0, 0);
        menu_category_list.setLayoutManager(mLayoutManager);
        menu_category_list.setItemAnimator(new DefaultItemAnimator());
        adapter = new CategoryManuAdapter(itemCategories, mContext, this);
        menu_category_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setProductRecyclerview() {
        rv_menu_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_menu_list.setItemAnimator(new DefaultItemAnimator());
        productListAdapter = new ProductListAdapter(vendorProducts, mContext, this);
        rv_menu_list.setAdapter(productListAdapter);
        productListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_category_menu:
                int pos = Integer.parseInt(v.getTag().toString());
                VendorItemCategory category = itemCategories.get(pos);
                ((TextView) view.findViewById(R.id.category_name)).setText(category.getCategoryName());
                break;
        }
    }
}
