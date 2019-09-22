import java.io.*;
import java.net.*;

public class server{
public static void main (String args[]) throws Exception{
ServerSocket s= new ServerSocket(4000);
Socket sr=s.accept();
FileInputStream fr= new FileInputStream("abc.txt");
byte b[] = new byte[20000];
fr.read(b,0,b.length);
OutputStream os= sr.getOutputStream();


}
}
