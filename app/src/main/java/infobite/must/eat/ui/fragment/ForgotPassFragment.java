package infobite.must.eat.ui.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import infobite.must.eat.R;
import infobite.must.eat.ui.activities.MainActivity;
import infobite.must.eat.utils.BaseFragment;

/**
 * Created by Dell on 12/3/2018.
 */

public class ForgotPassFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private TextView tvBack, tvForgotPass, tvTextA;
    private Button btnLogin;
    private EditText etEmail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        mContext = getActivity();

        init();
        return rootView;
    }

    private void init() {
        MainActivity.ivClose.setVisibility(View.VISIBLE);

        tvBack = (TextView) rootView.findViewById(R.id.tv_back);
        tvForgotPass = (TextView) rootView.findViewById(R.id.tv_forgot_pass);
        tvTextA = (TextView) rootView.findViewById(R.id.tv_text_a);

        etEmail = (EditText) rootView.findViewById(R.id.et_email);

        btnLogin = (Button) rootView.findViewById(R.id.bt_send);

        btnLogin.setOnClickListener(this);
        ((LinearLayout) rootView.findViewById(R.id.ll_back)).setOnClickListener(this);

        Typeface titleFont = Typeface.createFromAsset(mContext.getAssets(), "font/Raleway-SemiBold.ttf");
        Typeface fontB = Typeface.createFromAsset(mContext.getAssets(), "font/Raleway-Medium.ttf");
        tvForgotPass.setTypeface(titleFont);

        tvTextA.setTypeface(fontB);
        tvBack.setTypeface(fontB);
        etEmail.setTypeface(fontB);
        btnLogin.setTypeface(fontB);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_send:
                new MainActivity().replaceLoginFragment();
                break;
            case R.id.ll_back:
                new MainActivity().replaceLoginFragment();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.ivClose.setVisibility(View.VISIBLE);
    }
}
