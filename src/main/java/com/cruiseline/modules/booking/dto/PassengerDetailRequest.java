package com.cruiseline.modules.booking.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class PassengerDetailRequest {
    @NotBlank
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
    private String passportNumber;
    private LocalDate passportExpiry;
    private String dietaryRestrictions;
    private String medicalNotes;
    private String emergencyContact;

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

}
