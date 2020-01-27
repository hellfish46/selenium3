package api.objects;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


public class ApiVeterinarian {

    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("specialties")
    private List<ApiSpeciality> specialties = new ArrayList<>();

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("specialties")
    public List<ApiSpeciality> getSpecialties() {
        return specialties;
    }

    @JsonProperty("specialties")
    public void setSpecialties(List<ApiSpeciality> specialties) {

        if(specialties.size() == 0){
            this.specialties = new ArrayList<>();
        } else {
            this.specialties = specialties;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiVeterinarian that = (ApiVeterinarian) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(specialties, that.specialties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, specialties);
    }

    @Override
    public String toString() {
        return "ApiVeterinarian{" +
                "firstName='" + firstName + '\'' +
                ", id=" + id +
                ", lastName='" + lastName + '\'' +
                ", specialties=" + specialties +
                '}';
    }
}
