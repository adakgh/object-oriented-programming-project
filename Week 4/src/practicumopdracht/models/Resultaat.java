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

    public void setId(int id) {
        this.id = id;
    }

    public Vak getHoortBijVak() {
        return hoortBijVak;
    }

    public int getMasterId() {
        return masterId;
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