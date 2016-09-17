package org.redshiftrobotics.config;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.redshiftrobotics.util.Util;

/**
 * Created by Eric Golde on 9/16/2016.
 */

public class ConfigBase extends OpMode {
    protected Config config;
    Gamepad gp;

    Util util = new Util(this);

    boolean lastStateNext = false;
    boolean lastStateBack = false;
    boolean lastStateUp = false;
    boolean lastStateDown = false;

    int currentVar = 0;


    @Override
    public void init() {
        gp = gamepad1;
        util.log("Ready to start configuring? Press the start button!");
        util.updateTelemetry();
    }

    @Override
    public void start(){
        currentVar = 0;
        showCurrentVariable();
    }

    @Override
    public void loop() {

        if(gp.a && !lastStateNext){
            nextPressed();
        }

        if(gp.b && !lastStateBack){
            backPressed();;
        }

        if(gp.y && !lastStateUp){
            incrementUpPressed();
        }

        if(gp.x && !lastStateDown){
            incrementDownPressed();
        }

        lastStateNext = gp.a;
        lastStateBack = gp.b;
        lastStateUp = gp.y;
        lastStateDown = gp.x;
    }



    void nextPressed(){
        int max = config.variables.size();

        if(currentVar >= max - 1){
            util.clearTelemetry();
            util.log("Done configuring. Please exit this OPMode.");
            util.updateTelemetry();
            currentVar=max;
            return;
        }

        currentVar++;
        showCurrentVariable();
    }

    void backPressed(){
        if(currentVar != 0) {
            currentVar--;
        }
        showCurrentVariable();
    }

    void incrementUpPressed(){
        if(currentVar >= config.variables.size()){
            return; //Return so it doesn't crash
        }
        double cur = config.variables.get(currentVar).getValueDouble();
        double inc = config.variables.get(currentVar).increment;
        double max = config.variables.get(currentVar).max;
        double min = config.variables.get(currentVar).min;

        cur = cur + inc;

        if(cur > max){
            cur = max;
        }

        if(cur < min){
            cur = min;
        }

        config.variables.get(currentVar).setValue(cur);
        showCurrentVariable();
    }

    void incrementDownPressed(){
        if(currentVar >= config.variables.size()){
            return; //Return so it doesn't crash
        }

        double cur = config.variables.get(currentVar).getValueDouble();
        double inc = config.variables.get(currentVar).increment;
        double max = config.variables.get(currentVar).max;
        double min = config.variables.get(currentVar).min;

        cur = cur - inc;

        if(cur > max){
            cur = max;
        }

        if(cur < min){
            cur = min;
        }

        config.variables.get(currentVar).setValue(cur);
        showCurrentVariable();
    }

    void showCurrentVariable(){
        if(currentVar >= config.variables.size()){
            return; //Return so it doesn't crash
        }

        util.log("Variable", config.variables.get(currentVar).name);
        util.log("Value", config.variables.get(currentVar).getValueDouble());
        util.updateTelemetry();
    }

}
