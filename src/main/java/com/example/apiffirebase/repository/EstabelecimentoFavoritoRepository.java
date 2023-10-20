package com.example.apiffirebase.repository;

import com.example.apiffirebase.genericRepository.FirestoreGenericRepository;
import com.example.apiffirebase.genericRepository.GenericRepository;
import com.example.apiffirebase.model.EstabelecimentoFavorito;
import org.springframework.aot.generate.GeneratedClasses;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

@Repository
@DependsOn("firebaseInitization")
public class EstabelecimentoFavoritoRepository extends FirestoreGenericRepository<EstabelecimentoFavorito, String> {
    @Override
    protected String getCollectionName() {
        return "estabelecimentosFavoritos";
    }
}
