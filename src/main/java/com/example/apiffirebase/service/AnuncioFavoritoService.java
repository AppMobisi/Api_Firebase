package com.example.apiffirebase.service;

import com.example.apiffirebase.dto.AnuncianteDto;
import com.example.apiffirebase.dto.AnuncioDto;
import com.example.apiffirebase.exceptions.BaseHttpException;
import com.example.apiffirebase.exceptions.HttpExceptionHandler;
import com.example.apiffirebase.model.Anunciante;
import com.example.apiffirebase.model.Anuncio;
import com.example.apiffirebase.model.AnuncioFavorito;
import com.example.apiffirebase.repository.AnuncianteRepository;
import com.example.apiffirebase.repository.AnuncioFavoritoRepository;
import com.example.apiffirebase.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnuncioFavoritoService {

    private final AnuncioFavoritoRepository anuncioFavoritoRepository;
    private final AnuncioRepository anuncioRepository;
    private final AnuncianteRepository anuncianteRepository;

    @Autowired
    public AnuncioFavoritoService(AnuncioFavoritoRepository anuncioFavoritoRepository, AnuncioRepository anuncioRepository, AnuncianteRepository anuncianteRepository){
        this.anuncioFavoritoRepository = anuncioFavoritoRepository;
        this.anuncioRepository = anuncioRepository;
        this.anuncianteRepository = anuncianteRepository;
    }


    public AnuncioFavorito salvar(AnuncioFavorito anuncioFavorito) throws BaseHttpException {
        try {
            AnuncioFavorito anuncioFavorito1 = anuncioFavoritoRepository.save(anuncioFavorito);
            return anuncioFavorito1;
        } catch (Exception ex){
            throw HttpExceptionHandler.handleException(ex);
        }

    }

    public boolean desfavoritar(HashMap<String, Object> criterios) throws BaseHttpException{
        try {
           int desfavoritou =  anuncioFavoritoRepository.deleteByFields(criterios);

           if (desfavoritou == 0){
               return false;
           } else{
               return true;
           }
        } catch (Exception ex){
            throw HttpExceptionHandler.handleException(ex);
        }
    }

    public List<AnuncioDto> getByUsu(Integer iUsuarioId) throws BaseHttpException{
        try {
            List<AnuncioDto> anuncios = new ArrayList<>();
            List<AnuncioFavorito> anuncioFavoritos = anuncioFavoritoRepository.findByField("iUsuarioId", iUsuarioId);
            for (AnuncioFavorito anuncioFavorito : anuncioFavoritos){
                Optional<Anuncio> anuncioOptional = anuncioRepository.findById(anuncioFavorito.getcAnuncioId());
                if (anuncioOptional.isPresent()) {
                    Anuncio anuncio = anuncioOptional.get();
                    List<Anunciante> retorno  = anuncianteRepository.findByField("iUsuarioId",anuncio.getiAnuncianteId());
                    Anunciante anunciante = retorno.get(0);
                    AnuncioDto anuncioDto = new AnuncioDto(anuncio, anunciante, true);
                    anuncios.add(anuncioDto);
                }
            }
            return anuncios;
        } catch (Exception ex){
           throw  HttpExceptionHandler.handleException(ex);
        }
    }

    public boolean verificaFavorito(Integer iUsuarioId, String anuncioId){
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("iUsuarioId", iUsuarioId);
            map.put("cAnuncioId", anuncioId);
            List<AnuncioFavorito> anuncios = anuncioFavoritoRepository.searchByCriteriaFavoritos(map);
            if (!anuncios.isEmpty()){
                return true;
            }
            return false;
        }catch (Exception ex){
            return false;
        }
    }
}
