package com.acme.jobconnect.platform.users.domain.model.aggregates;

import com.acme.jobconnect.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User extends AbstractAggregateRoot<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    // --- Campos del Perfil ---
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private String gender;
    private String phone;
    private String country;
    private String city;
    private String address;

    // --- Campos para Recuperar Contraseña ---
    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "reset_token_expiry")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resetTokenExpiry;

    // --- Campos de Auditoría Incrustados ---
    @Embedded
    private AuditableModel audit = new AuditableModel();


    // --- Constructores ---
    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.roles.add("ROLE_USER");
    }


    // --- Setters para Actualizar el Perfil y la Contraseña ---
    public void setPassword(String password) { this.password = password; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public void setGender(String gender) { this.gender = gender; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setCountry(String country) { this.country = country; }
    public void setCity(String city) { this.city = city; }
    public void setAddress(String address) { this.address = address; }


    // --- Métodos de Ayuda para el Token de Reseteo ---
    public void generateResetToken() {
        this.resetToken = UUID.randomUUID().toString();
        this.resetTokenExpiry = new Date(System.currentTimeMillis() + 3600000); // 1 hora
    }

    public void clearResetToken() {
        this.resetToken = null;
        this.resetTokenExpiry = null;
    }
}