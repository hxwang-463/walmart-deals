package xyz.hxwang.walmartdeals.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import xyz.hxwang.walmartdeals.entity.WalmartCatalogs;

import java.util.List;

public interface WalmartCatalogsService {
    public void fetchDataAndSave() throws JsonMappingException, JsonProcessingException;

    public List<WalmartCatalogs> fetchAll();
    public int getTotalPages();
    public String deleteCatalogs();
}
