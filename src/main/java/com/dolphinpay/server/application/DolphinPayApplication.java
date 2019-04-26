/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dolphinpay.server.application;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author U374332
 */
public class DolphinPayApplication implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext context = sce.getServletContext();
            URL resourceContent = context.getResource("/WEB-INF/firebase-admin-sdk.json");
            File file = new File(resourceContent.toURI());
            FileInputStream serviceAccount = new FileInputStream(file);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://dolphinpay-d90e4.firebaseio.com")
                    .build();
            
            FirebaseApp.initializeApp(options);
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(DolphinPayApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
}
