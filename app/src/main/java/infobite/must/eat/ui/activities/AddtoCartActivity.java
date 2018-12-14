package infobite.must.eat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.RecyclerItemTouchHelper;
import infobite.must.eat.adapter.CartListAdapter;
import infobite.must.eat.database.DatabaseHandler;
import infobite.must.eat.modal.CartItemDetailModal;
import infobite.must.eat.utils.BaseActivity;

public class AddtoCartActivity extends BaseActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private CartListAdapter mAdapter;
    private LinearLayout cll_back;
    private DatabaseHandler db;
    private List<CartItemDetailModal> cartItemList = new ArrayList<>();
    private float addTotal = 0.0f;
    private String strTotal = "0.0", strVendorName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addto_cart);
        mContext = this;
        init();
    }

    private void init() {
        db = new DatabaseHandler(mContext);
        if (db.getContactsCount()) {
            cartItemList = db.getAllUrlList();
        }

        strVendorName = getIntent().getStringExtra("restaurant_name");

        if (cartItemList.size() > 0) {
            addTotal = addPrice();
            strTotal = String.valueOf(addTotal);
            String strSize = String.valueOf(cartItemList.size());
            ((TextView) findViewById(R.id.tvTotalPrice)).setText(strTotal);
            ((TextView) findViewById(R.id.tvTotalCartItem)).setText(strSize);
            findViewById(R.id.tvEmpty).setVisibility(View.GONE);
            findViewById(R.id.llCheckout).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.tvEmpty).setVisibility(View.VISIBLE);
            findViewById(R.id.llCheckout).setVisibility(View.GONE);
        }

        ((LinearLayout) findViewById(R.id.llCheckout)).setOnClickListener(this);

        recyclerView = findViewById(R.id.recycler_view);
        cll_back = findViewById(R.id.cll_back);
        mAdapter = new CartListAdapter(this, cartItemList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        cll_back.setOnClickListener(view -> finish());
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CartListAdapter.MyViewHolder) {
            CartItemDetailModal deletedItem = cartItemList.get(viewHolder.getAdapterPosition());
            mAdapter.removeItem(viewHolder.getAdapterPosition());
            db.deleteContact(deletedItem);
            mAdapter.notifyDataSetChanged();
        }
        if (cartItemList.size() > 0) {
            addTotal = addPrice();
            strTotal = String.valueOf(addTotal);
            String strSize = String.valueOf(cartItemList.size());
            ((TextView) findViewById(R.id.tvTotalPrice)).setText(strTotal);
            ((TextView) findViewById(R.id.tvTotalCartItem)).setText(strSize);
            findViewById(R.id.tvEmpty).setVisibility(View.GONE);
            findViewById(R.id.llCheckout).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.tvEmpty).setVisibility(View.VISIBLE);
            findViewById(R.id.llCheckout).setVisibility(View.GONE);
        }
    }

    private float addPrice() {
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
            case R.id.llCheckout:
                Intent intent = new Intent(mContext, CheckoutActivity.class);
                intent.putExtra("restaurant_name", strVendorName);
                startActivity(intent);
                break;
        }
    }
}
