package dev.selenium.objects;

import java.util.Objects;

public class Speciality {
    private String speciality;

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speciality that = (Speciality) o;
        return Objects.equals(speciality, that.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speciality);
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "speciality='" + speciality + '\'' +
                '}';
    }
}
