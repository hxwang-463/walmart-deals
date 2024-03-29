package xyz.hxwang.walmartdeals.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WalmartCatalogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wcId;
    @JsonProperty("Id")

    String id;
    @JsonProperty("Name")
    String name;
    @JsonProperty("Language")
    String language;
    @JsonProperty("AdvertiserId")
    String advertiserId;
    @JsonProperty("AdvertiserName")
    String advertiserName;
    @JsonProperty("AdvertiserLocation")
    String advertiserLocation;
    @JsonProperty("CampaignId")
    String campaignId;
    @JsonProperty("CampaignName")
    String campaignName;
    @JsonProperty("NumberOfItems")
    String numberOfItems;
    @JsonProperty("DateLastUpdated")
    String dateLastUpdated;
}
