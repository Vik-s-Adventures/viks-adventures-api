package com.upc.viksadventuresapi.profile.domain.model.aggregates;

import com.upc.viksadventuresapi.iam.domain.model.aggregates.User;
import com.upc.viksadventuresapi.profile.domain.model.valueobjects.*;
import com.upc.viksadventuresapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "profile")
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Embedded
    private FullName fullName;

    @Embedded
    private BirthDate birthDate;

    @Embedded
    private Sex sex;

    @Embedded
    private GradeLevel gradeLevel;

    @Embedded
    private School school;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Profile(User user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName != null ? fullName.getFullName() : null;
    }

    public String getBirthDate() {
        return birthDate != null ? birthDate.formatted() : null;
    }

    public String getSex() {
        return sex != null ? sex.sex() : null;
    }

    public String getGradeLevel() {
        return gradeLevel != null ? gradeLevel.gradeLevel() : null;
    }

    public String getSchool() {
        return school != null ? school.school() : null;
    }
}