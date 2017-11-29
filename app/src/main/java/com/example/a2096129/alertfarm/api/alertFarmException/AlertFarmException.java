package com.example.a2096129.alertfarm.api.alertFarmException;

import java.io.IOException;

/**
 * Created by cesaravega on 29/11/17.
 */

public class AlertFarmException extends Exception{

    public AlertFarmException(){
        super();
    }

    public AlertFarmException(int i, Object o, IOException e) {
        super(e);
    }
}
