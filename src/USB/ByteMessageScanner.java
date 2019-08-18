package USB;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;


public class ByteMessageScanner {

    public static byte[] giveHexMessage(String fileLocation) throws IOException {

        File messageFile = new File(fileLocation);
        byte[] internalDataArray;
        if(!messageFile.exists()){
            System.out.println("Hex file dont exist");
            return null;}

        BufferedReader fileScanner = new BufferedReader(new FileReader(messageFile));
        ArrayList<Byte> bytesReaded = new ArrayList<Byte>();
        while(fileScanner.read()!=-1) {
            String tempString="";
            byte byte1=(byte)Character.getNumericValue((char)fileScanner.read());
            byte byte2=(byte)Character.getNumericValue((char)fileScanner.read());
            int temp= (byte1<<4)+byte2;
            byte finalbyte=(byte)temp;
            //System.out.println(String.format("%X", finalbyte));
            bytesReaded.add(finalbyte);


        }
        System.out.println(bytesReaded);
        internalDataArray = new byte[bytesReaded.size()];
        for(int i=0;i<bytesReaded.size();i++)
            internalDataArray[i]=bytesReaded.get(i);



        try {
            fileScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" ");
        System.out.print("{");
        for (byte b : internalDataArray) {
            System.out.print("(byte)0x");
            System.out.print(String.format("%X", b));
            System.out.print(",");
        }
        System.out.print("}");
        System.out.println();


        return internalDataArray;
    }


    }













