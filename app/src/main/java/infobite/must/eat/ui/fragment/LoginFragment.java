package infobite.must.eat.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.User;
import infobite.must.eat.modal.api_modal.login_response.LoginModal;
import infobite.must.eat.retrofit_provider.RetrofitService;
import infobite.must.eat.retrofit_provider.WebResponse;
import infobite.must.eat.ui.activities.FindLocationActivity;
import infobite.must.eat.ui.activities.MainActivity;
import infobite.must.eat.ui.activities.PlaceOrderActivity;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.AppPreference;
import infobite.must.eat.utils.BaseFragment;
import infobite.must.eat.utils.ConnectionDetector;
import infobite.must.eat.utils.EmailChecker;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by Dell on 12/3/2018.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private View rootView;
    private static FragmentManager fragmentManager;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;


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
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .enableAutoManage(getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(Plus.API)
                .build();

        retrofitApiClient = RetrofitService.getRetrofit();
        MainActivity.ivClose.setVisibility(View.GONE);
        fragmentManager = getActivity().getSupportFragmentManager();
        rootView.findViewById(R.id.bt_login).setOnClickListener(this);
        rootView.findViewById(R.id.bt_fb).setOnClickListener(this);
        rootView.findViewById(R.id.bt_gmail).setOnClickListener(this);
        rootView.findViewById(R.id.tv_forgot_pass).setOnClickListener(this);
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
            case R.id.bt_gmail:
                //startActivity(new Intent(mContext, PlaceOrderActivity.class));
                googleSignIn();
                break;
        }
    }

    private void googleSignIn() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void loginApi() {
        String strEmail = ((EditText) rootView.findViewById(R.id.et_email)).getText().toString();
        String strPassword = ((EditText) rootView.findViewById(R.id.et_password)).getText().toString();

        if (!EmailChecker.isValid(strEmail)) {
            Alerts.show(mContext, "Email id is invalid !!!");
        } else if (strPassword.isEmpty()) {
            Alerts.show(mContext, "Please enter password");
        } else if (strPassword.length() < 6) {
            Alerts.show(mContext, "Password length must be more than 5");
        } else {
            if (cd.isNetworkAvailable()) {

                RetrofitService.getLoginData(new Dialog(mContext), retrofitApiClient.userLogin(strEmail, strPassword), new WebResponse() {
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
                            if (loginModal.getMessage().equals("User is Not Verified")) {
                                startFragment(Constant.Verification_Fragment, new VerificationFragment());
                            }
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("#############", "onConnectionFailed:" + connectionResult);

    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("###########", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Person person = null;
            if (mGoogleApiClient.isConnected()) {
                person = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            }

            Log.e("####", "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            String personPhotoUrl="";
            if (acct.getPhotoUrl()!=null){
                personPhotoUrl = acct.getPhotoUrl().toString();}
            String email = acct.getEmail();
            String id = acct.getId();
            String gender = "";
            if (person!=null && person.hasGender()) {
                gender = String.valueOf(person.getGender());
            }
            String msg = "Name: " + personName + ",\n\n email: " + email+ ",\n\n Gender: " + gender+ ",\n\n Id: " + id
                    + ",\n\n Image: " + personPhotoUrl;

            Log.e("#####", msg);

            //logiSocial(personName,email,personPhotoUrl,id, "Google");
        } else {
            Alerts.show(mContext,"Could not connect to google, please try after sometime. ");
        }
    }

}
