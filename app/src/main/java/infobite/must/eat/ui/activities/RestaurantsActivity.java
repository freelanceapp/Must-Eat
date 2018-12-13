package infobite.must.eat.ui.activities;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        mContext = this;
        init();
    }

    private void init() {
        ((ImageView) findViewById(R.id.list_btn)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.grid_btn)).setOnClickListener(this);

        recylerestaurant = (RecyclerView) findViewById(R.id.recylerestaurant);

        if (getIntent() == null)
            return;
        String strVendorListJson;
        strVendorListJson = getIntent().getStringExtra("all_vendor");
        Gson gson = new Gson();
        VendorListMainModal mainModal = gson.fromJson(strVendorListJson, VendorListMainModal.class);

        product.addAll(mainModal.getVendor());

        setItemAdapter((new LinearLayoutManager(RestaurantsActivity.this, LinearLayoutManager.VERTICAL, false)),
                "list");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_btn:
                setItemAdapter((new LinearLayoutManager(RestaurantsActivity.this, LinearLayoutManager.VERTICAL, false)),
                        "list");
                break;
            case R.id.grid_btn:
                setItemAdapter((new GridLayoutManager(RestaurantsActivity.this, 2)),
                        "grid");
                break;
        }
    }

    private void setItemAdapter(RecyclerView.LayoutManager layout, String strView) {
        ListGridAdapter adapter = new ListGridAdapter(mContext, product, strView);
        recylerestaurant.setLayoutManager(layout);
        recylerestaurant.setItemAnimator(new DefaultItemAnimator());
        recylerestaurant.setAdapter(adapter);
    }
}
