package org.hcl.oop_database_sem5;

public class FineItem {
    private Integer id;
    private String date;
    private String name;
    private String violation;
    private String passport;
    private Double fine;
// TODO: сделать проверки в сеттерах (на регулярках) регулярки мб в отдельный класс вынести
    public FineItem() {}

    public FineItem(String date, String name, String violation, String passport, Double fine) {
        setDate(date);
        setName(name);
        setViolation(violation);
        setPassport(passport);
        setFine(fine);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getViolation() {
        return violation;
    }

    public void setViolation(String violation) {
        this.violation = violation;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }
}
