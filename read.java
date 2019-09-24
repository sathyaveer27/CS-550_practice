
import java.io.*; 
import java.net.*;
import java.util.*;

public class read
{
		
public static void main(String[] args) throws IOException,ClassNotFoundException  
{ 

        
       
	try {       
	    File f = new File("/home/sathyaveer27/Desktop/ABC");
            Socket socket = new Socket("127.0.0.1",9999);
           
	    ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
	    System.out.println(names.toString());
    
       
          
                    ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
                    objectOutput.writeObject(names);
               }
		catch (Exception e) {
                    System.out.println("The title list has not come from the server");
                    e.printStackTrace();
                }
                      
        
} 

}



