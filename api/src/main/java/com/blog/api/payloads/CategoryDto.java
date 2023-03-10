package com.blog.api.payloads;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;
    @NotBlank
    @Size(min = 4, message = "Min size should be at least 4")
    private String categoryTitle;
    @NotBlank
    @Size(min = 10, message = "Min size should be at least 10")
    private String categoryDescription;
}
