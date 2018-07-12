package com.example.ines.circulo.tiposfecha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ines.circulo.App;
import com.example.ines.circulo.R;
import com.example.ines.circulo.dependencyinjection.activity.ActivityModule;
import com.example.ines.circulo.dependencyinjection.application.ViewModule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TiposFechaActivity extends AppCompatActivity implements TiposFechaView{

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
    String [] types = {"estudiantes"};

    @Inject
    TiposFechaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos_fecha);
        ButterKnife.bind(this);
        ((App) getApplication())
                .getComponent()
                .plus(new ActivityModule(this),
                        new ViewModule(this))
                .inject(this);
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
                presenter.newCirculo(date, types[checked]);
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
                    presenter.searchAll(date, types[checked]);
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

    /**
     *
     * @param errorCode 1:internal error.
     */
    @Override
    public void showError(int errorCode) {

    }

    /**
     * Load Result activity
     * @param list
     */
    @Override
    public void showResults(List<String> list) {

    }

    /**
     * Load Guion activity
     * @param date
     * @param type
     */
    @Override
    public void newCirculo(String date, String type) {

    }
}
