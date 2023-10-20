package com.example.apiffirebase.repository;

import com.example.apiffirebase.genericRepository.FirestoreGenericRepository;
import com.example.apiffirebase.model.Anuncio;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

@Repository
@DependsOn("firebaseInitization")
public class AnuncioRepository extends FirestoreGenericRepository<Anuncio, String> {
    @Override
    protected String getCollectionName() {
        return "Anuncios";
    }
}
