package com.pos.database.service;


import com.pos.database.model.*;
import com.pos.database.repository.ItemEntityRepository;
import com.pos.database.repository.ProductEntityRepository;
import com.pos.database.repository.StatusEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DatabaseServiceImpl implements DatabaseService{

    @Autowired
    private ProductEntityRepository productRepository;

    @Autowired
    private StatusEntityRepository statusRepository;

    @Autowired
    private ItemEntityRepository itemRepository;

    private ProductEntity readProduct(Product product){
        return productRepository.findOne(Example.of(ProductEntity.fromProduct(product))).orElse(null);
    }

    private ItemEntity fromItem(Item item){
        return new ItemEntity(
                readProduct(item.getProduct()),
                item.getQuantity()
        );
    }

    private ItemEntity fromItemAndSave(Item item){
        return itemRepository.saveAndFlush(fromItem(item));
    }

    private StatusEntity fromStatus(Status status){
        return new StatusEntity(
                status.getUid(),
                status.getItems().stream()
                        .map(this::fromItem)
                        .collect(Collectors.toSet()),
                status.getCreatedAt()
        );
    }

    private StatusEntity fromStatusAndSave(Status status){
        return statusRepository.saveAndFlush(new StatusEntity(
                status.getUid(),
                status.getItems().stream()
                        .map(this::fromItemAndSave)
                        .collect(Collectors.toSet()),
                status.getCreatedAt()
        ));
    }

    private List<Product> _getAllProduct() {
        return productRepository.findAll().stream()
                .map(ProductEntity::toProduct)
                .collect(Collectors.toList());
    }

    private Product _getProduct(String asin) {
       ProductEntity entity = productRepository.findOne(Example.of(ProductEntity.fromAsin(asin))).orElse(null);
       if(entity != null){
           return entity.toProduct();
       }
       return null;
    }

    private List<Status> _getAllStatus(String uid) {
        return statusRepository.findAll(Example.of(StatusEntity.fromUid(uid))).stream()
                .map(StatusEntity::toStatus)
                .collect(Collectors.toList());
    }

    private Boolean _addStatus(Status status) {
        fromStatusAndSave(status);
        return true;
    }

    @Override
    public Flux<Product> getAllProduct(){
        return Flux.fromIterable(_getAllProduct());
    }

    @Override
    public Mono<Product> getProduct(String asin){
        return Mono.fromCallable(()->_getProduct(asin));
    }

    @Override
    public Flux<Status> getAllStatus(String uid){
        return Flux.fromIterable(_getAllStatus(uid));
    }

    @Override
    public Mono<Boolean> addStatus(Status status){
        return Mono.fromCallable(()->_addStatus(status));
    }

}
