package com.example.apiffirebase.controller;

import com.example.apiffirebase.dto.AnuncioDto;
import com.example.apiffirebase.dto.AnuncioSaveDto;
import com.example.apiffirebase.exceptions.BaseHttpException;
import com.example.apiffirebase.model.Anuncio;
import com.example.apiffirebase.repository.AnuncioRepository;
import com.example.apiffirebase.responses.ApiResponse;
import com.example.apiffirebase.responses.DefaultResponse;
import com.example.apiffirebase.responses.ErrorResponse;
import com.example.apiffirebase.service.AnuncioService;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.google.protobuf.Api;
import jakarta.validation.Valid;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    private final AnuncioService anuncioService;
    @Autowired
    public AnuncioController(AnuncioService anuncioService){
        this.anuncioService = anuncioService;
    }

    @GetMapping("/getAnuncios")
    public ResponseEntity<ApiResponse> getByDeficiencia(@RequestParam Integer iUsuarioId, @RequestParam(required = false) Integer iTipoDeficienciaId) throws BaseHttpException {
        try {
            List<AnuncioDto> anuncios = anuncioService.getAnuncios(iUsuarioId,Optional.ofNullable(iTipoDeficienciaId));
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
            @RequestParam Integer iUsuarioId,
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) Double preco,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Integer anuncianteId) throws BaseHttpException {
        try {
            Map<String, Object> criteria = new HashMap<>();
            if (descricao != null) criteria.put("cDescricao", descricao);
            if (preco != null) criteria.put("cPreco", preco);
            if (titulo != null) criteria.put("cTitulo", titulo);
            if (anuncianteId != null) criteria.put("iAnuncianteId", anuncianteId);

            List<AnuncioDto> results = anuncioService.searchAnuncios(iUsuarioId, criteria);

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

    @PostMapping("/salvar")
    public ResponseEntity<ApiResponse> salvar(@RequestBody @Valid AnuncioSaveDto anuncioSaveDto){
        try {
            Anuncio anuncio = new Anuncio(
                    anuncioSaveDto.getFoto(),
                    anuncioSaveDto.getTitulo(),
                    anuncioSaveDto.getDescricao(),
                    anuncioSaveDto.getPreco(),
                    anuncioSaveDto.getTipoDeficienciaId(),
                    anuncioSaveDto.getAnuncianteId()
            );

            Anuncio retorno = anuncioService.salvar(anuncio);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new DefaultResponse<>(200, retorno));
        }catch (BaseHttpException ex){
            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorResponse(ex.getStatusCode(), ex.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ApiResponse> deletar(String id){
        try {
            boolean deletou = anuncioService.deleteById(id);
            if (deletou){
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new DefaultResponse<>(204, "Deletado com sucesso"));
            }
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DefaultResponse<>(404, "Algo deu errado"));
        }catch (BaseHttpException ex){
            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorResponse(ex.getStatusCode(), ex.getMessage()));
        }
    }

}
