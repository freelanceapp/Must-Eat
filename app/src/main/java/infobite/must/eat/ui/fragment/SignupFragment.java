package infobite.must.eat.ui.fragment;

import android.content.Intent;
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
import infobite.must.eat.utils.EmailChecker;

/**
 * Created by Dell on 12/3/2018.
 */

public class SignupFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private EditText etEmail, etPassword, etContact, etName, etCountryCode;


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

        etEmail = rootView.findViewById(R.id.et_email);
        etPassword = rootView.findViewById(R.id.et_password);
        etName = rootView.findViewById(R.id.et_name);
        etContact = rootView.findViewById(R.id.et_contact);
        etCountryCode = rootView.findViewById(R.id.et_country_code);

        rootView.findViewById(R.id.iv_back).setOnClickListener(this);
        rootView.findViewById(R.id.bt_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:

                break;
            case R.id.tv_signup:

                break;
        }
    }

    private void signUpApi() {
        String strName = etEmail.getText().toString();
        String strEmail = etPassword.getText().toString();
        String strPhone = etPassword.getText().toString();
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
