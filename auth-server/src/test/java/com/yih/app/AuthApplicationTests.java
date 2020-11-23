package com.yih.app;

import com.yih.auth.AuthApplication;
import com.yih.auth.ctl.AppUserCtl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;

@SpringBootTest(classes = AuthApplication.class)
@WebAppConfiguration
public class AuthApplicationTests {

    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void contextLoads() {
        System.out.println("hello world");
    }

    @Test
    public void registerUserTest() throws Exception {
        AppUserCtl.RegisterUser user = new AppUserCtl.RegisterUser();
        user.setUsername("ddd");
        user.setPassword("ddd");
        mvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtil.convertObjectToJsonBytes(user))
        ).andExpect(status().isOk());
    }
}

