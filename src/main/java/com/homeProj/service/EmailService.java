package com.homeProj.service;

import com.homeProj.domain.User;

public interface EmailService {

	void sendEmail(String to, String subject, String content, boolean isMultiPart, boolean isHtml);

	void sendEmailFromTemplate(User user, String templateName, String subject);

	void sendActivationEmail(User user);

	void sendWelcomeEmail(User user);

}