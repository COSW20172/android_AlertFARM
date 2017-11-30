package com.example.a2096129.alertfarm.api.networks;

import com.example.a2096129.alertfarm.api.alertFarmException.AlertFarmException;
import com.example.a2096129.alertfarm.api.services.HumidityService;
import com.example.a2096129.alertfarm.api.services.TemperatureService;
import com.example.a2096129.alertfarm.entities.Humidity;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cesaravega on 26/11/17.
 */

public class RetrofitNetwork
{

    private static final String BASE_URL = "https://alertfarm.herokuapp.com/";

    private HumidityService humidityService;

    private TemperatureService temperatureService;

    final class UnixEpochDateTypeAdapter
        extends TypeAdapter<Date>
    {


        private UnixEpochDateTypeAdapter()
        {
        }


        @Override
        public Date read( final JsonReader in )
            throws IOException
        {
            // this is where the conversion is performed
            return new Date( in.nextLong() );
        }

        @Override
        @SuppressWarnings( "resource" )
        public void write( final JsonWriter out, final Date value )
            throws IOException
        {
            // write back if necessary or throw UnsupportedOperationException
            out.value( value.getTime() );
        }

    }

    public RetrofitNetwork()
    {
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        Retrofit retrofit =
            new Retrofit.Builder().baseUrl( BASE_URL ).addConverterFactory( gsonConverterFactory ).build();
        humidityService = retrofit.create( HumidityService.class );
        temperatureService = retrofit.create( TemperatureService.class );
    }

    public void getHumidityMeasurementsByUser( RequestCallback<List<Humidity>> requestCallback, int id )
    {
        try
        {
            Call<List<Humidity>> call = humidityService.getHumidityByUser();
            Response<List<Humidity>> execute = call.execute();
            requestCallback.onSuccess( execute.body() );
        }
        catch ( IOException e )
        {
            requestCallback.onFailed( new AlertFarmException( 0, null, e ) );
        }
    }


    //TODO remove extra RetrofitNetwork Temparature

}
