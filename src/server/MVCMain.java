package server;
import java.io.IOException;
import java.net.ServerSocket;

import server.controller.ServerController;
import server.view.ServerView;


public class MVCMain {

	public static void main(String[] args) {
		ServerView view = new ServerView();
		int port = 5001;
		ServerController controller = new ServerController(view, port);

	}

}
