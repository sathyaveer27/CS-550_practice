import java.io.*; 
import java.text.*; 
import java.util.*;
import java.net.*;


public class get 
{ static TreeMap<String,String> sortedKey = new TreeMap<>();
    public static void main(String[] args) throws IOException
    { ServerSocket myServerSocket = new ServerSocket(9999);
	while(true)
	{
		Socket skt =null;
		try
		{
		    
		    
		    skt = myServerSocket.accept();   
		    System.out.println("A new client is connected : " + skt);
		    ArrayList<String> titleList = new ArrayList<String>();
	 	    ObjectInputStream objectInput = new ObjectInputStream(skt.getInputStream()); 
		    ObjectOutputStream objectOutput = new ObjectOutputStream(skt.getOutputStream());
	       
		    Object object = objectInput.readObject();
		    titleList =  (ArrayList<String>) object;
		    HashMap<String, String> map = new HashMap<>();
			for(int i=0;i<titleList.size();i++)
			{   
				map.put(titleList.get(i),skt.getInetAddress().toString().substring(1));
				
			}
		    //sortedKey.putAll(map);
		    //System.out.println(map.size());
		    System.out.print(map);                    
		   //System.out.println(titleList.get(1));               
		    System.out.println("\nAssigning new thread for this client"); 
		    
		    Thread t = new ClientHandler(skt, objectInput, objectOutput);
		    t.start(); 




		}
		    catch (Exception e) 
		    {
		        e.printStackTrace();
		    } 
        }
    }
}

class ClientHandler extends Thread
{
    final ObjectInputStream ois; 
    //Hashmap<String, String> totalfiles = new Hashmap<String, String>();
    
    final ObjectOutputStream oos; 
    final Socket s; 

 public ClientHandler(Socket s, ObjectInputStream ois, ObjectOutputStream oos)  
    { 
        this.s = s; 
        this.ois = ois; 
        this.oos = oos; 
	//totalfiles.putAll(map);
    } 
	

	public void run()  
    { 
        String received; 
        String toreturn; 
	String filename;
	
        while (true)  
        { 
            try { 
  
                 
                oos.writeUTF("Press 1 or 2 to Select \n"+
			    "1. Search a File\n"+ 
                            "2. Exit"); 
                  
                // receive the answer from client 
                received = ois.readUTF(); 
                  
                if(received.equals("2")) 
                {  
                    System.out.println("Client " + this.s + " sends exit..."); 
                    System.out.println("Closing this connection."); 
                    this.s.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 
		/*else
		{
		    System.out.println("Enter the File name that you want to Download \n");
		    filename = ois.readUTF();
			
			 if(filename.equals(totalfiles.key())) 
                	{  
		            System.out.println("Client " + this.s + " sends exit..."); 
		            System.out.println("Closing this connection."); 
		            this.s.close(); 
		            System.out.println("Connection closed"); 
		            break; 
                	} 

 






		}*/
	     }
	catch(Exception e)
	{	
	   e.printStackTrace();
	}
     }
  }
}



/*	HashMap<String, Stringr> map = new HashMap<>();   
        print(map); 
        map.put("vishal", 10); 
        map.put("sachin", 30); 
        map.put("vaibhav", 20); 
  
        System.out.println("Size of map is:- "
                           + map.size()); 
  
        print(map); 
        if (map.containsKey("vishal")) { 
            Integer a = map.get("vishal"); 
            System.out.println("value for key"
                               + " \"vishal\" is:- "
                               + a); 
        } 
  
        map.clear(); 
        print(map); 
    } 
  
    public static void print(Map<String, String> map) 
    { 
        if (map.isEmpty()) { 
            System.out.println("map is empty"); 
        } 
  
        else { 
            System.out.println(map); 
        } 
    } 
} 
*/
