package practicumopdracht.models;

/**
 * Main model voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class Vak {
    private String vakNaam;
    private String toetsNaam;
    private int aantalGemaakteToetsen;

    public Vak(String vakNaam, String toetsNaam, int aantalGemaakteToetsen) {
        this.vakNaam = vakNaam;
        this.toetsNaam = toetsNaam;
        this.aantalGemaakteToetsen = aantalGemaakteToetsen;
    }

    @Override
    public String toString() {
        return "Naam van het vak: " + vakNaam
                + "\nNaam van de toets: " + toetsNaam
                + "\nAantal gemaakte toetsen: " + aantalGemaakteToetsen;
    }

}
