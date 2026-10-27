package eni.ecole.eadl_hexa_demo.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateBookRequest(
        @NotBlank(message = "Le titre est obligatoire")
        String title
) {
}
