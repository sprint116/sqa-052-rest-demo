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
        "source_name",
        "source_description",
        "dataset_name",
        "dataset_link",
        "table_id",
        "topic",
        "subtopic"
})
@Generated("jsonschema2pojo")
public class Annotations {

    @JsonProperty("source_name")
    private String sourceName;
    @JsonProperty("source_description")
    private String sourceDescription;
    @JsonProperty("dataset_name")
    private String datasetName;
    @JsonProperty("dataset_link")
    private String datasetLink;
    @JsonProperty("table_id")
    private String tableId;
    @JsonProperty("topic")
    private String topic;
    @JsonProperty("subtopic")
    private String subtopic;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("source_name")
    public String getSourceName() {
        return sourceName;
    }

    @JsonProperty("source_name")
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Annotations withSourceName(String sourceName) {
        this.sourceName = sourceName;
        return this;
    }

    @JsonProperty("source_description")
    public String getSourceDescription() {
        return sourceDescription;
    }

    @JsonProperty("source_description")
    public void setSourceDescription(String sourceDescription) {
        this.sourceDescription = sourceDescription;
    }

    public Annotations withSourceDescription(String sourceDescription) {
        this.sourceDescription = sourceDescription;
        return this;
    }

    @JsonProperty("dataset_name")
    public String getDatasetName() {
        return datasetName;
    }

    @JsonProperty("dataset_name")
    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public Annotations withDatasetName(String datasetName) {
        this.datasetName = datasetName;
        return this;
    }

    @JsonProperty("dataset_link")
    public String getDatasetLink() {
        return datasetLink;
    }

    @JsonProperty("dataset_link")
    public void setDatasetLink(String datasetLink) {
        this.datasetLink = datasetLink;
    }

    public Annotations withDatasetLink(String datasetLink) {
        this.datasetLink = datasetLink;
        return this;
    }

    @JsonProperty("table_id")
    public String getTableId() {
        return tableId;
    }

    @JsonProperty("table_id")
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public Annotations withTableId(String tableId) {
        this.tableId = tableId;
        return this;
    }

    @JsonProperty("topic")
    public String getTopic() {
        return topic;
    }

    @JsonProperty("topic")
    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Annotations withTopic(String topic) {
        this.topic = topic;
        return this;
    }

    @JsonProperty("subtopic")
    public String getSubtopic() {
        return subtopic;
    }

    @JsonProperty("subtopic")
    public void setSubtopic(String subtopic) {
        this.subtopic = subtopic;
    }

    public Annotations withSubtopic(String subtopic) {
        this.subtopic = subtopic;
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

    public Annotations withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
