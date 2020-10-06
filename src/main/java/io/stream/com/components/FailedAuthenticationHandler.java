package io.stream.com.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import io.stream.com.cache.AuthCache;

@Component
public class FailedAuthenticationHandler implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private AuthCache authCache;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        Object username = event.getAuthentication().getPrincipal();

        authCache.failedLoginAttempt((String) username);
    }
}
