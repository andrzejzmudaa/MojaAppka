package PS3PAD;

import java.util.ArrayList;

import static PS3PAD.PS3_PADBUTTON.USB_PS3_Button.*;

public class PS3_PAD {

    //Set of PS3 buttons
    public PS3_PADBUTTON SELECT = new PS3_PADBUTTON(USBBUTTONSELECT);
    public PS3_PADBUTTON LANALOG = new PS3_PADBUTTON(USBBUTTONLANALOG);
    public PS3_PADBUTTON RANALOG = new PS3_PADBUTTON(USBBUTTONRANALOG);
    public PS3_PADBUTTON START = new PS3_PADBUTTON(USBBUTTONSTART);
    public PS3_PADBUTTON UP = new PS3_PADBUTTON(USBBUTTONUP);
    public PS3_PADBUTTON RIGHT = new PS3_PADBUTTON(USBBUTTONRIGHT);
    public PS3_PADBUTTON DOWN = new PS3_PADBUTTON(USBBUTTONDOWN);
    public PS3_PADBUTTON LEFT = new PS3_PADBUTTON(USBBUTTONLEFT);
    public PS3_PADBUTTON L2 = new PS3_PADBUTTON(USBBUTTONL2);
    public PS3_PADBUTTON R2 = new PS3_PADBUTTON(USBBUTTONR2);
    public PS3_PADBUTTON L1 = new PS3_PADBUTTON(USBBUTTONL1);
    public PS3_PADBUTTON R1 = new PS3_PADBUTTON(USBBUTTONR1);
    public PS3_PADBUTTON TRIANGLE = new PS3_PADBUTTON(USBBUTTONTRIANGLE);
    public PS3_PADBUTTON CIRCLE = new PS3_PADBUTTON(USBBUTTONCIRCLE);
    public PS3_PADBUTTON CROSS = new PS3_PADBUTTON(USBBUTTONCROSS);
    public PS3_PADBUTTON SQUARE = new PS3_PADBUTTON(USBBUTTONSQUARE);
    public PS3_PADBUTTON PS = new PS3_PADBUTTON(USBBUTTONPS);

    PS3_PADBUTTON[] arrayOfAllPadButtons = new PS3_PADBUTTON[]{SELECT, LANALOG, RANALOG, START, UP,
            RIGHT, DOWN, LEFT, L2, R2, L1, R1, TRIANGLE, CIRCLE, CROSS,
            SQUARE, PS};

    public void updateButtonsValue(byte[] incommingByteArray) throws Exception {
        if(incommingByteArray.length!=31)
            throw new Exception("Invalid ByteArray delivered to PS3 Controller");
        for (PS3_PADBUTTON Button : arrayOfAllPadButtons) {
            Button.updateButtonValue(incommingByteArray);
        }
    }
}





