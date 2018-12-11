package infobite.must.eat.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import infobite.must.eat.R;
import infobite.must.eat.utils.LocationTrack;


public class FindLocationActivity extends AppCompatActivity implements View.OnClickListener, LocationListener {

    private String strLat = "", strLng = "";
    private Location mLocation;
    private TextView manual_location_btn;
    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;
    private String provider;
    private LocationTrack locationTrack;
    private Double lat, longi;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_location);

        //get the permissions we have asked for before but are not granted..
        //we will store this in a global list to access later.


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        }
        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        manual_location_btn = findViewById(R.id.manual_location_btn);
        manual_location_btn.setOnClickListener(this);
        findViewById(R.id.ll_current_location).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.manual_location_btn:
                Intent intent = new Intent(FindLocationActivity.this, ManualLocationActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_current_location:
                getLocation();

                break;
        }
    }

    protected void checkPermission() {
        if (ContextCompat.checkSelfPermission
                (FindLocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Do something, when permissions not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    FindLocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // If we should give explanation of requested permissions
                // Show an alert dialog here with request explanation
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(FindLocationActivity.this);
                builder.setMessage("Location " + " Storage permissions are required to do the task.");
                builder.setTitle("Please grant those permissions");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(
                                FindLocationActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION
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
                android.support.v7.app.AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(FindLocationActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_CODE);
            }
        } else {
            // Do something, when permissions are already granted
            Toast.makeText(FindLocationActivity.this, "Permissions already granted", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CODE: {
                // When request is cancelled, the results array are empty
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permissions are granted
                    Toast.makeText(FindLocationActivity.this, "Permissions granted.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // Permissions are denied
                    Toast.makeText(FindLocationActivity.this, "Permissions denied.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);

            /*if (!strLat.isEmpty()) {
                restaurantActivity();
            }*/
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(FindLocationActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            mLocation = location;
            strLat = String.valueOf(location.getLatitude());
            strLng = String.valueOf(location.getLongitude());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            /*Toast.makeText(FindLocationActivity.this, "Location " + addresses.get(0).getAddressLine(0) + ", " +
                    addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2) + location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_SHORT).show();*/
            restaurantActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(FindLocationActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    private void restaurantActivity() {
        Intent intent = new Intent(FindLocationActivity.this, NearRestaurantActivity.class);
        startActivity(intent);
        finish();
    }
}
