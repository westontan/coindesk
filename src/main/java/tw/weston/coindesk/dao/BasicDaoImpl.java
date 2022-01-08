/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.dao;

import java.util.function.Function;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import tw.weston.coindesk.exc.CustomizedException;

/**
 * 資料存取層基本類別，實作基本介面上定義好的方法
 * @author weston.tan
 * @param <T>
 * @param <ID>
 */
public class BasicDaoImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BasicDao<T, ID> {

    private final EntityManager entityManager;

    BasicDaoImpl(JpaEntityInformation<T, ?> jpaEntityInformation, EntityManager entityManager) {
        super(jpaEntityInformation, entityManager);
        this.entityManager = entityManager;
    }
    
    @Override
    public T findOneOrThrow(ID id, Function<ID, ? extends CustomizedException> exceptionThrower) {
        return findById(id).orElseThrow(() -> exceptionThrower.apply(id));
    }

}
