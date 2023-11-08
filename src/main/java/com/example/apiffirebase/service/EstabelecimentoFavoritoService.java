package com.example.apiffirebase.service;

import com.example.apiffirebase.exceptions.BaseHttpException;
import com.example.apiffirebase.exceptions.HttpExceptionHandler;
import com.example.apiffirebase.model.AnuncioFavorito;
import com.example.apiffirebase.model.EstabelecimentoFavorito;
import com.example.apiffirebase.repository.EstabelecimentoFavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    public boolean desfavoritar(String cId) throws BaseHttpException{
        try {
            int desfavoritou = 0;
            estabelecimentoFavoritoRepository.deleteById(cId);
            Optional<EstabelecimentoFavorito> estabelecimentoFavorito = estabelecimentoFavoritoRepository.findById(cId);

            if (!estabelecimentoFavorito.isPresent()){
                desfavoritou = 1;
            }

            if (desfavoritou == 0){
                return false;
            } else{
                return true;
            }
        } catch (Exception ex){
            throw HttpExceptionHandler.handleException(ex);
        }
    }

    public List<EstabelecimentoFavorito> getFavoritos(Integer id) throws BaseHttpException{
        try {
            List<EstabelecimentoFavorito> estabelecimentoFavoritos = estabelecimentoFavoritoRepository.findByField("iUsuarioId", id);
            return estabelecimentoFavoritos;
        }catch (Exception ex){
            throw HttpExceptionHandler.handleException(ex);
        }
    }


}
