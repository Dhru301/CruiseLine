package com.cruiseline.modules.booking.dto;

import com.cruiseline.common.enums.PassengerStatus;
import com.cruiseline.modules.booking.entity.PassengerDetail;

import java.time.LocalDate;

public class PassengerDetailResponse {
    private Long passengerId;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
    private String passportNumber;
    private LocalDate passportExpiry;
    private String dietaryRestrictions;
    private String medicalNotes;
    private String emergencyContact;
    private PassengerStatus status;

    public static PassengerDetailResponse from(PassengerDetail p) {
        return PassengerDetailResponse.builder()
                .passengerId(p.getPassengerId())
                .name(p.getName())
                .dateOfBirth(p.getDateOfBirth())
                .gender(p.getGender())
                .nationality(p.getNationality())
                .passportNumber(p.getPassportNumber())
                .passportExpiry(p.getPassportExpiry())
                .dietaryRestrictions(p.getDietaryRestrictions())
                .medicalNotes(p.getMedicalNotes())
                .emergencyContact(p.getEmergencyContact())
                .status(p.getStatus())
                .build();
    }

    public PassengerDetailResponse() {
    }

    public PassengerDetailResponse(Long passengerId, String name, LocalDate dateOfBirth, String gender, String nationality, String passportNumber, LocalDate passportExpiry, String dietaryRestrictions, String medicalNotes, String emergencyContact, PassengerStatus status) {
        this.passengerId = passengerId;
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

    public static PassengerDetailResponseBuilder builder() {
        return new PassengerDetailResponseBuilder();
    }

    public static class PassengerDetailResponseBuilder {
        private Long passengerId;
        private String name;
        private LocalDate dateOfBirth;
        private String gender;
        private String nationality;
        private String passportNumber;
        private LocalDate passportExpiry;
        private String dietaryRestrictions;
        private String medicalNotes;
        private String emergencyContact;
        private PassengerStatus status;

        public PassengerDetailResponseBuilder passengerId(Long passengerId) {
            this.passengerId = passengerId;
            return this;
        }
        public PassengerDetailResponseBuilder name(String name) {
            this.name = name;
            return this;
        }
        public PassengerDetailResponseBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        public PassengerDetailResponseBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }
        public PassengerDetailResponseBuilder nationality(String nationality) {
            this.nationality = nationality;
            return this;
        }
        public PassengerDetailResponseBuilder passportNumber(String passportNumber) {
            this.passportNumber = passportNumber;
            return this;
        }
        public PassengerDetailResponseBuilder passportExpiry(LocalDate passportExpiry) {
            this.passportExpiry = passportExpiry;
            return this;
        }
        public PassengerDetailResponseBuilder dietaryRestrictions(String dietaryRestrictions) {
            this.dietaryRestrictions = dietaryRestrictions;
            return this;
        }
        public PassengerDetailResponseBuilder medicalNotes(String medicalNotes) {
            this.medicalNotes = medicalNotes;
            return this;
        }
        public PassengerDetailResponseBuilder emergencyContact(String emergencyContact) {
            this.emergencyContact = emergencyContact;
            return this;
        }
        public PassengerDetailResponseBuilder status(PassengerStatus status) {
            this.status = status;
            return this;
        }

        public PassengerDetailResponse build() {
            PassengerDetailResponse instance = new PassengerDetailResponse();
            instance.passengerId = this.passengerId;
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
