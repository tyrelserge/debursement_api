package com.lin_q.debursement_api.exception;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

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

    // private static UserProvisioningService userProvisioningService;

    // @Autowired
    // public void setUserProvisioningService(UserProvisioningService userProvisioningService) {
    //     Util.userProvisioningService = userProvisioningService;
    // }

    // public static void arSave(String remoteAddr, String action, String requestStatus) {
    //     try {
    //         userProvisioningService.saveActionRequest(remoteAddr, action, requestStatus);            
    //     } catch (NullPointerException e) {
    //         e.getStackTrace();
    //     }
    // }

}
