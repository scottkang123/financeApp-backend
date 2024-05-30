package com.finance.app.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

//Integer because user id is Integer
public class ApplicationAuditAware implements AuditorAware<String> {

    //used in BeansConfig
    @Override
    public Optional<String> getCurrentAuditor(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //anonymouseAuthenticationToken -> we don't know the user yet
        if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken){
            return Optional.empty();
        }
        //User userPrincipal = (User) authentication.getPrincipal();
        return Optional.ofNullable(authentication.getName());  //if inside is nullable then return the option of null
    }

}
