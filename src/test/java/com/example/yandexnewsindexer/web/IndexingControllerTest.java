package com.example.yandexnewsindexer.web;

import com.example.yandexnewsindexer.controller.IndexingController;
import com.example.yandexnewsindexer.security.JwtFilter;
import com.example.yandexnewsindexer.security.JwtUtils;
import com.example.yandexnewsindexer.service.IndexingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IndexingController.class)
@AutoConfigureMockMvc(addFilters = false)
class IndexingControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    IndexingService indexingService;

    @MockitoBean
    JwtUtils jwtUtils;

    @MockitoBean
    JwtFilter jwtFilter;

    @Test
    void index_returns200() throws Exception {
        String url = "https://example.com";
        Mockito.doNothing().when(indexingService).indexUrl(url);

        mvc.perform(get("/api/index").param("url", url))
                .andExpect(status().isOk());

        Mockito.verify(indexingService).indexUrl(url);
    }
}
