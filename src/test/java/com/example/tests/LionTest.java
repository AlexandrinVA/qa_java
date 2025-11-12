package com.example.tests;

import com.example.KittensProvider;
import com.example.Lion;
import com.example.Predator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LionTest {

    @Mock
    KittensProvider kittens;

    @Mock
    Predator predator;

    @Test
    @DisplayName("У самца льва есть грива")
    void maleHasManeTest() throws Exception {
        Lion lion = new Lion("Самец", kittens, predator);
        assertTrue(lion.doesHaveMane());
    }

    @Test
    @DisplayName("У самки льва нет гривы")
    void femaleNoManeTest() throws Exception {
        Lion lion = new Lion("Самка", kittens, predator);
        assertFalse(lion.doesHaveMane());
    }

    @ParameterizedTest(name = "Исключение при некорректном значении пола: {0}")
    @ValueSource(strings = {"", "самец", "Лев", "Other", "123"})
    void invalidSexThrowsExceptionTest(String sex) {
        Exception ex = assertThrows(Exception.class, () -> new Lion(sex, kittens, predator));
        assertTrue(ex.getMessage().contains("Используйте допустимые значения"));
    }

    @Test
    @DisplayName("getKittens использует KittensProvider")
    void getKittensUsesProviderTest() throws Exception {
        when(kittens.getKittens()).thenReturn(3);

        Lion lion = new Lion("Самец", kittens, predator);
        assertEquals(3, lion.getKittens());
        verify(kittens, times(1)).getKittens();
        verifyNoMoreInteractions(kittens);
    }

    @Test
    @DisplayName("getFood использует Predator.eatMeat()")
    void getFoodUsesPredatorTest() throws Exception {
        when(predator.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));

        Lion lion = new Lion("Самец", kittens, predator);
        assertEquals(List.of("Животные", "Птицы", "Рыба"), lion.getFood());
        verify(predator, times(1)).eatMeat();
        verifyNoMoreInteractions(predator);
    }
}
