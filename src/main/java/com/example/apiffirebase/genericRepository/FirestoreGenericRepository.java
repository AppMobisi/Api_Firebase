package com.example.apiffirebase.genericRepository;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public abstract class FirestoreGenericRepository<T, ID> implements GenericRepository<T,ID>{
    private final Class<T> entityClass;
    protected Firestore firestore = FirestoreClient.getFirestore();

    protected FirestoreGenericRepository() {
        this.entityClass = (Class<T>) GenericTypeResolver.resolveTypeArguments(getClass(), FirestoreGenericRepository.class)[0];

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
                T entity = queryDocumentSnapshot.toObject(entityClass);
                setIdToEntity(entity, queryDocumentSnapshot.getId());
                entities.add(entity);
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
                T entity =   documentSnapshot.toObject(entityClass);
                setIdToEntity(entity, documentSnapshot.getId());
                return Optional.of(entity);
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
                T entity = queryDocumentSnapshot.toObject(entityClass);
                setIdToEntity(entity, queryDocumentSnapshot.getId());
                lista.add(entity);
            });
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }



    @Override
    public List<T> searchByCriteria(Map<String, Object> criteria) {
        List<T> results = new ArrayList<>();

        CollectionReference collection = firestore.collection(getCollectionName());
        Query query = collection;

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            if (entry.getValue() instanceof String) {
                // Se o valor for uma string, use a lógica de "LIKE"
                String value = ((String) entry.getValue()).toLowerCase();
                query = query.orderBy(entry.getKey()).startAt(value).endAt(value + "\uf8ff");
            } else {
                // Caso contrário, faça uma busca exata
                query = query.whereEqualTo(entry.getKey(), entry.getValue());
            }
        }

        try {
            QuerySnapshot querySnapshot = query.get().get();
            querySnapshot.forEach(doc -> {
                T entity = doc.toObject(entityClass);
                setIdToEntity(entity, doc.getId());
                results.add(entity);
            });
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return results;
    }


    public List<T> searchByCriteriaFavoritos(Map<String, Object> criteria) {
        List<T> results = new ArrayList<>();

        CollectionReference collection = firestore.collection(getCollectionName());
        Query query = collection;

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            query = query.whereEqualTo(entry.getKey(), entry.getValue());
        }

        try {
            QuerySnapshot querySnapshot = query.get().get();
            querySnapshot.forEach(doc -> {
                T entity = doc.toObject(entityClass);
                setIdToEntity(entity, doc.getId());
                results.add(entity);
            });
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return results;
    }

    private void setIdToEntity(T entity, String id) {
        try {
            Field field = entity.getClass().getDeclaredField("id");
            field.setAccessible(true);
            field.set(entity, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int deleteByFields(Map<String, Object> criteria) {
        int controle= 0;

        Query query = firestore.collection(getCollectionName());

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            query = query.whereEqualTo(entry.getKey(), entry.getValue());
        }

        try {

            QuerySnapshot querySnapshot = query.get().get();

            if (querySnapshot.isEmpty()) {
                controle =  0;
            }

            WriteBatch batch = firestore.batch();
            for (DocumentSnapshot docSnapshot : querySnapshot) {
                batch.delete(docSnapshot.getReference());
            }
            batch.commit().get();

            controle =  querySnapshot.size();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return controle;
    }



}
