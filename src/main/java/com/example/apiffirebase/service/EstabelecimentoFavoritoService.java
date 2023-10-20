package com.example.apiffirebase.service;

import com.example.apiffirebase.exceptions.BaseHttpException;
import com.example.apiffirebase.exceptions.HttpExceptionHandler;
import com.example.apiffirebase.model.AnuncioFavorito;
import com.example.apiffirebase.model.EstabelecimentoFavorito;
import com.example.apiffirebase.repository.EstabelecimentoFavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class EstabelecimentoFavoritoService {
    private final EstabelecimentoFavoritoRepository estabelecimentoFavoritoRepository;

    @Autowired
    public EstabelecimentoFavoritoService(EstabelecimentoFavoritoRepository estabelecimentoFavoritoRepository){
        this.estabelecimentoFavoritoRepository =  estabelecimentoFavoritoRepository;
    }


    public EstabelecimentoFavorito salvar(EstabelecimentoFavorito estabelecimentoFavorito) throws BaseHttpException {
        try {
            EstabelecimentoFavorito estabelecimentoFavorito1 = estabelecimentoFavoritoRepository.save(estabelecimentoFavorito);
            return estabelecimentoFavorito1;
        } catch (Exception ex){
            throw HttpExceptionHandler.handleException(ex);
        }

    }

    public boolean desfavoritar(HashMap<String, Object> criterios) throws BaseHttpException{
        try {
            int desfavoritou =  estabelecimentoFavoritoRepository.deleteByFields(criterios);

            if (desfavoritou == 0){
                return false;
            } else{
                return true;
            }
        } catch (Exception ex){
            throw HttpExceptionHandler.handleException(ex);
        }
    }


}
