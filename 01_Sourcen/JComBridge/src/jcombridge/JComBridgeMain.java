package jcombridge;

import java.util.logging.Level;
import java.util.logging.Logger;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;
import matlabcontrol.MatlabProxyFactoryOptions;

/**
 *
 * @author KOALA
 */
public class JComBridgeMain {
    
    private static MatlabProxyFactoryOptions builder;
    private static MatlabProxyFactory factory;

    private JComBridgeMain() {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("[Client]: Start / init ..");
        initMatlabControl();
        long startTime = System.nanoTime();
        System.out.println("Starte erste Primzahlenberechnung.. ");
        callMatlabWithMatlabcontrol("primes(100000000)");
        System.out.println("Erste Primzahlenberechnung beendet.");
        System.out.println("Starte zweite Primzahlenberechnung.. ");
        callMatlabWithMatlabcontrol("primes(100000000)");
        System.out.println("Zweite Primzahlenberechnung beendet.");
        System.out.println( "[Client]: Finished - Total Time: " + (System.nanoTime() - startTime) / 1000000 / 1000);
    }
    
    public static void initMatlabControl() {
        //remember to set the path to the matlab executable on your computer in the       
        //MatlabProxyFactoryOptions.Builder().setMatlabLocation as below, it is very important
        builder = new MatlabProxyFactoryOptions.Builder().setMatlabLocation("C:\\Program Files\\MATLAB\\bin\\matlab.exe")
                .setUsePreviouslyControlledSession(true)
                .setHidden(true)
                .build();

        //Create a proxy, which we will use to control MATLAB
        factory = new MatlabProxyFactory(builder);
    }

    /**
     * 
     * @param command
     */
    public static void callMatlabWithMatlabcontrol(String command) {
        MatlabProxy proxy;
        try {
            proxy = factory.getProxy();
            try {
                //Display 'hello world' just like when using the demo
                proxy.eval(command);
            } catch (MatlabInvocationException ex) {
                Logger.getLogger(JComBridgeMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Disconnect the proxy from MATLAB
            proxy.disconnect();
        } catch (MatlabConnectionException ex) {
            Logger.getLogger(JComBridgeMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
