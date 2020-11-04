package id.sam.platerecognitiontext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class MenuActivity extends AppCompatActivity {

    CardView cvScan, cvListHistory, cvKeluar, cvListAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cvScan = findViewById(R.id.cvScan);
        cvListHistory = findViewById(R.id.cvListHistory);
        cvKeluar = findViewById(R.id.cvKeluar);
        cvListAll = findViewById(R.id.cvListAll);

        cvScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,OcrActivity.class);
                startActivity(intent);
            }
        });

        cvListHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        cvKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cvListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,ListAllActivity.class);
                startActivity(intent);
            }
        });
    }
}