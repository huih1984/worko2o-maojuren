package com.delta.worko2o.util;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 14-6-18
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
public class MyX509TrustManager implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];  //To change body of implemented methods use File | Settings | File Templates.
    }
}
