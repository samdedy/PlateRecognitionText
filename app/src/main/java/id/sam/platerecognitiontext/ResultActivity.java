package id.sam.platerecognitiontext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.location.aravind.getlocation.GeoLocator;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.sam.platerecognitiontext.model.edit.DataEditPlatModel;
import id.sam.platerecognitiontext.model.room.PlatesModel;
import id.sam.platerecognitiontext.model.searchplat.DataSearchPlatModel;
import id.sam.platerecognitiontext.service.APIClient;
import id.sam.platerecognitiontext.service.APIInterfacesRest;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    TextView txtName, txtAlamat, txtNoPlat, txtJenisKendaraan, txtStatus, txtLamaCicilan, txtWarna, txtTypeKendaraan, txtMerk, txtLat, txtLon;
    Double lat = 0.0, lon = 0.0;
    private GoogleMap mMap;
    private AppDatabase mDb;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

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

        mDb = AppDatabase.getInstance(getApplicationContext());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        searchPlat(getIntent().getStringExtra("noPlat"));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        GeoLocator geoLocator = new GeoLocator(getApplicationContext(),this);
        lat = geoLocator.getLattitude();
        lon = geoLocator.getLongitude();

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

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void searchPlat(final String noPlat){
        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(ResultActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        Call<DataSearchPlatModel> call3 = apiInterface.getSearchPlat((String) noPlat);
        call3.enqueue(new Callback<DataSearchPlatModel>() {
            @Override
            public void onResponse(Call<DataSearchPlatModel> call, Response<DataSearchPlatModel> response) {
                progressDialog.dismiss();
                DataSearchPlatModel data = response.body();
                if (data !=null) {
                    txtName.setText(data.getData().getName());
                    txtAlamat.setText(data.getData().getAlamat());
                    txtNoPlat.setText(data.getData().getNoPlat());
                    txtJenisKendaraan.setText(data.getData().getJenisKendaraan());
                    txtStatus.setText(data.getData().getStatus());
                    txtLamaCicilan.setText(data.getData().getLamaCicilan());
                    txtWarna.setText(data.getData().getWarna());
                    txtTypeKendaraan.setText(data.getData().getTypeKendaraan());
                    txtMerk.setText(data.getData().getMerk());
//                    LatLng latLng = new LatLng(Double.parseDouble(data.getData().getLat()), Double.parseDouble(data.getData().getLon()));
//                    mMap.addMarker(new MarkerOptions()
//                            .position(latLng)
//                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
//                            .title("Lokasi sebelumnya"));
                    txtLat.setText(lat.toString());
                    txtLon.setText(lon.toString());

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            PlatesModel plateModelList = null;
                            plateModelList = mDb.plateDAO().findByNoPlate(noPlat);
                            if (plateModelList == null){ // jika no_plat belum ada
                                mDb.plateDAO().insertAll(generateObjectData());
                            } else {// jika no_plat sudah ada
                                id = plateModelList.getId();
                                mDb.plateDAO().updatePlate(generateObjectData());
                                editPlat(noPlat);
                            }
                        }
                    }).start();

                }else{
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ResultActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ResultActivity.this, NotFoundActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(ResultActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ResultActivity.this, NotFoundActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataSearchPlatModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
                finish();
            }
        });
    }

    public void editPlat(String noPlat){
        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(ResultActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        Call<DataEditPlatModel> call3 = apiInterface.getEditPlat(
                (String) noPlat,
                toRequestBody(String.valueOf(lat)),
                toRequestBody(String.valueOf(lon))
        );
        call3.enqueue(new Callback<DataEditPlatModel>() {
            @Override
            public void onResponse(Call<DataEditPlatModel> call, Response<DataEditPlatModel> response) {
                progressDialog.dismiss();
                DataEditPlatModel data = response.body();
                if (data !=null) {
                    Toast.makeText(ResultActivity.this, data.getMessage(), Toast.LENGTH_LONG).show();
                }else{
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ResultActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ResultActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataEditPlatModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    public RequestBody toRequestBody(String value) {
        if (value == null) {
            value = "";
        }
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }

    public PlatesModel generateObjectData(){
        PlatesModel plateModel = new PlatesModel();
        if (id != 0){
            plateModel.setId(id);
        }
        plateModel.setName(txtName.getText().toString());
        plateModel.setAlamat(txtAlamat.getText().toString());
        plateModel.setNoPlat(txtNoPlat.getText().toString());
        plateModel.setJenisKendaraan(txtJenisKendaraan.getText().toString());
        plateModel.setStatus(txtStatus.getText().toString());
        plateModel.setLamaCicilan(txtLamaCicilan.getText().toString());
        plateModel.setWarna(txtWarna.getText().toString());
        plateModel.setTypeKendaraan(txtTypeKendaraan.getText().toString());
        plateModel.setMerk(txtMerk.getText().toString());
        plateModel.setLat(String.valueOf(lat));
        plateModel.setLon(String.valueOf(lon));
        plateModel.setPenunggakan("4");
        plateModel.setImagePlat("");
        return plateModel;
    }
}