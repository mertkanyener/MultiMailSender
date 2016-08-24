import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;




public class SendMail {
	
	public void sendMail(String username, String pass, String[]to, String mailText, String mailSubj, File[] att){
		
		
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username, pass);
			}
		});
		
		try{
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));	
			message.setSubject(mailSubj);
			MimeMultipart multipart = new MimeMultipart("related");
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			//String htmlText = mailText + "<img src=\"cid:image\">";
			//messageBodyPart.setContent(htmlText, "text/html");
			messageBodyPart.setText(mailText);
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
	        DataSource fds = new FileDataSource("mailImg.png");
	        messageBodyPart.setDataHandler(new DataHandler(fds));
	        messageBodyPart.setHeader("Content-ID", "<image>");
	        multipart.addBodyPart(messageBodyPart);
			for(int i = 0; i < att.length; i++){
				attachFile(att[i], multipart, messageBodyPart);
			}
			message.setContent(multipart);
			for(int i = 0; i < to.length; i++){
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to[i]));
				Transport.send(message);
				System.out.println("Mail has been sent successfully!");
			}	
		}catch(MessagingException e){
			throw new RuntimeException(e);
		}
	}
	public void attachFile(File file, Multipart multipart, MimeBodyPart messageBodyPart) throws MessagingException {
		messageBodyPart = new MimeBodyPart();
	    DataSource source = new FileDataSource(file);
	    messageBodyPart.setDataHandler(new DataHandler(source));
	    messageBodyPart.setFileName(file.getName());
	    multipart.addBodyPart(messageBodyPart);    
	}
	

}
