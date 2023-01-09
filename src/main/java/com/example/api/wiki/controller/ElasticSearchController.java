package com.example.api.wiki.controller;

import com.example.api.ElasticSearchQuery;
import com.example.api.wiki.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ElasticSearchController {

    private final ElasticSearchQuery elasticSearchQuery;

    public ElasticSearchController(ElasticSearchQuery elasticSearchQuery) {
        this.elasticSearchQuery = elasticSearchQuery;
    }

    @PostMapping("/add_product_test/{id}/{name}/{desc}/{price}")
    public ResponseEntity<Object> addProductTest(@PathVariable String id, @PathVariable String name, @PathVariable String desc, @PathVariable String price) throws IOException {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(desc);
        product.setPrice(Double.parseDouble(price));
        String response = elasticSearchQuery.createOrUpdateDocument(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create_or_update_document")
    public ResponseEntity<Object> createOrUpdateDocument(@RequestBody Product product) throws IOException {
        String response = elasticSearchQuery.createOrUpdateDocument(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get_document/{id}")
    public ResponseEntity<Object> getDocumentById(@PathVariable String id) throws IOException {
        Product product =  elasticSearchQuery.getDocumentById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/delete_document/{id}")
    public ResponseEntity<Object> deleteDocumentById(@PathVariable String id) throws IOException {
        String response =  elasticSearchQuery.deleteDocumentById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search_document")
    public ResponseEntity<Object> searchAllDocument() throws IOException {
        List<Product> products = elasticSearchQuery.searchAllDocuments();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
