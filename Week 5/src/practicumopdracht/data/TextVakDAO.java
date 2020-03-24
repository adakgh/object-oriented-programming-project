package practicumopdracht.data;

import practicumopdracht.models.Vak;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Master text DAO voor OOP2 practicumopdracht.
 *
 * @author Ghizlane el Adak
 */
public class TextVakDAO extends VakDAO {
    private static final String FILENAME = "/Users/ghizlane/oop2/Week 5/textfiles/VakkenFile.txt";

    @Override
    public boolean save() {
        try (PrintWriter writer = new PrintWriter(FILENAME)) {
            for (Vak vak : objects) {
                writer.println(vak.toString());
            }
            return true;
        } catch (FileNotFoundException ex) {
            System.err.println("VakkenFile niet gevonden");
        } catch (NullPointerException ex) {
            System.err.println("Vak object is null");
        } catch (Exception ex) {
            System.err.println("Vak exception: " + ex);
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
                objects.add(new Vak(arrayList.get(0), arrayList.get(1), Integer.parseInt(arrayList.get(2))));
            }
            return true;
        } catch (FileNotFoundException ex) {
            System.err.println("VakkenFile niet gevonden");
        } catch (NullPointerException ex) {
            System.err.println("Vak object is null");
        } catch (Exception ex) {
            System.err.println("Vak exception: " + ex);
        }
        return false;
    }
}