package com.ritam.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Integer categoryId;
    @NotEmpty(message = "Title should not be empty")
    private String categoryTitle;
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 10,max = 1000)
    private String categoryDescription;
}
