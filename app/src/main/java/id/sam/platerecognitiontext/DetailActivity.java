package id.sam.platerecognitiontext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.location.aravind.getlocation.GeoLocator;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import id.sam.platerecognitiontext.model.room.PlatesModel;

public class DetailActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    TextView txtName, txtAlamat, txtNoPlat, txtJenisKendaraan, txtStatus, txtLamaCicilan, txtWarna, txtTypeKendaraan, txtMerk, txtLat, txtLon;
    Button btnApp;
    Double lat = 0.0, lon = 0.0;
    private GoogleMap mMap;
    private AppDatabase mDb;
    PlatesModel platesModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtName = findViewById(R.id.txtName);
        txtAlamat = findViewById(R.id.txtAlamat);
        txtNoPlat = findViewById(R.id.txtNoPlat);
        txtJenisKendaraan = findViewById(R.id.txtJenisKendaraan);
        txtStatus = findViewById(R.id.txtStatus);
        txtLamaCicilan = findViewById(R.id.txtLamaCicilan);
        txtWarna = findViewById(R.id.txtWarna);
        txtTypeKendaraan = findViewById(R.id.txtTypeKendaraan);
        txtMerk = findViewById(R.id.txtMerk);
        txtLat = findViewById(R.id.txtLat);
        txtLon = findViewById(R.id.txtLon);
        btnApp = findViewById(R.id.btnAdd);

        Bundle bundle = getIntent().getBundleExtra(MainActivity.DATA_EXTRA);
        platesModel = Parcels.unwrap(bundle.getParcelable(MainActivity.DATA_SEARCH));

        mappingData();

        mDb = AppDatabase.getInstance(getApplicationContext());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in MyLocation and move the camera
        float zoomLevel = 16.0f; //This goes up to 21
        LatLng myLocation = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions()
                .position(myLocation)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .title("Lokasi Saya"));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, zoomLevel)); // zoom

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);
    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void mappingData(){
        txtName.setText(platesModel.getName());
        txtAlamat.setText(platesModel.getAlamat());
        txtNoPlat.setText(platesModel.getNoPlat());
        txtJenisKendaraan.setText(platesModel.getJenisKendaraan());
        txtStatus.setText(platesModel.getStatus());
        txtLamaCicilan.setText(platesModel.getLamaCicilan());
        txtWarna.setText(platesModel.getWarna());
        txtTypeKendaraan.setText(platesModel.getTypeKendaraan());
        txtMerk.setText(platesModel.getMerk());
        txtLat.setText(platesModel.getLat());
        txtLon.setText(platesModel.getLon());
        lat = Double.parseDouble(platesModel.getLat());
        lon = Double.parseDouble(platesModel.getLon());
    }

}