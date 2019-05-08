package com.dolphinpay.server.rest_api.stands_orders_states;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest_api/v1/")
@Slf4j
@RequiredArgsConstructor
public class StandsOrdersStateAPI {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StandsOrdersStateAPI.class);

    @NonNull
    private StandsOrdersStateService service;

    @GetMapping("/states")
    public ResponseEntity<List<StandsOrdersState>> findAll() throws FirebaseMessagingException {
        String token = "eXyUcS9WPVI:APA91bHORz_rH-T_DONu7EGbjwAS3mxombbrYotyWC4gmubxoEtBAOJWF8SaMg5TlOtQLrXyK9bGf2Fx3xU5ZszgLK5DVJLmJ0KymVoUKCSNLwqLFU7uVLqT20Y0ODzs0AFaNHWMvIcv";

        FirebaseMessaging.getInstance().send(Message.builder()
                .putData("title", "TEEEEEEEEEEEST")
                .putData("score", "850")
                .putData("time", "2:45")
                .setToken(token).build());

        return ResponseEntity.ok(service.findAll());
    }

}
