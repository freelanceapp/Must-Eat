package infobite.must.eat.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.User;
import infobite.must.eat.modal.api_modal.login_response.LoginModal;
import infobite.must.eat.retrofit_provider.RetrofitService;
import infobite.must.eat.retrofit_provider.WebResponse;
import infobite.must.eat.ui.activities.FindLocationActivity;
import infobite.must.eat.R;
import infobite.must.eat.ui.activities.MainActivity;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.AppPreference;
import infobite.must.eat.utils.BaseFragment;
import infobite.must.eat.utils.ConnectionDetector;
import retrofit2.Response;

/**
 * Created by Dell on 12/3/2018.
 */

public class VerificationFragment extends BaseFragment implements View.OnClickListener {

    private String strMobile;
    private View rootView;
    private TextView tv_resend;
    private Button btn_next;
    private EditText et_otp_a, et_otp_b, et_otp_c, et_otp_d, et_otp_e, et_otp_f;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_verification, container, false);
        mContext = getActivity();

        init();
        return rootView;
    }

    private void init() {
        cd = new ConnectionDetector(mContext);
        assert getArguments() != null;
        strMobile = getArguments().getString("mobile");

        ((TextView) rootView.findViewById(R.id.tv_phone)).setText(strMobile);

        retrofitApiClient = RetrofitService.getRetrofit();
        MainActivity.ivClose.setVisibility(View.VISIBLE);

        tv_resend = rootView.findViewById(R.id.tv_resend);

        et_otp_a = rootView.findViewById(R.id.et_otp_a);
        et_otp_b = rootView.findViewById(R.id.et_otp_b);
        et_otp_c = rootView.findViewById(R.id.et_otp_c);
        et_otp_d = rootView.findViewById(R.id.et_otp_d);
        et_otp_e = rootView.findViewById(R.id.et_otp_e);
        et_otp_f = rootView.findViewById(R.id.et_otp_f);

        btn_next = rootView.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        tv_resend.setOnClickListener(this);

        verificationCode();
    }

    private void verificationCode() {
        et_otp_a.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strA = String.valueOf(s);
                if (strA.isEmpty()) {
                    et_otp_a.requestFocus();
                    et_otp_b.clearFocus();
                    et_otp_c.clearFocus();
                    et_otp_d.clearFocus();
                } else {
                    et_otp_a.clearFocus();
                    et_otp_b.requestFocus();
                    et_otp_c.clearFocus();
                    et_otp_d.clearFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_otp_b.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strA = String.valueOf(s);

                if (strA.isEmpty()) {
                    et_otp_a.requestFocus();
                    et_otp_b.clearFocus();
                    et_otp_c.clearFocus();
                    et_otp_d.clearFocus();
                } else {
                    et_otp_a.clearFocus();
                    et_otp_b.clearFocus();
                    et_otp_c.requestFocus();
                    et_otp_d.clearFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_otp_c.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strA = String.valueOf(s);

                if (strA.isEmpty()) {
                    et_otp_a.clearFocus();
                    et_otp_b.requestFocus();
                    et_otp_c.clearFocus();
                    et_otp_d.clearFocus();
                } else {
                    et_otp_a.clearFocus();
                    et_otp_b.clearFocus();
                    et_otp_c.clearFocus();
                    et_otp_d.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_otp_d.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strA = String.valueOf(s);

                if (strA.isEmpty()) {
                    et_otp_a.clearFocus();
                    et_otp_b.clearFocus();
                    et_otp_c.requestFocus();
                    et_otp_d.clearFocus();
                    et_otp_e.clearFocus();
                    et_otp_f.clearFocus();
                } else {
                    et_otp_a.clearFocus();
                    et_otp_b.clearFocus();
                    et_otp_c.clearFocus();
                    et_otp_d.clearFocus();
                    et_otp_e.requestFocus();
                    et_otp_f.clearFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_otp_e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strA = String.valueOf(s);

                if (strA.isEmpty()) {
                    et_otp_a.clearFocus();
                    et_otp_b.clearFocus();
                    et_otp_c.clearFocus();
                    et_otp_d.requestFocus();
                    et_otp_e.clearFocus();
                    et_otp_f.clearFocus();
                } else {
                    et_otp_a.clearFocus();
                    et_otp_b.clearFocus();
                    et_otp_c.clearFocus();
                    et_otp_d.clearFocus();
                    et_otp_e.clearFocus();
                    et_otp_f.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_otp_f.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strA = String.valueOf(s);

                if (strA.isEmpty()) {
                    et_otp_a.clearFocus();
                    et_otp_b.clearFocus();
                    et_otp_c.clearFocus();
                    et_otp_d.clearFocus();
                    et_otp_e.requestFocus();
                    et_otp_f.clearFocus();
                } else {
                    et_otp_a.clearFocus();
                    et_otp_b.clearFocus();
                    et_otp_c.clearFocus();
                    et_otp_d.clearFocus();
                    et_otp_e.clearFocus();
                    et_otp_f.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                checkOTP();
                break;
            case R.id.tv_resend:
                Alerts.show(mContext, "OTP resend");
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.ivClose.setVisibility(View.VISIBLE);
    }

    private void checkOTP() {
        String strA = et_otp_a.getText().toString();
        String strB = et_otp_b.getText().toString();
        String strC = et_otp_c.getText().toString();
        String strD = et_otp_d.getText().toString();
        String strE = et_otp_e.getText().toString();
        String strF = et_otp_f.getText().toString();

        if (strMobile.isEmpty()) {
            Alerts.show(mContext, "Mobile no. can't be empty");
        } else if (strA.isEmpty()) {
            Alerts.show(mContext, "Please enter valid OTP");
        } else if (strB.isEmpty()) {
            Alerts.show(mContext, "Please enter valid OTP");
        } else if (strC.isEmpty()) {
            Alerts.show(mContext, "Please enter valid OTP");
        } else if (strD.isEmpty()) {
            Alerts.show(mContext, "Please enter valid OTP");
        } else if (strE.isEmpty()) {
            Alerts.show(mContext, "Please enter valid OTP");
        } else if (strF.isEmpty()) {
            Alerts.show(mContext, "Please enter valid OTP");
        } else {
            if (cd.isNetworkAvailable()) {

                String strOtp = strA + strB + strC + strD + strE + strF;
                RetrofitService.getUserData(new Dialog(mContext), retrofitApiClient.userVerification(
                        strMobile, strOtp), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        LoginModal loginModal = (LoginModal) result.body();
                        assert loginModal != null;
                        if (!loginModal.getError()) {
                            Alerts.show(mContext, loginModal.getMessage());

                            AppPreference.setBooleanPreference(mContext, Constant.Is_Login, true);

                            Gson gson = new GsonBuilder().setLenient().create();
                            String data = gson.toJson(loginModal);
                            AppPreference.setStringPreference(mContext, Constant.User_Data, data);
                            User.setUser(loginModal);

                            Intent intent = new Intent(mContext, FindLocationActivity.class);
                            startActivity(intent);
                        } else {
                            Alerts.show(mContext, loginModal.getMessage());
                        }
                    }

                    @Override
                    public void onResponseFailed(String error) {
                        Alerts.show(mContext, error);
                    }
                });
            } else {
                cd.show(mContext);
            }
        }
    }
}