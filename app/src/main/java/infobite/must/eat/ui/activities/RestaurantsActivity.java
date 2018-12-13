package infobite.must.eat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.adapter.ListGridAdapter;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorDetailMainModal;
import infobite.must.eat.modal.api_modal.vendor_list.VendorList;
import infobite.must.eat.modal.api_modal.vendor_list.VendorListMainModal;
import infobite.must.eat.modal.default_modal.ProductDetails;
import infobite.must.eat.utils.BaseActivity;

/**
 * Created by Dell on 12/1/2018.
 */

public class RestaurantsActivity extends BaseActivity implements View.OnClickListener {

    private List<VendorList> product = new ArrayList<>();
    private RecyclerView recylerestaurant;
    private String strListType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        mContext = this;
        init();
    }

    private void init() {
        findViewById(R.id.list_btn).setOnClickListener(this);
        findViewById(R.id.grid_btn).setOnClickListener(this);

        recylerestaurant = findViewById(R.id.recylerestaurant);

        if (getIntent() == null)
            return;
        String strVendorListJson;
        strVendorListJson = getIntent().getStringExtra("all_vendor");
        Gson gson = new Gson();
        VendorListMainModal mainModal = gson.fromJson(strVendorListJson, VendorListMainModal.class);

        product.addAll(mainModal.getVendor());

        setItemAdapter((new LinearLayoutManager(RestaurantsActivity.this, LinearLayoutManager.VERTICAL, false)),
                "list");
        strListType = "list";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_btn:
                setItemAdapter((new LinearLayoutManager(RestaurantsActivity.this, LinearLayoutManager.VERTICAL, false)),
                        "list");
                strListType = "list";
                break;
            case R.id.grid_btn:
                setItemAdapter((new GridLayoutManager(RestaurantsActivity.this, 2)),
                        "grid");
                strListType = "grid";
                break;
            case R.id.ll_more:
                if (strListType.equalsIgnoreCase("list")) {
                    int pos = Integer.parseInt(v.getTag().toString());
                    VendorList vendorList = product.get(pos);
                    Intent i = new Intent(mContext, RestaurentMenuActivity.class);
                    i.putExtra("vendor_id", vendorList.getVendorId());
                    startActivity(i);
                }
            case R.id.cardViewItem:
                if (strListType.equalsIgnoreCase("grid")) {
                    int posB = Integer.parseInt(v.getTag().toString());
                    VendorList vendorListB = product.get(posB);
                    Intent iB = new Intent(mContext, RestaurentMenuActivity.class);
                    iB.putExtra("vendor_id", vendorListB.getVendorId());
                    startActivity(iB);
                }
                break;
        }
    }

    private void setItemAdapter(RecyclerView.LayoutManager layout, String strView) {
        ListGridAdapter adapter = new ListGridAdapter(mContext, product, strView, this);
        recylerestaurant.setLayoutManager(layout);
        recylerestaurant.setItemAnimator(new DefaultItemAnimator());
        recylerestaurant.setAdapter(adapter);
    }
}
