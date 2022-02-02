package com.lin_q.debursement_api;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.lin_q.debursement_api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

public class Util {
    
    private static Util util;

	public static Util getInstance() {
		if(util ==  null) {
			util = new Util();
		}
		return util;
	}

    public static String getUUID() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString().toString();
        return uuidAsString;
    }

    private static UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        Util.userService = userService;
    }

    public static void arSave(String remoteAddr, String action, String requestStatus) {
        try {
            userService.saveActionRequest(remoteAddr, action, requestStatus);            
        } catch (NullPointerException e) {
            e.getStackTrace();
        }
    }

}
