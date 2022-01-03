/*
 * comconnect_arduino.c
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all rights reserved
 * Program for loading data from serialport
 * v1.0.0
 */
String data = ""; //Pusty ciąg odebranych danych

void setup() {
  Serial.begin(9600);           //Load the communication
  pinMode(LED_BUILTIN, OUTPUT); // Load the LED_BUILTIN as OUTPUT
}

void loop() {
  if(Serial.available() > 0) { 
    data = Serial.readStringUntil('\n');            //Load data to data variable
    Serial.println("LOADED" + data + "!"); //Print to serial
    digitalWrite(LED_BUILTIN, HIGH);
    delay(1000);                       
    digitalWrite(LED_BUILTIN, LOW);    
  }
}
