package com.dolphinpay.server.rest_api.utils;

import com.dolphinpay.server.rest_api.v1.orders.Orders;
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


    private enum PushNotificationsCode {
        NEW_PRODUCT_ORDER("0"),
        CLOSED_ORDER("1"),
        READY_ORDER("2"),
        CLOSED_ORDER_PRODUCT("3");
        private final String value;

        PushNotificationsCode(String i) {
            this.value = i;
        }
    }

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

    public static void sendClosedOrder(UsersDevices byUser, Orders orders) throws ExecutionException, InterruptedException, JsonProcessingException {
        Map<String, String> m = orders.toMap();
        m.put("p_code", PushNotificationsCode.CLOSED_ORDER.value);

        Message message = Message
                .builder()
                .putAllData(m)
                .setToken(byUser.getFirebaseToken())
                .setNotification(new Notification(
                        "Confirm pick up", "Order successful picked up"
                )).build();

        String response = FirebaseMessaging.getInstance().sendAsync(message).get();

    }


    public static void sendOrdersProducts(
            @NonNull String orderRetireCode,
            @NonNull UsersDevices[] usersDevices,
            @NonNull OrdersProducts ordersProducts,
            @NonNull Products products)
            throws ExecutionException, InterruptedException, JsonProcessingException {
        Map<String, String> m = ordersProducts.toMap(orderRetireCode, products.getResponse());
        m.put("p_code", PushNotificationsCode.NEW_PRODUCT_ORDER.value);

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

    public static void sendOrderProductClosed(
            UsersDevices[] usersDevices,
            OrdersProducts ordersProducts,
            Products products) throws JsonProcessingException, ExecutionException, InterruptedException {
        Map<String, String> m = ordersProducts.toMap(null, products.getResponse());
        m.put("p_code", PushNotificationsCode.CLOSED_ORDER_PRODUCT.value);

        for (UsersDevices u : usersDevices) {
            Message message = Message
                    .builder()
                    .putAllData(m)
                    .setToken(u.getFirebaseToken())
                    .setNotification(new Notification(
                            "Product is ready", "Your product is ready"
                    )).build();

            String response = FirebaseMessaging.getInstance().sendAsync(message).get();

        }
    }



    public static void sendOrderReady(UsersDevices byUser, Orders orders) throws JsonProcessingException, ExecutionException, InterruptedException {
        Map<String, String> m = orders.toMap();
        m.put("p_code", PushNotificationsCode.READY_ORDER.value);

        Message message = Message
                .builder()
                .putAllData(m)
                .setToken(byUser.getFirebaseToken())
                .setNotification(new Notification(
                        "Hey your order is ready!!!", "Your order is ready, use the qr code to pick it"
                )).build();

        String response = FirebaseMessaging.getInstance().sendAsync(message).get();
    }

}
