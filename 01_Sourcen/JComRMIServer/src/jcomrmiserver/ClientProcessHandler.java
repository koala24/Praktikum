package jcomrmiserver;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KOALA
 */
public class ClientProcessHandler {

    public List<ClientProcess> clientProcesses;

    public ClientProcessHandler() {
        clientProcesses = new ArrayList<>();
    }

    /**
     *
     * @param request
     */
    public void startClient() {
        ClientProcess process = new ClientProcess();
        clientProcesses.add(process);
        new Thread(process).start();
    }

    public void waitForClients() {
        int clientsRunning = 0;
        do {
            clientsRunning = 0;
            for (ClientProcess process : this.clientProcesses) {
                if (process.isRunning()) {
                    clientsRunning += 1;
                }
            }
        } while (clientsRunning != 0);
    }

}
