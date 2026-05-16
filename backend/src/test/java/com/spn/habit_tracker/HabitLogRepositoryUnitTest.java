package com.spn.habit_tracker;

import com.spn.habit_tracker.model.Habit;
import com.spn.habit_tracker.repository.HabitLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HabitLogRepositoryUnitTest {

    @Autowired
    private HabitLogRepository habitLogRepository;

    @Test
    public void testExistsByHabitAndDate_VraćaFalseAkoNePostoji() {
        Habit lažnaNavika = new Habit();
        lažnaNavika.setId(999L);

        boolean postoji = habitLogRepository.existsByHabitAndDate(lažnaNavika, LocalDate.now());
        assertThat(postoji).isFalse();
    }
}