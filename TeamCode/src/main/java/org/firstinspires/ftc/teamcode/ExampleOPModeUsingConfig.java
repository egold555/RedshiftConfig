package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.redshiftrobotics.config.ConfigVariable;
import org.redshiftrobotics.util.Util;

/**
 * Created by Eric Golde on 9/16/2016.
 */
@TeleOp(name="Run Me!", group="Some OPMode")
public class ExampleOPModeUsingConfig extends OpMode {

                                       // new ConfigVariable(name, value, min, max, increment);

    static final ConfigVariable test1 = new ConfigVariable("test1", 1, 0, 2, 0.1);
    static final ConfigVariable test2 = new ConfigVariable("test2", 100, 50, 300, 6);

    Util util = new Util(this);

    static void initMe() //you need this because static { } does not get called in FTC //CALL CLEAR BEFORE ANYTHING
    {
        ExampleConfig.config.clearArray();
        ExampleConfig.config.addVariable(test1);
        ExampleConfig.config.addVariable(test2);
    }

    @Override
    public void init() {
        //not needed
    }

    @Override
    public void start(){
        //for this simple op mode just spit out the data
        util.log("Test1: " + test1.getValueDouble());
        util.log("Test2: " + test2.getValueInt());
        util.updateTelemetry();
    }

    @Override
    public void loop() {
        //not needed
    }
}
