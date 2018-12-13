package infobite.must.eat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.adapter.ExtraStuffsAdapter;
import infobite.must.eat.database.DatabaseHandler;
import infobite.must.eat.modal.CartItemDetailModal;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorProduct;
import infobite.must.eat.modal.default_modal.ExtraStuffItemsModal;
import infobite.must.eat.retrofit_provider.RetrofitService;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.BaseActivity;
import infobite.must.eat.utils.ConnectionDetector;

public class MenuQuntityActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private CheckBox checkBox;
    private VendorProduct productDetail;
    private String strTotalPrice = "0.0", strQuantity = "", strAllStuffs = "", strAllStuffsPrice = "", strVendorId = "";
    private Button bt_cart;
    private ImageView close_btn;
    private List<ExtraStuffItemsModal> stuffItemList;
    private RecyclerView recyclerViewStuffs;
    private DatabaseHandler db;
    private List<CartItemDetailModal> cartItemList = new ArrayList<>();

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
        db = new DatabaseHandler(mContext);

        if (db.getContactsCount()) {
            cartItemList = db.getAllUrlList();
            //Alerts.show(mContext, "" + cartItemList.size());
        }

        recyclerViewStuffs = findViewById(R.id.recyclerViewStuffs);
        productDetail = getIntent().getParcelableExtra("product_detail");
        strQuantity = getIntent().getStringExtra("strQuantity");
        strVendorId = getIntent().getStringExtra("restaurent_id");

        String strPerItemPrice = productDetail.getProductFullRate();
        if (strPerItemPrice.isEmpty()) {
            strPerItemPrice = "0.0";
        }
        float perItemPrice = Float.parseFloat(strPerItemPrice);
        perItemPrice = perItemPrice * (Float.parseFloat(strQuantity));
        strTotalPrice = String.valueOf(perItemPrice);
        ((TextView) findViewById(R.id.tvTotalPrice)).setText(strTotalPrice);

        strAllStuffs = productDetail.getProductToppingName();
        strAllStuffsPrice = productDetail.getProductToppingRate();

        bt_cart = findViewById(R.id.bt_cart);
        bt_cart.setOnClickListener(this);
        close_btn = findViewById(R.id.close_btn);
        close_btn.setOnClickListener(this);

        ((TextView) findViewById(R.id.tvProductName)).setText(productDetail.getProductName());

        setStuffsList();
    }

    private void setStuffsList() {
        recyclerViewStuffs.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewStuffs.setItemAnimator(new DefaultItemAnimator());

        if (!strAllStuffs.isEmpty()) {
            String[] stuffList = strAllStuffs.split(",");
            String[] stuffPriceList = strAllStuffsPrice.split(",");

            stuffItemList = new ArrayList<>();
            for (int i = 0; i < stuffList.length; i++) {
                ExtraStuffItemsModal modal = new ExtraStuffItemsModal();
                modal.setItem(stuffList[i]);
                modal.setPrice(stuffPriceList[i]);
                stuffItemList.add(modal);
            }

            ExtraStuffsAdapter stuffsAdapter = new ExtraStuffsAdapter(stuffItemList, mContext, this);
            recyclerViewStuffs.setAdapter(stuffsAdapter);
            stuffsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cart:
                onCartClick();
                break;
            case R.id.close_btn:
                finish();
                break;
            case R.id.checkboxItem:
                int pos = Integer.parseInt(v.getTag().toString());
                View view = recyclerViewStuffs.getChildAt(pos);
                ExtraStuffItemsModal stuffItemsModal = stuffItemList.get(pos);
                checkBox = view.findViewById(R.id.checkboxItem);
                float totalQuantityPrice = Float.parseFloat(((TextView) findViewById(R.id.tvTotalPrice)).getText().toString());
                if (checkBox.isChecked()) {
                    stuffItemsModal.setSelected(true);
                    String strSelectPrice = stuffItemsModal.getPrice();
                    if (strSelectPrice.isEmpty()) {
                        strSelectPrice = "0.0";
                    }
                    float selectedPrice = Float.parseFloat(strSelectPrice);
                    totalQuantityPrice = totalQuantityPrice + selectedPrice;

                    ((TextView) findViewById(R.id.tvTotalPrice)).setText(String.valueOf(totalQuantityPrice));
                } else {
                    String strSelectPrice = stuffItemsModal.getPrice();
                    if (strSelectPrice.isEmpty()) {
                        strSelectPrice = "0.0";
                    }
                    float selectedPrice = Float.parseFloat(strSelectPrice);
                    totalQuantityPrice = totalQuantityPrice - selectedPrice;

                    ((TextView) findViewById(R.id.tvTotalPrice)).setText(String.valueOf(totalQuantityPrice));
                }
                break;
        }
    }

    private void onCartClick() {
        String strStuff = getStuffsItem();
        String strStuffPrice = getStuffsItemPrice();
        String TotalPrice = ((TextView) findViewById(R.id.tvTotalPrice)).getText().toString();
        cartItemList = db.getAllUrlList();
        if (cartItemList.size() > 0) {
            for (int i = 0; i < 1; i++) {
                if (strVendorId.equalsIgnoreCase(cartItemList.get(i).getVendorId())) {
                    db.addItemCart(new CartItemDetailModal(strStuff, strStuffPrice, TotalPrice,
                            productDetail.getProductName(), productDetail.getProductId(), strVendorId, productDetail.getProductImage()));
                } else {
                    Alerts.show(mContext, "You can add only same restaurant items to cart !!!");
                }
            }
        } else {
            db.addItemCart(new CartItemDetailModal(strStuff, strStuffPrice, TotalPrice,
                    productDetail.getProductName(), productDetail.getProductId(), strVendorId, productDetail.getProductImage()));
        }

        Intent intent = new Intent(MenuQuntityActivity.this, AddtoCartActivity.class);
        startActivity(intent);
    }

    private String getStuffsItem() {
        StringBuffer sb = new StringBuffer();
        for (ExtraStuffItemsModal x : stuffItemList) {
            if (x.isSelected()) {
                sb.append(x.getItem());
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private String getStuffsItemPrice() {
        StringBuffer sb = new StringBuffer();
        for (ExtraStuffItemsModal x : stuffItemList) {
            if (x.isSelected()) {
                sb.append(x.getPrice());
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


    }

    @Override
    protected void onResume() {
        super.onResume();
        db = new DatabaseHandler(mContext);
        if (db.getContactsCount()) {
            cartItemList = db.getAllUrlList();
            // Alerts.show(mContext, "" + cartItemList.size());
        }
    }
}
