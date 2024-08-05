package rca.ac.year3.security_starter.models;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rca.ac.year3.security_starter.enums.EGender;
import rca.ac.year3.security_starter.enums.EUserStatus;
import rca.ac.year3.security_starter.enums.Erole;
import rca.ac.year3.security_starter.utils.Utility;


@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EUserStatus status = EUserStatus.PENDING;

    @JsonIgnore
    @Column(name = "activation_code")
    private String activationCode = Utility.randomUUID(6, 0, 'N');

    @Column(name = "rejection_description")
    private String rejectionDescription;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @NotBlank
    @Column(name = "password")
    private String password;

    public UserData(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserData(String firstName, String lastName, String email, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }

    public UserData(String firstName, String lastName,String email, EUserStatus status, Set<Role> roles, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.roles = roles;
        this.password = password;
    }

    public Erole getRole() {
        Optional<Role> role = this.getRoles().stream().findFirst();
        Erole theRole = null;

        if (role.isPresent())
            theRole = role.get().getName();

        return theRole;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

}
