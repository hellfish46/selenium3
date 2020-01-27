package api.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ApiVisitPetObj extends ApiVisit {

    @JsonProperty("pet")
    private ApiPet pet;

    public ApiPet getPet() {
        return pet;
    }

    public void setPet(ApiPet pet) {
        this.pet = pet;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ApiVisitPetObj ApiVisitPetObj = (ApiVisitPetObj) o;
        return Objects.equals(date, ApiVisitPetObj.date) &&
                Objects.equals(description, ApiVisitPetObj.description) &&
                Objects.equals(pet, ApiVisitPetObj.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pet);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", pet=" + pet +
                '}';
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//        ApiPetId apiPetId = (ApiPetId) o;
//        return Objects.equals(owner, apiPetId.owner) &&
//                Objects.equals(birthDate, apiPetId.birthDate) &&
//                Objects.equals(id, apiPetId.id) &&
//                Objects.equals(name, apiPetId.name) &&
//                Objects.equals(apiOwner, apiPetId.apiOwner) &&
//                Objects.equals(apiType, apiPetId.apiType) &&
//                Objects.equals(visits, apiPetId.visits);
//    }
//
//
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), owner);
//    }


}
