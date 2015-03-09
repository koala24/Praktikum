package jcomrmiserver;

/**
 *
 * @author KOALA
 */
public class JComRMIServerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("[Server]: Start..");
        long startTime = System.nanoTime();
        ClientProcessHandler processHandler = new ClientProcessHandler();
        System.out.println("[Server]: Start first client..");
        processHandler.startClient();
        System.out.println("[Server]: Start second client..");
        processHandler.startClient();
        processHandler.waitForClients();
        System.out.println("[Server]: All Clients finished! - Total Time: " + (System.nanoTime() - startTime) / 1000000 / 1000);
    }
    
}
