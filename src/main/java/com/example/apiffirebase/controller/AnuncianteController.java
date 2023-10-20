package com.example.apiffirebase.controller;

import com.example.apiffirebase.dto.AnuncianteDto;
import com.example.apiffirebase.dto.AnuncioDto;
import com.example.apiffirebase.exceptions.BaseHttpException;
import com.example.apiffirebase.model.Anunciante;
import com.example.apiffirebase.responses.ApiResponse;
import com.example.apiffirebase.responses.DefaultResponse;
import com.example.apiffirebase.responses.ErrorResponse;
import com.example.apiffirebase.service.AnuncianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anunciantes")
public class AnuncianteController {
    private final AnuncianteService anuncianteService;

    @Autowired
    public AnuncianteController(AnuncianteService anuncianteService){
        this.anuncianteService = anuncianteService;
    }


    @PostMapping("/salvar")
    public ResponseEntity<ApiResponse> salvar(@RequestBody
                                              @Valid
                                              AnuncianteDto anuncianteDto){

        try {
            Anunciante anunciante = new Anunciante(anuncianteDto.getId(),
                    anuncianteDto.getNome(),
                    anuncianteDto.getTelefone(),
                    anuncianteDto.getEmail(),
                    anuncianteDto.getCep(),
                    anuncianteDto.getCpf(),
                    anuncianteDto.getNota()
            );

            Anunciante retorno = anuncianteService.salvar(anunciante);

            return  ResponseEntity.status(HttpStatus.OK)
                    .body(new DefaultResponse<>(200, retorno));
        }catch (BaseHttpException ex){
            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorResponse(ex.getStatusCode(), ex.getMessage()));
        }
    }

}
