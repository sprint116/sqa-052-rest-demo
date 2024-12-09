package com.sqa.model.datausa;

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

    /**
     * No args constructor for use in serialization
     *
     */
    public Source() {
    }

    public Source(List<String> measures, Annotations annotations, String name, List<Object> substitutions) {
        super();
        this.measures = measures;
        this.annotations = annotations;
        this.name = name;
        this.substitutions = substitutions;
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Source.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("measures");
        sb.append('=');
        sb.append(((this.measures == null)?"<null>":this.measures));
        sb.append(',');
        sb.append("annotations");
        sb.append('=');
        sb.append(((this.annotations == null)?"<null>":this.annotations));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("substitutions");
        sb.append('=');
        sb.append(((this.substitutions == null)?"<null>":this.substitutions));
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
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.annotations == null)? 0 :this.annotations.hashCode()));
        result = ((result* 31)+((this.measures == null)? 0 :this.measures.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.substitutions == null)? 0 :this.substitutions.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Source) == false) {
            return false;
        }
        Source rhs = ((Source) other);
        return ((((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.annotations == rhs.annotations)||((this.annotations!= null)&&this.annotations.equals(rhs.annotations))))&&((this.measures == rhs.measures)||((this.measures!= null)&&this.measures.equals(rhs.measures))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.substitutions == rhs.substitutions)||((this.substitutions!= null)&&this.substitutions.equals(rhs.substitutions))));
    }

}