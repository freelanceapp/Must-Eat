package infobite.must.eat.ui.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import infobite.must.eat.R;
import infobite.must.eat.ui.activities.MainActivity;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.BaseFragment;
import infobite.must.eat.utils.CustomFont;
import infobite.must.eat.utils.EmailChecker;

/**
 * Created by Dell on 12/3/2018.
 */

public class SignupFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private TextView tvTitle, tvSignupLbl, tvEmail, tvPassword, tvName, tvContact, tvCountryCode;
    private Button btRegister, btnFb, btnGmail;
    private EditText etEmail, etPassword, etContact, etName, etCountryCode;
    private ImageView ivBack;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_registration, container, false);
        mContext = getActivity();

        init();
        return rootView;
    }

    private void init() {
        MainActivity.ivClose.setVisibility(View.VISIBLE);

        ivBack = (ImageView) rootView.findViewById(R.id.iv_back);
        tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        tvSignupLbl = (TextView) rootView.findViewById(R.id.tv_register_lbl);
        tvEmail = (TextView) rootView.findViewById(R.id.tv_email);
        tvPassword = (TextView) rootView.findViewById(R.id.tv_password);
        tvName = (TextView) rootView.findViewById(R.id.tv_name);
        tvContact = (TextView) rootView.findViewById(R.id.tv_contact);
        tvCountryCode = (TextView) rootView.findViewById(R.id.tv_country_code);

        etEmail = (EditText) rootView.findViewById(R.id.et_email);
        etPassword = (EditText) rootView.findViewById(R.id.et_password);
        etName = (EditText) rootView.findViewById(R.id.et_name);
        etContact = (EditText) rootView.findViewById(R.id.et_contact);
        etCountryCode = (EditText) rootView.findViewById(R.id.et_country_code);

        btRegister = (Button) rootView.findViewById(R.id.bt_register);
        btnFb = (Button) rootView.findViewById(R.id.bt_fb);
        btnGmail = (Button) rootView.findViewById(R.id.bt_gmail);

        ivBack.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        btnFb.setOnClickListener(this);
        btnGmail.setOnClickListener(this);

        tvTitle.setTypeface(CustomFont.semiBold(mContext));
        tvSignupLbl.setTypeface(CustomFont.semiBold(mContext));

        tvEmail.setTypeface(CustomFont.medium(mContext));
        tvPassword.setTypeface(CustomFont.medium(mContext));
        tvName.setTypeface(CustomFont.medium(mContext));
        tvContact.setTypeface(CustomFont.medium(mContext));
        tvCountryCode.setTypeface(CustomFont.medium(mContext));
        etEmail.setTypeface(CustomFont.medium(mContext));
        etPassword.setTypeface(CustomFont.medium(mContext));
        etName.setTypeface(CustomFont.medium(mContext));
        etContact.setTypeface(CustomFont.medium(mContext));
        etCountryCode.setTypeface(CustomFont.medium(mContext));
        btRegister.setTypeface(CustomFont.medium(mContext));
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
                //startNewActivity(VerificationActivity.class);
            } else {
                cd.show(mContext);
            }
        }
    }

    private void startNewActivity(Class<?> aClass) {
        Intent intent = new Intent(mContext, aClass);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.ivClose.setVisibility(View.VISIBLE);
    }
}
