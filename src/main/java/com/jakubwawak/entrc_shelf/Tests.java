/*
by Jakub Wawak 2021
kubawawak@gmail.com
all rights reserved
 */
package com.jakubwawak.entrc_shelf;

import ComConnect.ComConnect;
import configuration.Configuration;
import java.io.IOException;
import user_interface.configuration_window;

/**
 *Object for creating tests
 * @author jakubwawak
 */
public class Tests {
    
    public Tests() throws IOException{
        System.out.println("Tests are running...");
        run();
    }
    
    /**
     * Function for running test
     */
    void run() throws IOException{
        //Configuration configuration = new Configuration();
        //configuration.look_for_file();
        //configuration.load_file();
        //configuration.show_glances();
        //new configuration_window(configuration);
        ComConnect cc = new ComConnect();
        cc.search_ports();
    }
    
}
