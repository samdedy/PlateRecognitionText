package id.sam.platerecognitiontext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        pbListAll = findViewById(R.id.pbListAll);
        rvListAll = findViewById(R.id.rvListAll);

        callListAll();
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
    public void searchPlat(String noPlat){
        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(ListAllActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        Call<DataSearchPlatModel> call3 = apiInterface.getSearchPlat((String) noPlat);
        call3.enqueue(new Callback<DataSearchPlatModel>() {
            @Override
            public void onResponse(Call<DataSearchPlatModel> call, Response<DataSearchPlatModel> response) {
                progressDialog.dismiss();
                DataSearchPlatModel data = response.body();
                if (data !=null) {
//                    List<Product> productList = new ArrayList<>();
//                    for (int i=0; i<1; i++){
//                        productList.add(data.getData());
//                    }
//
//                    AdapterListAll adapter = new AdapterListAll(ListAllActivity.this,productList);
//                    adapter.setOnItemClickListener(ListAllActivity.this);
//                    rvListAll.setLayoutManager(new LinearLayoutManager(ListAllActivity.this));
//                    rvListAll.setItemAnimator(new DefaultItemAnimator());
//                    rvListAll.setAdapter(adapter);

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
            public void onFailure(Call<DataSearchPlatModel> call, Throwable t) {
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