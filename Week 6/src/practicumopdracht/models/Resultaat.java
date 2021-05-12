package practicumopdracht.models;

import practicumopdracht.MainApplication;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Detail model voor OOP2 practicumopdracht.
 *
 */
public class Resultaat implements Serializable {
    private transient Vak hoortBijVak;
    private int studentennummer;
    private String volledigeNaamStudent;
    private LocalDate datum;
    private double cijfer;
    private boolean gehaald;

    private int id;
    private int masterId;

    public Resultaat(int masterId, int studentennummer, String volledigeNaamStudent, LocalDate datum, double cijfer, boolean gehaald) {
        this.masterId = masterId;
        this.studentennummer = studentennummer;
        this.volledigeNaamStudent = volledigeNaamStudent;
        this.datum = datum;
        this.cijfer = cijfer;
        this.gehaald = gehaald;
        this.hoortBijVak = MainApplication.getVakDAO().get(masterId);
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

    public boolean getGehaald() {
        return gehaald;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Studentennummer: " + studentennummer
                + "\nNaam van de student: " + volledigeNaamStudent
                + "\nDatum van toetsafname: " + datum
                + "\nBehaalde cijfer: " + cijfer
                + "\nStatus behaling: " + gehaald;
    }
}
