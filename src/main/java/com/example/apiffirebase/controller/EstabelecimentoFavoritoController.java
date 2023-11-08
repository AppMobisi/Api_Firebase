package com.example.apiffirebase.controller;

import com.example.apiffirebase.exceptions.BaseHttpException;
import com.example.apiffirebase.model.EstabelecimentoFavorito;
import com.example.apiffirebase.responses.ApiResponse;
import com.example.apiffirebase.responses.DefaultResponse;
import com.example.apiffirebase.responses.ErrorResponse;
import com.example.apiffirebase.service.EstabelecimentoFavoritoService;
import com.google.rpc.context.AttributeContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoFavoritoController {

    private final EstabelecimentoFavoritoService estabelecimentoFavoritoService;

    @Autowired
    public EstabelecimentoFavoritoController(EstabelecimentoFavoritoService estabelecimentoFavoritoService) {
        this.estabelecimentoFavoritoService = estabelecimentoFavoritoService;
    }

    @PostMapping("/favoritar")
    public ResponseEntity<ApiResponse> favoritar( @RequestBody
            EstabelecimentoFavorito estabelecimentoFavorito){

        try{
            EstabelecimentoFavorito retorno =  estabelecimentoFavoritoService.salvar(estabelecimentoFavorito);

            return  ResponseEntity.status(HttpStatus.OK)
                    .body(new DefaultResponse<>(200, retorno));

        }catch (BaseHttpException ex){
            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorResponse(ex.getStatusCode(), ex.getMessage()));
        }
    }

    @DeleteMapping("/desfavoritar")
    public ResponseEntity<ApiResponse> Desfavoritar(@RequestParam Integer iUsuarioId,
                                                    @RequestParam Integer iEstabelecimentoId){
        try{

            HashMap<String, Object> map = new HashMap<>();
            map.put("iUsuarioId", iUsuarioId);
            map.put("iEstabelecimentoId", iEstabelecimentoId);

            boolean retorno = estabelecimentoFavoritoService.desfavoritar(map);

            if (retorno){
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new DefaultResponse<>(204, "Estabelecimento desfavoritado com sucesso"));
            }

            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DefaultResponse<>(404, "Estabelecimento não encontrado"));

        }catch (BaseHttpException ex){
            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorResponse(ex.getStatusCode(), ex.getMessage()));
        }
    }

    @GetMapping("/favoritos/{id}")
    public ResponseEntity<ApiResponse> GetFavoritosByUsuario(@PathVariable Integer id){
        try{
            List<EstabelecimentoFavorito> estabelecimentos = estabelecimentoFavoritoService.getFavoritos(id);

            if (estabelecimentos.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new DefaultResponse<>(404, "Não existe nenhum estabelecimento favoritado"));
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new DefaultResponse<>(200, estabelecimentos));

        }catch (BaseHttpException ex){
            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorResponse(ex.getStatusCode(), ex.getMessage()));
        }
    }

}
