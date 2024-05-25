package com.finance.app.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchRequest {
    @NotEmpty(message = "Search query cannot be empty.")
    @NotBlank(message = "Search query cannot be blank.")
    private String query;

    // TODO: Search Filter functionality to be implemented
}
