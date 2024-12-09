package com.sqa.datausa;

import java.util.LinkedHashMap;
import java.util.List;
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
        "measures",
        "annotations",
        "name",
        "substitutions"
})
@Generated("jsonschema2pojo")
public class Source {

    @JsonProperty("measures")
    private List<String> measures;
    @JsonProperty("annotations")
    private Annotations annotations;
    @JsonProperty("name")
    private String name;
    @JsonProperty("substitutions")
    private List<Object> substitutions;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("measures")
    public List<String> getMeasures() {
        return measures;
    }

    @JsonProperty("measures")
    public void setMeasures(List<String> measures) {
        this.measures = measures;
    }

    public Source withMeasures(List<String> measures) {
        this.measures = measures;
        return this;
    }

    @JsonProperty("annotations")
    public Annotations getAnnotations() {
        return annotations;
    }

    @JsonProperty("annotations")
    public void setAnnotations(Annotations annotations) {
        this.annotations = annotations;
    }

    public Source withAnnotations(Annotations annotations) {
        this.annotations = annotations;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Source withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("substitutions")
    public List<Object> getSubstitutions() {
        return substitutions;
    }

    @JsonProperty("substitutions")
    public void setSubstitutions(List<Object> substitutions) {
        this.substitutions = substitutions;
    }

    public Source withSubstitutions(List<Object> substitutions) {
        this.substitutions = substitutions;
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

    public Source withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
