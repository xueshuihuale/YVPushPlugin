package com.yvertical.yvda.common;

import java.util.UUID;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

public class DeviceTerminal {
	private Context context;
	public DeviceTerminal(Context context){
		this.context = context;
	}
	public String getTerminalUUID() {

		final TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = "ANDROID-"
				+ android.provider.Settings.Secure.getString(
						context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID)
				+ getModel() + getManufacturer();

		UUID deviceUuid = new UUID(androidId.hashCode(),
				((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
		String deviceId = deviceUuid.toString();
		Log.i("YVda Login", "terminal_uuid="+deviceId);
		return deviceId;
	}
	public String getPhoneNumber() {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getLine1Number();
	}

	public int getSdkInt() {
		return android.os.Build.VERSION.SDK_INT;
	}

	public String getBuildVersionRelease() {
		return android.os.Build.VERSION.RELEASE;
	}

	// 获取手机型号
	public String getModel() {
		return android.os.Build.MODEL;
	}

	// 获取手机厂商
	public String getManufacturer() {
		return android.os.Build.MANUFACTURER;
	}

}
