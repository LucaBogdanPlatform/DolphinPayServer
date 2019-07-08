package com.dolphinpay.server.rest_api.interfaces;

import com.dolphinpay.server.rest_api.v1.users.User;
import org.springframework.http.ResponseEntity;

public interface UserExecutableRequest {

    public ResponseEntity execute(User user);
}
