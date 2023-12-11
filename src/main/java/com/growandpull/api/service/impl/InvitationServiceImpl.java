package com.growandpull.api.service.impl;

import com.growandpull.api.exception.EntityNotExistsException;
import com.growandpull.api.exception.PermissionException;
import com.growandpull.api.model.Invitation;
import com.growandpull.api.model.InvitationStatus;

import com.growandpull.api.model.entity.Startup;
import com.growandpull.api.model.entity.User;
import com.growandpull.api.repository.InvitationRepository;
import com.growandpull.api.repository.StartupRepository;
import com.growandpull.api.service.InvitationService;
import com.growandpull.api.service.StartupService;
import com.growandpull.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class InvitationServiceImpl implements InvitationService {
    private final InvitationRepository invitationRepository;
    private final JavaMailSender javaMailSender;
    private final StartupRepository startupRepository;
    private final UserService userService;
    private final StartupService startupService;

    @Override
    public void sendInvitation(String senderEmail, String recipientEmail, String startupId) {
        User sender = userService.findUserByEmail(senderEmail);
        User recipient = userService.findUserByEmail(recipientEmail);
        Startup startup = startupRepository.findById(startupId).orElseThrow(() -> new EntityNotExistsException("User not found"));
        if (startup.getCollaborators().contains(recipient)) {
            throw new RuntimeException("This user is already collaborator");
            //доробити есепшин
        }
        sendInvitation(sender, recipient, startup);
    }

    @Transactional
    @Override
    public void acceptInvitation(String invitationId, String currentUserEmail) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new EntityNotExistsException("Invitation not found"));
        if (!invitation.getRecipient().getEmail().equals(currentUserEmail)) {
            throw new PermissionException("You don't have permission to be collaborator");
        }
        invitation.setStatus(InvitationStatus.ACCEPTED);
        invitationRepository.save(invitation);
        Startup startup = invitation.getStartup();
        User recipient = invitation.getRecipient();
        startup.getCollaborators().add(recipient);

        startupRepository.save(startup);
    }


    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("grow.and.pull@gmail.com");
        javaMailSender.send(message);
    }

    private void sendInvitation(User sender, User recipient, Startup startup) {
        Invitation invitation = new Invitation();
        invitation.setSender(sender);
        invitation.setRecipient(recipient);
        invitation.setStartup(startup);
        invitation.setStatus(InvitationStatus.PENDING);
        invitationRepository.save(invitation);

        String invitationLink = "https://growandpull.pp.ua/api/v1/startups/accept/" + invitation.getId();
        String subject = "Invitation to join startup " + startup.getTitle();
        String body = "Dear " + recipient.getFirstName() + ",\n\n"
                + sender.getFirstName() + " " + sender.getLastName()
                + " invites you to join the startup: " +
                "https://growandpull.pp.ua/api/v1/startups/" + startup.getId()
                + ". \n\nClick the link below to accept the invitation:\n"
                + invitationLink + "\n\n" + "Best regards,\n" + "The Grow&Pull Team";

        sendEmail(recipient.getEmail(), subject, body);
    }
}

