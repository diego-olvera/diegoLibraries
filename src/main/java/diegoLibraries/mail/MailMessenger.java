package diegoLibraries.mail;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.smtp.SMTPTransport;

import diegoLibraries.file.FileUtil;

public class MailMessenger implements SMTP_Constants{
	
	private ArrayList<MailReceiver> receivers;
	private ArrayList<File> attachments;
	private HashMap<String, MailUser> deliveryUsers;
	private MailBody body;
	private String subject;
		
	private MailUser defaultUser;
	
	public MailMessenger() {
		receivers=new ArrayList<>();
		deliveryUsers=new HashMap<>();
		attachments=new ArrayList<>();
	}
	public MailMessenger(MailMessenger src){
		this();
		setSubject(src.getSubject()); 
		setBody(src.getBody());
		src.receivers.forEach((d)->addReceiver(d));
		for(String name:src.deliveryUsers.keySet()){
			addDeliveryUser(getDeliveryUser(name).clone());
		} 
		src.attachments.forEach((d)->addAttachment(new File(d.getAbsolutePath())));

	}
	public MailMessenger(String subject,MailBody body,MailReceiver receiver){
		this();
		setSubject(subject);
		setBody(body);
		addReceiver(receiver);
	}
	public MailMessenger(String subject,MailBody body,MailReceiver receiver,
			ArrayList<File> attachment){
		this();
		setSubject(subject);
		setBody(body);
		addReceiver(receiver);
		attachment.forEach((d)->addAttachment(d));	
	}
	public MailMessenger(String subject,MailBody body,ArrayList<MailReceiver> receivers){
		this();
		setSubject(subject);
		setBody(body);
		for(MailReceiver rec:receivers) {
			addReceiver(rec);
		}
	}
	public MailMessenger(String subject,MailBody body,ArrayList<MailReceiver> receivers,ArrayList<File>attachments){
		this();
		setSubject(subject);
		setBody(body);
		for(MailReceiver rec:receivers) {
			addReceiver(rec);
		}
		for(File f:attachments) {
			addAttachment(f);
		}
	}
	public MailMessenger(String subject,MailBody body,MailReceiver receiver,File attachment){
		this();
		setSubject(subject);
		setBody(body);
		addReceiver(receiver);
		addAttachment(attachment);	
	}
	public MailMessenger clone(){
		return new MailMessenger(this);
	}
	public void setDefaultUser(MailUser defaultUser) {
		this.defaultUser = defaultUser;
	}
	public MailUser getDefaultUser() {
		return defaultUser;
	}
	public boolean addAttachment(File attachment){
		if(!attachment.exists() || attachments.contains(attachment)){
			return false;
		}
		return attachments.add(attachment);
	}
	public boolean removeAttachment(File d){
		return attachments.remove(d);
	}
	public int attachmentsSize(){
		return attachments.size();
	}
	public File getAttachment(int p){
		return attachments.get(p);
	}
	public MailBody getBody() {
		return body;
	}

	public void setBody(MailBody b) {
		if(b==null) {
			body=new MailBody("",MailBody.PLAIN_TEXT);
		}
		else {
			body=b;
		}
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public boolean addReceiver(MailReceiver r){
		if(receivers.contains(r)){
			return false;
		}
		return receivers.add(r);
	}
	public boolean removeReceiver(MailReceiver r){
		return receivers.remove(r);
	}
	public int receiversSize(){
		return receivers.size();
	}
	public MailReceiver getReceiver(int p){
		return receivers.get(p);
	}
	public boolean containsReceiver(MailReceiver r){
		return receivers.contains(r);
	}
	public boolean containsAttachment(File f){
		return attachments.contains(f);
	}
	public boolean containsAttachment(String f){
		return containsAttachment(new File(f));
	}
	public void addDeliveryUser(MailUser u){
		deliveryUsers.put(u.getName(),u);
	}
	public MailUser removeDeliveryUser(String name){
		return name.equals(getDefaultUser().getName())?null:deliveryUsers.remove(name);
	} 
	public MailUser getDeliveryUser(String name){
		return deliveryUsers.get(name);
	}
	public void removeAllDeliveryUsers() {
		deliveryUsers=new HashMap<>();
	}
	public void removeAllAttachments() {
		attachments=new ArrayList<>();
	}
	public void removeAllReceivers() {
		receivers=new ArrayList<>();
	}
	public void addDeliveryUsers(List<MailUser> users) {
		users.forEach((u)->addDeliveryUser(u));
	}
	public void addReceivers(List<MailReceiver> recs) {
		recs.forEach((u)->addReceiver(u));
	}
	public void addAttachments(List<File> ats) {
		ats.forEach((u)->addAttachment(u));
	}
	public final void send() throws SendMailException{
		send(getDefaultUser());
	}
	public void send(String user) throws SendMailException{
		send(getDeliveryUser(user));
	}
	
	protected final Session getSession(MailUser sender) {
		Session session;
		Properties props = new Properties();
	    String host=sender.getHost();
	    String name=sender.getName();
		switch(host) {
			case "smtp.live.com":
			case "smtp.gmail.com":
			//default:
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", String.valueOf(sender.getPort()));
				props.put("mail.from",name);
				props.put("mail.user",name);
				props.put("mail.password",sender.getPassword());
				session=Session.getInstance(props,null);
				break;
			default:session=null;
		}
		return session;
	}
	private static final Message.RecipientType getType(String type){
		switch(type) {
			case MailReceiver.TYPE_CC:return Message.RecipientType.CC;
			case MailReceiver.TYPE_BCC:return Message.RecipientType.BCC;
			default:return Message.RecipientType.CC;
		}
	}
	public final void send(MailUser sender) throws SendMailException{
		if(sender==null) {
			throw new SendMailException("Sender is null");
		}
		Session session=getSession(sender);
		if(session==null) {
			throw new SendMailException("Session is null");
		}
		String senderName=sender.getName();
		SMTPTransport transport=null;
		MailBody body;
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderName));
			BodyPart messageBodyPart = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			for(MailReceiver d:receivers){
				message.addRecipient(getType(d.getType()),InternetAddress.parse(d.getAddress())[0]);
			}
			message.setSubject(getSubject());
			body=getBody();
			messageBodyPart.setContent(body.getContent(),body.getType());
			multipart.addBodyPart(messageBodyPart);
			for(File arch:attachments){
				messageBodyPart = new MimeBodyPart();
		        DataSource source = new FileDataSource(arch.getAbsolutePath());
		        messageBodyPart.setDataHandler(new DataHandler(source));
		        messageBodyPart.setFileName(arch.getName());
		        multipart.addBodyPart(messageBodyPart);
			}			
			message.setContent(multipart);			
			transport = (SMTPTransport) session.getTransport("smtp");
			try {
		        transport.connect(senderName,sender.getPassword());
			}catch(MessagingException e) {
				throw new SendMailException("Authentication failed");
			}
			try {
		        transport.sendMessage(message, message.getAllRecipients());
			}catch(MessagingException e) {
				throw new SendMailException(e.getNextException().getMessage());
			}
		}
		catch (MessagingException e) {		
			throw new SendMailException(e.getMessage());
		}
		finally {
			if(transport!=null) {
				try {
					transport.close();
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	public static ArrayList<File> send(ArrayList<MailReceiver> receivers,ArrayList<File> attatchments,
			String subject,String body,MailUser sender) throws SendMailException {
		return send(receivers,attatchments,subject,new MailBody(body,MailBody.PLAIN_TEXT),sender);
	}
	public static ArrayList<File> send(ArrayList<MailReceiver> receivers,ArrayList<File> attatchments,
			String subject,MailBody body,MailUser sender) throws SendMailException {
		MailMessenger messenger=new MailMessenger(subject, body, receivers,attatchments);
		ArrayList<File> filesNotSent=new ArrayList<>();
		for(File f:attatchments) {
			if(!messenger.containsAttachment(f)) {
				filesNotSent.add(f);
			}
		}
		messenger.send(sender);
		return filesNotSent;
	}
	public static void main(String[] args) throws SendMailException {
		MailUser sender=new MailUser("sender","senderPassword",GMAIL_HOST,GMAIL_PORT);
		ArrayList<MailReceiver> recs=new ArrayList<>();
		ArrayList<File> files=new ArrayList<>();
		ArrayList<File> filesNotSent=new ArrayList<>();
		files.add(new File("itunesManager.xls"));
		files.add(new File("pom.xml"));
		recs.add(new MailReceiver("insertReceiver",MailReceiver.TYPE_CC));
		recs.add(new MailReceiver("insertReceiver",MailReceiver.TYPE_CC));
		System.out.println("Sending...");
		try {
			filesNotSent=MailMessenger.send(recs, files, "Mail test",getContent(),sender);
			System.out.println("Sent");
			System.out.println("Files that didnt sent because they dont exist");
			filesNotSent.forEach(System.out::println);
		}catch(SendMailException e) {
			System.err.println(e.getMessage());
		}	
	}
	private static MailBody getContent()  {
		try {
			return new MailBody(FileUtil.getContents("webMailPage.html",Charset.forName("utf-8")
					),MailBody.HTML_TEXT);
		} catch (IOException e) {
			return new MailBody("web page not found",MailBody.PLAIN_TEXT);
		}
	}
}
