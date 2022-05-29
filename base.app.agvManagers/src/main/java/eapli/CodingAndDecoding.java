package eapli;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CodingAndDecoding {


    public MessageDto Decoding( DataInputStream r){

        try {
            int version =r.read();
            int code =r.read();
            int size1= r.read();
            int size2= r.read();
            int size=size1+256*size2;

            byte[] data_message=new byte[size/256];
            r.read(data_message,0,size/256);

            MessageDto message= new MessageDto(version,code,size,data_message);
            return message;
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        return null;
    }


    public byte[] Coding_String(int version,int code,String message){
        int data_byte_lenght = message.getBytes(StandardCharsets.UTF_8).length;;
        int lenght= 4+  data_byte_lenght;
        byte[] coded_message= new byte[lenght];
        coded_message[0] = (byte) version;
        coded_message[1] = (byte) code;
        coded_message[2] = (byte) (data_byte_lenght%256);
        coded_message[3] = (byte) (data_byte_lenght/256);
        for (int i = 0 ; i< coded_message.length; i++){
            coded_message[4+i] = message.getBytes(StandardCharsets.UTF_8)[i];
        }
        return coded_message;
    }

    public byte[] Coding_int(int version,int code, int message){
        int data_byte_lenght = 4;
        int lenght= 4+  data_byte_lenght;
        byte[] coded_message= new byte[lenght];
        coded_message[0] = (byte) version;
        coded_message[1] = (byte) code;
        coded_message[2] = (byte) (data_byte_lenght%256);
        coded_message[3] = (byte) (data_byte_lenght/256);
        coded_message[4]= (byte) message;
        return coded_message;
    }
}
