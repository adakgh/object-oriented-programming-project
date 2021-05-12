package practicumopdracht.models;

/**
 * Main model voor OOP2 practicumopdracht.
 *
 */
public class Vak {
    private String vakNaam;
    private String toetsNaam;
    private int aantalGemaakteToetsen;

    private int id;

    public Vak(String vakNaam, String toetsNaam, int aantalGemaakteToetsen) {
        this.vakNaam = vakNaam;
        this.toetsNaam = toetsNaam;
        this.aantalGemaakteToetsen = aantalGemaakteToetsen;
    }

    public Vak(String vakNaam, String toetsNaam, int aantalGemaakteToetsen, int id) {
        this(vakNaam, toetsNaam, aantalGemaakteToetsen);
        this.id = id;
    }

    public String getVakNaam() {
        return vakNaam;
    }

    public String getToetsNaam() {
        return toetsNaam;
    }

    public int getAantalGemaakteToetsen() {
        return aantalGemaakteToetsen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Naam van het vak: " + vakNaam
                + "\nNaam van de toets: " + toetsNaam
                + "\nAantal gemaakte toetsen: " + aantalGemaakteToetsen;
    }

}
