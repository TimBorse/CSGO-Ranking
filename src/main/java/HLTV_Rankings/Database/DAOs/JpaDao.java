package HLTV_Rankings.Database.DAOs;

import HLTV_Rankings.Database.DAOs.Interfaces.IDao;
import HLTV_Rankings.Database.DatabaseManager;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.*;

public abstract class JpaDao<E, PK extends Serializable> implements IDao<E, PK> {

    protected Class<E> entityClass;
    protected static EntityManager entityManager;

    JpaDao(){
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass
                .getActualTypeArguments()[0];
        this.entityManager = DatabaseManager.entityManager;
    }

    public void persist(E entity) {

        entityManager.persist(entity);
    }

    public void remove(E entity) {
        entityManager.remove(entity);
    }

    public E findById(PK id) {
        return entityManager.find(entityClass, id);
    }
}