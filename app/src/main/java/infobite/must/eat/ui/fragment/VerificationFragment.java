package infobite.must.eat.ui.fragment;

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

import infobite.must.eat.ui.activities.FindLocationActivity;
import infobite.must.eat.R;
import infobite.must.eat.ui.activities.MainActivity;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.BaseFragment;

/**
 * Created by Dell on 12/3/2018.
 */

public class VerificationFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private TextView tv_a, tv_b, tv_phone, tv_d, tv_e, tv_resend;
    private Button btn_next;
    private EditText et_otp_a, et_otp_b, et_otp_c, et_otp_d;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_verification, container, false);
        mContext = getActivity();

        init();
        return rootView;
    }

    private void init() {
        MainActivity.ivClose.setVisibility(View.VISIBLE);

        tv_a = (TextView) rootView.findViewById(R.id.tv_a);
        tv_b = (TextView) rootView.findViewById(R.id.tv_b);
        tv_d = (TextView) rootView.findViewById(R.id.tv_d);
        tv_e = (TextView) rootView.findViewById(R.id.tv_e);
        tv_phone = (TextView) rootView.findViewById(R.id.tv_phone);
        tv_resend = (TextView) rootView.findViewById(R.id.tv_resend);

        et_otp_a = (EditText) rootView.findViewById(R.id.et_otp_a);
        et_otp_b = (EditText) rootView.findViewById(R.id.et_otp_b);
        et_otp_c = (EditText) rootView.findViewById(R.id.et_otp_c);
        et_otp_d = (EditText) rootView.findViewById(R.id.et_otp_d);

        btn_next = (Button) rootView.findViewById(R.id.btn_next);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                if (et_otp_d.getText().toString().isEmpty()) {
                    Alerts.show(mContext, "Please enter valid OTP");
                } else {
                    Alerts.show(mContext, "Verify success");
                    Intent intent = new Intent(getActivity(), FindLocationActivity.class);
                    startActivity(intent);
                }
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
}
