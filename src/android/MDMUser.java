package com.yvertical.yvda.common;

public class MDMUser {
	public String host = null;
    
    public String terminal_uuid = null;
    public String device_uuid = null;
	public String session_uuid = null;
    public String device_id = null;
    
	public String user_uuid = null;
	public String user_name = null;
	public String user_password = null;
	public String user_type = null;
	public String user_icon = null;
    public String subtopic = null;

    public boolean user_online = false;
	
    public int pk_id = 0;
    public int user_id = 0;

    public String login_name = null;
    public String login_pwd = null;

//    public YVObjectResult yv_object = null;
    
    public MDMUser() {
        user_online = false;
        
        host = null;
        
        device_uuid = null;
        terminal_uuid = null ;
        session_uuid = null;
        user_uuid = null;
        device_id = null;
        
        user_name = null;
        user_password = null;
        user_type = null;
        user_icon = null;
        subtopic = null;

        user_id = 0; // server key
        pk_id = 0 ;	 // client key

        login_name = null;
        login_pwd = null;
    }

    public String toString() {
        return user_name + " : " + user_online + ", device_id: " + device_id;
    }
}
