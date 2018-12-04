package infobite.must.eat.ui.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.ui.activities.MainActivity;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.BaseFragment;
import infobite.must.eat.utils.ConnectionDetector;
import infobite.must.eat.utils.CustomFont;
import infobite.must.eat.utils.EmailChecker;

/**
 * Created by Dell on 12/3/2018.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private TextView tvTitle, tvLoginLbl, tvEmail, tvPassword, tvForgotPass, tvOr, tvSignUp;
    private Button btnLogin, btnFb, btnGmail;
    private EditText etEmail, etPassword;
    private static FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        init();
        return rootView;
    }

    private void init() {
        MainActivity.ivClose.setVisibility(View.GONE);

        fragmentManager = getActivity().getSupportFragmentManager();
        tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        tvLoginLbl = (TextView) rootView.findViewById(R.id.tv_login_lbl);
        tvEmail = (TextView) rootView.findViewById(R.id.tv_email);
        tvPassword = (TextView) rootView.findViewById(R.id.tv_password);
        tvForgotPass = (TextView) rootView.findViewById(R.id.tv_forgot_pass);
        tvOr = (TextView) rootView.findViewById(R.id.tv_or);
        tvSignUp = (TextView) rootView.findViewById(R.id.tv_signup);

        etEmail = (EditText) rootView.findViewById(R.id.et_email);
        etPassword = (EditText) rootView.findViewById(R.id.et_password);

        btnLogin = (Button) rootView.findViewById(R.id.bt_login);
        btnFb = (Button) rootView.findViewById(R.id.bt_fb);
        btnGmail = (Button) rootView.findViewById(R.id.bt_gmail);

        btnLogin.setOnClickListener(this);
        btnFb.setOnClickListener(this);
        btnGmail.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
        tvForgotPass.setOnClickListener(this);

        tvTitle.setTypeface(CustomFont.semiBold(mContext));
        tvLoginLbl.setTypeface(CustomFont.semiBold(mContext));

        tvEmail.setTypeface(CustomFont.medium(mContext));
        tvPassword.setTypeface(CustomFont.medium(mContext));
        tvForgotPass.setTypeface(CustomFont.medium(mContext));
        tvOr.setTypeface(CustomFont.medium(mContext));
        tvSignUp.setTypeface(CustomFont.medium(mContext));
        etEmail.setTypeface(CustomFont.medium(mContext));
        etPassword.setTypeface(CustomFont.medium(mContext));
        btnLogin.setTypeface(CustomFont.medium(mContext));
        btnFb.setTypeface(CustomFont.medium(mContext));
        btnGmail.setTypeface(CustomFont.medium(mContext));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                loginApi();
                break;
            case R.id.tv_signup:
                startFragment(Constant.SignUp_Fragment, new SignupFragment());
                break;
            case R.id.tv_forgot_pass:
                startFragment(Constant.ForgotPassword_Fragment, new ForgotPassFragment());
                break;
        }
    }

    private void loginApi() {
        String strEmail = etEmail.getText().toString();
        String strPassword = etPassword.getText().toString();

        if (!EmailChecker.isValid(strEmail)) {
            Alerts.show(mContext, "Email id is invalid !!!");
        } else if (strPassword.isEmpty()) {
            Alerts.show(mContext, "Please enter password");
        } else if (strPassword.length() < 6) {
            Alerts.show(mContext, "Password length must be more than 5");
        } else {
            if (cd.isNetworkAvailable()) {

                startFragment(Constant.Verification_Fragment, new VerificationFragment());

            } else {
                cd.show(mContext);
            }
        }
    }

    private void startFragment(String tag, Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                .replace(R.id.fram_container,
                        fragment,
                        tag).commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.ivClose.setVisibility(View.GONE);
    }
}
