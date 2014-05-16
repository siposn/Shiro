/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shiroapp;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;
import org.omnifaces.util.Faces;

/**
 *
 * @author siposn
 */
@Named(value = "logoutBean")
@javax.enterprise.context.RequestScoped
public class Logout {

    private static final String HOME_URL = "login.xhtml";

    public void submit() throws IOException {
        SecurityUtils.getSubject().logout();
        Faces.invalidateSession();
        Faces.redirect(HOME_URL);
    }
    
}
