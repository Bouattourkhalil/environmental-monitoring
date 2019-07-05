//heat and hum
#include "dht11.h"
#define DHT11_PIN A1
dht11 DHT;
//ligh
const int pResistor = A0;
int value;
//bleutooth
#include <SoftwareSerial.h>

  SoftwareSerial mavoieserie(9,10);
int info = 0;
void setup(){
  //humi //temp
  Serial.begin(9600);
  Serial.println("DHT TEST PROGRAM ");
  Serial.print("LIBRARY VERSION: ");
  Serial.println(DHT11LIB_VERSION);
  Serial.println();
  Serial.println("Type,\tstatus,\tHumidity (%),\tTemperature (C)");
//ligh
pinMode(pResistor, INPUT);
//bleu
    Serial.begin(9600);
    // Ouvre la voie série avec le module BT
    mavoieserie.begin(9600);
}

void loop(){

     //humi //temp
  int chk;
  //Serial.print("DHT11, \t");
  chk = DHT.read(DHT11_PIN);    // READ DATA
  switch (chk){
    case DHTLIB_OK:  
           //     Serial.print("OK ,"); 
                break;
    case DHTLIB_ERROR_CHECKSUM: 
                Serial.println("Checksum error,\t"); 
                break;
    case DHTLIB_ERROR_TIMEOUT: 
                Serial.println("Time out error,\t"); 
                break;
    default: 
                Serial.println("Unknown error,\t"); 
                break;
  }
 // DISPLAT DATA
  mavoieserie.print("humd:001:");
  mavoieserie.print(DHT.humidity,1);
  mavoieserie.print(";\n");
  mavoieserie.print("temp:001:");
  mavoieserie.print(DHT.temperature,1);
  mavoieserie.println(";");
 
  //ligh
  value = analogRead(pResistor);
   mavoieserie.print("ligh:001:");
   mavoieserie.print(value);
   mavoieserie.println(";");  
  delay(1500);  
      //bleu
  if (mavoieserie.available()) {
        Serial.write(mavoieserie.read());
    }
    if (Serial.available()) {
        mavoieserie.write(Serial.read());
        }
 
}

