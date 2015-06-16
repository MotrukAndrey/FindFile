package client.view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ClientView extends JFrame {
	private JPanel panel =  new JPanel();
	private JButton btnLink = new JButton("���������� �����");
	private JLabel link = new JLabel("����� �� �����������");
	private JTextField linkAddress = new JTextField("127.0.0.1");
	private JLabel label = new JLabel("����� ���������� ��������� ��� ���������� ������");
	
	public ClientView(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setVisible(true);
		panel.setLayout(new FlowLayout());
		panel.add(btnLink);
		panel.add(link);
		panel.add(label);
		panel.add(linkAddress);
		this.add(panel);
	}
	
	public String getLinkAddress(){
		return linkAddress.getText();
	}
	
	public void setLink(String text){
		link.setText(text);
	}
	
	public void addbtnLink(ActionListener listenerForBtnLink){
		btnLink.addActionListener(listenerForBtnLink);
	}
	
	 public void displayErrorMesege(String errorTitle, String erorMessage){
	    	JOptionPane.showMessageDialog(this, erorMessage, errorTitle, JOptionPane.ERROR);
	    }
}
