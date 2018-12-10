package infobite.must.eat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

import infobite.must.eat.R;
import infobite.must.eat.adapter.RestaurentShowAdapter;
import infobite.must.eat.modal.default_modal.RestaurentModel;

public class NearRestaurantActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionMenu fam;
    private FloatingActionButton fabuser, fabcart, faboffer, fabhome;
    RecyclerView restaurent_list;
    ArrayList<RestaurentModel> restaurentModelArrayList = new ArrayList<>();
    RestaurentShowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_restaurant);
        fabhome = findViewById(R.id.home_btn);
        faboffer = findViewById(R.id.offer_btn);
        fabcart = findViewById(R.id.cart_btn);
        fabuser = findViewById(R.id.account_btn);
        fam = findViewById(R.id.fab_menu);
        restaurent_list = findViewById(R.id.restaurent_list);
        //handling menu status (open or close)
        fam.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    showToast("Menu is opened");
                    fabhome.setVisibility(View.VISIBLE);
                    faboffer.setVisibility(View.VISIBLE);
                    fabcart.setVisibility(View.VISIBLE);
                    fabuser.setVisibility(View.VISIBLE);
                } else {
                    showToast("Menu is closed");
                    fabhome.setVisibility(View.GONE);
                    faboffer.setVisibility(View.GONE);
                    fabcart.setVisibility(View.GONE);
                    fabuser.setVisibility(View.GONE);
                }
            }
        });

        //handling each floating action button clicked
        fabuser.setOnClickListener(this);
        fabcart.setOnClickListener(this);
        faboffer.setOnClickListener(this);
        fabhome.setOnClickListener(this);

        fam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        for (int i = 0; i < 15; i++) {
            RestaurentModel restaurentModel = new RestaurentModel();
            restaurentModel.setR_discount("30% OFF");
            restaurentModel.setR_name("Restaurent Name");
            restaurentModel.setR_type("OPEN");
            restaurentModel.setR_img1(R.drawable.bg_food);
            restaurentModelArrayList.add(restaurentModel);
        }

        adapter = new RestaurentShowAdapter(restaurentModelArrayList, NearRestaurantActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(NearRestaurantActivity.this);
        //GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        restaurent_list.setLayoutManager(mLayoutManager);
        restaurent_list.setItemAnimator(new DefaultItemAnimator());
        restaurent_list.setAdapter(adapter);


    }


    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if (view == fabhome) {
            showToast("Button home clicked");
            Intent intent = new Intent(NearRestaurantActivity.this, HomeActivity.class);
            startActivity(intent);
        } else if (view == fabcart) {
            showToast("Button Cart clicked");
            Intent intent = new Intent(NearRestaurantActivity.this, AddtoCartActivity.class);
            startActivity(intent);
        } else if (view == faboffer) {
            showToast("Button Offer clicked");
            Intent intent = new Intent(NearRestaurantActivity.this, OffersActivity.class);
            startActivity(intent);
        } else {
            showToast("Button Account clicked");
            Intent intent = new Intent(NearRestaurantActivity.this, AccountActivity.class);
            startActivity(intent);
        }
        if (fam.isOpened()) {
            fam.close(true);
        }
        //fam.close(true);
    }
}
