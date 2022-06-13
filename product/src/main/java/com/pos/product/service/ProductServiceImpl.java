package com.pos.product.service;

import com.pos.product.model.Product;
import com.pos.product.model.ProductEntity;
import com.pos.product.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.util.annotation.Nullable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductEntityRepository productEntityRepository;

    @Nullable
    private Product productEntityToProduct(@Nullable ProductEntity productEntity){
        if(productEntity == null){
            return null;
        }
        return new Product(productEntity.getAsin(),
                productEntity.getImageUrl(),
                productEntity.getImageUrlHighRes(),
                productEntity.getMainCat(),
                productEntity.getPrice(),
                productEntity.getTitle());
    }

    @Override
    public List<Product> products() {
        return productEntityRepository.findAll().stream()
                .map(this::productEntityToProduct)
                .collect(Collectors.toList());
    }

    @Override
    public Product getProduct(String asin) {
        Example<ProductEntity> example = Example.of(ProductEntity.fromAsin(asin));
        return productEntityToProduct(productEntityRepository.findOne(example).orElse(null));
    }
}
