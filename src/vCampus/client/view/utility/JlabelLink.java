package vCampus.client.view.utility;
import java.awt.Cursor; 
import java.awt.Desktop; 
import java.awt.EventQueue; 
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import java.io.IOException; 
import java.net.URI; 
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level; 
import java.util.logging.Logger; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 


public class JlabelLink extends JLabel { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private JPanel pan; 
	private String Url;
	//private JLabel website; 

	public JlabelLink(String name,String a ) { 
		//this.setTitle("jLabelLinkExample"); 
		super(); 
		this.setSize(300, 100); 
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		//this.setLocationRelativeTo(null); 
        this.setOpaque(false);
		//pan = new JPanel(); 

		//website = new JLabel(); 

         Url = a;
         String demo = null;
		//this.setText("<html> Website : <a href=\"\">http://www.google.com/</a></html>");
        if(Url.length()>= 25)demo = Url.substring(0,24)+"...";
        else demo = Url;
		this.setText("<html> "+ name +" : <a href=\"\">"+demo+"</a></html>");
		this.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
		goWebsite(this); 
	} 
    
	
	

	public String getUrl() {
		return Url;
	}




	public void setUrl(String url) {
		Url = url;
	}




	public static void main(String args[]) { 
		Date date = new Date();
		SimpleDateFormat datetext = new SimpleDateFormat("YYYY-MM-dd");
		datetext.format(date);
		int day = date.getDate();
		System.out.println(day);
		
		EventQueue.invokeLater(new Runnable() { 

			@Override 
			public void run() { 
				new JlabelLink("a","a").setVisible(true); 
			} 
		}); 
	} 

	private void goWebsite(JLabel website) { 
		website.addMouseListener(new MouseAdapter() { 
			@Override 
			public void mouseClicked(MouseEvent e) { 
				try { 
					try { 
						//Desktop.getDesktop().browse(new URI("http://www.google.com/webhp?nomo=1&hl=fr")); 
						Desktop.getDesktop().browse(new URI(Url));
					} catch (IOException ex) { 
						Logger.getLogger(JlabelLink.class.getName()).log(Level.SEVERE, null, ex); 
					} 
				} 
				catch (URISyntaxException ex) { 

				} 
			} 
		}); 
	} 
	
} 
