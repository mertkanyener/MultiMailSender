import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Label;

public class MailGui extends JFrame implements ActionListener {
	private JButton btnSend, btnOpenEx, btnOpenTxt, btnOpenAtt;  
	private JFileChooser chsExcel, chsTxt, chsAtt; 
	private JLabel lblHello,lblEx, lblTxt, lblAtt;
	private File fileEx, fileTxt;
	private File[] filesAtt;
	private JTextField textMail;
	private JPasswordField passwordField;
	private String username, pass, mailText, sheetName;
	private String[] to;
	private Read r = new Read();
	private SendMail snd = new SendMail();
	private ImageIcon accept = new ImageIcon("accept.png");
	private int colNum;
	private JTextField textCol;
	private JMenu mnYardm;
	private JTextField textField;
	
	
	
	public MailGui(){
		setResizable(false);
		getContentPane().setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		getContentPane().setLayout(null);
		lblHello = new JLabel("Multi Mail Sender");
		lblHello.setHorizontalAlignment(SwingConstants.CENTER);
		lblHello.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblHello.setSize(450, 20);
		lblHello.setLocation(10, 10);
		getContentPane().add(lblHello);
		
		
		lblEx = new JLabel("Excel dosyası seç:");
		lblEx.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblEx.setSize(100, 20);
		lblEx.setLocation(10, 130);
		getContentPane().add(lblEx);
		
		btnOpenEx = new JButton("Seç");
		btnOpenEx.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		btnOpenEx.setSize(60, 30);
		btnOpenEx.setLocation(120, 125);
		btnOpenEx.addActionListener(this);
		getContentPane().add(btnOpenEx);
		
		lblTxt = new JLabel("Mail yazısı seç:");
		lblTxt.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblTxt.setSize(88, 20);
		lblTxt.setLocation(10, 171);
		getContentPane().add(lblTxt);
		
		btnOpenTxt = new JButton("Seç");
		btnOpenTxt.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		btnOpenTxt.setSize(60, 30);
		btnOpenTxt.setLocation(120, 166);
		btnOpenTxt.addActionListener(this);
		getContentPane().add(btnOpenTxt);
		
		lblAtt = new JLabel("Mail ekleri seç:");
		lblAtt.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblAtt.setSize(100, 20);
		lblAtt.setLocation(10, 212);
		getContentPane().add(lblAtt);
		
		btnOpenAtt = new JButton("Seç");
		btnOpenAtt.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		btnOpenAtt.setSize(60, 30);
		btnOpenAtt.setLocation(120,207);
		btnOpenAtt.addActionListener(this);
		getContentPane().add(btnOpenAtt);
		
		
		
		btnSend = new JButton("Çoklu mail gönder");
		btnSend.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		btnSend.setSize(150, 49);
		btnSend.setLocation(180, 286);
		btnSend.addActionListener(this);
		getContentPane().add(btnSend);
		
		chsExcel = new JFileChooser();
		getContentPane().add(chsExcel);
		chsTxt = new JFileChooser();
		getContentPane().add(chsTxt);
		chsAtt = new JFileChooser();
		getContentPane().add(chsAtt);
		
		JLabel label = new JLabel("");
		label.setBounds(284, 63, 46, 14);
		getContentPane().add(label);
		
		JLabel lblMailAdresiniz = new JLabel("Mail adresi:");
		lblMailAdresiniz.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblMailAdresiniz.setBounds(10, 41, 71, 14);
		getContentPane().add(lblMailAdresiniz);
		
		JLabel lblParola = new JLabel("Parola:");
		lblParola.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblParola.setBounds(10, 66, 46, 14);
		getContentPane().add(lblParola);
		
		textMail = new JTextField();
		textMail.setBackground(new Color(255, 255, 255));
		textMail.setBounds(91, 39, 139, 20);
		getContentPane().add(textMail);
		textMail.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(91, 64, 139, 20);
		getContentPane().add(passwordField);
		
		JLabel lblKolonNo = new JLabel("Kolon No:");
		lblKolonNo.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblKolonNo.setBounds(211, 134, 60, 14);
		getContentPane().add(lblKolonNo);
		
		textCol = new JTextField();
		textCol.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		textCol.setBounds(281, 131, 29, 20);
		textCol.setEnabled(false);
		getContentPane().add(textCol);
		textCol.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Sayfa Adı:");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(211, 175, 60, 14);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(281, 172, 86, 20);
		textField.setEnabled(false);
		getContentPane().add(textField);
		textField.setColumns(10);
		
	
		
		setSize(500, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Multi Mail Sender");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnYardm = new JMenu("Yardım");
		menuBar.add(mnYardm);
		
		JMenuItem mntmKullanmKlavuzu = new JMenuItem("Kullanım Kılavuzu");
		mnYardm.add(mntmKullanmKlavuzu);
		
		
	}
	
	
	
	public static void main(String[] args){
		new MailGui();
		
	}
	
	public String passStr(char[] pass){
		String result = "";
		for(int i = 0; i < pass.length; i++){
			result = result + pass[i];
		}
		return result;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnOpenEx)){
			FileFilter filterEx = new FileNameExtensionFilter("XLSX file", "xlsx");
			chsExcel.addChoosableFileFilter(filterEx);
			chsExcel.setAcceptAllFileFilterUsed(false);
			int retVal = chsExcel.showOpenDialog(MailGui.this);
			if(retVal == JFileChooser.APPROVE_OPTION){
				fileEx = chsExcel.getSelectedFile();
				textCol.setEnabled(true); 
				textField.setEnabled(true);
				}
		}
		else if(e.getSource().equals(btnOpenTxt)){
			FileFilter filterTxt = new FileNameExtensionFilter("TXT file", "txt");
			
			chsTxt.addChoosableFileFilter(filterTxt);
			chsTxt.setAcceptAllFileFilterUsed(false);
			int retVal = chsTxt.showOpenDialog(MailGui.this);
			if(retVal == JFileChooser.APPROVE_OPTION){
				fileTxt = chsTxt.getSelectedFile();
				mailText = r.readText(fileTxt.getPath()); // I may add a property that readText would convert it to html form
				
			}
		}
		else if(e.getSource().equals(btnOpenAtt)){ // Adds attachments to mail 
			chsAtt.setMultiSelectionEnabled(true);
			int retVal = chsAtt.showOpenDialog(MailGui.this);
			if(retVal == JFileChooser.APPROVE_OPTION){
				filesAtt = chsAtt.getSelectedFiles(); 
			
				
			}
		}
		// got the username and pass here but gotta show an error dialog if one or both of them are wrong
		// I need to get the exceptions and create dialogs to show error messages 
		else if(e.getSource().equals(btnSend)){
			username = textMail.getText(); 
			char[] password = passwordField.getPassword(); 
			pass = passStr(password);
			
			try {
				// gets the column number user entered. - 1 is to match the index
				colNum = Integer.parseInt(textCol.getText()) - 1; // what if we got some letters instead of digits??
				sheetName = textField.getText(); 
				to = r.readExcel(fileEx.getPath(), colNum, sheetName); // can't know if this line works correctly yet 
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			// Let's check if any of the textboxes are empty
			if(username.equals(null)){
				JOptionPane.showMessageDialog(this,
					    "Lütfen mail adresinizi girin.",
					    "Oops!",
					    JOptionPane.ERROR_MESSAGE);
			}
			else if(pass.equals(null)){
				JOptionPane.showMessageDialog(this,
					    "Lütfen parolanızı girin.",
					    "Oops!",
					    JOptionPane.ERROR_MESSAGE);
				
			}
			else if(fileEx.equals(null)){
				JOptionPane.showMessageDialog(this,
					    "Lütfen bir excel dosyası seçin.",
					    "Oops!",
					    JOptionPane.ERROR_MESSAGE);
			}
			else if(sheetName.equals(null)){
				JOptionPane.showMessageDialog(this, 
						"Lütfen sayfa adı yazın!.",
						"Oops!",
						JOptionPane.ERROR_MESSAGE);
			}			
			else{
				// Gotta update this part
				// Gotta try every error, get the exception names and create if statements to show error dialogs according to exception name
				try{
				snd.sendMail(username, pass, to, mailText, "test", filesAtt);
				JOptionPane.showMessageDialog(this,
					    "Mailler başarıyla gönderildi!",
					    "İşlem başarılı!",
					    JOptionPane.INFORMATION_MESSAGE, accept);
				}catch(Exception e2){
					e2.printStackTrace();
				}//test
			}
		}
			
	}
}
