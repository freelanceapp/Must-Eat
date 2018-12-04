package infobite.must.eat.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;

import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.ui.fragment.LoginFragment;
import infobite.must.eat.utils.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static FragmentManager fragmentManager;
    public static ImageView ivClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivClose = (ImageView) findViewById(R.id.iv_close);
        ivClose.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fram_container, new LoginFragment(),
                            Constant.Login_Fragment).commit();
        }
    }

    public void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.fram_container, new LoginFragment(),
                        Constant.Login_Fragment).commit();
    }

    @Override
    public void onBackPressed() {

        Fragment SignUp_Fragment = fragmentManager.findFragmentByTag(Constant.SignUp_Fragment);
        Fragment ForgotPassword_Fragment = fragmentManager.findFragmentByTag(Constant.ForgotPassword_Fragment);

        if (SignUp_Fragment != null)
            replaceLoginFragment();
        else if (ForgotPassword_Fragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
