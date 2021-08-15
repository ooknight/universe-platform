package test.io.github.ooknight.universe.core.client.web;

import io.github.ooknight.universe.core.client.web.WebAutoConfiguration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

@WebMvcTest
@ContextConfiguration(classes = WebAutoConfiguration.class)
@ComponentScan
public class Run {

    @Resource
    private MockMvc mvc;

    @Test
    public void test1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/demo")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("ok")).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void test2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/demo").param("p", "2018-01-02 03:04:05")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("parameter is 2018-01-02 03:04:05")).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testWithI18n() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/demo").param("locale", "en").param("key", "demo")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("demo")).andDo(MockMvcResultHandlers.print());
        mvc.perform(MockMvcRequestBuilders.get("/demo").param("locale", "zh").param("key", "demo")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("演示")).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testPage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/page")
            //.param("page", "9")
            //.param("size", "90")
        ).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("ok")).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testNoise() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/noise")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}
