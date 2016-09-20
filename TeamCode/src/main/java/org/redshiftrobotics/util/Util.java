package org.redshiftrobotics.util;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

/**
 * Created by Eric Golde on 9/16/2016.
 */
public class Util {

    OpMode opMode;

    public Util(OpMode opMode){
        this.opMode = opMode;
    }

    public void log(String thing, Object obj){opMode.telemetry.addData(thing, String.valueOf(obj));}

    public void log(Object obj){
        opMode.telemetry.addLine(String.valueOf(obj));
    }

    public void log(ArrayList<Object> obj){
        for(Object msg:obj){
            log(msg);
        }
        updateTelemetry();
    }

    public void writeLine (Object obj){
        opMode.telemetry.addLine(String.valueOf(obj));
        updateTelemetry();
    }

    public void clearTelemetry(){
        log("");
        updateTelemetry();
    }

    public void updateTelemetry(){
        opMode.updateTelemetry(opMode.telemetry);
    }

    public void console(Object obj){
        System.out.println(obj);
    }
}
