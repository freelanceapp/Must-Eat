package infobite.must.eat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.adapter.ExtraStuffsAdapter;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorProduct;
import infobite.must.eat.modal.default_modal.ExtraStuffItemsModal;
import infobite.must.eat.retrofit_provider.RetrofitService;
import infobite.must.eat.utils.BaseActivity;
import infobite.must.eat.utils.ConnectionDetector;

public class MenuQuntityActivity extends BaseActivity implements View.OnClickListener {

    private VendorProduct productDetail;
    private String strProductId = "", strQuantity = "", strAllStuffs = "", strAllStuffsPrice = "";
    private Button bt_cart;
    private ImageView close_btn;
    private List<ExtraStuffItemsModal> stuffItemList;
    private RecyclerView recyclerViewStuffs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_quantity);

        mContext = this;
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
    }

    private void init() {
        if (getIntent() == null)
            return;

        recyclerViewStuffs = findViewById(R.id.recyclerViewStuffs);
        productDetail = getIntent().getParcelableExtra("product_detail");
        strQuantity = getIntent().getStringExtra("strQuantity");

        strAllStuffs = productDetail.getProductToppingName();
        strAllStuffsPrice = productDetail.getProductToppingRate();

        bt_cart = findViewById(R.id.bt_cart);
        bt_cart.setOnClickListener(this);
        close_btn = findViewById(R.id.close_btn);
        close_btn.setOnClickListener(this);

        setData();
        setStuffsList();
    }

    private void setData() {
        ((TextView) findViewById(R.id.tvProductName)).setText(productDetail.getProductName());
        /*((TextView) findViewById(R.id.tvHalfPrice)).setText(productDetail.getProductHalfRate());
        ((TextView) findViewById(R.id.tvFullPrice)).setText(productDetail.getProductFullRate());*/
    }

    private void setStuffsList() {
        recyclerViewStuffs.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewStuffs.setItemAnimator(new DefaultItemAnimator());

        String[] stuffList = strAllStuffs.split(",");
        String[] stuffPriceList = strAllStuffsPrice.split(",");

        stuffItemList = new ArrayList<>();
        for (int i = 0; i < stuffList.length; i++) {
            ExtraStuffItemsModal modal = new ExtraStuffItemsModal();
            modal.setItem(stuffList[i]);
            modal.setPrice(stuffPriceList[i]);
            stuffItemList.add(modal);
        }

        ExtraStuffsAdapter stuffsAdapter = new ExtraStuffsAdapter(stuffItemList, mContext);
        recyclerViewStuffs.setAdapter(stuffsAdapter);
        stuffsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cart:

                break;
            case R.id.close_btn:
                finish();
                break;
        }
    }

    private void onCartClick() {


        Intent intent = new Intent(MenuQuntityActivity.this, AddtoCartActivity.class);
        startActivity(intent);
    }

    private String getStuffs() {
        StringBuffer sb = new StringBuffer();
        for (ExtraStuffItemsModal x : stuffItemList) {
            sb.append(x);
            sb.append(", ");
        }
        return sb.toString();
    }
}
