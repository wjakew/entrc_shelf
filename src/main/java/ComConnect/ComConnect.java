/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package ComConnect;

import gnu.io.CommPortIdentifier;
import java.util.Enumeration;

/**
 *Object for connecting to com
 * @author jakubwawak
 */
public class ComConnect {
    
    String version = "v0.0.1";
    CommPortIdentifier serialPortId;
    Enumeration enumComm;
    
    /**
     * Constructor
     */
    public ComConnect(){
        System.out.println("ComConnect "+version);
        enumComm = CommPortIdentifier.getPortIdentifiers();
    }
    
    /**
     * Function for loading and checking connection
     * @return CommPortIdentifier
     */
    public CommPortIdentifier search_ports(){
        CommPortIdentifier serialPortId = (CommPortIdentifier) enumComm.nextElement();
        if (serialPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println("Loaded Arduino on: "+serialPortId.getName());
                return serialPortId;
        }
        return null;
    }
    
}
