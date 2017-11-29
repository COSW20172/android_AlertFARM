package com.example.a2096129.alertfarm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.a2096129.alertfarm.api.alertFarmException.AlertFarmException;
import com.example.a2096129.alertfarm.api.networks.RequestCallback;
import com.example.a2096129.alertfarm.api.networks.TemperatureRetrofitNetwork;
import com.example.a2096129.alertfarm.entities.Temperature;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 2096129 on 11/22/17.
 */

public class Activity_Temperature  extends AppCompatActivity {


    private List<Temperature> items;
    private TemperatureRetrofitNetwork network;
    private int id = 1013622878;
    ProgressDialog cargando;

    public void cargar() {
        cargando.setMessage("Cargando...");
        cargando.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        cargando.setIndeterminate(true);
        cargando.setCanceledOnTouchOutside(false);
        cargando.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cargando = new ProgressDialog(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature);
        getHumidiy();
    }

    public void getHumidiy() {
        network = new TemperatureRetrofitNetwork();
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.execute(new Runnable() {
            int id;

            public Runnable init(int iduser) {
                id = iduser;
                return this;
            }

            @Override
            public void run() {
                network.getTemperaturesByUser(new RequestCallback<List<Temperature>>() {
                    @Override
                    public void onSuccess(List<Temperature> response) {
                        System.out.println("response: " + response);
                        items = response;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cargando.hide();
                                showTemperatures(items);
                            }
                        });
                    }

                    @Override
                    public void onFailed(AlertFarmException e) {
                        System.out.println(e);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cargando.hide();
                            }
                        });
                    }
                }, id);

            }
        }.init(getId()));

    }

    private void showTemperatures(List<Temperature> items){
        TableLayout tableLayout = (TableLayout) findViewById(R.id.history_table_temperatures);
        TableRow row;
        TextView tv_sync_date, tv_sync_value, tv_sync_idArduino;
        Date date;
        int cont = 0;
        for(Temperature t: items){
            if(cont == 10) break;

            row = new TableRow(getBaseContext());
            row.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            tv_sync_date = new TextView(this);
            tv_sync_value = new TextView(this);
            tv_sync_idArduino = new TextView(this);

            date = new Date((long) (t.getFecha()) * 1000);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            tv_sync_date.setText(dateFormat.format(date));
            tv_sync_value.setText(String.valueOf(t.getValor()));
            tv_sync_idArduino.setText(String.valueOf(t.getArduino_idArduino()));

            row.addView(tv_sync_date);
            row.addView(tv_sync_value);
            row.addView(tv_sync_idArduino);

            tableLayout.addView(row);

            cont++;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
