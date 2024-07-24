package com.Manoj.ecom_project.Controller;

import com.Manoj.ecom_project.Model.Product;
import com.Manoj.ecom_project.Service.ProductService;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Provider;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {

        @Autowired
        ProductService service;


    @RequestMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct()
    {

        return new ResponseEntity<>(service.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id)
    {
        Product product = service.getProductById(id);

        if(product!=null)
             return  new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile)
    {
        try {
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1,HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProduct(@PathVariable int productId)
    {
        Product product = service.getProductById(productId);
        byte[] imageFile = product.getImageDate();

        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);

    }
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product1, @RequestPart MultipartFile imageFile)
    {
        Product product=null;
        try {
            product = service.updateProduct(id, product1, imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(product !=null)
       {
           return new ResponseEntity<>("Updated",HttpStatus.OK);
       }
       else
           return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id)
    {
        Product product = service.getProductById(id);
        if(product!=null)
        {
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);

        }
        else {
            return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword)
    {
        System.out.println("Searching with "+ keyword);
        List<Product> listofallproducts = service.searchProduct( keyword);

        return new ResponseEntity<>(listofallproducts,HttpStatus.OK);
//        if(listofallproducts.size()!=0)
//        {
//            return new ResponseEntity<>(listofallproducts,HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
//        }
    }
}
