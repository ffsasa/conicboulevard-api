package com.example.formapi;

import com.example.formapi.controller.AccountController;
import com.example.formapi.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class FormRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService formService;

//    @Test
//    void shouldReturnOk() throws Exception {
//        when(formService.getAll()).thenReturn(java.util.List.of());
//        mockMvc.perform(get("/forms").contentType(MediaType.APPLICATION_JSON))
//               .andExpect(status().isOk());
//    }
}
