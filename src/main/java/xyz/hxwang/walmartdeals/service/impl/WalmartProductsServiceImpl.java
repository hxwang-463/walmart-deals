package xyz.hxwang.walmartdeals.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import xyz.hxwang.walmartdeals.dao.WalmartProductsRepo;
import xyz.hxwang.walmartdeals.entity.WalmartCatalogs;
import xyz.hxwang.walmartdeals.entity.WalmartProducts;
import xyz.hxwang.walmartdeals.service.WalmartProductsService;
import xyz.hxwang.walmartdeals.utils.Walmart;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Service
public class WalmartProductsServiceImpl implements WalmartProductsService {
    static final String USERNAME = "IRA4K3ySvbUh3917115tdrHHtKz6Y6tff1";
    static final String PASSWORD = "mXZ.6AWn6YpUFaEMkCAwBwe.BeRdvPaE";

    @Autowired
    WalmartCatalogsServiceImpl walmartCatalogsServiceImpl;

    @Autowired
    WalmartProductsRepo walmartProductsRepo;

    @Override
    public void fetchDataAndSave() throws JsonMappingException, JsonProcessingException {

        String credentials = this.USERNAME + ":" + this.PASSWORD;

        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Basic " + base64Credentials);
        headers.add("Accept", "application/json");

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        List<WalmartCatalogs> catalogs = this.walmartCatalogsServiceImpl.fetchAll();

        for (WalmartCatalogs wc : catalogs) {
            ResponseEntity<String> response = restTemplate
                    .exchange("https://api.impact.com/Mediapartners/IRA4K3ySvbUh3917115tdrHHtKz6Y6tff1/Catalogs/"
                            + wc.getId() + "/Items?=", HttpMethod.GET, httpEntity, String.class);
            String responseBody = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            Walmart walmart = objectMapper.readValue(responseBody, Walmart.class);
//
            List<WalmartProducts> products = walmart.getProducts();
            this.walmartProductsRepo.saveAll(products);

//			return products;
        }

    }



    @Override
    public List<WalmartProducts> search(String s) {
        List<WalmartProducts> list;
        if(s.contains(" ")) {
            list=this.walmartProductsRepo.indexSearch(s);
        }
        else {
            list=this.walmartProductsRepo.searchByName(s);
        }
        return list;

    }

    @Override
    public String deleteProducts() {
        this.walmartProductsRepo.deleteAll();
        return "Products deleted successfully";
    }

    @Override
    public List<WalmartProducts> getDiscountProducts() {
        return this.walmartProductsRepo.findDiscountProducts();
    }

    @Override
    public WalmartProducts getByCatalogItemId(String cId) {
        return walmartProductsRepo.getByCatalogItemId(cId);
    }

    @Override
    public List<WalmartProducts> getDiscountProductsByCatalog(String cIId) {
        return this.walmartProductsRepo.getDiscountProductsBycatalog(cIId);
    }

}
