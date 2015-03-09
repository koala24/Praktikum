package jcomrmiserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KOALA
 */
public class ClientProcess implements Runnable {
    
    public static final String CLIENT_JAR = "C:/Users/KOALA/Desktop/Praktikum/01_Sourcen/JComRMIClient/dist/JComRMIClient.jar";
    private BufferedReader inputStream;
    private boolean running = false;
    
    public ClientProcess() {
        inputStream = null;
    }

    @Override
    public void run() {
        running = true;
        String inLine = null;
        try {
            Process process = Runtime.getRuntime().exec("java -jar " + CLIENT_JAR);
            try {
                process.waitFor();
                this.inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while ((inLine = inputStream.readLine()) != null) {
                    System.out.println(inLine);
                }
            } catch (InterruptedException ex) {
                System.out.println("error waitFor client process");
            }
        } catch (IOException ex) {
            System.out.println("error exec client process");
        }
        running = false;
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
}
