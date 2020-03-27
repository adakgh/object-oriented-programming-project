package practicumopdracht.data;

import practicumopdracht.models.Resultaat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectResultaatDAO extends ResultaatDAO {
    private static final String FILENAME = "/Users/ghizlane/oop2/Week 6/textfiles/ResultatenFile.dat";

    @Override
    public boolean save() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FILENAME)))) {
            objectOutputStream.writeObject(objects);
            return true;
        } catch (FileNotFoundException ex) {
            System.err.println("ResultatenFile niet gevonden");
        } catch (NullPointerException ex) {
            System.err.println("Resultaat object is null");
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception ex) {
            System.err.println("Resultaat exception: " + ex);
        }
        return false;
    }

    @Override
    public boolean load() {
        objects = new ArrayList<>();

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(FILENAME)))) {

            while (true) {
                objects = ((List<Resultaat>) objectInputStream.readObject());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("ResultatenFile not Found");
        } catch (EOFException ex) {
            System.err.println("Alles is gelezen");
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception ex) {
            System.err.println("Resultaat exception: " + ex);
        }
        return false;
    }
}