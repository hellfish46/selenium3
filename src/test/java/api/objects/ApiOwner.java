package api.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


//@JsonPropertyOrder({
//        "address",
//        "city",
//        "firstName",
//        "id",
//        "lastName",
//        "pets",
//        "telephone"
//})

public class ApiOwner {

    @JsonProperty("address")
    private String address;
    @JsonProperty("city")
    private String city;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("pets")
    private List<ApiPetId> pets = new ArrayList<>();

    @JsonProperty("telephone")
    private String telephone;

    @JsonProperty("visits")
    private List<ApiVisit> visits = new ArrayList<>();

    public List<ApiVisit> getVisits() {
        return visits;
    }

    public void setVisits(List<ApiVisit> visits) {
        if(visits.size() == 0){
            this.visits = new ArrayList<>();
        } else {
            this.visits = visits;
        }
    }

    @JsonProperty("pets")
    public List<ApiPetId> getApiPets() {
        return pets;
    }

    @JsonProperty("pets")
    public void setApiPets(List<ApiPetId> listPets) {
        if(listPets.size() == 0){
            this.pets = new ArrayList<>();
        } else {
            this.pets = listPets;
        }

        //this.pets = listPets;
     }




    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

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


    @JsonProperty("telephone")
    public String getTelephone() {
        return telephone;
    }

    @JsonProperty("telephone")
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "ApiOwner{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", firstName='" + firstName + '\'' +
                ", id=" + id +
                ", lastName='" + lastName + '\'' +
                ", pets=" + pets +
                ", telephone='" + telephone + '\'' +
                ", visits=" + visits +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiOwner apiOwner = (ApiOwner) o;
        return Objects.equals(address, apiOwner.address) &&
                Objects.equals(city, apiOwner.city) &&
                Objects.equals(firstName, apiOwner.firstName) &&
                Objects.equals(lastName, apiOwner.lastName) &&
                Objects.equals(pets, apiOwner.pets) &&
                Objects.equals(telephone, apiOwner.telephone);
    }

    @Override
    public int hashCode() {
        //return Objects.hash(address, city, firstName, lastName, telephone);
        return Objects.hash(address, city, firstName, lastName, pets, telephone);
    }
}
