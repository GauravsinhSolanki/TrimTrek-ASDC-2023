package org.example;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long barber_id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String address;


    private String phone;

    private String email;
    private String password;

    //TODO: implement personalized inventory
//    private Inventory inventory;

    @ElementCollection
    @CollectionTable(name = "specialty_haircuts", joinColumns = @JoinColumn(name = "barber_id"))
    @Column(name = "haircut")
    private List<String> specialtyHaircuts;

    @ElementCollection
    @CollectionTable(name = "specialty_services", joinColumns = @JoinColumn(name = "barber_id"))
    @Column(name = "services")
    private List<String> specialtyServices;

    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Shift> shifts;

    public Barber(String firstName, String lastName, String address, String phone, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Barber(Long barber_id, String firstName, String lastName, String address, String phone, String email, String password, List<String> specialtyHaircuts, List<String> specialtyServices) {
        this.barber_id = barber_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.specialtyHaircuts = specialtyHaircuts;
        this.specialtyServices = specialtyServices;
    }

    public Barber() {

    }

    public Long getBarber_id() {
        return barber_id;
    }

    public void setBarber_id(Long barber_id) {
        this.barber_id = barber_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getSpecialtyHaircuts() {
        return specialtyHaircuts;
    }

    public void setSpecialtyHaircuts(List<String> specialtyHaircuts) {
        this.specialtyHaircuts = specialtyHaircuts;
    }

    public List<String> getSpecialtyServices() {
        return specialtyServices;
    }

    public void setSpecialtyServices(List<String> specialtyServices) {
        this.specialtyServices = specialtyServices;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }

    @Override
    public String toString() {
        return "Barber{" +
                "barber_id=" + barber_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", specialtyHaircuts=" + specialtyHaircuts +
                ", specialtyServices=" + specialtyServices +
                ", shifts=" + shifts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barber barber = (Barber) o;
        return getBarber_id().equals(barber.getBarber_id()) && getFirstName().equals(barber.getFirstName()) && getLastName().equals(barber.getLastName()) && getAddress().equals(barber.getAddress()) && getPhone().equals(barber.getPhone()) && getEmail().equals(barber.getEmail()) && Objects.equals(getPassword(), barber.getPassword()) && Objects.equals(getSpecialtyHaircuts(), barber.getSpecialtyHaircuts()) && Objects.equals(getSpecialtyServices(), barber.getSpecialtyServices()) && Objects.equals(getShifts(), barber.getShifts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBarber_id(), getFirstName(), getLastName(), getAddress(), getPhone(), getEmail());
    }
}
