package infobite.must.eat.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import infobite.must.eat.R;
import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.User;
import infobite.must.eat.modal.api_modal.login_response.LoginModal;
import infobite.must.eat.retrofit_provider.RetrofitService;
import infobite.must.eat.retrofit_provider.WebResponse;
import infobite.must.eat.ui.activities.FindLocationActivity;
import infobite.must.eat.utils.Alerts;
import infobite.must.eat.utils.AppPreference;
import infobite.must.eat.utils.BaseFragment;
import infobite.must.eat.utils.EmailChecker;
import retrofit2.Response;


public class AccountFragment extends BaseFragment implements View.OnClickListener {
    private View view;
    private CircleImageView profile_image;
    private File file;
    private int GALLERY = 1, CAMERA = 2;
    private String userChoosenTask;
    private static final String IMAGE_DIRECTORY = "/musteat";

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);
        initViews();
        return view;
    }

    private void initViews() {
        profile_image = view.findViewById(R.id.profile_image);
        profile_image.setOnClickListener(this);
        getProfile();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_image:
                selectImage();
                break;
        }
    }

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
     * Get profile
     * */
    private void getProfile() {
        String strUserId = AppPreference.getStringPreference(mContext, Constant.User_Id);
        if (cd.isNetworkAvailable()) {
            RetrofitService.getUserData(new Dialog(mContext), retrofitApiClient.userProfile(strUserId), new WebResponse() {
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
                            //startFragment(Constant.Verification_Fragment, new VerificationFragment(), loginModal.getUser().getPhone());
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
