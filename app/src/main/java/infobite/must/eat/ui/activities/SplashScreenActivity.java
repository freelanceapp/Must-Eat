package infobite.must.eat.ui.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.User;
import infobite.must.eat.modal.api_modal.login_response.LoginModal;
import infobite.must.eat.modal.api_modal.version_response.VersionModel;
import infobite.must.eat.retrofit_provider.RetrofitService;
import infobite.must.eat.retrofit_provider.WebResponse;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.AppPreference;
import infobite.must.eat.utils.BaseActivity;
import infobite.must.eat.utils.ConnectionDetector;
import retrofit2.Response;

public class SplashScreenActivity extends BaseActivity {

    public static String MY_PREFS_NAME = "SRIL_APP";
    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;
    ImageView logoimage;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mContext = this;

        logoimage = (ImageView) findViewById(R.id.logoimage);
        title = (TextView) findViewById(R.id.title);
        mContext = SplashScreenActivity.this;
        cd = new ConnectionDetector(mContext);
        retrofitRxClient = RetrofitService.getRxClient();
        retrofitApiClient = RetrofitService.getRetrofit();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        }

        versionApi();
    }

    protected void checkPermission() {
        if (ContextCompat.checkSelfPermission(SplashScreenActivity.this, Manifest.permission.CAMERA)
                + ContextCompat.checkSelfPermission
                (SplashScreenActivity.this, Manifest.permission.READ_CONTACTS)
                + ContextCompat.checkSelfPermission
                (SplashScreenActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                + ContextCompat.checkSelfPermission
                (SplashScreenActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Do something, when permissions not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    SplashScreenActivity.this, Manifest.permission.CAMERA)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    SplashScreenActivity.this, Manifest.permission.READ_CONTACTS)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    SplashScreenActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    SplashScreenActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // If we should give explanation of requested permissions
                // Show an alert dialog here with request explanation
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreenActivity.this);
                builder.setMessage("Camera, Read Contacts, Location and Write External" +
                        " Storage permissions are required to do the task.");
                builder.setTitle("Please grant those permissions");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(
                                SplashScreenActivity.this,
                                new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION
                                },
                                MY_PERMISSIONS_REQUEST_CODE
                        );
                    }
                });
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(
                        SplashScreenActivity.this,
                        new String[]{
                                Manifest.permission.CAMERA,
                                Manifest.permission.READ_CONTACTS,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_FINE_LOCATION
                        },
                        MY_PERMISSIONS_REQUEST_CODE
                );
            }
        } else {
            // Do something, when permissions are already granted
            Toast.makeText(SplashScreenActivity.this, "Permissions already granted", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (AppPreference.getBooleanPreference(mContext, Constant.Is_Login)) {
                        Gson gson = new Gson();
                        String userData = AppPreference.getStringPreference(mContext, Constant.User_Data);
                        LoginModal loginModal = gson.fromJson(userData, LoginModal.class);
                        User.setUser(loginModal);

                        if (AppPreference.getFloatPreference(mContext, Constant.Latitude) > 0) {
                            Intent i = new Intent(SplashScreenActivity.this, NearRestaurantActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Intent i = new Intent(SplashScreenActivity.this, FindLocationActivity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }, 3000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CODE: {
                // When request is cancelled, the results array are empty
                if (
                        (grantResults.length > 0) &&
                                (grantResults[0]
                                        + grantResults[1]
                                        + grantResults[2]
                                        + grantResults[3]
                                        == PackageManager.PERMISSION_GRANTED
                                )
                        ) {
                    // Permissions are granted
                    Toast.makeText(SplashScreenActivity.this, "Permissions granted.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                } else {
                    // Permissions are denied
                    Toast.makeText(SplashScreenActivity.this, "Permissions denied.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
            }
        }
    }


    private void versionApi() {
        if (cd.isNetworkAvailable()) {

            RetrofitService.getVersion(new Dialog(mContext), retrofitApiClient.getVersion(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    VersionModel versionModel = (VersionModel) result.body();
                    assert versionModel != null;
                    if (versionModel.getVersion().equals("1")) {
                        Alerts.show(mContext, versionModel.getVersion());

                    } else {
                        Alerts.show(mContext, versionModel.getVersion());
                            /*if (offerMainModal.getMessage().equals("User is Not Verified")) {
                               // startFragment(Constant.Verification_Fragment, new VerificationFragment(), loginModal.getUser().getPhone());
                                //activity.finish();
                            }*/
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
