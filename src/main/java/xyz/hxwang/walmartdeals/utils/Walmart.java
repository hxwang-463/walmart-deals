package xyz.hxwang.walmartdeals.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.hxwang.walmartdeals.entity.WalmartCatalogs;
import xyz.hxwang.walmartdeals.entity.WalmartProducts;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Walmart {
    @JsonProperty("Catalogs")
    private List<WalmartCatalogs> catalogs;
    @JsonProperty("Items")
    private List<WalmartProducts> products;
    @JsonProperty("@numpages")
    private String numberOfPages;
    @JsonProperty("@total")
    private String totalRecords;
}
