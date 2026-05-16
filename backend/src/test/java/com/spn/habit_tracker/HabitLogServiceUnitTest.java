package com.spn.habit_tracker;

import com.spn.habit_tracker.model.Habit;
import com.spn.habit_tracker.model.HabitLog;
import com.spn.habit_tracker.repository.HabitLogRepository;
import com.spn.habit_tracker.service.HabitLogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Testira čisti servis bez dizanja Springa (brzo)
public class HabitLogServiceUnitTest {

    @Mock
    private HabitLogRepository habitLogRepository;

    @InjectMocks
    private HabitLogService habitLogService;

    @Test
    public void testSaveLog_BacaIznimkuZaDatumUBudućnosti() {
        HabitLog buduciLog = new HabitLog();
        buduciLog.setDate(LocalDate.now().plusDays(1)); // Sutra

        assertThrows(IllegalArgumentException.class, () -> {
            habitLogService.saveLog(buduciLog);
        });
    }
}