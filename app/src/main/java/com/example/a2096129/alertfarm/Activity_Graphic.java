package com.example.a2096129.alertfarm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.a2096129.alertfarm.api.alertFarmException.AlertFarmException;
import com.example.a2096129.alertfarm.api.networks.RequestCallback;
import com.example.a2096129.alertfarm.api.networks.TemperatureRetrofitNetwork;
import com.example.a2096129.alertfarm.entities.Temperature;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.a2096129.alertfarm.R.id.temp;

/**
 * Created by 2096129 on 12/4/17.
 */

public class Activity_Graphic extends AppCompatActivity   {
    private List<Temperature> items;
    private TemperatureRetrofitNetwork network;
    private int id = 1013622878;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);
        getTemperature();
    }
    public void showGraph(List<Temperature> items){

        GraphView graph = (GraphView) findViewById(R.id.graph);

        DataPoint[] dataPoint = new DataPoint[items.size()];
        int x=0;

        for(Temperature t : items){
            System.out.println("me lo chupa "+t.getValor());
            //dataPoint[x] = new DataPoint(x++,t.getValor());

        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoint);
        graph.addSeries(series);


    }

    public void showProgressDialog()
    {
        progressDialog.setMessage( getString( R.string.loading) );
        progressDialog.setCanceledOnTouchOutside( false );
        progressDialog.show();
    }

    public void getTemperature() {
        network = new TemperatureRetrofitNetwork();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        showProgressDialog();
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
                                progressDialog.dismiss();
                                showGraph(items);
                            }
                        });
                    }

                    @Override
                    public void onFailed(AlertFarmException e) {
                        System.out.println(e);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                            }
                        });
                    }
                }, id);

            }
        }.init(getId()));

    }


    public int getId() {
        return id;
    }
}
