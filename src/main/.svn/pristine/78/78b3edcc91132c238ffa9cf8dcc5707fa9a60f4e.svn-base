package com.tes.services.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServices
{

	private JavaMailSender javaMailSender;

	@Autowired
	public EmailServices(JavaMailSender javaMailSender)
	{
		this.javaMailSender = javaMailSender;
	}

	public int sendMail(String from, String toEmail, String subject, String message)
	{
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(toEmail);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		mailMessage.setFrom(from);

		javaMailSender.send(mailMessage);
		return 1;
	}
}
