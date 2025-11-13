package com.example.tests;

import com.example.Feline;
import com.example.Lion;
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
    private Feline feline;

    @Test
    @DisplayName("У самца льва есть грива")
    void maleHasManeTest() throws Exception {
        Lion lion = new Lion("Самец", feline);
        assertTrue(lion.doesHaveMane());
    }

    @Test
    @DisplayName("У самки льва нет гривы")
    void femaleNoManeTest() throws Exception {
        Lion lion = new Lion("Самка", feline);
        assertFalse(lion.doesHaveMane());
    }

    @ParameterizedTest(name = "Исключение при некорректном значении пола: {0}")
    @ValueSource(strings = {"", "самец", "Лев", "Other", "123"})
    void invalidSexThrowsExceptionTest(String sex) {
        Exception ex = assertThrows(Exception.class, () -> new Lion(sex, feline));
        assertTrue(ex.getMessage().contains("Используйте допустимые значения"));
    }

    @Test
    @DisplayName("getKittens использует KittensProvider")
    void getKittensUsesProviderTest() throws Exception {
        when(feline.getKittens()).thenReturn(3);

        Lion lion = new Lion("Самец", feline);
        assertEquals(3, lion.getKittens());
        verify(feline, times(1)).getKittens();
        verifyNoMoreInteractions(feline);
    }

    @Test
    @DisplayName("getFood использует Predator.eatMeat()")
    void getFoodUsesPredatorTest() throws Exception {
        when(feline.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));

        Lion lion = new Lion("Самец", feline);
        assertEquals(List.of("Животные", "Птицы", "Рыба"), lion.getFood());
        verify(feline, times(1)).eatMeat();
        verifyNoMoreInteractions(feline);
    }
}
