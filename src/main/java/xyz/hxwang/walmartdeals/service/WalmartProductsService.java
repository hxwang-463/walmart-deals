package xyz.hxwang.walmartdeals.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import xyz.hxwang.walmartdeals.entity.WalmartProducts;

import java.util.List;

public interface WalmartProductsService {
    public void fetchDataAndSave() throws JsonMappingException, JsonProcessingException;

    public List<WalmartProducts> search(String s);

    public String deleteProducts();

    public List<WalmartProducts> getDiscountProducts();

    public WalmartProducts getByCatalogItemId(String cId);

    public List<WalmartProducts> getDiscountProductsByCatalog(String cIId);

}
