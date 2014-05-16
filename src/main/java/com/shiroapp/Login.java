package com.shiroapp;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

/**
 *
 * @author siposn
 */
@Named(value = "login")
@RequestScoped
public class Login implements Serializable {

    private static final String HOME_URL = "app/test.xhtml";
    
    private String username;
    private String password;
    private boolean remember;
    
    public void submit() throws IOException  {
        try {
            
            Subject currentUser         = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password,  remember);
            currentUser.login(token);
            
            if(currentUser.isAuthenticated()) {
                SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
                Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : HOME_URL);
            }
            
//            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, remember));
//            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
//            Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : HOME_URL);
        } catch (UnknownAccountException ex ) {
            ex.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "UnknownAccountException", "kullanıcı adınız yanlış"));
        } catch (IncorrectCredentialsException ex ) {
            ex.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "IncorrectCredentialsException", "parolanız yanlış"));
        } catch (LockedAccountException ex ) {
            ex.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "LockedAccountException", "Bu kullanıcı adı kilitli"));
        } catch(AuthenticationException ex){
            ex.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "AuthenticationException", ex.toString()));
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
