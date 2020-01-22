package api.objects;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//        "birthDate",
//        "id",
//        "name",
//        "owner",
//        "type",
//        "visits"
//})

public class ApiPet {

    @JsonProperty("birthDate")
    protected String birthDate;
    @JsonProperty("id")
    protected Integer id;
    @JsonProperty("name")
    protected String name;
    @JsonProperty("owner")
    protected ApiOwner apiOwner;
    @JsonProperty("type")
    protected ApiType apiType;
    @JsonProperty("visits")
    protected List<ApiVisit> visits = new ArrayList<>();


    @JsonProperty("birthDate")
    public String getBirthDate() {
        return birthDate;
    }

    @JsonProperty("birthDate")
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("owner")
    public ApiOwner getApiOwner() {
        return apiOwner;
    }

    @JsonProperty("owner")
    public void setApiOwner(ApiOwner apiOwner) {
        this.apiOwner = apiOwner;
    }

    @JsonProperty("type")
    public ApiType getApiType() {
        return apiType;
    }

    @JsonProperty("type")
    public void setApiType(ApiType apiType) {
        this.apiType = apiType;
    }

    @JsonProperty("visits")
    public List<ApiVisit> getVisits() {
        return visits;
    }

    @JsonProperty("visits")
    public void setVisits(List<ApiVisit> visits) {
        if(visits.size() == 0){
            this.visits = new ArrayList<>();
        } else {
            this.visits = visits;
        }
    }


    @Override
    public String toString() {
        return "ApiPet{" +
                "birthDate='" + birthDate + '\'' +
               // ", id=" + id +
                ", name='" + name + '\'' +
                ", apiOwner=" + apiOwner +
                ", apiType=" + apiType +
                ", visits=" + visits +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiPet apiPet = (ApiPet) o;
        return Objects.equals(birthDate, apiPet.birthDate) &&
               // Objects.equals(id, apiPet.id) &&
                Objects.equals(name, apiPet.name) &&
                Objects.equals(apiOwner, apiPet.apiOwner) &&
                Objects.equals(apiType, apiPet.apiType) &&
                Objects.equals(visits, apiPet.visits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthDate, name, apiOwner, apiType, visits);
    }
}
