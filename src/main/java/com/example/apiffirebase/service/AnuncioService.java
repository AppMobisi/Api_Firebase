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
    private final AnuncioFavoritoService anuncioFavoritoService;

    private  List<Anuncio> anuncios = new ArrayList<>();
    private  List<AnuncioDto> anunciosDtos = new ArrayList<>();

    @Autowired
    public AnuncioService(AnuncioRepository anuncioRepository, AnuncianteRepository anuncianteRepository, AnuncioFavoritoService anuncioFavoritoService)
    {
        this.anuncioRepository = anuncioRepository;
        this.anuncianteRepository = anuncianteRepository;
        this.anuncioFavoritoService = anuncioFavoritoService;
    }

    public List<AnuncioDto> getAnuncios(Integer iUsuarioId,Optional<Integer> TipoDeficiencia ) throws BaseHttpException {
        try {
            Limpar();

            if (TipoDeficiencia.isPresent()){
                Integer teste = TipoDeficiencia.get();
                 anuncios = anuncioRepository.findByField("iTipoDeficiencia", teste);
            }else {
                anuncios = anuncioRepository.findAll();
            }

            pegaAnunciantePorAnuncio(iUsuarioId);
            return anunciosDtos;
        }catch (Exception ex){
            throw HttpExceptionHandler.handleException(ex);
        }
    }





    public List<AnuncioDto> searchAnuncios(Integer iUsuarioId,Map<String, Object> criteria) throws BaseHttpException{
        try {
            Limpar();
            anuncios =  anuncioRepository.searchByCriteria(criteria);
            pegaAnunciantePorAnuncio(iUsuarioId);
            return anunciosDtos;
        }catch (Exception ex){
          throw   HttpExceptionHandler.handleException(ex);
        }

    }


    public void pegaAnunciantePorAnuncio(Integer iUsuarioId) throws BaseHttpException{
        try {
            Limpar();
            for (Anuncio anuncio : anuncios) {
                List<Anunciante> retorno = anuncianteRepository.findByField("iUsuarioId",anuncio.getiAnuncianteId());
                Anunciante anunciante = retorno.get(0);
                boolean favorito = anuncioFavoritoService.verificaFavorito(iUsuarioId, anuncio.id);
                AnuncioDto anuncioDto = new AnuncioDto(anuncio, anunciante, favorito);
                anunciosDtos.add(anuncioDto);

            }
        }catch (Exception ex){
            HttpExceptionHandler.handleException(ex);
        }
    }

    public Anuncio salvar(Anuncio anuncio) throws BaseHttpException{
        try{
            anuncio.setcDescricao(anuncio.getcDescricao().toLowerCase());
            anuncio.setcTitulo(anuncio.getcTitulo().toLowerCase());
            return anuncioRepository.save(anuncio);
        }catch (Exception ex){
           throw  HttpExceptionHandler.handleException(ex);
        }
    }

    public Optional<Anuncio> getById(String id) throws  BaseHttpException{
        try {
            return anuncioRepository.findById(id);

        }catch (Exception ex){
            throw HttpExceptionHandler.handleException(ex);
        }
    }

    public boolean deleteById(String id) throws BaseHttpException{
        try{
            anuncioRepository.deleteById(id);
            Optional<Anuncio> anuncio = anuncioRepository.findById(id);
            if (anuncio.isPresent()){
                return true;
            }
            return false;

        }catch (Exception ex){
            throw HttpExceptionHandler.handleException(ex);
        }
    }


    public void Limpar(){
        anunciosDtos.clear();
    }
}
