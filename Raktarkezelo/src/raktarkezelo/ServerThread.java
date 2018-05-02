/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raktarkezelo;

/**
 *
 * @author hegyi
 */
import java.net.*;
import java.io.*;


public class ServerThread extends Thread{
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;

	public ServerThread(Socket client){
		this.client=client;
		try{
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void run(){
		try{
			while(true)
			{
				if(false)
				{
					Server.remove(this);
					client.close();
					break;
				}
				BufferedReader input =new BufferedReader(new InputStreamReader(client.getInputStream()));
				String cmd = input.readLine();
				System.out.println("command: " + cmd);
				// send back to client
				PrintWriter out =new PrintWriter(client.getOutputStream(), true);

			}
		}catch(Exception e){
			System.out.println("One of the clients disconnected");
		}
	}
}

