package practicumopdracht.data;

import practicumopdracht.models.Resultaat;
import practicumopdracht.models.Vak;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Detail text DAO voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class TextResultaatDAO extends ResultaatDAO {
    private static final String FILENAME = "/Users/ghizlane/oop2/Week 5/textfiles/ResultatenFile.txt";

    @Override
    public boolean save() {
        try (PrintWriter writer = new PrintWriter(FILENAME)) {
            for (Resultaat resultaat : objects) {
                writer.println(resultaat.toString());
            }
            return true;
        } catch (FileNotFoundException ex) {
            System.err.println("ResultatenFile niet gevonden");
        } catch (NullPointerException ex) {
            System.err.println("Resultaat object is null");
        } catch (Exception ex) {
            System.err.println("Resultaat exception: " + ex);
        }
        return false;
    }

    @Override
    public boolean load() {
        objects = new ArrayList<>();
        String[] data;
        ArrayList<String> arrayList;

        try (Scanner input = new Scanner(new File(FILENAME))) {
            while (input.hasNextLine()) {
                data = input.nextLine().split(",");
                arrayList = new ArrayList<>();

                for (String s : data) {
                    arrayList.add(s.trim());
                }
                objects.add(new Resultaat(Integer.parseInt(arrayList.get(0)), Integer.parseInt(arrayList.get(1)), arrayList.get(2), LocalDate.parse(arrayList.get(3)), Double.parseDouble(arrayList.get(4)), Boolean.parseBoolean(arrayList.get(5)), new Vak(arrayList.get(6), arrayList.get(7), Integer.parseInt(arrayList.get(8)))));
            }
            return true;
        } catch (FileNotFoundException ex) {
            System.err.println("ResultatenFile niet gevonden");
        } catch (NullPointerException ex) {
            System.err.println("Resultaat object is null");
        } catch (Exception ex) {
            System.err.println("Resultaat exception: " + ex);
        }
        return false;
    }
}
