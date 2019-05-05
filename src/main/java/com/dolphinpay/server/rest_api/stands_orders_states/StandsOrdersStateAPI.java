package com.dolphinpay.server.rest_api.stands_orders_states;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nonnegative;
import java.util.List;

@RestController
@RequestMapping("/rest_api/v1/")
@Slf4j
@RequiredArgsConstructor
public class StandsOrdersStateAPI {

    @NonNull
    private StandsOrdersStateService service;

    @GetMapping("/states")
    public ResponseEntity<List<StandsOrdersState>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

}
