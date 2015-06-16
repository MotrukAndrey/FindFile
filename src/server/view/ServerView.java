package server.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ServerView extends JFrame {
	private JTextField textFind = new JTextField("Имя файла");
    private JButton btnString = new JButton("поиск по назв");
    private JButton btnArray = new JButton("поиск по розширению");
    private JButton connect =  new JButton("Connect");
    private JButton disconnect = new JButton("Disconnect");
    private JTextArea textInfo = new JTextArea();
    private JLabel finish = new JLabel("Тут будет результат подключения");
   
    
    public ServerView(){
    	JPanel panel = new JPanel();
    	JPanel panelBtn = new JPanel();
    	
    	setButtonInPanel(panelBtn);
    	
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setTitle("Поиск файла на удаленке");
    	this.setSize(300, 400);
    	this.setVisible(true);
    	
    	panel.setLayout(new BorderLayout());
        panel.add(finish, BorderLayout.SOUTH);
        panel.add(textInfo, BorderLayout.CENTER);
        panel.add(panelBtn, BorderLayout.WEST);
        panel.add(textFind, BorderLayout.NORTH);
        this.add(panel);
    }
  	
    private void setButtonInPanel(JPanel panelBtn){
    	panelBtn.setLayout(new GridLayout(10, 0 ,10, 10));
    	panelBtn.add(btnString);
    	panelBtn.add(btnArray);
    	panelBtn.add(connect);
    	panelBtn.add(disconnect);
    }

    public String getTextFind(){
    	return textFind.getText();
    }
    
    public void setTextInfo(String text){
    	textInfo.setText(text);
    }
    
    public void setFinishLabel(String text){
    	finish.setText(text);
    }
    
    public void addbtnStringListener(ActionListener listenerForBtnString){
    	btnString.addActionListener(listenerForBtnString);
    }
    
    public void addbtnArrayListener(ActionListener listenerForBtnArray){
    	btnArray.addActionListener(listenerForBtnArray);
    }
    
    public void addConnectListener(ActionListener listenerForConect){
    	connect.addActionListener(listenerForConect);
    }
    
    public void addDisconnectListener(ActionListener listenerForDisconnect){
    	disconnect.addActionListener(listenerForDisconnect);
    }
    public void displayErrorMesege(String errorTitle, String erorMessage){
    	JOptionPane.showMessageDialog(this, erorMessage, errorTitle, JOptionPane.ERROR);
    }
    
}
