package id.sam.platerecognitiontext;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.location.aravind.getlocation.GeoLocator;

import org.json.JSONObject;

import id.sam.platerecognitiontext.model.searchplat.DataSearchPlatModel;
import id.sam.platerecognitiontext.service.APIClient;
import id.sam.platerecognitiontext.service.APIInterfacesRest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {

    TextView txtName, txtAlamat, txtNoPlat, txtJenisKendaraan, txtStatus, txtLamaCicilan, txtWarna, txtTypeKendaraan, txtMerk, txtLat, txtLon;
    Double lat = 0.0, lon = 0.0;

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

        GeoLocator geoLocator = new GeoLocator(getApplicationContext(),this);
        lat = geoLocator.getLattitude();
        lon = geoLocator.getLongitude();

        searchPlat(getIntent().getStringExtra("noPlat"));
    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void searchPlat(String noPlat){
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
//                    txtLat.setText(data.getData().getLat());
//                    txtLon.setText(data.getData().getLon());
                    txtLat.setText(lat.toString());
                    txtLon.setText(lon.toString());

                }else{
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ResultActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ResultActivity.this, NotFoundActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(ResultActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataSearchPlatModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}