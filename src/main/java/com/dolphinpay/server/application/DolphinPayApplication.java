/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dolphinpay.server.application;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author U374332
 */
public class DolphinPayApplication implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        FileInputStream serviceAccount =
                null;
        try {
            serviceAccount = new FileInputStream("resources/firebase-admin-sdk.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://dolphinpay-d90e4.firebaseio.com")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        FirebaseApp.initializeApp(options);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
}
