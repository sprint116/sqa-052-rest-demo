package com.sqa.datausa;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "IDNation",
        "Nation",
        "IDYear",
        "Year",
        "Population",
        "SlugNation"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("IDNation")
    private String iDNation;
    @JsonProperty("Nation")
    private String nation;
    @JsonProperty("IDYear")
    private Integer iDYear;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Population")
    private Integer population;
    @JsonProperty("SlugNation")
    private String slugNation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("IDNation")
    public String getIDNation() {
        return iDNation;
    }

    @JsonProperty("IDNation")
    public void setIDNation(String iDNation) {
        this.iDNation = iDNation;
    }

    public Datum withIDNation(String iDNation) {
        this.iDNation = iDNation;
        return this;
    }

    @JsonProperty("Nation")
    public String getNation() {
        return nation;
    }

    @JsonProperty("Nation")
    public void setNation(String nation) {
        this.nation = nation;
    }

    public Datum withNation(String nation) {
        this.nation = nation;
        return this;
    }

    @JsonProperty("IDYear")
    public Integer getIDYear() {
        return iDYear;
    }

    @JsonProperty("IDYear")
    public void setIDYear(Integer iDYear) {
        this.iDYear = iDYear;
    }

    public Datum withIDYear(Integer iDYear) {
        this.iDYear = iDYear;
        return this;
    }

    @JsonProperty("Year")
    public String getYear() {
        return year;
    }

    @JsonProperty("Year")
    public void setYear(String year) {
        this.year = year;
    }

    public Datum withYear(String year) {
        this.year = year;
        return this;
    }

    @JsonProperty("Population")
    public Integer getPopulation() {
        return population;
    }

    @JsonProperty("Population")
    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Datum withPopulation(Integer population) {
        this.population = population;
        return this;
    }

    @JsonProperty("SlugNation")
    public String getSlugNation() {
        return slugNation;
    }

    @JsonProperty("SlugNation")
    public void setSlugNation(String slugNation) {
        this.slugNation = slugNation;
    }

    public Datum withSlugNation(String slugNation) {
        this.slugNation = slugNation;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Datum withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
