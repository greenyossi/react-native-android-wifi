package com.devstepbcn.wifi;

import com.facebook.react.bridge.*;
import android.net.wifi.WifiManager;
import android.content.Context;
import java.lang.reflect.Method;

public class AndroidWifiModule extends ReactContextBaseJavaModule {

	//WifiManager Instance
	WifiManager wifi;
	ReactApplicationContext reactContext;

	//Constructor
	public AndroidWifiModule(ReactApplicationContext reactContext) {
		super(reactContext);

		wifi = (WifiManager)reactContext.getSystemService(Context.WIFI_SERVICE);
		this.reactContext = reactContext;
	}

	//Name for module register to use:
	@Override
	public String getName() {
		return "AndroidWifiModule";
	}


	@ReactMethod
	public void isApOn(Promise p) {
		try {
			Method method = wifi.getClass().getDeclaredMethod("isWifiApEnabled");
			method.setAccessible(true);
			p.resolve(_isApOn());
		} catch (Throwable e) {
			p.reject(e.getMessage());
		}
	}

	private boolean _isApOn() {
		try {
			Method method = wifi.getClass().getDeclaredMethod("isWifiApEnabled");
			method.setAccessible(true);
			return (Boolean) method.invoke(wifi);
		} catch (Throwable e) {
			return false;
		}
	}
}
