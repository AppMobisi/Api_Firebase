package com.example.apiffirebase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioSaveDto {

    @NotBlank(message = "Invalid foto: Empty foto")
    @NotNull(message = "Invalid foto: foto is NULL")
    private String foto;

    @NotBlank(message = "Invalid titulo: Empty titulo")
    @NotNull(message = "Invalid titulo: titulo is NULL")
    private String titulo;

    @NotBlank(message = "Invalid descricao: Empty descricao")
    @NotNull(message = "Invalid descricao: descricao is NULL")
    private String descricao;

    @NotNull(message = "Invalid preco: preco is NULL")
    private Double preco;

    @NotNull(message = "Invalid tipoDeficienciaId: tipoDeficienciaId is NULL")
    private Integer tipoDeficienciaId;

    @NotNull(message = "Invalid anuncianteId: anuncianteId is NULL")
    private Integer anuncianteId;

}
