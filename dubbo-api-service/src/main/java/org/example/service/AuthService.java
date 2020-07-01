package org.example.service;

import java.util.Map;

public interface AuthService {

    boolean authUser(String sessionId);

    Map<String, Object> authUserInfo(String sessionId);

}
