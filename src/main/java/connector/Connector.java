/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package connector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import configuration.Configuration;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import user_interface.message_window;

/**
 *Object for maintaining connection to server
 * @author kubaw
 */
public class Connector {
    
    public Configuration configuration;
    public boolean health;
    public Date ldt;
    public boolean error;
    public String version,bulid;
    /**
     * Constructor
     * @param oauth 
     */
    public Connector(Configuration configuration){
        this.configuration = configuration;
        ldt = new Date();
        error = false;
    }
    
    /**
     * Function for resetting 
     * @param url
     * @return String
     */
    public String url_builder(String url){
        return configuration.url+url;
    }
    
    /**
     * Function for preparing raw JSON
     * @param url
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement commit(String url,JFrame object) throws UnirestException{
        HttpResponse<JsonNode> response = response_creator(url);
        System.out.println("Trying to commit url:"+url);
        Parser parser = new Parser(parse_response(response));
        try{
            if ( parser == null ){
                new message_window(object,true,"Connection error...","");
                System.exit(0);
            }
            if ( parser.get_int("flag") < 0){
                switch(parser.get_int("flag")){
                    case -11:
                        new message_window(object,true,"Provided app token is wrong","APPTOKEN-ERROR");
                        break;
                    case -99:
                        new message_window(object,true,"Your login session has expired","SESSION-EXPIRED");
                        break;
                    case -88:
                        new message_window(object,true,"Database error when checking session token","");
                        break;
                }
            }
        }catch(Exception e){
            System.out.println("CONNECTOR ERROR: "+e.toString());
        }
        return parse_response(response);
    }
    
    /**
     * Function for preparing raw JSON
     * @param url
     * @return JsonElement
     * @throws UnirestException 
     */
    public JsonElement commit(String url,JDialog object) throws UnirestException{
        HttpResponse<JsonNode> response = response_creator(url);
        System.out.println("Trying to commit url:"+url);
        Parser parser = new Parser(parse_response(response));
        try{
            if ( parser == null ){
                new message_window(object,true,"Connection error...","");
                System.exit(0);
            }
            
            if ( parser.get_int("flag") < 0){
                switch(parser.get_int("flag")){
                    case -11:
                        new message_window(object,true,"Provided app token is wrong","APPTOKEN-ERROR");
                        break;
                    case -99:
                        new message_window(object,true,"Your login session has expired","SESSION-EXPIRED");
                        break;
                    case -88:
                        new message_window(object,true,"Database error when checking session token","");
                        break;
                }
            }
            
        }catch(Exception e){
            System.out.println("CONNECTOR ERROR: "+e.toString());
        }
        return parse_response(response);
    }
    
    /**
     * Function for creating response
     * @param url
     * @return String
     * @throws UnirestException 
     */
    HttpResponse<JsonNode> response_creator(String url) throws UnirestException{
        try{
            System.out.println("Creating response ("+url_builder(url)+")");
            return Unirest.get(url_builder(url)).asJson();
        }catch(UnirestException e){
            System.out.println("Failed to create response ("+e.toString()+")");
            return null;
        }
    } 

    /**
     * Function for parasing response for data
     * @param data
     * @return JsonElement
     */
    JsonElement parse_response(HttpResponse<JsonNode> data){
        System.out.println("Parasing response for data");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        return jp.parse(data.getBody().toString());
    }
    
    /**
     * Function for checking server health
     * @return JsonElement
     */
    public JsonElement health() throws UnirestException{
        try{
            System.out.println("Trying to check /health of "+url_builder("/health")+")");
            HttpResponse <JsonNode> response = response_creator("/health");
            JsonParser jp = new JsonParser();
            System.out.println("/health checked, returning response");
            health = true;
            return jp.parse(response.getBody().toString());
        }catch(Exception e){
            System.out.println("Failed to get /health response ("+e.toString()+")");
            health = false;
            return null;
        }
    }
    
    /**
     * Function for loading shelf content
     * @return JsonElement
     */
    public JsonElement load_shelf_content(String shelf_code){
        try{
            System.out.println("Loading shelf content..");
            HttpResponse <JsonNode> response = response_creator("/entrcoordinator-list/"+shelf_code);
            JsonParser jp = new JsonParser();
            return jp.parse(response.getBody().toString());
        }catch(Exception e){
            System.out.println("Failed to get shelf content ("+e.toString()+")");
            return null;
        }
    }
    
    /**
     * Function for authorizing user
     * @param pin
     * @return JsonElement
     */
    public JsonElement authorize_user(String shelf,String pin,String app_code){
        try{
            System.out.println("Authorize user..");
            HttpResponse <JsonNode> response = response_creator("/entrcoordinator-auth/"+app_code+"/"+shelf+"/"+pin);
            JsonParser jp = new JsonParser();
            return jp.parse(response.getBody().toString());
        }catch(Exception e){
            System.out.println("Failed to authorize user ("+e.toString()+")");
            return null;
        }
    }
    
    /**
     * Function for getting drawer item for item_manipulation_window object
     * @param entrc_ic_item_id
     * @param shelf
     * @param app_code
     * @return JsonElement
     */
    public JsonElement get_drawer_item(int entrc_ic_item_id,String shelf,String app_code){
        try{
            System.out.println("Loading item details...");
            HttpResponse <JsonNode> response = response_creator("/entrcoordinator-item-details/"+app_code+"/"+shelf+"/"+entrc_ic_item_id);
            JsonParser jp = new JsonParser();
            return jp.parse(response.getBody().toString());
        }catch(Exception e){
            System.out.println("Failed to get item details ("+e.toString()+")");
            return null;
        }
    }

    
}
