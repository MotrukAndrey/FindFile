package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import client.model.Searcher;
import client.view.ClientView;

public class ClientController {
	ClientView view;
	Searcher search;
	
	private final int port = 5001;
	private  DataOutputStream out = null;
	private  DataInputStream in = null;
	private String[] fileNameArray;
	
	public ClientController(ClientView clientView, Searcher searcher){
		view = clientView;
		search = searcher;
		
		this.view.addbtnLink(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				connect();
				
			}
			
		});
	}
	
	private void connect() {
		Thread con = new Thread(new Runnable(){

			public void run() {
				Socket socket = null;
				try{
				InetAddress ipAddress = InetAddress.getByName(view.getLinkAddress());
				socket = new Socket(ipAddress, port);
				OutputStream sout = socket.getOutputStream();
				InputStream sin = socket.getInputStream();
				in = new DataInputStream(sin);
				out = new DataOutputStream(sout);
				
				String fileName = in.readUTF();	
				fileNameArray = fileName.split(" ");
				Searcher s = new Searcher();
				if(fileNameArray[0].equals("name")){
				s.setOutputFile("D:\\local.txt");
				s.setFileName(fileNameArray);
				s.find();
				}else if(fileNameArray[0].equals("ext")){
					s.setOutputFile("D:\\local.txt");
					s.setFileExts(fileNameArray);
					s.find();
				}
			} catch (UnknownHostException e) {
				view.displayErrorMesege("yнеизвесный ip адрес", e.getMessage());
				view.setLink("yнеизвесный ип адрес");
			} catch (IOException e) {
				view.setLink("нету конекта с сокетом, сокет занят");
				e.printStackTrace();
			}
		}
			
		});
		con.start();
	}
}

