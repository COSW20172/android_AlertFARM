package com.example.a2096129.alertfarm.api.networks;

import com.example.a2096129.alertfarm.api.alertFarmException.AlertFarmException;
import com.example.a2096129.alertfarm.api.services.TemperatureService;
import com.example.a2096129.alertfarm.entities.Temperature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
 * Created by cesaravega on 29/11/17.
 */

public class TemperatureRetrofitNetwork {

    private static final String BASE_URL = "https://alertfarm.herokuapp.com/";
    private TemperatureService temperatureService;

    final class UnixEpochDateTypeAdapter
            extends TypeAdapter<Date> {


        private UnixEpochDateTypeAdapter() {
        }



        @Override
        public Date read(final JsonReader in)
                throws IOException {
            // this is where the conversion is performed
            return new Date(in.nextLong());
        }

        @Override
        @SuppressWarnings("resource")
        public void write(final JsonWriter out, final Date value)
                throws IOException {
            // write back if necessary or throw UnsupportedOperationException
            out.value(value.getTime());
        }

    }

    public TemperatureRetrofitNetwork(){
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();

        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new TemperatureRetrofitNetwork.UnixEpochDateTypeAdapter()).create();
        Retrofit retrofit= new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        temperatureService = retrofit.create( TemperatureService.class );
    }

    public void getTemperaturesByUser( RequestCallback<List<Temperature>> requestCallback, int id)
    {
        try {
            Call<List<Temperature>> call = temperatureService.getTemperatureByUser();
            Response<List<Temperature>> execute = call.execute();
            requestCallback.onSuccess(execute.body());
        }
        catch ( IOException e )
        {
            requestCallback.onFailed( new AlertFarmException( 0, null, e ) );
        }
    }
}
