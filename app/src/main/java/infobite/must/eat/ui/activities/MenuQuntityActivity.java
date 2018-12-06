package infobite.must.eat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import infobite.must.eat.R;

public class MenuQuntityActivity extends AppCompatActivity {
    Button bt_cart;
    ImageView close_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_to_cart);
        bt_cart = (Button)findViewById(R.id.bt_cart);
        bt_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuQuntityActivity.this,AddtoCartActivity.class);
                startActivity(intent);
            }
        });
        close_btn = (ImageView)findViewById(R.id.close_btn);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
