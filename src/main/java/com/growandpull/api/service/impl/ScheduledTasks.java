package com.growandpull.api.service.impl;

import com.growandpull.api.token.ConfirmationTokenRepository;
import com.growandpull.api.token.InvitationTokenRepository;
import com.growandpull.api.token.PasswordResetTokenRepository;
import com.growandpull.api.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class ScheduledTasks {

    private final TokenRepository tokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final InvitationTokenRepository invitationTokenRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Transactional
    @Scheduled(cron = "0 0 3 * * ?")
    public void deleteRevokedTokens(){
        tokenRepository.deleteByIsExpiredTrueOrIsRevokedTrue();
    }

    @Transactional
    @Scheduled(cron = "0 0 3 * * ?")
    public void deleteOldPasswordResetTokens(){
        passwordResetTokenRepository.deleteByCreatedAtBefore(LocalDate.now().minusDays(10));
    }

    @Transactional
    @Scheduled(cron = "0 0 3 * * ?")
    public void deleteOldInvitationTokens(){
        invitationTokenRepository.deleteByCreatedAtBefore(LocalDate.now().minusDays(10));
    }

    @Transactional
    @Scheduled(cron = "0 0 3 * * ?")
    public void deleteOldConfirmationTokens(){
        confirmationTokenRepository.deleteByCreatedAtBefore(LocalDate.now().minusDays(10));
    }

}
