import java.io.*;
import java.net.*;

public class client{
public static void main (String args[]) throws Exception{

byte []b=new byte[20000];
Socket sr= new Socket("localhost",4000);
InputStream is=sr.getInputStream();
FileOutputStream fr=new FileOutputStream("abc_sent.txt");
is.read(b,0,b.length);
fr.write(b,0,b.length);



}
}
