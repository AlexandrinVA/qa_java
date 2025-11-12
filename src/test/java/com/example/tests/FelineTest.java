package com.example.tests;

import com.example.Feline;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FelineTest {

    @Test
    @DisplayName("eatMeat возвращает список еды predator")
    void eatMeatThenReturnsPredatorFoodTest() throws Exception {
        Feline feline = new Feline();
        List<String> food = feline.eatMeat();
        assertEquals(List.of("Животные", "Птицы", "Рыба"), food);
    }

    @Test
    @DisplayName("getFamily возвращает 'Кошачьи'")
    void getFamilyThenReturnsCatsTest() {
        Feline feline = new Feline();
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    @DisplayName("getKittens() без аргументов возвращает 1")
    void getKittensWithNoArgsThenReturnsOneTest() {
        Feline feline = new Feline();
        assertEquals(1, feline.getKittens());
    }

    @ParameterizedTest(name = "getKittens({0}) возвращает одинаковое кол-во")
    @ValueSource(ints = {0, 1, 2, 5, 10})
    void getKittensWithArgsThenReturnsValueTest(int count) {
        Feline feline = new Feline();
        assertEquals(count, feline.getKittens(count));
    }
}
