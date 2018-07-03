package com.example.ines.circulo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout est, sup, num, coop, dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        est = (LinearLayout) findViewById(R.id.est_layout);
        sup = (LinearLayout) findViewById(R.id.sup_layout);
        num = (LinearLayout) findViewById(R.id.num_layout);
        coop = (LinearLayout) findViewById(R.id.coop_layout);
        dir = (LinearLayout) findViewById(R.id.dir_layout);
        est.setOnClickListener(this);
        sup.setOnClickListener(this);
        num.setOnClickListener(this);
        coop.setOnClickListener(this);
        dir.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.est_layout :
                intent = new Intent(getApplicationContext(), Guion.class);
                intent.putExtra("type", "E");
                break;
            case R.id.sup_layout:
                intent = new Intent(getApplicationContext(), Guion.class);
                intent.putExtra("type", "S");
                break;
            case R.id.num_layout:
                intent = new Intent(getApplicationContext(), Guion.class);
                intent.putExtra("type", "N");
                break;
            case R.id.coop_layout:
                intent = new Intent(getApplicationContext(), Guion.class);
                intent.putExtra("type", "C");
                break;
            case R.id.dir_layout:
                //TODO: Redirect to directory activity
                break;
            default:
                break;
        }

        startActivity(intent);
    }
}
