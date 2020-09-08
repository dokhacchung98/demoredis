package com.khacchung.demoredis.repository;

import com.khacchung.demoredis.DAO.ProductDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductDetail, Integer> {
    @Query("select c from ProductDetail c")
    List<ProductDetail> getAllProducts();

    @Query("select c from  ProductDetail c where c.id = :id")
    ProductDetail getProductDetailById(int id);
}
