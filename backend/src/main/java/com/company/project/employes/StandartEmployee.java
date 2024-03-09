package com.company.project.employes;

import com.company.project.utils.ObjectIsEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
public class StandartEmployee implements Employee {
    private final int id;
    private final Profile profile;

    public StandartEmployee(int id,
                            Profile profile) {
        this.id = id;
        this.profile = new StandartProfile(profile.getFirstName(), profile.getSurname(), profile.getAge());
    }

    @Override
    public int compareTo(Employee employee) {
        if (new ObjectIsEmpty<>(employee).test()) return -1;
        if (Objects.equals(this, employee)) return 0;
        return Integer.compare(id, employee.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StandartEmployee)) return false;
        StandartEmployee that = (StandartEmployee) o;
        return profile.equals(that.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profile);
    }
}