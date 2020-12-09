import java.net.ServerSocket;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class Server {
    static int PortNo;
	//public ServerSocket datasoc;
	 public static void main(String args[]) throws Exception
	 
	    {
		 	
		 	//System.out.println(args[0]);
		 	if (args.length == 1){
		 		//System.out.println(args[0]);
		 		PortNo=Integer.parseInt(args[0]);
		 		
		 	}
		 	else {
		 		PortNo=5217;
		 	}
	        ServerSocket soc=new ServerSocket(PortNo);
	        ServerSocket datasoc=new ServerSocket(PortNo-1);
	        System.out.println("FTP Server Started on Port Number "+ PortNo);
	        while(true)
	        {
	            System.out.println("Waiting for Connection ...");
	            //System.out.println(123);
	            transferfile t=new transferfile(soc.accept(),datasoc);
	        }
	    }
}
