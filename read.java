
import java.io.*; 
import java.net.*;
import java.util.*;
import java.text.*; 

public class read
{
		
public static void main(String[] args) throws IOException,ClassNotFoundException  
{  
       
	try {       
		
            
	 
	    File f = new File("/home/harsh/Desktop/10kbfiles/");
            Socket socket = new Socket("127.0.0.1",9999);
	   
	    ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
	    ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream()); 
	    ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
	    System.out.println(names.toString());
	   
            objectOutput.writeObject(names);
	    socket.close();
	     
	    Thread t = new ClientHandler();
	    t.start();
	}
	catch(Exception e)
		{	
	   		e.printStackTrace();
		}
}
}


class ClientHandler extends Thread
{

     
    
   
		 		
	

	public void run()  
    { 
        //String received; 
        String toreturn; 
	String filename;
	
	
            try { 
  		
		
                 
               System.out.println("Press 1 or 2 to Select \n"+
			    "1. Search a File\n"+ 
                            "2. Exit"); 

                  
                // receive the answer from client 
               int received = new Scanner(System.in).nextInt(); 
		
                  
                if(received==2) 
                {  
                   //System.out.println("Client " + this.s + " sends exit..."); 
                    System.out.println("Closing this connection."); 
                   // socket.close(); 
                    System.out.println("Connection closed"); 
                   
                } 
		else if(received==1)
		{
				 Socket socket = new Socket("127.0.0.1",9999);
				DataInputStream dis = new DataInputStream(socket.getInputStream());
    				DataOutputStream dos = new DataOutputStream(socket.getOutputStream()); 
 				String abc="hii";
				dos.writeUTF(abc);
				dos.flush();
				socket.close();


		}
		else
		{
			System.out.println("Invalid Input");
		}


		}
	  
	catch(Exception e)
	{	
	   e.printStackTrace();
	}
     
	}
                      
        
 
}


