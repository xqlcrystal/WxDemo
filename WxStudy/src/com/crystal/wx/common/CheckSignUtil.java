package com.crystal.wx.common;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by xieql2 on 2016/11/7.
 */
public class CheckSignUtil {
    public static boolean Check(String token,String signature, String timestamp, String nonce, String echostr) throws IOException {
        String sortString=sort(token,timestamp,nonce);

        String mySignature=sha1(sortString);

        if(mySignature!=null && mySignature!="" && mySignature.equals(signature)){
            return true;
        }else{
            return false;
        }
    }

    private static String sort(String token, String timestamp, String nonce)
    {
        String[] array={token,timestamp,nonce};
        Arrays.sort(array);
        StringBuilder sb=new StringBuilder();
        for(String str:array){
            sb.append(str);
        }
        return  sb.toString();
    }

    private static String sha1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
