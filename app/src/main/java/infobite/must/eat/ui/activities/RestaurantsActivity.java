package infobite.must.eat.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.adapter.DataAdapter;
import infobite.must.eat.modal.default_modal.ProductDetails;

/**
 * Created by Dell on 12/1/2018.
 */

public class RestaurantsActivity extends AppCompatActivity implements View.OnClickListener {

    private List<ProductDetails> product = new ArrayList<>();
    private RecyclerView recylerestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        init();
    }

    private void init() {
        ((ImageView) findViewById(R.id.list_btn)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.grid_btn)).setOnClickListener(this);

        recylerestaurant = (RecyclerView) findViewById(R.id.recylerestaurant);

        for (int i = 0; i < 10; i++) {
            product.add(new ProductDetails(R.drawable.bg_food, "The Noodle", "Additional Item", "Menu", R.drawable.forward));
            product.add(new ProductDetails(R.drawable.bg_food, "The Noodle", "Additional Item", "Menu", R.drawable.forward));
            product.add(new ProductDetails(R.drawable.bg_food, "The Noodle", "Additional Item", "Menu", R.drawable.forward));
        }
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
        DataAdapter adapter = new DataAdapter(RestaurantsActivity.this, product, "list");
        recylerestaurant.setLayoutManager(new LinearLayoutManager(RestaurantsActivity.this, LinearLayoutManager.VERTICAL, false));
        recylerestaurant.setItemAnimator(new DefaultItemAnimator());
        recylerestaurant.setAdapter(adapter);
    }
}
