/*
by Jakub Wawak 2021
kubawawak@gmail.com
all rights reserved
 */
package com.jakubwawak.entrc_shelf;

import com.formdev.flatlaf.FlatDarkLaf;
import configuration.Configuration;
import java.io.IOException;
import user_interface.configuration_window;

/**
 *Main program function
 * @author jakubwawak
 */
public class Entrc_Shelf {
    
    public static String version = "v1.0.0";
    public static String build_number = "ESHELF-030122REV1";
    public static int debug = 0;
    
    
    /**
     * Main object function
     * @param args 
     */
    public static void main(String[] args) throws IOException{
        FlatDarkLaf.setup();
        show_header();
        if ( debug == 1){
            new Tests();
        }
        else{
            System.out.println("Starting entrc_shelf service...");
            Configuration configuration = new Configuration();
            configuration.look_for_file();
            if ( configuration.file_found ){
                // config file found
                configuration.load_file();  // loading file
                
                if ( configuration.standard_file ){
                    // configuration file is standard
                    // need to ask about shelf code
                    System.out.println("Configuration file is standard");
                }
                else{
                    // configuration file contains shelf code
                    System.out.println("Configuration file contains shelf name");
                    
                }
                new configuration_window(configuration);
                System.out.println("Loaded configuration file, loading application...");
            }
            else{
                // config file not found
                System.out.println("Configuration file not found");
                new configuration_window(configuration);
            }
        }
    }
    
    /**
     * Function for showing header
     */
    public static void show_header(){
        String header = "            _                     _          _  __\n" +
                        "  ___ _ __ | |_ _ __ ___      ___| |__   ___| |/ _|\n" +
                        " / _ \\ '_ \\| __| '__/ __|____/ __| '_ \\ / _ \\ | |_\n" +
                        "|  __/ | | | |_| | | (_|_____\\__ \\ | | |  __/ |  _|\n" +
                        " \\___|_| |_|\\__|_|  \\___|    |___/_| |_|\\___|_|_|"+version+build_number;
        System.out.println(header);
    }
}
