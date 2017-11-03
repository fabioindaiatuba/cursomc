package com.fabioindaiatuba.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.fabioindaiatuba.cursomc.domain.Cliente;
import com.fabioindaiatuba.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);
	
}
