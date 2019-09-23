
// Java implementation of Server side 
// It contains two classes : Server and ClientHandler 
// Save file as Server.java 

import java.io.*; 
import java.text.*; 
import java.util.*;
import java.net.*; 

// Server class 
public class Server 
{ 
	
	public static ArrayList<FileInfo> fileArray = new ArrayList<FileInfo>();
	
	public static void main(String[] args) throws IOException 
	{ 
		// server is listening on port 5056 
		ServerSocket ss = new ServerSocket(9056); 

		
		// running infinite loop for getting 
		// client request 
		while (true) 
		{ 
			Socket s = null; 
			
			try
			{ 
				// socket object to receive incoming client requests 
				s = ss.accept(); 
				
				System.out.println("A new client is connected : " + s); 
				
				// obtaining input and out streams 
				DataInputStream dis = new DataInputStream(s.getInputStream()); 
				DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
				
				System.out.println("Assigning new thread for this client"); 

				// create a new thread object 
				Thread t = new ClientHandler(s, fileArray); 

				// Invoking the start() method 
				t.start(); 
				
			} 
			catch (Exception e){ 
				s.close(); 
				e.printStackTrace(); 
			} 
			
		} 
	} 
} 

// ClientHandler class 
class ClientHandler extends Thread 
{ 
	ObjectInputStream ois; 
	ObjectOutputStream oos; 
	protected Socket s; 
	ArrayList<FileInfo> globalArray;
	ArrayList<FileInfo> files = new ArrayList<FileInfo>();
	String str;
	int index;
	

	// Constructor 
	public ClientHandler(Socket s, ArrayList<FileInfo> globalArray) 
	{ 
		this.s = s; 
		this.globalArray=globalArray;
		
	} 

	@Override
	public void run() 
	{ 
	 
			try { 
				
				InputStream is= s.getInputStream();
				oos = new ObjectOutputStream(s.getOutputStream());
				ois = new ObjectInputStream(is);
				files = (ArrayList<FileInfo>)ois.readObject();
				
				System.out.println();

				for (int i=0;i<files.size();i++)
				{
					globalArray.add(files.get(i));
					
				}
	
				
			} catch (Exception e) { 
				e.printStackTrace(); 
		} 
		
		ArrayList<FileInfo> sendingfile = new ArrayList<FileInfo>();
		System.out.println("Searching for the file name");
		
		for (int j=0;j<globalArray.size();j++) {
			
			FileInfo fileinfo = globalArray.get(j);
			Boolean tf = fileinfo.filename.equals(str);
			
			if (tf)
			{
				index = j;
				sendingfile.add(fileinfo);
				
			}
		}
		
		
		try
		{ 
			oos.writeObject(sendingfile);
			
		}catch(IOException e){ 
			e.printStackTrace(); 
		} 
	} 
} 
