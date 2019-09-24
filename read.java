
import java.io.*; 
import java.net.*;
import java.util.*;

public class read
{
		
public static void main(String[] args) throws IOException,ClassNotFoundException  
{  
       
	try {       
		
            Scanner scn = new Scanner(System.in); 
	 
	    File f = new File("/home/harsh/Desktop/10kbfiles/");
            Socket socket = new Socket("127.0.0.1",9999);
	    ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
	    ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
	    System.out.println(names.toString());
	   
                    objectOutput.writeObject(names);
	    ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream()); 
	 while (true)  
            { 
                System.out.println(objectInput.readUTF()); 
                String tosend = scn.nextLine(); 
                objectOutput.writeUTF(tosend); 
                  
                // If client sends exit,close this connection  
                // and then break from the while loop 
                if(tosend.equals("2")) 
                { 
                    System.out.println("Closing this connection : " + socket); 
                    socket.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 
                  
                // printing date or time as requested by client 
                String received = objectInput.readUTF(); 
                System.out.println(received); 
            } 


          }
		catch (Exception e) {
                    System.out.println("The title list has not come from the server");
                    e.printStackTrace();
                }
                      
        
} 

}



