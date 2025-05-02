package com.upc.viksadventuresapi.profile.domain.model.aggregates;

import com.upc.viksadventuresapi.iam.domain.model.aggregates.User;
import com.upc.viksadventuresapi.profile.domain.model.commands.CreateProfileCommand;
import com.upc.viksadventuresapi.profile.domain.model.valueobjects.*;
import com.upc.viksadventuresapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    @JoinColumn(name = "user_id")
    private User user;

    public Profile(User user, CreateProfileCommand command) {
        this.user = user;
        this.fullName = new FullName(command.firstName(), command.lastName());
        this.birthDate = new BirthDate(LocalDate.parse(command.birthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.sex = new Sex(command.sex());
        this.gradeLevel = new GradeLevel(command.gradeLevel());
        this.school = new School(command.school());
    }

    public String getFullName() {
        return fullName.getFullName();
    }

    public String getBirthDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return birthDate.birthDate().format(formatter);
    }

    public String getSex() {
        return sex.sex();
    }

    public String getGradeLevel() {
        return gradeLevel.gradeLevel();
    }

    public String getSchool() {
        return school.school();
    }
}
