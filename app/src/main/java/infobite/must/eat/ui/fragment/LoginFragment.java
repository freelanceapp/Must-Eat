package infobite.must.eat.ui.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.User;
import infobite.must.eat.modal.api_modal.login_response.LoginModal;
import infobite.must.eat.retrofit_provider.RetrofitApiClient;
import infobite.must.eat.retrofit_provider.RetrofitService;
import infobite.must.eat.retrofit_provider.WebResponse;
import infobite.must.eat.ui.activities.FindLocationActivity;
import infobite.must.eat.ui.activities.MainActivity;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.AppPreference;
import infobite.must.eat.utils.BaseFragment;
import infobite.must.eat.utils.ConnectionDetector;
import infobite.must.eat.utils.EmailChecker;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 12/3/2018.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private static final String IMAGE_DIRECTORY = "/musteat";
    private ResponseBody responseBody;
    private Boolean per = false;
    private String[] permissions = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    private View rootView;
    private static int RESULT_LOAD_IMAGE = 1;
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

                break;
            case R.id.tv_forgot_pass:
                startFragment(Constant.ForgotPassword_Fragment, new ForgotPassFragment(), "");
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

                RetrofitService.getUserData(new Dialog(mContext), retrofitApiClient.userLogin(strEmail, strPassword), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        LoginModal loginModal = (LoginModal) result.body();
                        assert loginModal != null;
                        if (!loginModal.getError()) {
                            Alerts.show(mContext, loginModal.getMessage());

                            AppPreference.setBooleanPreference(mContext, Constant.Is_Login, true);
                            AppPreference.setStringPreference(mContext, Constant.User_Id, loginModal.getUser().getUserId());

                            Gson gson = new GsonBuilder().setLenient().create();
                            String data = gson.toJson(loginModal);
                            AppPreference.setStringPreference(mContext, Constant.User_Data, data);
                            User.setUser(loginModal);

                            Intent intent = new Intent(mContext, FindLocationActivity.class);
                            startActivity(intent);
                        } else {
                            Alerts.show(mContext, loginModal.getMessage());
                            if (loginModal.getMessage().equals("User is Not Verified")) {
                                startFragment(Constant.Verification_Fragment, new VerificationFragment(), loginModal.getUser().getPhone());
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

    private void startFragment(String tag, Fragment fragment, String strMobile) {
        Bundle args = new Bundle();
        args.putString("mobile", strMobile);
        fragment.setArguments(args);
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                .replace(R.id.fram_container, fragment, tag).commit();
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
            String personPhotoUrl = "";
            if (acct.getPhotoUrl() != null) {
                personPhotoUrl = acct.getPhotoUrl().toString();
            }
            String email = acct.getEmail();
            String id = acct.getId();
            String gender = "";
            if (person != null && person.hasGender()) {
                gender = String.valueOf(person.getGender());
            }
            String msg = "Name: " + personName + ",\n\n email: " + email + ",\n\n Gender: " + gender + ",\n\n Id: " + id
                    + ",\n\n Image: " + personPhotoUrl;

            Log.e("#####", msg);
            //checkPermissions();
            if (personPhotoUrl.isEmpty()) {
                getRetrofitImage(Constant.IMAGE, personName, email);
            } else {
                getRetrofitImage(personPhotoUrl, personName, email);
            }
            //logiSocial(personName,email,personPhotoUrl,id, "Google");
        } else {
            /*checkPermissions();
            getRetrofitImage(Constant.IMAGE);*/
            Alerts.show(mContext, "Could not connect to google, please try after sometime. ");
        }
    }

    /*
     * Download image from Facebook and Gmail
     * */
    void getRetrofitImage(String strBaseUrl, String name, String email) {
        RetrofitService.getResponse(new Dialog(mContext), retrofitApiClient.getImageDetails(strBaseUrl), new WebResponse() {
            @Override
            public void onResponseSuccess(Response<?> result) {
                responseBody = (ResponseBody) result.body();
                assert responseBody != null;
                boolean file = DownloadImage(responseBody);
                if (file) {
                    Bundle bundle = new Bundle();
                    SignupFragment signupFragment = new SignupFragment();
                    bundle.putString("name", name);
                    bundle.putString("email", email);
                    signupFragment.setArguments(bundle);
                    fragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                            .replace(R.id.fram_container, signupFragment, Constant.SignUp_Fragment).commit();
                }
            }

            @Override
            public void onResponseFailed(String error) {
                Alerts.show(mContext, error);
            }
        });
    }

    private boolean DownloadImage(ResponseBody body) {
        try {
            Log.e("DownloadImage", "Reading and writing file");
            InputStream in = null;
            FileOutputStream out = null;
            try {
                File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
                Log.e("File", wallpaperDirectory.getPath());
                Log.e("wallpaperDirectory", "..." + wallpaperDirectory.exists());
                Log.e("wallpaperDirectory", "===" + wallpaperDirectory.mkdirs());
                if (!wallpaperDirectory.exists()) {
                    wallpaperDirectory.mkdirs();
                }
                File file = new File(wallpaperDirectory, "MustEatProfile.jpg");
                file.createNewFile();
                in = body.byteStream();
                out = new FileOutputStream(file);
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
            } catch (IOException e) {
                Log.e("DownloadImage", e.toString());
                return false;
            } finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }



            return true;
        } catch (IOException e) {
            Log.e("DownloadImage", e.toString());
            return false;
        }
    }


    /*
     * Request permissions
     * */
    private boolean checkPermissions() {
        int result;
        List listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(mContext, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
                Log.e("-----------", "granteddddd ddddddddddd ");
            } else {
                Log.e("-----------", "granteddddd elseeeeeeeeeeeeeeee");

            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity, (String[]) listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    100);
            Log.e("-----------", "granteddddd  iffff");
            return false;
        } else {
            Log.e("-----------", "granteddddd 666666666(");
            startActivity(new Intent(mContext, MainActivity.class));
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                boolean file = DownloadImage(responseBody);
                if (file) {
                    Alerts.show(mContext, "Image download failed");
                } else {
                    Alerts.show(mContext, "Image downloading");
                }
            } else {
                Alerts.show(mContext, "Image not download");
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
    }
}
