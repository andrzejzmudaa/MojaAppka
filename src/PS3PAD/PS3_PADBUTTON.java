package PS3PAD;

import java.util.HashMap;
import java.util.Map;

public class PS3_PADBUTTON {

    public String buttonName;
    public boolean isButtonPressedStatus;
    public byte buttonPressedValue;
    public int byteToDecodeButtonAnalogStatus;   // byte position in status frame to decode value for isButtonPressedStatus
    public int byteToDecodeSecondButtonAnalogStatus;   // byte position in status frame to decode value for isButtonPressedStatus
    public int byteToDecodeButtonBooleanStatus;
    public byte decodingValueForBooleanStatus;

    boolean analogKey=false;


    PS3_PADBUTTON(String buttonName,int bytetoDecodeButtonBooleanStatus,int byteToDecodeButtonAnalogStatus,byte decodingValueForBooleanStatus ){
        this.buttonName=buttonName;
        this.byteToDecodeButtonBooleanStatus=bytetoDecodeButtonBooleanStatus;
        this.byteToDecodeButtonAnalogStatus=byteToDecodeButtonAnalogStatus;
        this.decodingValueForBooleanStatus=decodingValueForBooleanStatus;
    }

    PS3_PADBUTTON(String buttonName,int bytetoDecodeButtonBooleanStatus,int byteToDecodeButtonAnalogStatus,int byteToDecodeSecondButtonAnalogStatus,byte decodingValueForBooleanStatus){
        this.buttonName=buttonName;
        this.byteToDecodeButtonBooleanStatus=bytetoDecodeButtonBooleanStatus;
        this.byteToDecodeButtonAnalogStatus=byteToDecodeButtonAnalogStatus;
        this.byteToDecodeSecondButtonAnalogStatus=byteToDecodeSecondButtonAnalogStatus;
        this.decodingValueForBooleanStatus=decodingValueForBooleanStatus;
        analogKey=true;
    }


    public void updateButtonValue(byte[] incommingByteArray){
        if ((incommingByteArray[this.byteToDecodeButtonBooleanStatus] & decodingValueForBooleanStatus) == decodingValueForBooleanStatus) {
            isButtonPressedStatus = true;
            System.out.println("Button : " + buttonName + " pressed");
        } else
            isButtonPressedStatus = false;

    }


    }


