/*import java.io.*;
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
*/

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

class Client{  
public static void main(String args[])throws Exception{  
String address = "";
Scanner sc=new Scanner(System.in);
System.out.println("Enter Server Address: ");
address=sc.nextLine();
//create the socket on port 6124
Socket s=new Socket(address,6124);  
DataInputStream din=new DataInputStream(s.getInputStream());  
DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  

System.out.println("Send Get to start...");
String str="",filename="";  
try{
while(!str.equals("start"))
	str=br.readLine(); 
 
	dout.writeUTF(str); 
	dout.flush();  
	
	filename=din.readUTF(); 
	System.out.println("Receving file: "+filename);
	filename="client"+filename;
	System.out.println("Saving as file: "+filename);
//
long sz=Long.parseLong(din.readUTF());
System.out.println ("File Size: "+(sz/(1024*1024))+" MB");

byte b[]=new byte [1024];
System.out.println("Receving file..");
FileOutputStream fos=new FileOutputStream(new File(filename),true);
long bytesRead = 0;
do
{
bytesRead = din.read(b, 0, b.length);
fos.write(b,0,b.length);
}while(!(bytesRead<1024));
System.out.println("Comleted");
fos.close(); 
dout.close();  	
s.close();  
}
catch(EOFException e)
{
	//do nothing
}
}
}  
//this is a typical client program 
