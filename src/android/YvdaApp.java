package com.yvertical.yvda.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.yvertical.yvda.common.MDMUser;
import com.yvertical.yvda.common.Constants;
import com.yvertical.yvda.mqtt.YVMqttConnection;


public class YvdaApp extends Application {
	
	protected static final String TAG = "YvdaApp";
    public static YVMqttConnection mYVMqttConnection ;
    
	@Override
	public void onCreate() {
		super.onCreate();
    }
    public void setYVMqttConnection(YVMqttConnection _conn) {
        mYVMqttConnection = _conn;
    }

    public YVMqttConnection getYVMqttConnection() {
        return mYVMqttConnection;
    }

    public void connectMqtt(Context context,MDMUser muser) {
        YVMqttConnection _connection = getYVMqttConnection();
        if (_connection == null) {
            _connection = YVMqttConnection.createConnection(context,muser);
            _connection.register();
            
            _connection.state = YVMqttConnection.CONNECTION_CONNECTING;
            _connection.connect();

            setYVMqttConnection(_connection);
            mYVMqttConnection = _connection;
            return;
        }
        
        Log.i(Constants.DEBUG_TAG, "conn: " + _connection.state);
        
        if (_connection.state != YVMqttConnection.CONNECTION_NULL) {
            _connection.state = YVMqttConnection.CONNECTION_RECONNECTING;
            _connection.disconnect();
        } else {
            _connection = YVMqttConnection.createConnection(context,muser);
            mYVMqttConnection = _connection;
            _connection.register();
            
            _connection.state = YVMqttConnection.CONNECTION_CONNECTING;
            _connection.connect();
        }

        return;
    }

    public void disconnectMqtt() {
        YVMqttConnection _connection = mYVMqttConnection ;
        if (_connection == null) {
            return;
        }
        if (_connection.state != YVMqttConnection.CONNECTION_NULL) {
            _connection.state = YVMqttConnection.CONNECTION_DISCONNECTING;
            _connection.disconnect();
        }        
    }

    public void onMqttDisconnect(boolean success) {
        YVMqttConnection _connection = mYVMqttConnection;
        if (_connection == null) {
            return;
        }
        
        if (_connection.state == YVMqttConnection.CONNECTION_RECONNECTING) {
            _connection.state = YVMqttConnection.CONNECTION_CONNECTING;
            _connection.connect();
            return;
        }

        if (_connection.state == YVMqttConnection.CONNECTION_DISCONNECTING) {
            _connection.state = YVMqttConnection.CONNECTION_NULL;
            return;
        }

        if (_connection.state == YVMqttConnection.CONNECTION_CONNECTED) {
            _connection.state = YVMqttConnection.CONNECTION_NULL;
            return;
        }
    }
    
    public void onMqttConnect(boolean success) {
    	YVMqttConnection _connection = mYVMqttConnection;
        if (mYVMqttConnection == null) {
            return;
        }
        
        if (_connection.state == YVMqttConnection.CONNECTION_CONNECTING) {
            if (success) {
            	_connection.state = YVMqttConnection.CONNECTION_CONNECTED;
            	_connection.subscribe();
            } else {
            	_connection.state = YVMqttConnection.CONNECTION_NULL;
            }
        }        
        return;
    }
}
