/**
 * (C) Copyright YVertical Inc., 2010-2014 All Rights Reserved.
 * Author: guijin.ding@yvertical.com
 * Created: 2014-04-19
 *
 **/

package com.yvertical.yvda.mqtt;

import com.yvertical.yvda.common.Constants;
import com.yvertical.yvda.mqtt.YVMqttConnection;
import com.yvertical.yvda.app.YvdaApp;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import android.content.Context;
import android.util.Log;

class ActionListener implements IMqttActionListener {

    enum Action {
        /** Connect Action **/
        CONNECT,
        /** Disconnect Action **/
        DISCONNECT,
        /** Subscribe Action **/
        SUBSCRIBE,
        /** Publish Action **/
        PUBLISH
    }

    private Action action;
    private String clientHandle;
    private Context context;
    private YvdaApp app;

    public ActionListener(Context context,
                          Action action,
                          String clientHandle) {
        this.context = context;
        this.action = action;
        this.clientHandle = clientHandle;
        app = new YvdaApp();
    }

    /**
     * The action associated with this listener has been successful.
     * 
     * @param asyncActionToken
     *            This argument is not used
     */
    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        switch (action) {
        case CONNECT :
            connect();
            break;
        case DISCONNECT :
            disconnect();
            break;
        case SUBSCRIBE :
            subscribe();
            break;
        case PUBLISH :
            publish();
            break;
        }
    }

    private void publish() {
        
    }

    private void subscribe() {
        Log.i(Constants.DEBUG_TAG, "<<<<<<<<<<<<< subscribe success <<<<<<<<<<<<<<<");
//        app.onMqttSubscribe(true);
    }

    private void disconnect() {
        Log.i(Constants.DEBUG_TAG, "<<<<<<<<<<<<< disconnect success <<<<<<<<<<<<<<<");
        app.onMqttDisconnect(true);
    }
    
    private void connect() {
        Log.i(Constants.DEBUG_TAG, "<<<<<<<<<<<<< connect success <<<<<<<<<<<<<<<");
        Log.i("MyPlugin", "<<<<<<<<<<<<< connect success <<<<<<<<<<<<<<<");
        app.onMqttConnect(true);
    }

    /**
     * The action associated with the object was a failure
     * 
     * @param token
     *            This argument is not used
     * @param exception
     *            The exception which indicates why the action failed
     */
    @Override
    public void onFailure(IMqttToken token, Throwable exception) {
        switch (action) {
        case CONNECT :
            connect(exception);
            break;
        case DISCONNECT :
            disconnect(exception);
            break;
        case SUBSCRIBE :
            subscribe(exception);
            break;
        case PUBLISH :
            publish(exception);
            break;
        }

    }

    private void publish(Throwable exception) {
    }

    private void subscribe(Throwable exception) {
        Log.i(Constants.DEBUG_TAG, "<<<<<<<<<<<<< subscribe Failure <<<<<<<<<<<<<<<");
//        app.onMqttSubscribe(false);
    }

    private void disconnect(Throwable exception) {
        Log.i(Constants.DEBUG_TAG, "<<<<<<<<<<<<< Disconnection Failure <<<<<<<<<<<<<<<");
        app.onMqttDisconnect(false);
    }

    private void connect(Throwable exception) {
        Log.i(Constants.DEBUG_TAG, "<<<<<<<<<<<<< Connection Failure <<<<<<<<<<<<<<<");
        app.onMqttConnect(false);
    }

}
