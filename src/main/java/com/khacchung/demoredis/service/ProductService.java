package com.khacchung.demoredis.service;

import com.khacchung.demoredis.DAO.ProductDetail;
import com.khacchung.demoredis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDetail> getAllProduct() {
        try {
            List<ProductDetail> tmp = productRepository.getAllProducts();
            return tmp;
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public ProductDetail getById(int id) {
        try {
            ProductDetail tmp = productRepository.getProductDetailById(id);
            return tmp;
        } catch (Exception e) {

        }
        return null;
    }
}
