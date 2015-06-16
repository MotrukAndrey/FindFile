package client;

import client.controller.ClientController;
import client.model.Searcher;
import client.view.ClientView;

public class MVCClient {

	public static void main(String[] args) {
		ClientView view = new ClientView();
		Searcher search = new Searcher();
		ClientController controller = new ClientController(view, search);

	}

}
