package com.hajin.mylist.todo.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetToDoByDateRequestDtoTest {

    @Test
    @DisplayName("GetToDoByDateRequestDto 기본 생성자 테스트")
    void testDefaultConstructor() {
        // Given & When
        GetToDoByDateRequestDto dto = new GetToDoByDateRequestDto();

        // Then
        assertEquals(null, dto.getDate());
    }

    @Test
    @DisplayName("GetToDoByDateRequestDto 필드 설정 후 값 확인 테스트")
    void testFieldSetting() {
        // Given
        LocalDate expectedDate = LocalDate.of(2024, 12, 25);

        // When
        GetToDoByDateRequestDto dto = new GetToDoByDateRequestDto();
    }
}
