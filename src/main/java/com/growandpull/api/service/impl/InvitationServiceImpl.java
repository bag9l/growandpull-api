package com.growandpull.api.service.impl;

import com.growandpull.api.exception.EntityNotExistsException;
import com.growandpull.api.model.entity.Startup;
import com.growandpull.api.model.entity.User;
import com.growandpull.api.repository.StartupRepository;
import com.growandpull.api.repository.UserRepository;
import com.growandpull.api.service.EmailService;
import com.growandpull.api.service.InvitationService;
import com.growandpull.api.service.JwtService;
import com.growandpull.api.service.UserService;
import com.growandpull.api.token.InvitationToken;
import com.growandpull.api.token.InvitationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService {
    @Value(value = "${jwt.invitation_expiration}")
    private long invitationTokenExpiration;

    private final StartupRepository startupRepository;
    private final UserRepository userRepository;
    private final InvitationTokenRepository invitationTokenRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final EmailService emailService;

    @Override

    public void sendInvitation(String sender, String recipient, String startupId) {
        User userRecipient = userService.findUserByEmail(recipient);
        User userSender = userService.findUserByEmail(sender);
        Startup startup = startupRepository.findById(startupId).orElseThrow(() -> new EntityNotExistsException("Startup not found"));
        String invitationToken = jwtService.generateToken(userRecipient, invitationTokenExpiration);
        saveUserInvitationToken(userRecipient, startup, invitationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userRecipient.getUsername());
        mailMessage.setSubject("Invitation to join startup");
        mailMessage.setText("Dear " + userRecipient.getFirstName() + ",\n\n"
                + userSender.getFirstName() + " " + userSender.getLastName()
                + " invites you to join the startup: " +
                "https://growandpull.pp.ua/api/v1/startups/" + startup.getId()
                + ". \n\nClick the link below to accept the invitation:\n"
                + "http://localhost:8000/accept-invite/" + invitationToken
                + "Best regards,\n" + "The Grow&Pull Team" + "To confirm your account, please click here : ");
        emailService.sendEmail(mailMessage);
        System.out.println("invitation token " + invitationToken);

    }

    @Transactional
    @Override
    public void acceptInvitation(String invitationToken) {
        InvitationToken token = invitationTokenRepository.findByToken(invitationToken)
                .orElseThrow(() -> new EntityNotExistsException("Invitation not found"));
        User user = userRepository.findUserByEmail(token.getUser().getEmail())
                .orElseThrow(() -> new EntityNotExistsException("User not found"));
        invitationTokenRepository.save(token);
        Startup startup = token.getStartup();
        User recipient = token.getUser();
        startup.getCollaborators().add(recipient);
        startupRepository.save(startup);
    }

    private void saveUserInvitationToken(User user, Startup startup, String jwtToken) {
        InvitationToken invitationToken = new InvitationToken(jwtToken, false, false, user, startup);
        invitationTokenRepository.save(invitationToken);
    }


}

