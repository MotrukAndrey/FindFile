package server.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import server.view.ServerView;


public class ServerController {
	ServerView serverView;
	int port;
	
	 private DataInputStream in ;
	 private DataOutputStream out ;
	 private Socket socket ;
	 private ServerSocket serverSocket;
	 
	public ServerController(ServerView view, int port){
		serverView = view;
		this.port = port;
		
		this.serverView.addConnectListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				connect();
				
			}});
		
		
		this.serverView.addDisconnectListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
					if(!serverSocket.isClosed()){
						try {
							serverSocket.close();
						} catch (IOException e) {
							serverView.displayErrorMesege("НЕ может отключить", e.getMessage());
							serverView.setFinishLabel("НЕ может отключить");
						}
					}
					
				}
			});
		this.serverView.addbtnStringListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String str ="name " + serverView.getTextFind();
				try {
					out.writeUTF(str);
					out.flush();
				} catch (IOException e) {
					serverView.displayErrorMesege("Error!", e.getMessage());
				}
				
			}
		});
		this.serverView.addbtnArrayListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String str ="ext " + serverView.getTextFind();
				try {
					out.writeUTF(str);
					out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void connect(){
		Thread con = new Thread(new Runnable(){

			public void run() {
				try {	
					serverSocket = new ServerSocket(port);
				    socket = serverSocket.accept();
					InputStream istr = socket.getInputStream();
					OutputStream ostr = socket.getOutputStream();
					in = new DataInputStream(istr);
					out = new DataOutputStream(ostr);
				} catch (IOException e) {
					serverView.displayErrorMesege("ERROR!", e.getMessage());
					}	
				
			}
			
		});
		con.start();
	}
	
}
