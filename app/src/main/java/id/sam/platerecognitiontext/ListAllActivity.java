package id.sam.platerecognitiontext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.sam.platerecognitiontext.adapter.AdapterListAll;
import id.sam.platerecognitiontext.model.listall.ListAllModel;
import id.sam.platerecognitiontext.model.listall.Product;
import id.sam.platerecognitiontext.model.room.PlatesModel;
import id.sam.platerecognitiontext.model.searchplat.Data;
import id.sam.platerecognitiontext.model.searchplat.DataSearchPlatModel;
import id.sam.platerecognitiontext.service.APIClient;
import id.sam.platerecognitiontext.service.APIInterfacesRest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAllActivity extends AppCompatActivity implements AdapterListAll.OnItemClickListener {

    RecyclerView rvListAll;
    ProgressBar pbListAll;
    TextView txtSearch;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        txtSearch = findViewById(R.id.txtSearch);
        pbListAll = findViewById(R.id.pbListAll);
        rvListAll = findViewById(R.id.rvListAll);
        btnSearch = findViewById(R.id.btnSearch);

        callListAll();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchPlat(txtSearch.getText().toString());
            }
        });
    }

    APIInterfacesRest apiInterface;
    public void callListAll(){
        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        pbListAll.setVisibility(View.VISIBLE);
        Call<ListAllModel> call3 = apiInterface.getListAll();
        call3.enqueue(new Callback<ListAllModel>() {
            @Override
            public void onResponse(Call<ListAllModel> call, Response<ListAllModel> response) {
                pbListAll.setVisibility(View.GONE);
                ListAllModel listAllModel = response.body();
                if (listAllModel !=null) {
                    AdapterListAll adapter = new AdapterListAll(ListAllActivity.this,listAllModel.getProduct());
                    adapter.setOnItemClickListener(ListAllActivity.this);
                    rvListAll.setLayoutManager(new LinearLayoutManager(ListAllActivity.this));
                    rvListAll.setItemAnimator(new DefaultItemAnimator());
                    rvListAll.setAdapter(adapter);
                }else{
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListAllActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListAllActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListAllModel> call, Throwable t) {
                pbListAll.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    ProgressDialog progressDialog;
    public void searchPlat(String param){
        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(ListAllActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        Call<ListAllModel> call3 = apiInterface.getSearchList((String) param);
        call3.enqueue(new Callback<ListAllModel>() {
            @Override
            public void onResponse(Call<ListAllModel> call, Response<ListAllModel> response) {
                progressDialog.dismiss();
                ListAllModel listAllModel = response.body();
                if (listAllModel !=null) {
                    AdapterListAll adapter = new AdapterListAll(ListAllActivity.this,listAllModel.getProduct());
                    adapter.setOnItemClickListener(ListAllActivity.this);
                    rvListAll.setLayoutManager(new LinearLayoutManager(ListAllActivity.this));
                    rvListAll.setItemAnimator(new DefaultItemAnimator());
                    rvListAll.setAdapter(adapter);

                }else{
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListAllActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ListAllActivity.this, NotFoundActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(ListAllActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ListAllActivity.this, NotFoundActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListAllModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
                finish();
            }
        });
    }

    @Override
    public void onItemClick(View view, Product obj, int position) {

    }
}