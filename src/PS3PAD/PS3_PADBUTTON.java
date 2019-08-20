package PS3PAD;

import java.util.HashMap;
import java.util.Map;

public class PS3_PADBUTTON {

    public boolean isButtonPressedStatus;
    public byte buttonPressedValue;
    public byte buttonLeftRightValue;
    public byte buttonUpDownValue;
    boolean hasAnalogValue=false;
    boolean isAnalogKey=false;
    //Data taken from ENUM during class constructing
    public String buttonName;
    int byteToDecodeButtonAnalogStatus;          // byte position in status frame to decode value for LEFT/RIGHTvalue
    int byteToDecodeSecondButtonAnalogStatus;   // byte position in status frame to decode value for UP/DOWN value
    int byteToDecodeButtonBooleanStatus;         // byte position in status frame to decode value for isButtonPressedStatus
    byte decodingValueForBooleanStatus;


    PS3_PADBUTTON(USB_PS3_Button PS3_Button){
        this.buttonName=PS3_Button.buttonName;
        if((this.byteToDecodeButtonAnalogStatus=PS3_Button.byteToDecodeButtonAnalogStatus)>0)
            hasAnalogValue=true;
        if((this.byteToDecodeSecondButtonAnalogStatus=PS3_Button.byteToDecodeSecondButtonAnalogStatus)>0)
            isAnalogKey=true;
        this.byteToDecodeButtonBooleanStatus=PS3_Button.byteToDecodeButtonBooleanStatus;
        this.decodingValueForBooleanStatus=PS3_Button.decodingValueForBooleanStatus;
    }
    PS3_PADBUTTON(BT_PS3_Button PS3_Button){
    //To complete in future
    }

    public void updateButtonValue(byte[] incommingByteArray){
        if ((incommingByteArray[this.byteToDecodeButtonBooleanStatus] & decodingValueForBooleanStatus) == decodingValueForBooleanStatus) {
            isButtonPressedStatus = true;
            //System.out.println("Button : " + buttonName + " pressed");
        } else
            isButtonPressedStatus = false;
        if(!hasAnalogValue)
            return;

        if(!isAnalogKey)
            buttonPressedValue=incommingByteArray[byteToDecodeButtonAnalogStatus];
        else{
            buttonLeftRightValue=incommingByteArray[byteToDecodeButtonAnalogStatus];
            buttonUpDownValue=incommingByteArray[byteToDecodeSecondButtonAnalogStatus];}

        //printAnalogButtonPressedValues();
    }



    public enum USB_PS3_Button {
        USBBUTTONSELECT("BUTTONSELECT", 2, -1,-1, (byte) 0x01),
        USBBUTTONLANALOG("BUTTONLANALOG", 2, 6, 7, (byte) 0x02), //6,7
        USBBUTTONRANALOG ("BUTTONRANALOG", 2, 8, 9, (byte) 0x04), //8,9
        USBBUTTONSTART ("BUTTONSTART", 2, -1,-1, (byte) 0x08),
        USBBUTTONUP ("BUTTONUP", 2, 14,-1, (byte) 0x10),
        USBBUTTONRIGHT("BUTTONRIGHT", 2, 15,-1, (byte) 0x20),
        USBBUTTONDOWN("BUTTONDOWN", 2, 16,-1, (byte) 0x40),
        USBBUTTONLEFT("BUTTONLEFT", 2, 17,-1, (byte) 0x80),
        USBBUTTONL2("BUTTONL2", 3, 18,-1, (byte) 0x01),
        USBBUTTONR2("BUTTONR2", 3, 19,-1, (byte) 0x02),
        USBBUTTONL1("BUTTONL1", 3, 20,-1, (byte) 0x04),
        USBBUTTONR1("BUTTONR1", 3, 21,-1, (byte) 0x08),
        USBBUTTONTRIANGLE("BUTTONTRIANGLE", 3, 22,-1, (byte) 0x10),
        USBBUTTONCIRCLE("BUTTONCIRCLE", 3, 23,-1, (byte) 0x20),
        USBBUTTONCROSS("BUTTONCROSS", 3, 24,-1, (byte) 0x40),
        USBBUTTONSQUARE("BUTTONSQUARE", 3, 25,-1, (byte) 0x80),
        USBBUTTONPS("BUTTONPS", 4, -1,-1, (byte) 0x01);

        String buttonName;
        int byteToDecodeButtonAnalogStatus;          // byte position in status frame to decode value for LEFT/RIGHTvalue
        int byteToDecodeSecondButtonAnalogStatus;   // byte position in status frame to decode value for UP/DOWN value
        int byteToDecodeButtonBooleanStatus;         // byte position in status frame to decode value for isButtonPressedStatus
        byte decodingValueForBooleanStatus;

        USB_PS3_Button(String buttonName,int byteToDecodeButtonBooleanStatus,int byteToDecodeButtonAnalogStatus,int byteToDecodeSecondButtonAnalogStatus,byte decodingValueForBooleanStatus){
            this.buttonName=buttonName;
            this.byteToDecodeButtonBooleanStatus=byteToDecodeButtonBooleanStatus;
            this.byteToDecodeButtonAnalogStatus=byteToDecodeButtonAnalogStatus;
            this.byteToDecodeSecondButtonAnalogStatus=byteToDecodeSecondButtonAnalogStatus;
            this.decodingValueForBooleanStatus=decodingValueForBooleanStatus;
        }
    }
    public enum BT_PS3_Button{}

    private void printAnalogButtonPressedValues() {
        if (buttonPressedValue!=(byte)0x00)
            System.out.println("Button : " + buttonName + " value : "+String.format(" %x",buttonPressedValue));
        if (buttonLeftRightValue<(byte)0x7E&&buttonLeftRightValue>(byte)0x81&&isAnalogKey)
            System.out.println("Button : " + buttonName + " value L/R: "+String.format(" %x",buttonLeftRightValue));
        if (buttonUpDownValue<(byte)0x7E&&buttonUpDownValue>(byte)0x81&&isAnalogKey)
            System.out.println("Button : " + buttonName + " value : U/D"+String.format(" %x",buttonUpDownValue));
    }

    }



