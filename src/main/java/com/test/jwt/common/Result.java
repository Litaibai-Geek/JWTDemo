package com.test.jwt.common;

import net.sf.json.JSONObject;
/**
 * 返回信息定义枚举类
 * @author zhangyichong
 *
 */
public enum Result {
	
    OK(0, "操作成功"),
    FALSE(-1, "操作失败"),
    AUTHCODE_ERROR(1001,"验证码不对或超时，请重新获取验证码！"), 
    REGISTER_ERROR(2001,"注册失败，请重新注册！"),
    REGISTER_USERNAME(2004,"注册失败，用户名已被注册！"),
    REGISTER_DELETION_TELEPHONE(2002,"注册失败，缺失电话号码！"),
    REGISTER_DELETION_PASSWORD(2003,"注册失败，缺失注册密码！"),
    USERNAME_OR_PASSWORD_ERROR(3001,"用户名或者密码错误！"),
    UNALLOW_LOGIN(3002,"当前用户被限制登入，请联系客户中心！"),
    ACCOUNT_EXITS(3003,"账号已存在"),
    LOGIN_FAIL(3004,"登入失败"),
    ABNORMAL(3005,"当前用户异常，请重新登入"),
    ACCOUNT_NOT_EXITS(3006,"用户不存在！"),
    LOGIN_MEMBER_NULL(3007,"登入用户为null"),
    LOGIN_MEMBER_USERNAME_NULL(3008,"登入用户名为null"),
    LOGIN_MEMBER_PASSWORD_NULL(3009,"登入密码为null"),
    USER_NO_PRIVILEGE(50001,"用户无权限"),
    UNKNOW_ERROR(9999,"不明类型定义！"),
    UNKNOW_PARAMS(6666,"参数传值有问题！"),
    NETWORK_NOT_ONLINE(10001,"您当前网络不给力，请稍后再试！"),
    STUDENT_RECRUIT_OK(11000,"录入成功！"),
    TELEPHONE_RECEPION(11001,"该手机号码已被绑定！"),
    STUDENT_RECRUIT_UNKNOW_ERROR(11002,"录入异常"),
    
    ORDER_LIMIT_COUNT(30001,"超出限制购买数"),
    ORDER_RESIDUE_COUNT_INSUFFICIENT(30002,"商品库存不足!"),
    
    PAY_FAIL(90001,"支付失败"),
    PAY_ORDER_FORBID_PAY(90002,"当前订单不允许支付!"),
    
	//妈妈身边页面跳转码
	FOCUS_XZMM(001,"未关注小程序"),
	FOCUS_PUBLIC(002,"未关注公众号"),
	INVITE_BUY(003,"支付页面"),
	TRANSFOR_CONFLICT(60001,"线索已被录入");
	
    public static final String CODE = "code";
    public static final String MSG = "msg";
    public static final String DATA = "data";

    public final int code;
    public final String msg;

    Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean equal(Object o) {
        if (o == null)
            return false;
        if (o instanceof Result)
            return code == ((Result) o).code;
        if (o instanceof Integer)
            return code == ((Integer) o).intValue();
        if (o instanceof Long)
            return code == ((Long) o).intValue();
        return false;
    }

    public JSONObject toJson(JSONObject obj) {
        obj.put(CODE, code);
        obj.put(MSG, msg);
        obj.put(DATA, "");
        return obj;
    }
    
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        toJson(obj);
        return obj;
    }

    public JSONObject toJson(JSONObject obj, String newMsg) {
        obj.put(CODE, code);
        obj.put(MSG, newMsg);
        obj.put(DATA, "");
        return obj;
    }

    public JSONObject toJson(String newMsg) {
        JSONObject obj = new JSONObject();
        toJson(obj, newMsg);
        return obj;
    }

    public <T> JSONObject makeResult(T data) {
        JSONObject res = toJson();
        res.put(DATA, data);
        return res;
    }
    public <T> JSONObject makeResult(String name1,T data1,String name2,T data2) {
        JSONObject res = toJson();
    	JSONObject temp = new JSONObject();
    	temp.put(name1, data1);
    	temp.put(name2, data2);
        res.put(DATA, temp);
        return res;
    }
    public <T> JSONObject makeResult(JSONObject jsonObj) {
        JSONObject res = toJson();
        res.put(DATA, jsonObj);
        return res;
    }
    
    public <T> JSONObject makeResult(T data, String newMsg) {
        JSONObject res = toJson(newMsg);
        res.put(DATA, data);
        return res;
    }

    public static Result findResult(int code) {
        Result[] vs = Result.values();
        for (Result gr : vs) {
            if (gr.code == code)
                return gr;
        }
        return UNKNOW_ERROR;
    }

    public static <T> JSONObject build(Result gr, T data) {
        return gr.makeResult(data);
    }

    public static <T> JSONObject build(Result gr, T data, String newMsg) {
        return gr.makeResult(data, newMsg);
    }
}
