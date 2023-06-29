package net.javatutorial.tutorials;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class SchedulerMain {

    
    public static void main(String[] args) throws Exception {
//    	String message = VMSArchiveManagerDAO.moveVisitor();
//    	System.out.println("Visitor Records Moved: " + message);
    	String line;
		try 
		{ 
			URL url = new URL( "https://k11-vms-ssw.herokuapp.com/archiveRecords" ); 
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream())); 
			line = in.readLine(); 

			System.out.println( "Records Moved: " + line );	

			in.close(); 
		}
		catch (Exception e)
		{ 
			e.printStackTrace(); 
		} 
    }

}
