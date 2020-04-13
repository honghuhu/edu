package org.online.common.constant;

/**
 * Redis key 前缀
 */
public interface RedisConstant {

    String PROJECT = "007";

    abstract class Sms {
        private final static String SMS = PROJECT.concat(":sms");
        private final static String REGISTER = SMS.concat(":register");

        public static String Register(String phone) {
            return String.format("%s:%s", REGISTER, phone);
        }
    }
}
