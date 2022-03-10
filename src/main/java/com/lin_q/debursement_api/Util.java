package com.lin_q.debursement_api;

import com.lin_q.debursement_api.service.UserService;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;


public class Util
{
  private static com.lin_q.debursement_api.Util util;
  private static UserService userService;
  
  public static com.lin_q.debursement_api.Util getInstance() {
     if (util == null) {
       util = new com.lin_q.debursement_api.Util();
    }
     return util;
  }
  
  public static String getUUID() throws NoSuchAlgorithmException, UnsupportedEncodingException {
     UUID uuid = UUID.randomUUID();
     String uuidAsString = uuid.toString().toString();
     return uuidAsString;
  }
  
  @Autowired
  public void setUserService(UserService userService) {
     com.lin_q.debursement_api.Util.userService = userService;
  }
  
  public static void arSave(String remoteAddr, String action, String requestStatus) {
    try {
       userService.saveActionRequest(remoteAddr, action, requestStatus);
     } catch (NullPointerException e) {
       e.getStackTrace();
    } 
  }

  public static  String thousandSeparator(int n) 
   {
      String str=n+"";
      StringBuilder sb=new StringBuilder();
      for(int i=str.length()-1;i>=0;){
         sb.append(str.charAt(i));
         i--;
         if(i==-1)break; 
         sb.append(str.charAt(i));
         i--;
         if(i==-1)break;
         sb.append(str.charAt(i));
         i--;
         if(i==-1)break;
         sb.append(" ");
      }
      return sb.reverse().toString();
   }

}
