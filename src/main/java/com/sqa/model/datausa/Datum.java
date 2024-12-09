package com.sqa.model.datausa;

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
        "ID Nation",
        "Nation",
        "ID Year",
        "Year",
        "Population",
        "Slug Nation"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("ID Nation")
    private String iDNation;
    @JsonProperty("Nation")
    private String nation;
    @JsonProperty("ID Year")
    private Long iDYear;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Population")
    private Long population;
    @JsonProperty("Slug Nation")
    private String slugNation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Datum() {
    }

    public Datum(String iDNation, String nation, Long iDYear, String year, Long population, String slugNation) {
        super();
        this.iDNation = iDNation;
        this.nation = nation;
        this.iDYear = iDYear;
        this.year = year;
        this.population = population;
        this.slugNation = slugNation;
    }

    @JsonProperty("ID Nation")
    public String getIDNation() {
        return iDNation;
    }

    @JsonProperty("ID Nation")
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

    @JsonProperty("ID Year")
    public Long getIDYear() {
        return iDYear;
    }

    @JsonProperty("ID Year")
    public void setIDYear(Long iDYear) {
        this.iDYear = iDYear;
    }

    public Datum withIDYear(Long iDYear) {
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
    public Long getPopulation() {
        return population;
    }

    @JsonProperty("Population")
    public void setPopulation(Long population) {
        this.population = population;
    }

    public Datum withPopulation(Long population) {
        this.population = population;
        return this;
    }

    @JsonProperty("Slug Nation")
    public String getSlugNation() {
        return slugNation;
    }

    @JsonProperty("Slug Nation")
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Datum.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("iDNation");
        sb.append('=');
        sb.append(((this.iDNation == null)?"<null>":this.iDNation));
        sb.append(',');
        sb.append("nation");
        sb.append('=');
        sb.append(((this.nation == null)?"<null>":this.nation));
        sb.append(',');
        sb.append("iDYear");
        sb.append('=');
        sb.append(((this.iDYear == null)?"<null>":this.iDYear));
        sb.append(',');
        sb.append("year");
        sb.append('=');
        sb.append(((this.year == null)?"<null>":this.year));
        sb.append(',');
        sb.append("population");
        sb.append('=');
        sb.append(((this.population == null)?"<null>":this.population));
        sb.append(',');
        sb.append("slugNation");
        sb.append('=');
        sb.append(((this.slugNation == null)?"<null>":this.slugNation));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.nation == null)? 0 :this.nation.hashCode()));
        result = ((result* 31)+((this.year == null)? 0 :this.year.hashCode()));
        result = ((result* 31)+((this.iDNation == null)? 0 :this.iDNation.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.iDYear == null)? 0 :this.iDYear.hashCode()));
        result = ((result* 31)+((this.slugNation == null)? 0 :this.slugNation.hashCode()));
        result = ((result* 31)+((this.population == null)? 0 :this.population.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Datum) == false) {
            return false;
        }
        Datum rhs = ((Datum) other);
        return ((((((((this.nation == rhs.nation)||((this.nation!= null)&&this.nation.equals(rhs.nation)))&&((this.year == rhs.year)||((this.year!= null)&&this.year.equals(rhs.year))))&&((this.iDNation == rhs.iDNation)||((this.iDNation!= null)&&this.iDNation.equals(rhs.iDNation))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.iDYear == rhs.iDYear)||((this.iDYear!= null)&&this.iDYear.equals(rhs.iDYear))))&&((this.slugNation == rhs.slugNation)||((this.slugNation!= null)&&this.slugNation.equals(rhs.slugNation))))&&((this.population == rhs.population)||((this.population!= null)&&this.population.equals(rhs.population))));
    }

}