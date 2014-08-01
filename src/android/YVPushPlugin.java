package com.yvertical.yvio.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.yvertical.mdm.HIUApp;
import com.yvertical.yvda.app.YvdaApp;
import com.yvertical.yvda.common.Constants;
import com.yvertical.yvda.common.DeviceTerminal;
import com.yvertical.yvda.common.MDMUser;

public class YVPushPlugin extends CordovaPlugin{
	private String TAG = "MyPlugin";
	private String ACTION_START_MQTT = "start";
	private String ACTION_GET_TERMINAL_UUID = "get_terminal_uuid";
	private static CordovaWebView cordView;
	private static boolean isForground = false;
	private MDMUser muser;
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		// TODO Auto-generated method stub
		super.initialize(cordova, webView);
		isForground = true;
	}
	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		// TODO Auto-generated method stub
		if(action.equals(ACTION_START_MQTT)){
		    Log.i("MyPlugin", "mqtt-------------------");
			IntentFilter filter = new IntentFilter();
			filter.addAction(Constants.MQTT_MESSAGE_ARRIVED);
			cordova.getActivity().registerReceiver(reRevier,filter);	
			cordView = this.webView;						
			muser = new MDMUser();
			muser.host = args.getString(0);
			muser.user_uuid = args.getString(1) ;
			muser.session_uuid = args.getString(2);
			muser.device_id = args.getInt(3)+"";
			muser.subtopic = args.getString(4).replace("\\", "");
			ConnectRunnable cr = new ConnectRunnable();
			Thread th = new Thread(cr);
			th.start();
			this.echo("success", callbackContext);
			return true;
		}
		if(action.equals(ACTION_GET_TERMINAL_UUID)){
			
			DeviceTerminal device = new DeviceTerminal(cordova.getActivity());
			String terminal_uuid = device.getTerminalUUID();
			Log.i("MyPlugin", "terminal_uuid-------------------"+terminal_uuid);
			this.echo(terminal_uuid, callbackContext);
			return true;
		}
		return false;
	}
	@Override
	public void onResume(boolean multitasking) {
		// TODO Auto-generated method stub
		super.onResume(multitasking);
		isForground = true;
	}
	@Override
	public void onPause(boolean multitasking) {
		// TODO Auto-generated method stub
		super.onPause(multitasking);
		isForground = false;
		final NotificationManager notificationManager = (NotificationManager) cordova.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		cordView = null;
		isForground = false;
		cordova.getActivity().unregisterReceiver(reRevier);
	}
	public static boolean isActive(){
		return cordView != null;
	}
	public static boolean isInForgound(){
		return isForground;
	}
	
	public void callJs(final String str){
		cordova.getActivity().runOnUiThread(new Runnable() {
			
			@SuppressLint("SetJavaScriptEnabled")
			@Override
			public void run() {
				cordView.getSettings().setJavaScriptEnabled(true);
				cordView.sendJavascript(str);
			}
		});
	}
	
	class ConnectRunnable implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			YvdaApp app = new YvdaApp();
			app.connectMqtt(cordova.getActivity().getBaseContext(),muser);
		}		
	}
	
    private void echo(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private BroadcastReceiver reRevier = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
		
			if(intent.getAction().equals(Constants.MQTT_MESSAGE_ARRIVED)){
				String message = intent.getStringExtra("message");
				String str = "javascript:_fn_android_listener("+message+")";
				callJs(str);
				//create a notification
				if(!YVPushPlugin.isInForgound()){
		        	createNotification(context);
		        	callJs("javascript:_android_go_message()");
		        }
			}
		}
	};
	public void createNotification(Context context) {
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
        String appName = getAppName(context);
		Intent notificationIntent = new Intent(context,
				HIUApp.class);
		notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		int defaults = Notification.DEFAULT_ALL;
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				context).setDefaults(defaults)
				.setSmallIcon(context.getApplicationInfo().icon)
				.setWhen(System.currentTimeMillis())
				.setContentTitle(appName)
				.setContentText(Constants.NOTIFICATION_TEXT)
				.setTicker(Constants.NOTIFICATION_TICKER)
				.setContentIntent(contentIntent).setAutoCancel(true);
		mNotificationManager.notify((String) appName, 0, mBuilder.build());
	}

	private String getAppName(Context context) {
		CharSequence appName = context.getPackageManager().getApplicationLabel(
				context.getApplicationInfo());
		return (String) appName;
	}

}
