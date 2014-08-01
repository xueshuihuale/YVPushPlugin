/*
 * * (C) Copyright YVertical Inc., 2010-2014 All Rights Reserved.
 * Author: guijin.ding@yvertical.com
 * Create: 2014-04-19
 * 
 */

package com.yvertical.yvda.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yvertical.yvda.common.Constants;

public class YVMqttCallbackHandler implements MqttCallback {
	private String TAG = "MyPlugin";

    private Context context;
    private String clientHandle;

    public YVMqttCallbackHandler(Context context, String clientHandle) {
        this.context = context;
        this.clientHandle = clientHandle;
    }

    @Override
    public void connectionLost(Throwable cause) {
        if (YVMqttConnection.access(clientHandle).state == YVMqttConnection.CONNECTION_CONNECTED) {
            Log.i(TAG, ">>>>>>>>> MQTT Connection LOST <<<<<<<");
            //FIXME: needs reconnect if networkavailable.
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Intent intent =new Intent();
        intent.setAction(Constants.MQTT_MESSAGE_ARRIVED);
        intent.putExtra("message", message.toString());
        JSONObject json = new JSONObject(message.toString());
        Log.i(TAG, "MQTT Got message: " + message.toString()+"    tl :"+json.getString("tl")); 
        
        context.sendBroadcast(intent);
    }
    
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.i(TAG, ">>>>>>>>> MQTT DELIVERY COMPLETE <<<<<<<");
    }
    
}
