package com.auth0.example.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration
public class InterviewControllerTest {
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext controller;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(controller)
                .apply(springSecurity())
                .build();
    }

    @MockBean
    @SuppressWarnings("unused")
    private JwtDecoder jwtDecoder;

    @Test
    @WithMockUser(username = "testUser", authorities = {"SCOPE_read:interviews"})
    public void testInterviewsEndpointReturnsOkWithProperScope() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/interviews"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(username = "testUser", authorities = {"SCOPE_read:messages"})
    public void testInterviewsEndpointReturnsForbiddenWithIncorrectScope() throws Exception {
        mockMvc.perform(get("/api/interviews"))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andReturn();
    }
}
