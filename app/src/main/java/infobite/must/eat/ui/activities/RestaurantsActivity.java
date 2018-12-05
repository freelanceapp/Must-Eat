package infobite.must.eat.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.adapter.DataAdapter;
import infobite.must.eat.modal.ProductDetails;

/**
 * Created by Dell on 12/1/2018.
 */

public class RestaurantsActivity extends AppCompatActivity {

    private List<ProductDetails> product = new ArrayList<>();
    private RecyclerView recylerestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        recylerestaurant = (RecyclerView) findViewById(R.id.recylerestaurant);



        for (int i = 0 ; i < 10 ; i++) {
            product.add(new ProductDetails(R.drawable.bg_food, "The Noodle", "Additional Item", "Menu", R.drawable.forward));
            product.add(new ProductDetails(R.drawable.bg_food, "The Noodle", "Additional Item", "Menu", R.drawable.forward));
            product.add(new ProductDetails(R.drawable.bg_food, "The Noodle", "Additional Item", "Menu", R.drawable.forward));

        }
        DataAdapter adapter = new DataAdapter(RestaurantsActivity.this, product);
        LinearLayoutManager layoutManager = new LinearLayoutManager(RestaurantsActivity.this, LinearLayoutManager.VERTICAL, false);
        recylerestaurant.setLayoutManager(layoutManager);
        recylerestaurant.setItemAnimator(new DefaultItemAnimator());
        recylerestaurant.setAdapter(adapter);

    }
}
