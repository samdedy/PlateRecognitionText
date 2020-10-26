package id.sam.platerecognitiontext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.parceler.Parcels;

import java.util.List;

import id.sam.platerecognitiontext.adapter.AdapterListBasic;
import id.sam.platerecognitiontext.model.room.PlatesModel;

public class MainActivity extends AppCompatActivity implements AdapterListBasic.OnItemClickListener {

    FloatingActionButton fab;
    RecyclerView rvPlate;
    private AppDatabase mDb;
    private AdapterListBasic adapter;
    public static final String DATA_SEARCH = "dataSearch";
    public static final String DATA_EXTRA = "dataExtra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvPlate = findViewById(R.id.rvPlate);
        rvPlate.setHasFixedSize(true);
        rvPlate.setLayoutManager(new LinearLayoutManager(this));
        mDb = AppDatabase.getInstance(getApplicationContext());
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadDatabase();
            }
        }).start();
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OcrActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            showDialogDelete();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDialogDelete(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Hapus Semua");
        alertDialog.setMessage("Histori scan Plat Nomor")
                .setIcon(R.drawable.ic_delete_forever_red_24)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mDb.plateDAO().deletePlateAll();
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        loadDatabase();
                                    }
                                }).start();
                            }
                        }).start();
                    }
                }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, "Cancel ditekan", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void loadDatabase(){
        List<PlatesModel> plateModelList = null;
        plateModelList = mDb.plateDAO().getAll();
        adapter = new AdapterListBasic(MainActivity.this, plateModelList);
        adapter.setOnItemClickListener(MainActivity.this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rvPlate.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rvPlate.setItemAnimator(new DefaultItemAnimator());
                rvPlate.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onItemClick(View view, final PlatesModel obj, int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PlatesModel plateModel = null;
                plateModel = mDb.plateDAO().findById(obj.getId());
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DATA_SEARCH, Parcels.wrap(plateModel));
                intent.putExtra(DATA_EXTRA, bundle);
                startActivity(intent);
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadDatabase();
            }
        }).start();
    }
}