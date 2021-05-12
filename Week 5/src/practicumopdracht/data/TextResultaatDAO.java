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
 */
public class TextResultaatDAO extends ResultaatDAO {
    private static final String FILENAME = "/oop2/Week 5/textfiles/ResultatenFile.txt";
    private Vak vak;

    @Override
    public boolean save() {
        try (PrintWriter writer = new PrintWriter(FILENAME)) {
            for (Resultaat resultaat : objects) {
                writer.println(resultaat.getMasterId() + ", " + resultaat.getStudentennummer() + ", " + resultaat.getVolledigeNaamStudent() + ", " + resultaat.getDatum() + ", " + resultaat.getCijfer() + ", " + resultaat.getGehaald());
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

                for (String string : data) {
                    arrayList.add(string.trim());
                }
                objects.add(new Resultaat(Integer.parseInt(arrayList.get(0)), Integer.parseInt(arrayList.get(1)), arrayList.get(2), LocalDate.parse(arrayList.get(3)), Double.parseDouble(arrayList.get(4)), Boolean.parseBoolean(arrayList.get(5))));
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
