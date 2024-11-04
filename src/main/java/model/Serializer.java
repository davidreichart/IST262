package model;

import java.io.*;

public class Serializer {

    public Serializer() {
    }

    public static void saveState(ApplicationContext applicationContext) {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/state.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(applicationContext);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in state.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static ApplicationContext loadState() {
        ApplicationContext applicationContext = null;
        try {
            FileInputStream fileIn = new FileInputStream("src/main/resources/state.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            applicationContext = (ApplicationContext) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Deserialized data is loaded from state.ser");
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("ApplicationContext class not found");
            c.printStackTrace();
            return null;
        }
        return applicationContext;
    }
}
