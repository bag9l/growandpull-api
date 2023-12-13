package com.growandpull.api.service;

public interface InvitationService {
    void sendInvitation(String sender, String recipient, String startupId);
    void acceptInvitation(String invitationToken);

}