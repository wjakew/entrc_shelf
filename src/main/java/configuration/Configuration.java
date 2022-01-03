/*
by Jakub Wawak 2021
kubawawak@gmail.com
all rights reserved
 */
package configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 *Object for creating configuration file
 * @author jakubwawak
 */
public class Configuration {
    
    public String configuration_file_path;
    public boolean error,file_found;
    public String version,build_number;
    
    public boolean standard_file;
    
    // fields from file
    public String url;
    public String apptoken;
    public String entrc_ic_drawer_code;
    public String time;
    
    /**
     * Constructor
     */
    public Configuration(){
        error = false;
        file_found = false;
        configuration_file_path = "";
        standard_file = false;
    }
    
    /**
     * Function for finding config file in directory
     * @return String
     * @throws IOException 
     */
    public String look_for_file() throws IOException{
        File dir = new File(".");
        System.out.println("Looking for config files in: "+dir.getAbsolutePath());
        File[] directory_content = dir.listFiles();
        for(File child : directory_content){
            if (child.getName().contains(".shelf")){
                configuration_file_path = child.getAbsolutePath();
                System.out.println("Configuration file found: "+configuration_file_path);
                file_found = true;
                return child.getAbsolutePath();
            }
        }
        return "blank";
    }
    
    /**
     * Function for loading file from file
     */
    public void load_file(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(configuration_file_path));
            String line;
            while((line = br.readLine()) != null){
                if ( line.contains("url") ){
                    url = line.split("%")[1];
                }
                else if ( line.contains("apptoken")){
                    apptoken = line.split("%")[1];
                }
                else if ( line.contains("entrc_ic_drawer_name")){
                    entrc_ic_drawer_code = line.split("%")[1];
                }
                else if ( line.contains("time")){
                    time = line.split("%")[1];
                }
                else{
                    error = true;
                }
            }
            if ( entrc_ic_drawer_code.contains("standard_")){
                standard_file = true;
            }
        }catch(FileNotFoundException e){
            error = true;
        }catch(IOException e){
            error = true;
        }
    }
    
    /**
     * Function for showing glances
     */
    public void show_glances(){
        System.out.println("url: "+url);
        System.out.println("appcode: "+apptoken);
        System.out.println("entrc_ic_dawer_code: "+entrc_ic_drawer_code);
        System.out.println("time: "+time);
    }
    
}
