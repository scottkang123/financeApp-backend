package com.finance.app.config;

import com.finance.app.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

//Integer because user id is Integer
public class ApplicationAuditAware implements AuditorAware<Integer> {

    //used in BeansConfig
    @Override
    public Optional<Integer> getCurrentAuditor(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //anonymouseAuthenticationToken -> we don't know the user yet
        if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken){
            return Optional.empty();
        }
        User userPrincipal = (User) authentication.getPrincipal();
        return Optional.ofNullable(userPrincipal.getId());  //if inside is nullable then return the option of null
    }

}
