package com.j.best.common.desensitized;

import junit.framework.TestCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DesensitizedUtilsTest extends TestCase {

    public void testIdCardNum() {
        System.out.println(DesensitizedUtils.idCardNum("13579246810"));
    }

    public void testMobilePhone() {
        System.out.println(DesensitizedUtils.mobilePhone("13579246810"));
    }

    public void testPassword() {
        System.out.println(DesensitizedUtils.password("123qwe"));
    }

    public void testGetJson(){
        UserInfo userInfo = UserInfo.builder().bankCardNo("328812812").password("123434").build();
        //UserInfo userInfo = new UserInfo();
        System.out.println(DesensitizedUtils.getJson(userInfo));
    }


    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class UserInfo{


        @Desensitized(type = SensitiveTypeEnum.CHINESE_NAME)
        private String realName;

        @Desensitized(type = SensitiveTypeEnum.ID_CARD)
        private String idCardNo;

        @Desensitized(type = SensitiveTypeEnum.MOBILE_PHONE)
        private String mobileNo;

        @Desensitized(type = SensitiveTypeEnum.PASSWORD)
        private String password;

        @Desensitized(type = SensitiveTypeEnum.BANK_CARD)
        private String bankCardNo;

        @Desensitized(type = SensitiveTypeEnum.EMAIL)
        private String email;


    }
}