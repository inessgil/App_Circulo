package com.example.ines.circulo.tiposfecha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ines.circulo.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TiposFechaActivity extends AppCompatActivity {

    @BindView(R.id.rb_group)
    RadioGroup group;
    @BindView(R.id.rb_c1)
    RadioButton circulo_1;
    @BindView(R.id.rb_c2)
    RadioButton circulo_2;
    @BindView(R.id.rb_c3)
    RadioButton circulo_3;
    @BindView(R.id.rb_c4)
    RadioButton circulo_4;
    @BindView(R.id.calendar)
    CalendarView calendar;
    int type; //0:new; 1:load existing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos_fecha);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("TYPE", -1);
        calendar.setDate(System.currentTimeMillis(),false,true);
        group.clearCheck();
    }

    @OnClick(R.id.b_aceptar)
    public void submit () {
        int checked = group.getCheckedRadioButtonId();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMYYYY");
        String date = simpleDateFormat.format(calendar.getDate());

        // NEW
        if ( type == 0 ) {
            if (checked == -1)
                Toast.makeText(this, "Por favor, selecciona un círculo", Toast.LENGTH_SHORT).show();
            else {
                //TODO: Check if file already exists
            }
        }

        // LOAD
        /**
         * Currently, searching by date is mandatory. Further versions must allow searching by type only.
         */
        else if ( type == 1) {
            if ( date == null && checked != -1 ) {
                Toast.makeText(this, "Por favor, seleccione una fecha o tipo", Toast.LENGTH_SHORT).show();
            }
            else {
                if (date!=null) {
                    //TODO: Extract all file names in the directory
                }
                if( checked!= -1 ) {
                    //TODO: Extract all file names with the correct name
                }
            }
        }

        else {
            Toast.makeText(this, "Error de sistema", Toast.LENGTH_LONG).show();
        }
    }
}
