package api.objects;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date",
        "description",
        "id",
        "pet"
})

public class ApiVisit {

    @JsonProperty("date")
    protected String date;
    @JsonProperty("description")
    protected String description;
    @JsonProperty("id")
    protected Integer id;
    @JsonProperty("pet")
    private Integer petId;


    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("pet")
    public Integer getApiPet() {
        return petId;
    }

    @JsonProperty("pet")
    public void setApiPet(Integer petId) {
        this.petId = petId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiVisit apiVisit = (ApiVisit) o;
        return Objects.equals(date, apiVisit.date) &&
                Objects.equals(description, apiVisit.description) &&
                Objects.equals(petId, apiVisit.petId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description, petId);
    }
}
