package com.wuochoang.binarybot.common;

/**
 * Created by quyenlx on 8/9/2017.
 */

public interface Constant {

    String KEY_USER_MODEL = "user_model";
    String PLATFORM_DEVICE = "android";
    String API_TOKEN = "EJeJgLFMj3K1GkC";
    String APP_ID = "13114";
    String CONTRACT_TYPE_CALL = "CALL";
    String CONTRACT_TYPE_PUT = "PUT";
    String BASIS_STAKE = "stake";
    String CURRENCY = "USD";

    String CONTRACT_DURATION_UNIT_TICK = "t";
    String CONTRACT_DURATION_UNIT_MINUTE = "m";
    String API_TOKEN_VIRTUAL = "EJeJgLFMj3K1GkC";
    String API_TOKEN_REAL = "ey9oVVWmUZ21aHo";

    String isVirtual = "isVirtual";
    String ACCOUNT_BALANCE = "accountBalance";



    String AUTHORIZATION_REQUEST = "{\"authorize\": \"EJeJgLFMj3K1GkC\"\n}";
    String BALANCE_REQUEST = "{\n" +
            "  \"balance\": 1,\n" +
            "  \"subscribe\": 1\n" +
            "}";

    String PING = "{\n" +
            "  \"ping\": 1\n" +
            "}";



}
