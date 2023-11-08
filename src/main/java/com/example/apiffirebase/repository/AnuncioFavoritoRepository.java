package com.example.apiffirebase.repository;

import com.example.apiffirebase.genericRepository.FirestoreGenericRepository;
import com.example.apiffirebase.model.AnuncioFavorito;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

@Repository
@DependsOn("firebaseInitization")
public class AnuncioFavoritoRepository extends FirestoreGenericRepository<AnuncioFavorito, String> {
    @Override
    protected String getCollectionName() {
        return "anunciosFavoritos";
    }

}
