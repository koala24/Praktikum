package jcomrmiclient;

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
public class JComRMIClientMain {

    private static MatlabProxyFactoryOptions builder;
    private static MatlabProxyFactory factory;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("[Client]: Start / init ..");
        initMatlabControl();
        long startTime = System.nanoTime();
        System.out.println("[Client]: Starting TestCase2..");
        matlabcontrolTestCase2();
        System.out.println( "[Client]: Finished - Total Time: " + (System.nanoTime() - startTime) / 1000000 / 1000);
    }
    
    /**
     * 
     */
    public static void matlabcontrolTestCase1() {
        callMatlabWithMatlabcontrol("primes(100000000)");
    }
    
    public static void matlabcontrolTestCase2() {
        callMatlabWithMatlabcontrol("randint(3,3)");
    }
    
    
    // /MLAutomation
    public static void initMatlabControl() {
        //remember to set the path to the matlab executable on your computer in the       
        //MatlabProxyFactoryOptions.Builder().setMatlabLocation as below, it is very important 
        builder = new MatlabProxyFactoryOptions.Builder().setMatlabLocation("C:\\Program Files\\MATLAB\\bin\\matlab.exe")
                .setUsePreviouslyControlledSession(true)
                .setHidden(false)
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
                Logger.getLogger(JComRMIClientMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            proxy.disconnect();
        } catch (MatlabConnectionException ex) {
            Logger.getLogger(JComRMIClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
