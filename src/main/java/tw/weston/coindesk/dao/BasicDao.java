/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.dao;

import java.util.function.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import tw.weston.coindesk.exc.CustomizedException;

/**
 * 資料存取層基本介面，用來擴展JpaRepository，可以定義一些常用的方法
 * @author weston.tan
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BasicDao<T, ID> extends JpaRepository<T, ID> {
    
    T findOneOrThrow(ID id, Function<ID, ? extends CustomizedException> exceptionThrower);

}
