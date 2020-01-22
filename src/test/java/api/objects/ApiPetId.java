package api.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ApiPetId extends ApiPet {

    @JsonProperty("owner")
    private Integer owner;

    @JsonProperty("owner")
    public Integer getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "ApiPetId{" +
              //  "owner=" + owner +
                ", birthDate='" + birthDate + '\'' +
                ", id=" + id +
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
        if (!super.equals(o)) return false;
        ApiPetId apiPetId = (ApiPetId) o;
        return Objects.equals(owner, apiPetId.owner) &&
                Objects.equals(birthDate, apiPetId.birthDate) &&
                Objects.equals(id, apiPetId.id) &&
                Objects.equals(name, apiPetId.name) &&
                Objects.equals(apiOwner, apiPetId.apiOwner) &&
                Objects.equals(apiType, apiPetId.apiType) &&
                Objects.equals(visits, apiPetId.visits);
    }



    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), owner);
    }
}
