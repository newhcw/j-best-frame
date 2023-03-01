package com.j.best.user.support;


import com.j.best.user.application.service.ThreadPoolMonitor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringMainTest {


    @Autowired
    private ThreadPoolMonitor threadPoolMonitor;

    @Test
    public void testPromethus(){
        threadPoolMonitor.execute();;
    }
}
