import com.pi4j.component.switches.SwitchListener;
import com.pi4j.component.switches.SwitchState;
import com.pi4j.component.switches.SwitchStateChangeEvent;
import com.pi4j.device.piface.PiFace;
import com.pi4j.device.piface.PiFaceLed;
import com.pi4j.device.piface.PiFaceSwitch;
import com.pi4j.device.piface.impl.PiFaceDevice;
import com.pi4j.io.gpio.PiFacePin;
import com.pi4j.wiringpi.Spi;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by wxk's alienware on 2017/12/31.
 */
public class application {

    public static PiFace piFace;
    public static void main(String[] args)
    {
        try {
            piFace = new PiFaceDevice(PiFace.DEFAULT_ADDRESS, Spi.CHANNEL_0);
        } catch (IOException e) {
            piFace = null;
            e.printStackTrace();
        }

        if(piFace != null)
        {
            piFace.getSwitch(PiFaceSwitch.S1).addListener(new SwitchListener() {
                public void onStateChange(SwitchStateChangeEvent switchStateChangeEvent) {
                    if(switchStateChangeEvent.getNewState() == SwitchState.ON)
                    {
                        piFace.getLed(PiFaceLed.LED0).on();
                    }
                    else
                    {
                        piFace.getLed(PiFaceLed.LED0).off();
                    }
                }
            });

            piFace.getSwitch(PiFaceSwitch.S2).addListener(new SwitchListener() {
                public void onStateChange(SwitchStateChangeEvent switchStateChangeEvent) {
                    if(switchStateChangeEvent.getNewState() == SwitchState.ON)
                    {
                        piFace.getLed(PiFaceLed.LED1).on();
                    }
                    else
                    {
                        piFace.getLed(PiFaceLed.LED1).off();
                    }
                }
            });
        }
    }
}
