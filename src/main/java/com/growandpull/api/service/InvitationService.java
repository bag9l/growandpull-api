package com.growandpull.api.service;

public interface InvitationService {
    void sendInvitation(String senderId, String recipientId, String startupId);

    void acceptInvitation(String invitationId, String currentUserEmail);

}