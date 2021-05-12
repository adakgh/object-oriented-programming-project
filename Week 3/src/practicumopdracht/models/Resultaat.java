package practicumopdracht.models;

import java.time.LocalDate;

/**
 * Detail model voor OOP2 practicumopdracht.
 *
 */
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
