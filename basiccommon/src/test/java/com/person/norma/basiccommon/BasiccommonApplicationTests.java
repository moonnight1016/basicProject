package com.person.norma.basiccommon;

import com.person.norma.basiccommon.gererator.config.DBConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BasiccommonApplicationTests {

    @Autowired
    private DBConfig config;

    @Test
    void contextLoads() {
        System.out.println("sa");
    }

}
