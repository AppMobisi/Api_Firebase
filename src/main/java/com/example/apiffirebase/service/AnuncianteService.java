package com.example.apiffirebase.service;

import com.example.apiffirebase.dto.AnuncioDto;
import com.example.apiffirebase.exceptions.BaseHttpException;
import com.example.apiffirebase.exceptions.HttpExceptionHandler;
import com.example.apiffirebase.model.Anunciante;
import com.example.apiffirebase.model.Anuncio;
import com.example.apiffirebase.repository.AnuncianteRepository;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpIOExceptionHandler;
import com.google.cloud.http.BaseHttpServiceException;
import jdk.jshell.spi.ExecutionControl;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnuncianteService {
    private final AnuncianteRepository anuncianteRepository;

    @Autowired
    public AnuncianteService(AnuncianteRepository anuncianteRepository) {
        this.anuncianteRepository = anuncianteRepository;
    }

    public Optional<Anunciante> getInformacoes(int AnuncianteId) throws BaseHttpException {
        try {
            return anuncianteRepository.findById(AnuncianteId);
        }catch (Exception  ex){
           throw  HttpExceptionHandler.handleException(ex);
        }

    }

    public Anunciante salvar(Anunciante anunciante) throws  BaseHttpException{
        try {
            Anunciante anunciante1 =  anuncianteRepository.save(anunciante);
            return anunciante1;
        } catch (Exception ex){
            throw  HttpExceptionHandler.handleException(ex);
        }
    }
}
