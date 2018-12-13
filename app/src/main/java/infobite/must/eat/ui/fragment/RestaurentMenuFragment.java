package infobite.must.eat.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import infobite.must.eat.ui.activities.MenuQuntityActivity;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.BaseFragment;

public class RestaurentMenuFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private RecyclerView menu_category_list, rv_menu_list;
    private CategoryManuAdapter adapter;
    private ProductListAdapter productListAdapter;
    private List<VendorItemCategory> itemCategories = new ArrayList<>();
    private List<VendorProduct> vendorProducts = new ArrayList<>();
    private List<VendorProduct> newProductsList;
    private String strTypeCategory = "";
    private VendorDetailMainModal mainModal;

    public RestaurentMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_restaurent_menu, container, false);
        init();
        return rootView;
    }

    private void init() {
        mContext = getActivity();
        if (getArguments() == null)
            return;
        strTypeCategory = "All products";
        Bundle bundle = getArguments();
        String strVendorDetail = bundle.getString("vendor_detail");
        Gson gson = new Gson();
        mainModal = gson.fromJson(strVendorDetail, VendorDetailMainModal.class);
        itemCategories.addAll(mainModal.getCategory());
        vendorProducts.addAll(mainModal.getProduct());

        menu_category_list = rootView.findViewById(R.id.menu_category_list);
        rv_menu_list = rootView.findViewById(R.id.rv_menu_list);
        setCategoryRecyclerview();
        setProductRecyclerview(vendorProducts);
    }

    private void setCategoryRecyclerview() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mLayoutManager.scrollToPositionWithOffset(0, 0);
        menu_category_list.setLayoutManager(mLayoutManager);
        menu_category_list.setItemAnimator(new DefaultItemAnimator());
        adapter = new CategoryManuAdapter(itemCategories, mContext, this);
        menu_category_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setProductRecyclerview(List<VendorProduct> productsList) {
        rv_menu_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_menu_list.setItemAnimator(new DefaultItemAnimator());
        productListAdapter = new ProductListAdapter(productsList, mContext, this);
        rv_menu_list.setAdapter(productListAdapter);
        productListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_category_menu:
                filterProductsByCategory(v);
                break;
            case R.id.llItem:
                VendorProduct product;
                int pos = Integer.parseInt(v.getTag().toString());
                if (strTypeCategory.equals("All products")) {
                    product = vendorProducts.get(pos);
                } else {
                    product = newProductsList.get(pos);
                }
                String strQuantity = productListAdapter.getQuantity();
                Intent intent = new Intent(mContext, MenuQuntityActivity.class);
                intent.putExtra("product_detail", (Parcelable) product);
                intent.putExtra("strQuantity", strQuantity);
                intent.putExtra("restaurent_id", mainModal.getVendor().getVendorId());
                mContext.startActivity(intent);
                break;
        }
    }

    private void filterProductsByCategory(View view) {
        int pos = Integer.parseInt(view.getTag().toString());
        VendorItemCategory category = itemCategories.get(pos);
        strTypeCategory = category.getCategoryName();
        ((TextView) rootView.findViewById(R.id.category_name)).setText(category.getCategoryName());
        newProductsList = new ArrayList<>();
        for (int i = 0; i < vendorProducts.size(); i++) {
            if (category.getCategoryId().equals(vendorProducts.get(i).getProductCategoryId())) {
                VendorProduct product;
                product = vendorProducts.get(i);
                newProductsList.add(product);
            }
        }
        setProductRecyclerview(newProductsList);
    }
}
