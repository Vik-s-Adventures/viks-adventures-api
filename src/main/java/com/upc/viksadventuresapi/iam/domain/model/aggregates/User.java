package com.upc.viksadventuresapi.iam.domain.model.aggregates;

import com.upc.viksadventuresapi.iam.domain.model.enums.AuthProvider;
import com.upc.viksadventuresapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "users")
public class User extends AuditableAbstractAggregateRoot<User> {
    private String name;
    private String email;
    private String password;

    @Enumerated
    private AuthProvider authProvider;
}
