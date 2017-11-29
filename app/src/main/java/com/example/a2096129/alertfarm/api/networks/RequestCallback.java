package com.example.a2096129.alertfarm.api.networks;

import com.example.a2096129.alertfarm.api.alertFarmException.AlertFarmException;

/**
 * Created by cesaravega on 29/11/17.
 */

public interface RequestCallback<T> {

    void onSuccess( T response );

    void onFailed(AlertFarmException e );

}
