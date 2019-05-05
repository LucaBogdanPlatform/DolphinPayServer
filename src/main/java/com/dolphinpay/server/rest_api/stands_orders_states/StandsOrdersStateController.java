package com.dolphinpay.server.rest_api.stands_orders_states;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StandsOrdersStateController {

    @GetMapping("/")
    public String list() {
        return "standsOrdersStates";
    }

}
