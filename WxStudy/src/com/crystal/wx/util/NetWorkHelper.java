package com.crystal.wx.util;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by xieql2 on 2016/11/7.
 */
public class NetWorkHelper {

    public String getHttpsResponse(String reqUrl,String requestMethod){
        URL url;
        InputStream is;
        String resultData="";

        try {
            url=new URL(reqUrl);
            HttpsURLConnection con=(HttpsURLConnection) url.openConnection();
            TrustManager[] tm={xtm};
            SSLContext ctx=SSLContext.getInstance("TLS");
            ctx.init(null,tm,null);
            con.setSSLSocketFactory(ctx.getSocketFactory());
            con.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });

            con.setDoInput(true);
            con.setDoOutput(false);
            con.setUseCaches(false);
            if(null!=requestMethod && !requestMethod.equals("")){
                con.setRequestMethod(requestMethod);
            }else {
                con.setRequestMethod("GET");
            }
            is=con.getInputStream();
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader bufferReader=new BufferedReader(isr);
            String inputLine;
            while ((inputLine = bufferReader.readLine()) != null) {
                resultData += inputLine + "\n";
            }
            System.out.println(resultData);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return resultData;

    }

    X509TrustManager xtm=new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };
}
