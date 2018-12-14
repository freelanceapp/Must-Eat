package infobite.must.eat.ui.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.transition.Slide;
import android.support.transition.TransitionManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.adapter.CheckoutItemAdapter;
import infobite.must.eat.database.DatabaseHandler;
import infobite.must.eat.interfaces.VisibleToggleClickListener;
import infobite.must.eat.modal.CartItemDetailModal;
import infobite.must.eat.modal.User;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.BaseActivity;


public class CheckoutActivity extends BaseActivity implements View.OnClickListener {

    private DatabaseHandler db;
    private List<CartItemDetailModal> cartItemList = new ArrayList<>();
    private float addTotal = 0.0f;
    private String strTotal = "0.0", strVendorName = "";
    private Dialog dialogAddress, dialogPaymentType;
    private TextView tvAddPromoClick, tvAddClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        mContext = this;
        init();
    }

    private void init() {
        db = new DatabaseHandler(mContext);
        if (db.getContactsCount()) {
            cartItemList = db.getAllUrlList();
        }
        strVendorName = getIntent().getStringExtra("restaurant_name");
        ((TextView) findViewById(R.id.tvRestaurantName)).setText(strVendorName);

        findViewById(R.id.llAddAddress).setOnClickListener(this);
        findViewById(R.id.tvAddPromoClick).setOnClickListener(this);
        findViewById(R.id.tvAddClick).setOnClickListener(this);
        findViewById(R.id.tvChangeClick).setOnClickListener(this);

        if (cartItemList.size() > 0) {
            addTotal = addSubTotalPrice();
            strTotal = String.valueOf(addTotal);
            ((TextView) findViewById(R.id.tvSubTotal)).setText(strTotal);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerViewItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        CheckoutItemAdapter adapter = new CheckoutItemAdapter(cartItemList, mContext, this);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        totalPrice();
        promoCodeClickEvent();
    }

    private void promoCodeClickEvent() {
        ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.llContainer);
        tvAddClick = (TextView) transitionsContainer.findViewById(R.id.tvAddClick);
        tvAddPromoClick = (TextView) transitionsContainer.findViewById(R.id.tvAddPromoClick);
        RelativeLayout rlPromo = (RelativeLayout) findViewById(R.id.rlPromo);
        transitionsContainer.findViewById(R.id.tvAddPromoClick).setOnClickListener(new VisibleToggleClickListener() {
            @Override
            protected void changeVisibility(boolean visible) {
                TransitionManager.beginDelayedTransition(transitionsContainer, new Slide(Gravity.LEFT));
                tvAddPromoClick.setVisibility(visible ? View.GONE : View.VISIBLE);
                TransitionManager.beginDelayedTransition(transitionsContainer, new Slide(Gravity.RIGHT));
                rlPromo.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        });

        transitionsContainer.findViewById(R.id.tvAddClick).setOnClickListener(new VisibleToggleClickListener() {
            @Override
            protected void changeVisibility(boolean visible) {
                TransitionManager.beginDelayedTransition(transitionsContainer, new Slide(Gravity.LEFT));
                tvAddPromoClick.setVisibility(visible ? View.VISIBLE : View.GONE);
                TransitionManager.beginDelayedTransition(transitionsContainer, new Slide(Gravity.RIGHT));
                rlPromo.setVisibility(visible ? View.GONE : View.VISIBLE);
            }
        });
    }

    private void totalPrice() {
        String strTax = ((TextView) findViewById(R.id.tvTax)).getText().toString();
        String strDeliveryFee = ((TextView) findViewById(R.id.tvDeliveryFees)).getText().toString();
        if (strTax.isEmpty()) {
            strTax = "0.0";
        }
        if (strDeliveryFee.isEmpty()) {
            strDeliveryFee = "0.0";
        }
        if (strTotal.isEmpty()) {
            strTotal = "0.0";
        }

        float subTotal = Float.parseFloat(strTotal);
        float tax = Float.parseFloat(strTax);
        float deliveryFees = Float.parseFloat(strDeliveryFee);
        float addAll = subTotal + tax + deliveryFees;
        String strAddall = String.valueOf(addAll);

        ((TextView) findViewById(R.id.tvTotalPrice)).setText(strAddall);
    }

    private float addSubTotalPrice() {
        float addPriceTotal = 0.0f;
        if (cartItemList.size() > 0) {
            for (CartItemDetailModal cartItemDetailModal : cartItemList) {
                addPriceTotal = addPriceTotal + Float.parseFloat(cartItemDetailModal.getTotalPrice());
            }
        }
        return addPriceTotal;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llAddAddress:
                newAddressDialog();
                break;
            case R.id.tvChangeClick:
                paymentTypeDialog();
                break;
            case R.id.tvAddPromoClick:
                break;
            case R.id.tvAddClick:
                break;
        }
    }

    private void newAddressDialog() {
        dialogAddress = new Dialog(mContext);
        dialogAddress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAddress.setContentView(R.layout.dialog_edit_address);

        dialogAddress.setCanceledOnTouchOutside(true);
        dialogAddress.setCancelable(true);
        if (dialogAddress.getWindow() != null)
            dialogAddress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialogAddress.findViewById(R.id.btnSubmit).setOnClickListener(v -> {
            dialogAddress.dismiss();
            Alerts.show(mContext, "Updated");
        });

        Window window = dialogAddress.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        dialogAddress.show();
    }

    private void paymentTypeDialog() {
        dialogPaymentType = new Dialog(mContext);
        dialogPaymentType.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogPaymentType.setContentView(R.layout.dialog_payment_type);

        dialogPaymentType.setCanceledOnTouchOutside(true);
        dialogPaymentType.setCancelable(true);
        if (dialogPaymentType.getWindow() != null)
            dialogPaymentType.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialogPaymentType.findViewById(R.id.btnSubmit).setOnClickListener(v -> {
            dialogPaymentType.dismiss();
            Alerts.show(mContext, "Updated");
        });

        Window window = dialogPaymentType.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        dialogPaymentType.show();
    }

}
