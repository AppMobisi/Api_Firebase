package com.example.apiffirebase.genericRepository;

import com.example.apiffirebase.firebase.Config;
import com.google.cloud.firestore.*;
import org.springframework.core.GenericTypeResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public abstract class FirestoreGenericRepository<T, ID> implements GenericRepository<T,ID>{
    private final Class<T> entityClass;
    protected Firestore firestore = Config.getDatabase();

    protected FirestoreGenericRepository() {
        this.entityClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), FirestoreGenericRepository.class);
    }

    protected abstract String getCollectionName();

    private CollectionReference getCollection() {
        return firestore.collection(getCollectionName());
    }

    @Override
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();
        try {
            getCollection().get().get().forEach(queryDocumentSnapshot -> {
                entities.add(queryDocumentSnapshot.toObject(entityClass));
            });
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public Optional<T> findById(Object id) {
        try {
            DocumentSnapshot documentSnapshot = getCollection().document(id.toString()).get().get();
            if (documentSnapshot.exists()) {
                return Optional.of(documentSnapshot.toObject(entityClass));
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public T save(Object entity) {
        try {
            DocumentReference documentReference = getCollection().add(entity).get();
            return documentReference.get().get().toObject(entityClass);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(Object id) {
        getCollection().document(id.toString()).delete();
    }

    @Override
    public List<T> findByField(String field, Object value){
        List<T> lista = new ArrayList<>();
        try{
            QuerySnapshot snapshots =  firestore.collection(getCollectionName()).whereEqualTo(field, value)
                    .get()
                    .get();

            snapshots.forEach(queryDocumentSnapshot -> {
                lista.add(queryDocumentSnapshot.toObject(entityClass));
            });
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }



    @Override
    public List<T> searchByCriteria(Map<String, Object> criteria){
        List<T> results = new ArrayList<>();

        CollectionReference collection = firestore.collection(getCollectionName());
        Query query = collection;

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            query = query.whereEqualTo(entry.getKey(), entry.getValue());
        }

        try {
            QuerySnapshot querySnapshot = query.get().get();
            querySnapshot.forEach(doc -> results.add(doc.toObject(entityClass)));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return results;
    }

}
