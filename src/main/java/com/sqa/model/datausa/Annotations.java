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

    /**
     * No args constructor for use in serialization
     *
     */
    public Annotations() {
    }

    public Annotations(String sourceName, String sourceDescription, String datasetName, String datasetLink, String tableId, String topic, String subtopic) {
        super();
        this.sourceName = sourceName;
        this.sourceDescription = sourceDescription;
        this.datasetName = datasetName;
        this.datasetLink = datasetLink;
        this.tableId = tableId;
        this.topic = topic;
        this.subtopic = subtopic;
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Annotations.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sourceName");
        sb.append('=');
        sb.append(((this.sourceName == null)?"<null>":this.sourceName));
        sb.append(',');
        sb.append("sourceDescription");
        sb.append('=');
        sb.append(((this.sourceDescription == null)?"<null>":this.sourceDescription));
        sb.append(',');
        sb.append("datasetName");
        sb.append('=');
        sb.append(((this.datasetName == null)?"<null>":this.datasetName));
        sb.append(',');
        sb.append("datasetLink");
        sb.append('=');
        sb.append(((this.datasetLink == null)?"<null>":this.datasetLink));
        sb.append(',');
        sb.append("tableId");
        sb.append('=');
        sb.append(((this.tableId == null)?"<null>":this.tableId));
        sb.append(',');
        sb.append("topic");
        sb.append('=');
        sb.append(((this.topic == null)?"<null>":this.topic));
        sb.append(',');
        sb.append("subtopic");
        sb.append('=');
        sb.append(((this.subtopic == null)?"<null>":this.subtopic));
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
        result = ((result* 31)+((this.datasetName == null)? 0 :this.datasetName.hashCode()));
        result = ((result* 31)+((this.datasetLink == null)? 0 :this.datasetLink.hashCode()));
        result = ((result* 31)+((this.tableId == null)? 0 :this.tableId.hashCode()));
        result = ((result* 31)+((this.topic == null)? 0 :this.topic.hashCode()));
        result = ((result* 31)+((this.sourceName == null)? 0 :this.sourceName.hashCode()));
        result = ((result* 31)+((this.sourceDescription == null)? 0 :this.sourceDescription.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.subtopic == null)? 0 :this.subtopic.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Annotations) == false) {
            return false;
        }
        Annotations rhs = ((Annotations) other);
        return (((((((((this.datasetName == rhs.datasetName)||((this.datasetName!= null)&&this.datasetName.equals(rhs.datasetName)))&&((this.datasetLink == rhs.datasetLink)||((this.datasetLink!= null)&&this.datasetLink.equals(rhs.datasetLink))))&&((this.tableId == rhs.tableId)||((this.tableId!= null)&&this.tableId.equals(rhs.tableId))))&&((this.topic == rhs.topic)||((this.topic!= null)&&this.topic.equals(rhs.topic))))&&((this.sourceName == rhs.sourceName)||((this.sourceName!= null)&&this.sourceName.equals(rhs.sourceName))))&&((this.sourceDescription == rhs.sourceDescription)||((this.sourceDescription!= null)&&this.sourceDescription.equals(rhs.sourceDescription))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.subtopic == rhs.subtopic)||((this.subtopic!= null)&&this.subtopic.equals(rhs.subtopic))));
    }

}