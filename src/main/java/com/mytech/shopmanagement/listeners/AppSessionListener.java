package com.mytech.shopmanagement.listeners;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class AppSessionListener implements HttpSessionListener {
	public static int SESSION_COUNT= 0;
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		SESSION_COUNT++;
		System.out.println("Created! current session active: " + SESSION_COUNT);
		HttpSessionListener.super.sessionCreated(se);
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("Destroyed! current session active: " + --SESSION_COUNT);
		HttpSessionListener.super.sessionDestroyed(se);
	}
	
}
