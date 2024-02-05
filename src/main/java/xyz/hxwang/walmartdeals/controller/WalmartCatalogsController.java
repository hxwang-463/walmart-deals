package xyz.hxwang.walmartdeals.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.hxwang.walmartdeals.service.WalmartCatalogsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/walmart")
public class WalmartCatalogsController {
    @Autowired
    WalmartCatalogsService walmartCatalogsService;

    @GetMapping("/save")
    public String saveAllCatalogs() throws JsonMappingException, JsonProcessingException {
        this.walmartCatalogsService.fetchDataAndSave();
        return "saved";
    }

    @DeleteMapping("/deleteAllCatalogs")
    public ResponseEntity<String> deleteAllCatalogs(){
        return ResponseEntity.ok(this.walmartCatalogsService.deleteCatalogs());
    }
}
