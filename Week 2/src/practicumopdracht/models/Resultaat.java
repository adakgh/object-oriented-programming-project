package practicumopdracht.models;

import java.time.LocalDate;

public class Resultaat {
    private int studentennummer;
    private String volledigeNaamStudent;
    private LocalDate datum;
    private double cijfer;
    private boolean gehaald;
    private Vak hoortBijVak;

    public Resultaat(int studentennummer, String volledigeNaamStudent, LocalDate datum, double cijfer, boolean gehaald, Vak hoortBijVak) {
        this.studentennummer = studentennummer;
        this.volledigeNaamStudent = volledigeNaamStudent;
        this.datum = datum;
        this.cijfer = cijfer;
        this.gehaald = gehaald;
        this.hoortBijVak = hoortBijVak;
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

    @Override
    public String toString() {
        return hoortBijVak + " op: " + datum + "\n" + studentennummer + " " + volledigeNaamStudent + ", cijfer: " + cijfer + ", status gehaald: " + gehaald;
    }
}