package com.orlando.quesadillacloud.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FSTest {
    
    @Test
    public void lsTest() {
        System.out.println(FS.ls("/foobar"));
    }

}