package com.rrx.kaoqins;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author JQQ
 * @Date 2019/3/4 11:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EncryptTest {

    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void encryptPwd() {
        String pwd = "JDB2017?";
        String result = stringEncryptor.encrypt(pwd);
        String pwd2 =  stringEncryptor.decrypt(result);
        System.out.println(result+"|"+pwd2);
    }

}
