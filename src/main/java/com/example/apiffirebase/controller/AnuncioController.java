package com.example.apiffirebase.controller;

import com.example.apiffirebase.dto.AnuncioDto;
import com.example.apiffirebase.exceptions.BaseHttpException;
import com.example.apiffirebase.model.Anuncio;
import com.example.apiffirebase.responses.ApiResponse;
import com.example.apiffirebase.responses.DefaultResponse;
import com.example.apiffirebase.responses.ErrorResponse;
import com.example.apiffirebase.service.AnuncioService;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.google.protobuf.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {
    @Autowired
    AnuncioService anuncioService;

    @GetMapping("/getAnuncios[/{iTipoDeficienciaId}]")
    public ResponseEntity<ApiResponse> getByDeficiencia(@PathVariable int iTipoDeficienciaId) throws BaseHttpException {
        try {
            List<AnuncioDto> anuncios = anuncioService.getAnuncios(Optional.of(iTipoDeficienciaId));
            if (!anuncios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new DefaultResponse<>(200, anuncios));
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404,
                            "Não conseguimos achar algum anuncio com esse tipo de deficiencia"));

        } catch (BaseHttpException ex){
            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorResponse(ex.getStatusCode(), ex.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchAnuncios(
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) Double preco,
            @RequestParam(required = false) String titulo) throws BaseHttpException {
        try {
            Map<String, Object> criteria = new HashMap<>();
            if (descricao != null) criteria.put("cDescricao", descricao);
            if (preco != null) criteria.put("cPreco", preco);
            if (titulo != null) criteria.put("cTitulo", titulo);

            List<AnuncioDto> results = anuncioService.searchAnuncios(criteria);

            if (!results.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new DefaultResponse<>(200, results));
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(404,
                            "Não achamos o que você quer pesquisar"));

        }catch (BaseHttpException ex){
            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorResponse(ex.getStatusCode(), ex.getMessage()));
        }
    }

}
