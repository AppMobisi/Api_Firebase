package com.example.apiffirebase.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnuncianteDto {

    @NotNull(message = "Invalid id: id is NULL")
    private Integer id;
    @NotBlank(message = "Invalid nome: Empty nome")
    @NotNull(message = "Invalid nome: nome is NULL")
    private String nome;
    @NotBlank(message = "Invalid email: Empty email")
    @NotNull(message = "Invalid email: email is NULL")
    @Email(message = "Invalid email: email is not a valid email")
    private String email;
    @NotBlank(message = "Invalid telefone: Empty telefone")
    @NotNull(message = "Invalid telefone: telefone is NULL")
    private String telefone;
    @NotBlank(message = "Invalid CPF: Empty CPF")
    @NotNull(message = "Invalid CPF: CPF is NULL")
    private String cpf;
    @NotBlank(message = "Invalid cep: Empty cep")
    @NotNull(message = "Invalid cep: cep is NULL")
    private String cep;
    @NotNull(message = "Invalid nota: nota is NULL")
    @PositiveOrZero(message = "Invalid nota: nota must be a positive or zero value")
    private Double nota;


}
