package xyz.hxwang.walmartdeals.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.hxwang.walmartdeals.entity.WalmartProducts;
import xyz.hxwang.walmartdeals.service.WalmartProductsService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/walmart")
public class WalmartProductsController {
    @Autowired
    WalmartProductsService walmartProductsService;

    @GetMapping("/saveProducts")
    public String saveAllProducts() throws JsonMappingException, JsonProcessingException {
        this.walmartProductsService.fetchDataAndSave();
        return "saved";
    }
    @GetMapping("/search/{search}")
    public ResponseEntity<List<WalmartProducts>> search(@PathVariable String search){
        return ResponseEntity.ok(this.walmartProductsService.search(search));
    }

    @GetMapping("/catalogItemId/{cId}")
    public ResponseEntity<WalmartProducts> getByCatalogItemId(@PathVariable String cId){
        return ResponseEntity.ok(this.walmartProductsService.getByCatalogItemId(cId));
    }

    @GetMapping("/getDiscounts")
    public ResponseEntity<List<WalmartProducts>> getDiscountProducts(){
        return ResponseEntity.ok(this.walmartProductsService.getDiscountProducts());
    }

    @DeleteMapping("/delteAllProducts")
    public ResponseEntity<String> deleteAllProducts(){
        return ResponseEntity.ok(this.walmartProductsService.deleteProducts());
    }

    @GetMapping("/getDiscountsByCatalog/{cIId}")
    public ResponseEntity<List<WalmartProducts>> getProductDiscountsByCatalog(@PathVariable String cIId){
        return ResponseEntity.ok(this.walmartProductsService.getDiscountProductsByCatalog(cIId));
    }
}
