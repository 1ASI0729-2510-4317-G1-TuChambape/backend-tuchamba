package com.acme.jobconnect.platform.profiles.domain.model.aggregates;

import com.acme.jobconnect.platform.shared.domain.model.entities.AuditableModel;
import com.acme.jobconnect.platform.users.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Entity
@Table(name = "profiles")
@EntityListeners(AuditingEntityListener.class)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
    @Temporal(TemporalType.DATE) private Date birthDate;
    private String gender;
    private String phone;
    private String country;
    private String city;
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Embedded
    private AuditableModel audit = new AuditableModel();

    public Profile() {}

    public Profile(User user, String firstName, String lastName) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Setters para los campos que se pueden actualizar
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
}