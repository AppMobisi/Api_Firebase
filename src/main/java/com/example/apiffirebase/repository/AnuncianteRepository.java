package com.example.apiffirebase.repository;

import com.example.apiffirebase.genericRepository.FirestoreGenericRepository;
import com.example.apiffirebase.model.Anunciante;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AnuncianteRepository extends FirestoreGenericRepository<Anunciante, String> {

    @Override
    protected String getCollectionName() {
        return "Anunciates";
    }


}
