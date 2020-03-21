package practicumopdracht.models;

import java.time.LocalDate;

/**
 * Detail model voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class Resultaat {
    private int studentennummer;
    private String volledigeNaamStudent;
    private LocalDate datum;
    private double cijfer;
    private boolean gehaald;
    private Vak hoortBijVak;

    private int id;
    private int masterId;

    public Resultaat(int masterId, int studentennummer, String volledigeNaamStudent, LocalDate datum, double cijfer, boolean gehaald, Vak hoortBijVak) {
        id = -1;
        this.masterId = masterId;
        this.studentennummer = studentennummer;
        this.volledigeNaamStudent = volledigeNaamStudent;
        this.datum = datum;
        this.cijfer = cijfer;
        this.gehaald = gehaald;
        this.hoortBijVak = hoortBijVak;
    }

    public int getId() {
        return id;
    }

    public int getStudentennummer() {
        return studentennummer;
    }

    public String getVolledigeNaamStudent() {
        return volledigeNaamStudent;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public double getCijfer() {
        return cijfer;
    }

    public boolean isGehaald() {
        return gehaald;
    }

    public Vak getHoortBijVak() {
        return hoortBijVak;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setStudentennummer(int studentennummer) {
        this.studentennummer = studentennummer;
    }

    public void setVolledigeNaamStudent(String volledigeNaamStudent) {
        this.volledigeNaamStudent = volledigeNaamStudent;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public void setCijfer(double cijfer) {
        this.cijfer = cijfer;
    }

    public void setGehaald(boolean gehaald) {
        this.gehaald = gehaald;
    }

    public void setHoortBijVak(Vak hoortBijVak) {
        this.hoortBijVak = hoortBijVak;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return hoortBijVak
                + "\n\nStudentennummer: " + studentennummer
                + "\nNaam van de student: " + volledigeNaamStudent
                + "\nDatum van toetsafname: " + datum
                + "\nBehaalde cijfer: " + cijfer
                + "\nStatus behaling: " + gehaald;
    }
}