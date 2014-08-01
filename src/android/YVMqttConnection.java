/**
 * (C) Copyright YVertical Inc., 2010-2014 All Rights Reserved.
 * Author: guijin.ding@yvertical.com
 * Created: 2014-04-19
 *
 **/

package com.yvertical.yvda.mqtt;

import com.yvertical.yvda.common.Constants;
import com.yvertical.yvda.common.MDMUser;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import android.content.Context;
import android.util.Log;

public class YVMqttConnection {
    private MqttAndroidClient client;
    private MqttConnectOptions conOpt;

    private YVMqttCallbackHandler callback;

    private MDMUser mdmUser;
    private Context context;

    private static YVMqttConnection instance;

    public int state;
    public final static int CONNECTION_NULL = 0;
    public final static int CONNECTION_CONNECTING = 1;
    public final static int CONNECTION_CONNECTED = 2;
    public final static int CONNECTION_RECONNECTING = 3;
    public final static int CONNECTION_DISCONNECTING = 4;
    

    private YVMqttConnection(Context _context, String _host, MDMUser _user) {
    	Log.i("MyPlugin", "----------yvmgttConnection---------------");
        state = CONNECTION_NULL;
        context = _context;
        mdmUser = _user;
        String uri = "tcp://" + _host + ":" + Constants.MQTT_PORT;       
        conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(true);
        conOpt.setConnectionTimeout(Constants.MQTT_CONNECTION_TIMEOUT);
        conOpt.setKeepAliveInterval(Constants.MQTT_KEEPALIVE_INTERVAL);
        conOpt.setUserName(_user.user_uuid);
        conOpt.setPassword(_user.session_uuid.toCharArray());

        callback = new YVMqttCallbackHandler(context, _user.device_id);
        client = new MqttAndroidClient(context, uri, _user.device_id);
        
        client.setCallback(callback);
        client.setTraceEnabled(true);
        
    }
    
    public static YVMqttConnection createConnection(Context context,MDMUser _user) {
        String _host =  _user.host;
        if (_user == null || _host == null) {
            return null;
        }
        instance = new YVMqttConnection(context, _host, _user);
        return instance;
    }

    public static YVMqttConnection access(String _uuid) {
        if (instance == null) {
            return null;
        }
        if (_uuid.equals(instance.mdmUser.device_id)) {
            return instance;
        }
        return null;
    }

    public void connect() {
        ActionListener _actionCallback = new ActionListener(context,
                                                            ActionListener.Action.CONNECT,
                                                            mdmUser.device_id);
        try {
            client.connect(conOpt, null, _actionCallback);
        } catch (MqttException e) {
            e.printStackTrace();
            state = CONNECTION_NULL;
        }
    }

    public void subscribe() {
        ActionListener _actionCallback = new ActionListener(context,
                                                            ActionListener.Action.SUBSCRIBE,
                                                            mdmUser.device_id);
        try {
            client.subscribe(mdmUser.subtopic, Constants.MQTT_QOS, null, _actionCallback);
            Log.i("MyPlugin", "====subscribe mqtt");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    
    public void disconnect() {
        try {
            Log.i(Constants.DEBUG_TAG, "DISCONNECT YVMQTT");
            client.disconnect(null, new ActionListener(context, ActionListener.Action.DISCONNECT, mdmUser.device_id));
        } catch (MqttException e) {
            e.printStackTrace();
            state = CONNECTION_NULL;
        }
    }
    
    public void register() {
        client.registerResources(context);
    }

    public void unregister() {
        Log.i(Constants.DEBUG_TAG, "UNREGISTER YVMQTT");
        client.unregisterResources();
    }

}
