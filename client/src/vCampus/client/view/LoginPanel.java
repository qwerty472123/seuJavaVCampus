package vCampus.client.view;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginPanel {
	
	private JPanel thisPanel;

    private JButton loginBtn;
    private JButton forgBtn;
    private JButton registerBtn;

    private JTextField tf1;
    private JPasswordField tf2;

    public LoginPanel() {

        thisPanel = new JPanel();

        thisPanel.setBackground(new Color(255, 255, 255, 223));

        //thisPanel.setLayout(new BoxLayout(thisPanel, BoxLayout.Y_AXIS));
        thisPanel.setLayout(null);

        JPanel line1 = newLine(40, 20, 200, 50);
        thisPanel.add(line1);
        JLabel titleUnit = new JLabel("ÕÊºÅµÇÂ¼");
        line1.add(titleUnit);
        line1.setOpaque(false);
        //line1.setBorder(BorderFactory.createLineBorder(Color.red));

        JPanel line2 = newLine(40, 80, 200, 30);
        thisPanel.add(line2);
        JLabel tf1tip = new JLabel("ÓÃ»§Ãû");
        line2.add(tf1tip);
        tf1 = new JTextField("", 12);
        line2.add(tf1);
        line2.setOpaque(false);

        JPanel line3 = newLine(40, 120, 200, 30);
        thisPanel.add(line3);
        JLabel tf2tip = new JLabel("ÃÜ    Âë");
        line3.add(tf2tip);
        tf2 = new JPasswordField("", 12);
        line3.add(tf2);
        line3.setOpaque(false);

        JPanel line4 = newLine(40, 160, 200, 35);
        thisPanel.add(line4);
        loginBtn = new JButton("µÇ        Â¼");
        loginBtn.setForeground(Color.white);
        loginBtn.setBackground(new Color(0, 127, 63));
        loginBtn.setPreferredSize(new Dimension(180,25));
        line4.add(loginBtn);
        line4.setOpaque(false);

        JPanel line5 = newLine(40, 200, 200, 30);
        thisPanel.add(line5);
        forgBtn = new JButton("Íü¼ÇÃÜÂë£¿");
        forgBtn.setPreferredSize(new Dimension(90,25));
        forgBtn.setBackground(null);
        forgBtn.setBorder(null);
        forgBtn.setForeground(new Color(0, 127, 63));
        forgBtn.setOpaque(false);
        line5.add(forgBtn);
        registerBtn = new JButton("×¢²á");
        registerBtn.setPreferredSize(new Dimension(90,25));
        registerBtn.setBackground(null);
        registerBtn.setOpaque(false);
        line5.add(registerBtn);
        line5.setOpaque(false);

        thisPanel.setSize(280, 350);
        
        loginBtn.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae){
                //System.out.println("Login Procedure..");
                String sAcc = tf1.getText();
                String sPwd = String.valueOf(tf2.getPassword());
                LoginController.login(sAcc, sPwd);
            }
        });
        

    }

    private JPanel newLine(int x, int y, int width, int height){
         JPanel pLine = new JPanel();
         pLine.setBackground(null);
         pLine.setBounds(x, y, width, height);
         return pLine;
    }

    public JPanel getPanel(){
        return  thisPanel;
    }

    public JButton getLoginBtn(){
        return loginBtn;
    }

    public JButton getForgBtn() {
        return forgBtn;
    }

    public JButton getRegisterBtn(){
        return registerBtn;
    }

    public JTextField getAccField(){ return tf1;}

    public JPasswordField getPwdField() { return tf2;}

}
