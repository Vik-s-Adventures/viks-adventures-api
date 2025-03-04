package com.upc.viksadventuresapi.profile.domain.model.aggregates;

import com.upc.viksadventuresapi.profile.domain.model.commands.CreateProfileCommand;
import com.upc.viksadventuresapi.profile.domain.model.valueobjects.*;
import com.upc.viksadventuresapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Embedded
    private StudentName name;

    @Embedded
    private BirthDate birthDate;

    @Embedded
    private Sex sex;

    @Embedded
    private GradeLevel gradeLevel;

    @Embedded
    private School school;

    public Profile(String firstName, String lastName, String birthDate, String sex, String gradeLevel, String school) {
        this.name = new StudentName(firstName, lastName);
        this.birthDate = new BirthDate(birthDate);
        this.sex = new Sex(sex);
        this.gradeLevel = new GradeLevel(gradeLevel);
        this.school = new School(school);
    }

    public Profile(CreateProfileCommand command) {
        this(
                command.firstName(),
                command.lastName(),
                command.birthDate(),
                command.sex(),
                command.gradeLevel(),
                command.school()
        );
    }

    public Profile() {}

    public void updateName(String firstName, String lastName) {
        this.name = new StudentName(firstName, lastName);
    }

    public void updateBirthDate(String birthDate) {
        this.birthDate = new BirthDate(birthDate);
    }

    public void updateSex(String sex) {
        this.sex = new Sex(sex);
    }

    public void updateGradeLevel(String gradeLevel) {
        this.gradeLevel = new GradeLevel(gradeLevel);
    }

    public void updateSchool(String school) {
        this.school = new School(school);
    }

    public String getFullName() {
        return name.getFullName();
    }

    public String getBirthDate() {
        return birthDate.birthDate();
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
