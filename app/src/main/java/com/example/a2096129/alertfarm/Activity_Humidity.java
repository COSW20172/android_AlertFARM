package com.example.a2096129.alertfarm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.a2096129.alertfarm.api.alertFarmException.AlertFarmException;
import com.example.a2096129.alertfarm.api.networks.RequestCallback;
import com.example.a2096129.alertfarm.api.networks.RetrofitNetwork;
import com.example.a2096129.alertfarm.entities.Humidity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 2096129 on 11/22/17.
 */

public class Activity_Humidity
    extends AppCompatActivity
    implements SwipeRefreshLayout.OnRefreshListener
{


    private List<Humidity> items;

    private RetrofitNetwork network;

    private int id = 1013622878;

    ProgressDialog progressDialog;

    private  SwipeRefreshLayout swipeRefreshLayout;

    public void showProgressDialog()
    {
        progressDialog.setMessage( getString( R.string.loading) );
        progressDialog.setCanceledOnTouchOutside( false );
        progressDialog.show();
    }

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        progressDialog = new ProgressDialog( this );
        super.onCreate( savedInstanceState );
        setContentView( R.layout.humidity );
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById( R.id.swipe_refresh_layout );
        swipeRefreshLayout.setOnRefreshListener( this );
        getHumidiy();
    }

    public void getHumidiy()
    {
        network = new RetrofitNetwork();
        ExecutorService executorService = Executors.newFixedThreadPool( 1 );

        showProgressDialog();
        executorService.execute( new Runnable()
        {
            int id;

            public Runnable init( int iduser )
            {
                id = iduser;
                return this;
            }

            @Override
            public void run()
            {
                network.getHumidityMeasurementsByUser( new RequestCallback<List<Humidity>>()
                {
                    @Override
                    public void onSuccess( List<Humidity> response )
                    {
                        System.out.println( "response: " + response );
                        items = response;
                        runOnUiThread( new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                showTemperatures( items );
                                progressDialog.dismiss();
                            }
                        } );
                    }

                    @Override
                    public void onFailed( AlertFarmException e )
                    {
                        System.out.println( e );
                        runOnUiThread( new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                progressDialog.dismiss();
                            }
                        } );
                    }
                }, id );

            }
        }.init( getId() ) );

    }

    //TODO change this to use an Adapter and RecyclerView
    private void showTemperatures( List<Humidity> items )
    {
        TableLayout tableLayout = (TableLayout) findViewById( R.id.history_table_humidities );
        TableRow row;
        TextView tv_sync_date, tv_sync_value, tv_sync_idArduino;
        Date date;
        int cont = 0;
        for ( Humidity h : items )
        {
            if ( cont == 10 )
            {
                break;
            }

            row = new TableRow( getBaseContext() );
            row.setLayoutParams(
                new TableRow.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                                           ViewGroup.LayoutParams.WRAP_CONTENT ) );

            tv_sync_date = new TextView( this );
            tv_sync_value = new TextView( this );
            tv_sync_idArduino = new TextView( this );

            date = new Date( (long) ( h.getFecha() ) * 1000 );

            DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );

            tv_sync_date.setText( dateFormat.format( date ) );
            tv_sync_value.setText( String.valueOf( h.getValor() ) );
            tv_sync_idArduino.setText( String.valueOf( h.getArduino_idArduino() ) );

            row.addView( tv_sync_date );
            row.addView( tv_sync_value );
            row.addView( tv_sync_idArduino );

            tableLayout.addView( row );

            cont++;
        }
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    @Override
    public void onRefresh()
    {
        //TODO make new request to server to retrieve data again

    //TO hide refresh animation
    //        swipeRefreshLayout.setRefreshing( false );


        //TODO implment Chart
        //Chart Layout
//        <lecho.lib.hellocharts.view.LineChartView
//        android:id="@+id/chart"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:layout_weight="1"
//        android:padding="15dp"
//        android:visibility="gone" />



//        private void loadChart( List<MeasurementData> measurementDataList, String xAxisLabel, String yAxisLabel )
//        {
//
//            if ( measurementDataList != null && !measurementDataList.isEmpty() )
//            {
//                final List<PointValue> valuesMetric1 = new ArrayList<>();
//                final List<PointValue> valuesMetric2 = new ArrayList<>();
//                List<AxisValue> xAxisValues = new ArrayList<>();
//                Collections.sort( measurementDataList, new Comparator<MeasurementData>()
//                {
//                    @Override
//                    public int compare( MeasurementData o1, MeasurementData o2 )
//                    {
//                        return o1.getTimestamp().compareTo( o2.getTimestamp() );
//                    }
//                } );
//                int i = 1;
//                for ( MeasurementData measurementData : measurementDataList )
//                {
//                    valuesMetric1.add( new PointValue( i, measurementData.getMainMetric() ) );
//                    valuesMetric2.add( new PointValue( i, measurementData.getSecondMetric() ) );
//                    AxisValue axisValue = new AxisValue( i );
//                    axisValue.setLabel( DateUtil.formatDateForChart( measurementData.getTimestamp() ) );
//                    xAxisValues.add( axisValue );
//                    i++;
//                }
//
//                //In most cased you can call data model methods in builder-pattern-like manner.
//
//                List<Line> lines = new ArrayList<>();
//
//                Line line1 =
//                    new Line( valuesMetric1 ).setColor( getContext().getResources().getColor( R.color.chart_main_color ) );
//                Line line2 = new Line( valuesMetric2 ).setColor(
//                    getContext().getResources().getColor( R.color.chart_second_color ) );
//
//                lines.add( line1 );
//                lines.add( line2 );
//
//                line1.setHasPoints( false );
//                line2.setHasPoints( false );
//
//                LineChartData data = new LineChartData();
//                data.setLines( lines );
//
//                Axis axisX = new Axis();
//                Axis axisY = new Axis().setHasLines( true );
//                axisX.setName( xAxisLabel );
//                axisX.setValues( xAxisValues );
//                axisY.setName( yAxisLabel );
//
//                data.setAxisXBottom( axisX );
//                data.setAxisYLeft( axisY );
//
//                lineChartView.setLineChartData( data );
//
//                final MeasurementData measurementData1 = measurementDataList.get( 0 );
//
//                uiHandler.post( new Runnable()
//                {
//                    @Override
//                    public void run()
//                    {
//                        lineChartView.setVisibility( View.VISIBLE );
//                        mainMetricChartLabel.setText( measurementData1.getMainMetricTitleResId() );
//                        mainMetricChartLabel.setVisibility( View.VISIBLE );
//                        if ( measurementData1.getSecondMetricTitleResId() != 0 )
//                        {
//                            secondMetricChartLabel.setText( measurementData1.getSecondMetricTitleResId() );
//                            secondMetricChartLabel.setVisibility( View.VISIBLE );
//                        }
//                    }
//                } );
//            }
//        }

    }
}
