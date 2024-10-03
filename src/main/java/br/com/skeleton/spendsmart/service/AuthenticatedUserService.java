package br.com.skeleton.spendsmart.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService {

    public String getAuthenticatedUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}