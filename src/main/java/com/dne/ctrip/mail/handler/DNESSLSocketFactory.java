package com.dne.ctrip.mail.handler;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class DNESSLSocketFactory extends SSLSocketFactory{ 
	private SSLSocketFactory factory;

	public DNESSLSocketFactory(){
		try{
			SSLContext localSSLContext = SSLContext.getInstance("TLS");
			localSSLContext.init(null, new TrustManager[] { new DNETrustManager() }, new java.security.SecureRandom());
			this.factory = localSSLContext.getSocketFactory();
		}catch (Exception localException){
			localException.printStackTrace();
		}
	}


	public static SocketFactory getDefault() {
		return new DNESSLSocketFactory();
	}
	public Socket createSocket( Socket socket, String s, int i, boolean 
			flag)
					throws IOException {
		return factory.createSocket( socket, s, i, flag);
	}
	public Socket createSocket( InetAddress inaddr, int i,
			InetAddress inaddr1, int j) throws IOException {
		return factory.createSocket( inaddr, i, inaddr1, j);
	}
	public Socket createSocket( InetAddress inaddr, int i) throws 
	IOException {
		return factory.createSocket( inaddr, i);
	}
	public Socket createSocket( String s, int i, InetAddress inaddr, int j)
			throws IOException {
		return factory.createSocket( s, i, inaddr, j);
	}
	public Socket createSocket( String s, int i) throws IOException {
		return factory.createSocket( s, i);
	}
	public String[] getDefaultCipherSuites() {
		return factory.getSupportedCipherSuites();
	}
	public String[] getSupportedCipherSuites() {
		return factory.getSupportedCipherSuites();
	}
}