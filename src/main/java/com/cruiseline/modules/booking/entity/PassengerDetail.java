package com.cruiseline.modules.booking.entity;

import com.cruiseline.common.entity.BaseEntity;
import com.cruiseline.common.enums.PassengerStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "passenger_details", indexes = @Index(name = "idx_pax_booking", columnList = "booking_id"))
public class PassengerDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Long passengerId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    private CruiseBooking booking;

    @Column(nullable = false, length = 120)
    private String name;

    private LocalDate dateOfBirth;

    @Column(length = 20)
    private String gender;

    @Column(length = 60)
    private String nationality;

    // NOTE: encrypt at rest in production (see notes / JPA AttributeConverter).
    @Column(length = 60)
    private String passportNumber;

    private LocalDate passportExpiry;

    @Column(length = 255)
    private String dietaryRestrictions;

    @Column(length = 500)
    private String medicalNotes;

    @Column(length = 160)
    private String emergencyContact;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private PassengerStatus status = PassengerStatus.ACTIVE;

    public PassengerDetail() {
    }

    public PassengerDetail(Long passengerId, CruiseBooking booking, String name, LocalDate dateOfBirth, String gender, String nationality, String passportNumber, LocalDate passportExpiry, String dietaryRestrictions, String medicalNotes, String emergencyContact, PassengerStatus status) {
        this.passengerId = passengerId;
        this.booking = booking;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.passportNumber = passportNumber;
        this.passportExpiry = passportExpiry;
        this.dietaryRestrictions = dietaryRestrictions;
        this.medicalNotes = medicalNotes;
        this.emergencyContact = emergencyContact;
        this.status = status;
    }

    public Long getPassengerId() {
        return this.passengerId;
    }

    public CruiseBooking getBooking() {
        return this.booking;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getGender() {
        return this.gender;
    }

    public String getNationality() {
        return this.nationality;
    }

    public String getPassportNumber() {
        return this.passportNumber;
    }

    public LocalDate getPassportExpiry() {
        return this.passportExpiry;
    }

    public String getDietaryRestrictions() {
        return this.dietaryRestrictions;
    }

    public String getMedicalNotes() {
        return this.medicalNotes;
    }

    public String getEmergencyContact() {
        return this.emergencyContact;
    }

    public PassengerStatus getStatus() {
        return this.status;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public void setBooking(CruiseBooking booking) {
        this.booking = booking;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setPassportExpiry(LocalDate passportExpiry) {
        this.passportExpiry = passportExpiry;
    }

    public void setDietaryRestrictions(String dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public void setMedicalNotes(String medicalNotes) {
        this.medicalNotes = medicalNotes;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public void setStatus(PassengerStatus status) {
        this.status = status;
    }

    public static PassengerDetailBuilder builder() {
        return new PassengerDetailBuilder();
    }

    public static class PassengerDetailBuilder {
        private Long passengerId;
        private CruiseBooking booking;
        private String name;
        private LocalDate dateOfBirth;
        private String gender;
        private String nationality;
        private String passportNumber;
        private LocalDate passportExpiry;
        private String dietaryRestrictions;
        private String medicalNotes;
        private String emergencyContact;
        private PassengerStatus status = PassengerStatus.ACTIVE;

        public PassengerDetailBuilder passengerId(Long passengerId) {
            this.passengerId = passengerId;
            return this;
        }
        public PassengerDetailBuilder booking(CruiseBooking booking) {
            this.booking = booking;
            return this;
        }
        public PassengerDetailBuilder name(String name) {
            this.name = name;
            return this;
        }
        public PassengerDetailBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        public PassengerDetailBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }
        public PassengerDetailBuilder nationality(String nationality) {
            this.nationality = nationality;
            return this;
        }
        public PassengerDetailBuilder passportNumber(String passportNumber) {
            this.passportNumber = passportNumber;
            return this;
        }
        public PassengerDetailBuilder passportExpiry(LocalDate passportExpiry) {
            this.passportExpiry = passportExpiry;
            return this;
        }
        public PassengerDetailBuilder dietaryRestrictions(String dietaryRestrictions) {
            this.dietaryRestrictions = dietaryRestrictions;
            return this;
        }
        public PassengerDetailBuilder medicalNotes(String medicalNotes) {
            this.medicalNotes = medicalNotes;
            return this;
        }
        public PassengerDetailBuilder emergencyContact(String emergencyContact) {
            this.emergencyContact = emergencyContact;
            return this;
        }
        public PassengerDetailBuilder status(PassengerStatus status) {
            this.status = status;
            return this;
        }

        public PassengerDetail build() {
            PassengerDetail instance = new PassengerDetail();
            instance.passengerId = this.passengerId;
            instance.booking = this.booking;
            instance.name = this.name;
            instance.dateOfBirth = this.dateOfBirth;
            instance.gender = this.gender;
            instance.nationality = this.nationality;
            instance.passportNumber = this.passportNumber;
            instance.passportExpiry = this.passportExpiry;
            instance.dietaryRestrictions = this.dietaryRestrictions;
            instance.medicalNotes = this.medicalNotes;
            instance.emergencyContact = this.emergencyContact;
            instance.status = this.status;
            return instance;
        }
    }

}
