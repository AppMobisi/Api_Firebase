package com.example.apiffirebase.service;

import com.example.apiffirebase.dto.AnuncioDto;
import com.example.apiffirebase.exceptions.BaseHttpException;
import com.example.apiffirebase.exceptions.HttpExceptionHandler;
import com.example.apiffirebase.model.Anunciante;
import com.example.apiffirebase.model.Anuncio;
import com.example.apiffirebase.repository.AnuncianteRepository;
import com.example.apiffirebase.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AnuncioService {
    private final AnuncioRepository anuncioRepository;
    private final AnuncianteRepository anuncianteRepository;

    private  List<Anuncio> anuncios;
    private  List<AnuncioDto> anunciosDtos;

    @Autowired
    public AnuncioService(AnuncioRepository anuncioRepository, AnuncianteRepository anuncianteRepository)
    {
        this.anuncioRepository = anuncioRepository;
        this.anuncianteRepository = anuncianteRepository;
    }

    public List<AnuncioDto> getAnuncios(Optional<Integer> TipoDeficiencia ) throws BaseHttpException {
        try {

            if (TipoDeficiencia.isPresent()){
                 anuncios = anuncioRepository.findByField("iTipoDeficiaId", TipoDeficiencia);
            }else {
                anuncios = anuncioRepository.findAll();
            }

            pegaAnunciantePorAnuncio();
            return anunciosDtos;
        }catch (Exception ex){
            throw HttpExceptionHandler.handleException(ex);
        }
    }


    public List<Anuncio> getByAnunciante(int anuncianteId){
        return anuncioRepository.findByField("iAnuncianteId", anuncianteId);
    }

    public List<AnuncioDto> searchAnuncios(Map<String, Object> criteria) throws BaseHttpException{
        try {
            anuncios =  anuncioRepository.searchByCriteria(criteria);
            pegaAnunciantePorAnuncio();
            return anunciosDtos;
        }catch (Exception ex){
          throw   HttpExceptionHandler.handleException(ex);
        }

    }


    public void pegaAnunciantePorAnuncio() throws BaseHttpException{
        try {
            for (Anuncio anuncio : anuncios) {
                Optional<Anunciante> anuncianteOptional = anuncianteRepository.findById(anuncio.getiAnuncianteId());
                if (anuncianteOptional.isPresent()) {
                    Anunciante anunciante = anuncianteOptional.get();

                    AnuncioDto anuncioDto = new AnuncioDto(anuncio, anunciante);
                    anunciosDtos.add(anuncioDto);
                }
            }
        }catch (Exception ex){
            HttpExceptionHandler.handleException(ex);
        }
    }



}
