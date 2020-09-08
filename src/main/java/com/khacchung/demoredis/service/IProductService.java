package com.khacchung.demoredis.service;
import com.khacchung.demoredis.DAO.ProductDetail;

import java.util.List;

public interface IProductService {
    List<ProductDetail> getAllProduct();

    ProductDetail getById(int id);
}
