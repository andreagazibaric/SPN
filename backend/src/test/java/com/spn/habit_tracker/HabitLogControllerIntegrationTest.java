package com.spn.habit_tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spn.habit_tracker.model.Habit;
import com.spn.habit_tracker.model.HabitLog;
import com.spn.habit_tracker.repository.HabitRepository;
import com.spn.habit_tracker.repository.HabitLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // Ovo osigurava da se baza očisti nakon svakog testa!
public class HabitLogControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private HabitLogRepository habitLogRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSlozenaValidacijaDuplogUnosa() throws Exception {
        // 1. Priprema: Stvaramo i spremamo testnu naviku u bazu
        Habit testHabit = new Habit();
        testHabit.setTitle("Meditacija");
        testHabit.setDescription("10 minuta");
        testHabit = habitRepository.save(testHabit);

        // 2. Priprema: Stvaramo prvi log za današnji datum
        HabitLog firstLog = new HabitLog();
        firstLog.setDate(LocalDate.now());
        firstLog.setCompleted(true);
        firstLog.setHabit(testHabit);

        // Šaljemo prvi POST zahtjev - ovo mora proći (200 OK)
        mockMvc.perform(post("/api/logs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(firstLog)))
                .andExpect(status().isOk());

        // 3. Test: Pokušavamo poslati POTPUNO ISTI log ponovno
        mockMvc.perform(post("/api/logs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(firstLog)))
                .andExpect(status().isBadRequest()) // Očekujemo grešku 400
                .andExpect(content().string("Navika je već logirana za ovaj datum!")); // Očekujemo našu poruku
    }
}