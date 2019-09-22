package com.dolphinpay.server.rest_api.utils;

import com.dolphinpay.server.rest_api.v1.orders_products.OrdersProducts;
import com.dolphinpay.server.rest_api.v1.products.Products;
import com.dolphinpay.server.rest_api.v1.users_devices.UsersDevices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import javafx.application.Application;
import lombok.NonNull;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FirebaseUtils {
    private static final String FIREBASE_ADMIN_SDK_RESOURCE_PATH = "/WEB-INF/firebase-admin-sdk.json";
    private static final String FIREBASE_ADMIN_CONFIG_URL = "https://dolphinpay-d90e4.firebaseio.com";


    public static void init(@NonNull ServletContext servletContext) {
        try {
            URL resourceContent = servletContext.getResource(FIREBASE_ADMIN_SDK_RESOURCE_PATH);
            File file = new File(resourceContent.toURI());
            FileInputStream serviceAccount = new FileInputStream(file);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(FIREBASE_ADMIN_CONFIG_URL)
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendOrdersProducts(
            @NonNull UsersDevices[] usersDevices,
            @NonNull OrdersProducts ordersProducts,
            @NonNull Products products)
            throws ExecutionException, InterruptedException, JsonProcessingException {
        Map<String, String> m = ordersProducts.toMap(products.getResponse());

        for (UsersDevices u : usersDevices) {
            Message message = Message
                    .builder()
                    .putAllData(m)
                    .setToken(u.getFirebaseToken())
                    .setNotification(new Notification(
                            "New order", "New order still available on your account"
                    )).build();

            String response = FirebaseMessaging.getInstance().sendAsync(message).get();

        }

    }

}
