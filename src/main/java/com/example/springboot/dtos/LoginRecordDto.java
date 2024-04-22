package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRecordDto(@NotBlank String email, @NotBlank String password) {
}


