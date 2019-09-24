import java.io.*; 
import java.text.*; 
import java.util.*;
import java.net.*;


public class get 
{ 
    public static void main(String[] args) 
    { 

	try
        {
	    
            ServerSocket myServerSocket = new ServerSocket(9999);
            Socket skt = myServerSocket.accept();   
	     ArrayList<String> titleList = new ArrayList<String>();
 		ObjectInputStream objectInput = new ObjectInputStream(skt.getInputStream()); 
          
                
		Object object = objectInput.readObject();
                    titleList =  (ArrayList<String>) object;
		    HashMap<String, String> map = new HashMap<>();
for(int i=0;i<titleList.size();i++)
{   
		map.put(titleList.get(i),skt.getInetAddress().toString().substring(1));
		
}
		System.out.print(map);                    
		//System.out.println(titleList.get(1));               
             
	}
            catch (Exception e) 
            {
                e.printStackTrace();
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
