package PS3PAD;

import org.usb4java.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class PS3PAD_USB  extends PS3_PAD  implements Runnable  {
    // bmRequestType USB Setup Package Constants
    // https://www.beyondlogic.org/usbnutshell/usb6.shtml#StandardInterfaceRequests
    //	D7 Data Phase Transfer Direct ion
    final static byte HOST_TO_DEVICE=(byte)0x00;
    final static byte DEVICE_TO_HOST=(byte)0x80;
    //  D6..5 Type
    final static byte STANDARD=(byte)0x00;
    final static byte CLASS=(byte)0x20;
    final static byte VENDOR=(byte)0x40;
    final static byte RESERVED=(byte)0x60;
    //  D4..0 Recipient
    final static byte DEVICE=(byte)0x00;
    final static byte INTERFACE=(byte)0x01;
    final static byte ENDPOINT=(byte)0x02;
    final static byte OTHER=(byte)0x03;
    //                4..31 = Reserved

    //Standard Device Requests
    // https://www.beyondlogic.org/usbnutshell/usb6.shtml#StandardInterfaceRequests
    final static byte GET_STATUS=(byte)0x00;
    final static byte CLEAR_FEATURE =(byte)0x01;
    final static byte SET_FEATURE =(byte)0x03;
    final static byte SET_ADDRESS =(byte)0x05;
    final static byte GET_DESCRIPTOR =(byte)0x06;
    final static byte SET_DESCRIPTOR =(byte)0x07;
    final static byte GET_CONFIGURATION =(byte)0x08;
    final static byte SET_CONFIGURATION =(byte)0x09;



    final static short VendorID=(short)0x054c;
    final static short ProductID=(short)0x0268;
    final static byte[] HOST_BT_ADRESS={(byte)0x01,(byte)0x02,(byte)0x03,(byte)0xFF,(byte)0x0,(byte)0x0};
    final static int PS3_REPORT_BUFFER_SIZE=48; //Verify if needed in future
    final static int PS3_BTADRESS_BUFFER_SIZE=8; //Verify if needed in future
    final static int PS2_MAXPKTSIZE=64; //Verify if needed in future
    final static byte INPUT_ENDPOINT=0x02; //Verify if needed in future
    final static byte OUTPUT_ENDPOINT=(byte)0x81;
    final static byte[] BYTE_MESSAGE=  //Verify if needed in future
            {(byte)0x0,(byte)0xFF,(byte)0x0,(byte)0xFF,(byte)0x0,(byte)0x0,(byte)0x0,(byte)0x0,
            (byte)0x0,(byte)0x0,(byte)0xFF,(byte)0x27,(byte)0x10,(byte)0x0,(byte)0x32,(byte)0xFF,(byte)0x27,(byte)0x10,
            (byte)0x0,(byte)0x32,(byte)0xFF,(byte)0x27,(byte)0x10,(byte)0x0,(byte)0x32,(byte)0xFF,(byte)0x27,(byte)0x10,
            (byte)0x0,(byte)0x32,(byte)0x0,(byte)0x0,(byte)0x0,(byte)0x0,(byte)0x0,(byte)0x0,(byte)0x0,(byte)0x0,
            (byte)0x0,(byte)0x0,(byte)0x0,(byte)0x0,(byte)0x0,(byte)0x0,(byte)0x0,(byte)0x0,(byte)0x0,(byte)0x0};
    final static byte[] LED_RUMBLE_MESSAGE_FRAME=
            {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x02, (byte)0xff, (byte)0x27, (byte)0x10, (byte)0x00, (byte)0x32, (byte)0xff,
            (byte)0x27, (byte)0x10, (byte)0x00, (byte)0x32, (byte)0xff, (byte)0x27, (byte)0x10, (byte)0x00,
            (byte)0x32, (byte)0xff, (byte)0x27, (byte)0x10, (byte)0x00, (byte)0x32, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};


    //Buttons Values :


    int status;
    boolean enabled=false;

    DeviceHandle ps3Pad;
    Context context = new Context();
    ByteBuffer dataBuffer;
    IntBuffer dataIntBuffer;

    public PS3PAD_USB(){

        status = LibUsb.init(context);
        if (status != LibUsb.SUCCESS) throw new LibUsbException("Unable to initialize libusb.", status);
        findAllDevices();
        ps3Pad = LibUsb.openDeviceWithVidPid(context,VendorID,ProductID);

        if (ps3Pad==null){
            System.out.println("PS3Pad not connected");
            return;
        }
        LibUsb.resetDevice(ps3Pad);
        // bConfigurationValue = 1 **Configuration nr 1 , please check ConfigurationDescriptor
        status=LibUsb.setConfiguration(ps3Pad,1);
        System.out.println("SetConfiguration status : "+status);  // 0 if OK
        //bInterfaceNumber=0  **Interface nr 0, please check ConfigurationDescriptor
        status=LibUsb.claimInterface(ps3Pad,0);
            System.out.println("ClaimInterface status : "+status);

            //setBluetoothHostAdress(HOST_BT_ADRESS);
            //dsiplayBluetoothHostAdress();
            enablePS3Pad();
            setLED(6);
            setRumble((byte)5,(byte)255,(byte)5,(byte)20);
        }

        public void printRawPS3PadData() {
            if(!enabled){
                System.out.println("PS3 Pad not enabled");
                return;
            }
            dataBuffer = ByteBuffer.allocateDirect(0x31);
            dataIntBuffer = IntBuffer.allocate(31);
            status= LibUsb.interruptTransfer(ps3Pad,OUTPUT_ENDPOINT,dataBuffer,dataIntBuffer,0);
            System.out.println("Interrupt transfer status : "+status);
            printDataBuffer(dataBuffer,0x31);
        }

    public byte[] getRawPS3PadData() {
        if(!enabled){
            System.out.println("PS3 Pad not enabled");
            return null;
        }
        dataBuffer = ByteBuffer.allocateDirect(0x31);
        dataIntBuffer = IntBuffer.allocate(31);
        status= LibUsb.interruptTransfer(ps3Pad,OUTPUT_ENDPOINT,dataBuffer,dataIntBuffer,0);
        if(status!=0)
        System.out.println("Interrupt transfer status : "+status);
        byte[] dataToReturn = new byte[31];
        for(int i=0;i<31;i++)
            dataToReturn[i]=dataBuffer.get(i);
        return dataToReturn;
    }

        public void setLED(int i){
            byte[] ledpattern = {0x02, 0x04, 0x08, 0x10, 0x12, 0x14, 0x18 };
            byte[] LEDFrameToSend = LED_RUMBLE_MESSAGE_FRAME;
            if (i < 7) LEDFrameToSend[9] = ledpattern[i];
            dataBuffer = ByteBuffer.allocateDirect(LEDFrameToSend.length);
            for (byte b : LEDFrameToSend) {
                dataBuffer.put(b);
            }
        status = LibUsb.controlTransfer(ps3Pad, (byte)(HOST_TO_DEVICE|CLASS|INTERFACE), SET_CONFIGURATION, (short) 0x0201, (short) 0, dataBuffer, 0);
        printDataBuffer(dataBuffer,LEDFrameToSend.length);
        }

        public void setRumble(byte duration_right,byte power_right,byte duration_left,byte power_left){

        byte[] rumbleSettings = LED_RUMBLE_MESSAGE_FRAME;
            rumbleSettings[1] = duration_right;
            rumbleSettings[2] = power_right;
            rumbleSettings[3] = duration_left;
            rumbleSettings[4] = power_left;
            dataBuffer = ByteBuffer.allocateDirect(rumbleSettings.length);
            for (byte b : rumbleSettings) {
                dataBuffer.put(b);
            }
            status = LibUsb.controlTransfer(ps3Pad, (byte)(HOST_TO_DEVICE|CLASS|INTERFACE), SET_CONFIGURATION, (short) 0x0201, (short) 0, dataBuffer, 0);
            printDataBuffer(dataBuffer,rumbleSettings.length);




        }

    public void setBluetoothHostAdress(byte[] byteBluetoothAdress){
        int i=0;
        if (byteBluetoothAdress.length!=6){
            System.out.println("Wrong Bluetooth Adress");
        return;}
        byte[] bluetoothAdress= new byte[8];
        bluetoothAdress[0]=0x01; //First byte of packet,have to be 0x01
        bluetoothAdress[1] = 0x00; //Second byte of packet,have to be 0x00
        for (byte adress : byteBluetoothAdress) {
            bluetoothAdress[i+2]=adress;
            i++;
        }
        dataBuffer = ByteBuffer.allocateDirect(bluetoothAdress.length);
        System.out.print("Assigned BT adress will be : ");
        for (byte adress : bluetoothAdress) {
            System.out.print(String.format("%x",adress) + " : ");
            dataBuffer.put(adress);
        }
        System.out.println();
        status=LibUsb.controlTransfer(ps3Pad, (byte)(HOST_TO_DEVICE|CLASS|INTERFACE), SET_CONFIGURATION, (short) 0x03F5, (short) 0, dataBuffer, 0);
        if (status==bluetoothAdress.length)
            System.out.println("BT Adress assigiment OK");
        else
            System.out.println("BT Adress assigiment not OK, error status : "+status);
        printDataBuffer(dataBuffer,bluetoothAdress.length);



    }

    public void dsiplayBluetoothHostAdress(){
        int bluetoothAdressLenght=8;
        dataBuffer = ByteBuffer.allocateDirect(bluetoothAdressLenght);
        status=LibUsb.controlTransfer(ps3Pad, (byte)(HOST_TO_DEVICE|CLASS|INTERFACE),CLEAR_FEATURE, (short) 0x03F5, (short) 0, dataBuffer, 0);
        if (status==bluetoothAdressLenght)
            System.out.println("BT Adress read OK");
        else
            System.out.println("BT Adress read not OK, error status : "+status);
        printDataBuffer(dataBuffer,bluetoothAdressLenght);




    }


    private void enablePS3Pad() {
        dataBuffer = ByteBuffer.allocateDirect(0x04);
        dataBuffer.put((byte)0x42);
        dataBuffer.put((byte)0x0c);
        dataBuffer.put((byte)0x00);
        dataBuffer.put((byte)0x00);
        status = LibUsb.controlTransfer(ps3Pad, (byte)(HOST_TO_DEVICE|CLASS|INTERFACE), SET_CONFIGURATION, (short) 0x03F4, (short) 0, dataBuffer, 0);
        if (status == 4 ){
            enabled=true;
            System.out.println("PS3 Pad correctly enabled");}
        else
            System.out.println("Error during PS3 Pad Enabling : "+status);
        printDataBuffer(dataBuffer,0x04);
    }

    public void findAllDevices()
    {

        // Read the USB device list
        DeviceList list = new DeviceList();
        int result = LibUsb.getDeviceList(context, list);
        if (result < 0) throw new LibUsbException("Unable to get device list", result);

        try
        {
            // Iterate over all devices and scan for the right one
            System.out.println("List of devices : ");
            for (Device device: list)
            {
                DeviceDescriptor descriptor = new DeviceDescriptor();
                result = LibUsb.getDeviceDescriptor(device, descriptor);
                System.out.println("Device : "+device+" Descriptor : "+descriptor);
            }
        }
        finally
        {
            // Ensure the allocated device list is freed
            LibUsb.freeDeviceList(list, true);
        }


    }
    public void disconnectPad(){
        LibUsb.releaseInterface(ps3Pad,0);
        LibUsb.resetDevice(ps3Pad);
        LibUsb.close(ps3Pad);
        LibUsb.exit(context);


    }

    void printDataBuffer(ByteBuffer incommingBuffer,int bufferSize){
        System.out.println("Printed buffer size "+bufferSize);
        for(int i=0;(i<bufferSize);i++){
            System.out.print(String.format(" %x",incommingBuffer.get(i)));
        }
        System.out.println();
    }


    @Override
    public void run()  {

        while(true) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                this.updateButtonsValue(this.getRawPS3PadData());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //this.printRawPS3PadData();

        }


    }
}
