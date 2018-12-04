package infobite.must.eat.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import infobite.must.eat.Fragment.AccountFragment;
import infobite.must.eat.R;
import infobite.must.eat.Utils;


public class AccountActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        fragmentManager = getSupportFragmentManager();

        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.account_frame, new AccountFragment(),
                            Utils.AccountFragment).commit();
        }
    }
}
