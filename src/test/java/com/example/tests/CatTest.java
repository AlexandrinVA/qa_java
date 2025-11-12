package com.example.tests;

import com.example.Cat;
import com.example.Predator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatTest {

    @Mock
    private Predator predator;

    @Test
    @DisplayName("getSound возвращает 'Мяу'")
    void getSoundThenReturnsMeowTest() {
        Cat cat = new Cat(predator);
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    @DisplayName("getFood использует Predator.eatMeat()")
    void getFoodUsePredatorTest() throws Exception {
        when(predator.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        Cat cat = new Cat(predator);

        assertEquals(List.of("Животные", "Птицы", "Рыба"), cat.getFood());
        verify(predator, times(1)).eatMeat();
        verifyNoMoreInteractions(predator);
    }
}