package practicumopdracht.data;

import practicumopdracht.models.Vak;

import java.io.*;
import java.util.ArrayList;

/**
 * Master Binary DAO voor OOP2 practicumopdracht.
 *
 */
public class BinaryVakDAO extends VakDAO {
    private static final String FILENAME = "/oop2/Week 6/textfiles/VakkenFile.dat";

    @Override
    public boolean save() {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(FILENAME))) {
            for (Vak vak : objects) {
                dataOutputStream.writeUTF(vak.getVakNaam() + ", " + vak.getToetsNaam() + ", " + vak.getAantalGemaakteToetsen() + ", " + vak.getId());
            }
            return true;
        } catch (FileNotFoundException ex) {
            System.err.println("VakkenFile niet gevonden");
        } catch (NullPointerException ex) {
            System.err.println("Vak object is null");
        } catch (IOException ex) {
            System.err.println("IO Exception");
        } catch (Exception ex) {
            System.err.println("Vak exception: " + ex);
        }
        return false;
    }

    @Override
    public boolean load() {
        objects = new ArrayList<>();
        String[] data;

        try (DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(FILENAME)))) {
            while (dataInputStream.available() > 0) {
                data = dataInputStream.readUTF().split(",");

                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].trim();
                }
                objects.add(new Vak(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));
            }
            return true;
        } catch (FileNotFoundException ex) {
            System.err.println("VakkenFile niet gevonden");
        } catch (EOFException ex) {
            System.err.println("Alle data is gelezen");
        } catch (IOException e) {
            System.err.println("IO Exception");
        } catch (Exception ex) {
            System.err.println("Vak exception: " + ex);
        }
        return false;
    }
}
