import java.io.*;
import java.net.*;
public class FileDownload extends Thread {
	
	int peerServerPort;
	String directorypath=null;
	ServerSocket downloadServerSocket;
	Socket downloadSocket= null;
	
	
	public FileDownload(int peerServerPort, String directorypath) {
		this.peerServerPort = peerServerPort;
		this.directorypath = directorypath;
	}
	
	public void run() {
		try {
			
			downloadServerSocket = new ServerSocket(peerServerPort);
			downloadSocket = downloadServerSocket.accept();
			new FileDownloadThread(downloadSocket, directorypath).start();
			
			
		}
		catch(Exception e) {
			
			
		}
	}	
}
class FileDownloadThread extends Thread{
	
	Socket downloadsocket;
	String directorypath;
	
	public FileDownloadThread(Socket downloadsocket, String directorypath) {
		this.downloadsocket = downloadsocket;
		this.directorypath = directorypath;
	}
	
	public void run() {
		
	try {
		ObjectOutputStream oos = new ObjectOutputStream(downloadsocket.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(downloadsocket.getInputStream());
		
		String filename = (String)ois.readObject();
		String filelocation;
		
		while(true)
		{
			File myfile = new File(directorypath+"//"+filename);
			long length = myfile.length();
			byte [] byte_arr = new byte[(int)length];
			
			oos.writeObject((int)myfile.length());
			oos.flush();
			
			FileInputStream fis = new FileInputStream(myfile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			bis.read(byte_arr,0,byte_arr.length);
			
			oos.flush();
			
		}
		
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	
	}	
}