package infobite.must.eat.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.User;
import infobite.must.eat.modal.api_modal.login_response.LoginModal;
import infobite.must.eat.retrofit_provider.RetrofitService;
import infobite.must.eat.retrofit_provider.WebResponse;
import infobite.must.eat.ui.activities.FindLocationActivity;
import infobite.must.eat.ui.activities.MainActivity;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.AppPreference;
import infobite.must.eat.utils.BaseFragment;
import infobite.must.eat.utils.EmailChecker;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * Created by Dell on 12/3/2018.
 */

public class SignupFragment extends BaseFragment implements View.OnClickListener {

    private File file;
    private CircleImageView profile_image;
    private int GALLERY = 1, CAMERA = 2;
    private String userChoosenTask;
    private static final String IMAGE_DIRECTORY = "/musteat";
    private View rootView;
    private static FragmentManager fragmentManager;
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
        fragmentManager = getActivity().getSupportFragmentManager();
        Bundle bundle = getArguments();
        assert bundle != null;
        String strName = bundle.getString("name");
        String strEmail = bundle.getString("email");
        MainActivity.ivClose.setVisibility(View.VISIBLE);

        ((EditText) rootView.findViewById(R.id.et_name)).setText(strName);
        ((EditText) rootView.findViewById(R.id.et_email)).setText(strEmail);
        profile_image = rootView.findViewById(R.id.profile_image);
        profile_image.setOnClickListener(this);
        etName = rootView.findViewById(R.id.et_name);
        etEmail = rootView.findViewById(R.id.et_email);
        etContact = rootView.findViewById(R.id.et_contact);
        etCountryCode = rootView.findViewById(R.id.et_country_code);
        etPassword = rootView.findViewById(R.id.et_password);

        rootView.findViewById(R.id.iv_back).setOnClickListener(this);
        rootView.findViewById(R.id.bt_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:

                break;
            case R.id.tv_signup:
                signUpApi();
                break;
            case R.id.profile_image:
                selectImage();
                break;
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

    private void getDefaultImage() {
        Bitmap bMap = BitmapFactory.decodeFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                + File.separator + "MustEatProfile.jpg");
        Bitmap bMap2 = Bitmap.createScaledBitmap(bMap, bMap.getWidth(), bMap.getHeight(), false);

        File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                + File.separator + "MustEatProfile.jpg");
    }

    /*
     * Capture image
     * */
    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                //boolean result= Utility.checkPermission(getActivity());

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), GALLERY);
		/*Intent galleryIntent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		startActivityForResult(galleryIntent, GALLERY);*/
    }

    private void cameraIntent() {
		/*Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		startActivityForResult(intent, CAMERA);*/
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
                    profile_image.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            profile_image.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        Log.e("File", wallpaperDirectory.getPath());
        Log.e("wallpaperDirectory", "..." + wallpaperDirectory.exists());
        Log.e("wallpaperDirectory", "===" + wallpaperDirectory.mkdirs());
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            file = new File(wallpaperDirectory, "MustEatProfile.jpg");
            file.createNewFile();
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(getActivity(), new String[]{file.getPath()},
                    new String[]{"image/*"}, null);
            fo.close();
            Log.e("TAG", "File Saved::--->" + file.getAbsolutePath());

            return file.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    /*
     *  Sign up api
     * */
    private void signUpApi() {
        String strName = etEmail.getText().toString();
        String strEmail = etEmail.getText().toString();
        String strPhone = etContact.getText().toString();
        String strCountryCode = etCountryCode.getText().toString();
        String strPassword = etPassword.getText().toString();

        if (strName.isEmpty()) {
            Alerts.show(mContext, "Please enter name");
        } else if (!EmailChecker.isValid(strEmail)) {
            Alerts.show(mContext, "Email id is invalid !!!");
        } else if (strPhone.isEmpty()) {
            Alerts.show(mContext, "Please enter contact number");
        } else if (strCountryCode.isEmpty()) {
            Alerts.show(mContext, "Please enter country code");
        } else if (strPassword.isEmpty()) {
            Alerts.show(mContext, "Please enter password");
        } else if (strPassword.length() < 6) {
            Alerts.show(mContext, "Password length must be more than 5");
        } else {
            if (cd.isNetworkAvailable()) {
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
                RequestBody _name = RequestBody.create(MediaType.parse("text/plain"), strName);
                RequestBody _email = RequestBody.create(MediaType.parse("text/plain"), strEmail);
                RequestBody _phone = RequestBody.create(MediaType.parse("text/plain"), strPhone);
                RequestBody _countryCode = RequestBody.create(MediaType.parse("text/plain"), strCountryCode);
                RequestBody _password = RequestBody.create(MediaType.parse("text/plain"), strPassword);

                RetrofitService.getUserData(new Dialog(mContext), retrofitApiClient.userRegistration(
                        _name, fileToUpload, _email, _password, _phone), new WebResponse() {
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
}
