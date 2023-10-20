package com.example.apiffirebase.controller;

import com.example.apiffirebase.dto.AnuncioDto;
import com.example.apiffirebase.exceptions.BaseHttpException;
import com.example.apiffirebase.model.AnuncioFavorito;
import com.example.apiffirebase.responses.ApiResponse;
import com.example.apiffirebase.responses.DefaultResponse;
import com.example.apiffirebase.responses.ErrorResponse;
import com.example.apiffirebase.service.AnuncioFavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class AnuncioFavoritoController {

    private final AnuncioFavoritoService anuncioFavoritoService;

    @Autowired
    public AnuncioFavoritoController(AnuncioFavoritoService anuncioFavoritoService){
        this.anuncioFavoritoService = anuncioFavoritoService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<ApiResponse> FavoritarAnuncio(@RequestBody AnuncioFavorito anuncioFavorito){

        try{

            AnuncioFavorito retorno = anuncioFavoritoService.salvar(anuncioFavorito);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new DefaultResponse<>(200, retorno));

        }catch (BaseHttpException ex){
            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorResponse(ex.getStatusCode(), ex.getMessage()));
        }
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<ApiResponse> DesfavoritarAnuncio(@RequestParam Integer iUsuarioId, @RequestParam String cAnuncioId){
        try{

            HashMap<String, Object> criterios = new HashMap<>();
            criterios.put("iUsuarioId", iUsuarioId);
            criterios.put("cAnuncioId", cAnuncioId);

            boolean retorno = anuncioFavoritoService.desfavoritar(criterios);

            if (retorno){
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new DefaultResponse<>(204, "Desfavoritado com sucesso"));
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DefaultResponse<>(404, "Algo deu errado ao tentar desfavoritar"));

        } catch(BaseHttpException ex){
            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorResponse(ex.getStatusCode(), ex.getMessage()));
        }
    }

    @GetMapping("/getByUsuario/{iUsuarioId}")
    public ResponseEntity<ApiResponse> getFavoritosByUsuario(@PathVariable Integer iUsuarioId){
        try {
            List<AnuncioDto> anuncios = anuncioFavoritoService.getByUsu(iUsuarioId);

            if (anuncios.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new DefaultResponse<>(404, "Algo de errado ao tentar achar os favoritos desse usuário"));
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new DefaultResponse<>(200, anuncios));

        }catch(BaseHttpException ex){
            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorResponse(ex.getStatusCode(), ex.getMessage()));
        }
    }
}
