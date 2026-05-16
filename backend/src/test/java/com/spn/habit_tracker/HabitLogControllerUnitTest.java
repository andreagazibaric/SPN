package com.spn.habit_tracker;

import com.spn.habit_tracker.controller.HabitLogController;
import com.spn.habit_tracker.repository.HabitLogRepository;
import com.spn.habit_tracker.service.HabitLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HabitLogController.class) // Testira isključivo web kontroler sloj
public class HabitLogControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HabitLogService habitLogService;

    @MockBean
    private HabitLogRepository habitLogRepository;

    @Test
    public void testGetAllLogs_VraćaStatusOk() throws Exception {
        mockMvc.perform(get("/api/logs"))
                .andExpect(status().isOk());
    }
}