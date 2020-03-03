package practicumopdracht.models;

public class Vak {
    private String vakNaam;
    private String toetsNaam;
    private int aantalGemaakteToetsen;

    public Vak(String vakNaam, String toetsNaam, int aantalGemaakteToetsen) {
        this.vakNaam = vakNaam;
        this.toetsNaam = toetsNaam;
        this.aantalGemaakteToetsen = aantalGemaakteToetsen;
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

    @Override
    public String toString() {
        return vakNaam + ", " + toetsNaam + ": " + aantalGemaakteToetsen + " toetsen gemaakt";
    }

}
