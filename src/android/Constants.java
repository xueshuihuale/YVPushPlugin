package com.yvertical.yvda.common;

public class Constants {
    public static final String MQTT_MESSAGE_ARRIVED = "MQTT_MESSAGE_ARRIVED";
    public static final String NOTI_TITLE = "龙外消息";
    public static final String USERNAME_PASSWORD_EMPTY = "登录用户名称或密码都不能为空";
    public static final String USERNAME_PASSWORD_MISMATCH = "用户名或者密码错误";
    public static final String NA_NETWORK = "无可用网络";
    public static final String NA_SERVICE = "无可用服务";
    public static final String NO_CONN = "无连接";
    public static final String SIGINING = "登录中";
    public static final String WAITING = "请稍候...";
    public static final String LOGIN_SUCCESS = "登录成功";
    public static final String ON_SETTING = "更新中";
    public static final String SETTING_SUCCESS = "更新成功";
    public static final String SETTING_FAILURE = "更新失败";
    
    public static final String TITLE_SETTINGS = "设置";
    public static final String TITLE_CONTACTS = "通讯录";
    public static final String TITLE_SERVICENUM = "服务号";
    public static final String TITLE_SERVICELIST = "服务列表";
    public static final String TITLE_SERVICEDETAIL = "详细信息";
    public static final String TITLE_MESSAGES = "消息";
    public static final String TITLE_APPGROUP = "服务";
    public static final String TITLE_ORGGROUP = "组织";
    public static final String TITLE_FOLLOWGROUP = "服务号";
	public static final String TITLE_LOGIN = "龙外登录";
	public static final String TITLE_HOSET_LIST = "主机列表";
	public static final String TITLE_ADD_NEW_HOST = "添加新服务器";	
	public static final String TITLE_SET_PASSWORD = "修改密码";
	public static final String TITLE_SET_EMAIL = "修改邮箱";
	public static final String TITLE_SET_NAME = "修改姓名";
	public static final String TITLE_CHAT = "聊天 ";
	public static final String TITLE_IMAGE_SHOW = "显示图片";
	public static final String TITLE_ABOUT = "关于龙外";
	public static final String TITLE_USETINFO = "用户信息";
	public static final String TITLE_MESSAGE_CARD = "消息卡";
		
	public static final String ITEM_SET_PASSWORD_ERROR = "原密码错误！";
	public static final String ITEM_SET_PASSWORD_NOEMPTY = "密码不能为空！";
	public static final String ITEM_SET_PASSWORD_NOMATCH = "两次输入的新密码不一致！";
	public static final String ITEM_SET_PASSWORD_ORIGIN_ERROR = "原密码错误！";
	public static final String ITEM_SET_INPUT_EMPTY = "输入内容为空！";
	
	public static final String ITEM_IMAGE_SAVE_FAIL = "图片保存失败！";
	public static final String ITEM_IMAGE_SAVE_SUCCESS = "图片保存成功！";
	
	public static final String TITLE_SERVER_HOST = "服务器主机";
	public static final String ITEM_SET_DEFAULT_SERVER = "设为默认服务器";
	public static final String ITEM_SET_DEL_SERVER = "删除服务器主机";
	public static final String HOSTNAME_HOSTADDRESS_NOEMPTIY="主机名称与地址不能为空";
	public static final String ITEM_VERSION_NUM = "版本号 ：";
	public static final String ITEM_SET_CHECK_VERSION = "当前是最新版本";
	
	public static final String ITEM_CHECK_SDCARD_ISEXIST = "检查SD卡是否存在";
	public static final String ITEM_DOWNLOAD_IMAGE = "下载图片";
	public static final String ITEM_DOWNLOADLING = "正在下载";
	public static final String ITEM_DOWNLOAD_SUCCESS = "下载成功！";
	public static final String ITEM_DOWNLOAD_FAIL = "下载失败！";
	
	public static final String FOLLOW_SUCCESS = "关注成功！";
	public static final String FOLLOW_FAIL = "关注失败！";
	public static final String FOLLOW_CANCEL_SUCCESS = "取消关注成功！";
	public static final String FOLLOW_CANCEL_FAIL = "取消关注失败！";
	
	public static final String USER_LEADER = "领导";
	public static final String USER_NOTLEADER = "用户";
	
	
	
    public static final String TITLE_SET_USERNAME = "";
    public static final String TITLE_SET_USEREMAIL = "";
    public static final String TITLE_SET_USERPASSWORD = "";

    public static final String APP_NAME = "龙外";
    public static final String CANCEL = "取消";
    public static final String CONFIRM = "确认";
    public static final String REALLY_EXIT = "确认退出？";
    public static final String DEBUG_TAG = "==YVDA==";

    public static final String NOTI_AUDIO = "语音消息";
    public static final String NOTI_IMAGE = "图片消息";
    public static final String NOTI_TXT = "文本消息";
    public static final String NOTI_MENU = "菜单消息";

    public static final String TYPE_NOTI = "noti";
    public static final String SUBTYPE_TEXT = "text";
    public static final String SUBTYPE_AUDIO = "audio";
    public static final String SUBTYPE_TXT = "txt";
    public static final String SUBTYPE_IMAGE = "image";
    public static final String SUBTYPE_VIDEO = "video";
    public static final String SUBTYPE_CARD = "card";
    public static final String SUBTYPE_MENU = "menu";

    public static final String ICON_DIR = "icon";
    public static final String DOWNLOAD_DIR = "download";
    
    public static final String CAMERA_CAPTURE = "yv_cc.jpg";
    public static final String TIMESTAMP_FORMAT = "MM月dd日 HH:mm";
    
    public static final String[] ALPHA_LIST = { "A", "B", "C", "D", "E", "F", "G",
                                                "H", "I", "J", "K", "L", "M", "N",
                                                "O", "P", "Q","R","S","T",
                                                "U", "V", "W", "X", "Y", "Z" };
    public static final int LIST_SCROLL = 0;
    public static final int INDEX_SCROLL = 1;

    public static final int CATEGORY_GROUP = 2;
    public static final int CATEGORY_MEMBER = 1;
    public static final int CATEGORY_ALPHA = 0;

    public static final int MAX_TEXT_LEN = 128;
    public static final int MAX_TITLE_LEN = 12;

    public static final int THUMBNAIL_XY = 160;

    public static final int MSG_DIR_IN = 0;
    public static final int MSG_DIR_OUT = 1;

    public static final int MSG_SEND_SENDING = 0;
    public static final int MSG_SEND_ERROR = 1;
    public static final int MSG_SEND_SUCCESS = 2;
    public static final int MSG_RECV_UNACK = 3;
    public static final int MSG_RECV_UNREAD = 4;
    public static final int MSG_RECV_READ = 5;

    public static final int MQTT_PORT = 1883;
    public static final int MQTT_CONNECTION_TIMEOUT = 10;
    public static final int MQTT_KEEPALIVE_INTERVAL = 20 * 60;
    public static final int MQTT_QOS = 1;

    public static final String AUDIO_MIME = "audio/amr";
    
    public static final String AUDIO_EXT = "3gp";
    public static final String TXT_EXT = "txt";
    public static final String IMAGE_EXT = "jpg";
    public static final String CARD_EXT = "card";
    
    public static final String ACTION_UNREAD_NUM_TOTAL = "com.yvertical.yvda.UNREAD_NUM_TATAL";
    public static final String ACTION_REFRESH_LIST = "com.yvertical.yvda.REFRESH_LIST";
    public static final String ACTION_MQTT_RECV = "com.yvertical.yvda.ACTION_MQTT_RECV";
	public static final String ACTION_LOGOUT = "com.yvertical.yvda.LOGINOUT";
	public static final String ACTION_SETTING = "com.yvertical.yvda.SETTING";

    public static final String DEVICE_USER = "du";
    public static final String ORG_GROUP = "og";
    public static final String APP_GROUP = "ag";
    public static final String FOLLOW_GROUP = "fg";

    public static final int ICON_WIDTH = 120;
    public static final int ICON_HEIGHT = 120;
    public static final float ICON_FONT_SIZE = 80f;
    
    //notification 
    public static final String NOTIFICATION_TICKER = "您收到一条新消息";
    public static final String NOTIFICATION_TEXT = "点击查看";
    
};
