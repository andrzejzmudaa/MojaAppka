

import javax.bluetooth.*;
import javax.microedition.io.Connector;

import static javax.bluetooth.LocalDevice.*;


public class Main{




    public static void main(String[] args) {
        System.setProperty("bluecove.jsr82.psm_minimum_off","true");
        System.setProperty("bluecove.stack","widcomm");
        //System.out.println(getProperty("bluecove.stack"));


        L2CAPConnectionNotifier notifier = null;
        L2CAPConnection connection = null;

        //display local device address and name
        LocalDevice localDevice = null;
        try {
            localDevice = getLocalDevice();
            localDevice.setDiscoverable(DiscoveryAgent.GIAC);
            UUID uuid = new UUID(1003);
            System.out.println("UUID : "+uuid.toString());
            String url = "btl2cap://0006F5D32FA3:0011;";
            notifier = (L2CAPConnectionNotifier) Connector.open(url);
            System.out.println(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Address: "+localDevice.getBluetoothAddress());
        System.out.println("Name: "+localDevice.getFriendlyName());

        while(true) {
            try {
                System.out.println("waiting for connection...");
                connection = notifier.acceptAndOpen();
                
                //Thread processThread = new Thread(new ProcessConnectionThread(connection));
               // processThread.start();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }











    }


}


