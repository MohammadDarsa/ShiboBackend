package com.example.shibo.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        ConfirmationToken ct =  getToken(token).orElse(null);
        if(ct == null) throw new IllegalStateException("token not found");
        ct.setConfirmedAt(LocalDateTime.now());
        saveConfirmationToken(ct);
//        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
