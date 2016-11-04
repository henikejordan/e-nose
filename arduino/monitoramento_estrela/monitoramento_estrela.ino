/*This program was written for the Arduino Uno. The Uno has an XBee Series 2 RF Module connected to it as a coordinator.
  The Uno uses the XBee coordinator to communicate with two router or end point XBees with temperature sensors.
  This program recieves the temperature readings from the two endpoint XBees and writes the data to the serial monitor */

//Carrega a biblioteca do RTC DS1307
#include <DS1307.h>

//Modulo RTC DS1307 ligado as portas A4 e A5 do Arduino
DS1307 rtc(A8, A9);

/*Each Xbee has a unque 64 bit address.
  The first 32 bits are common to all XBee.
  The following four ints (each int holds an address byte) hold the unique 32 bits of the second half of the XBee address*/
int addr1;
int addr2;
int addr3;
int addr4;

//This function returns analog pins value
int calculateXBeeAnalog(int analogMSB, int analogLSB) {
  return analogLSB + (analogMSB * 256); //Turn the two bytes into an integer value
}

//this function calculates temp in C from temp sensor
float calculateTempC(float volt) {
  return volt * 100;
}

//this function calculates luminosity from temp sensor
float calculateLuminosity(float value) {
  return value / 10.23;
}

//this function calculates humidity soil from temp sensor
float calculateHumiditySoil(float volt) {
  return (-2500 * volt / 9) + 200;
}

//this function calculates humidity air from temp sensor
float calculateHumidityAir(float value, float temp) {
  return (value / 10.23 * 1.31) / (1.0546 - (0.00216 * temp));
}

//This function takes an XBee analog pin reading and converts it to a voltage value
float calculateXBeeVolt(int analogMSB, int analogLSB) {
  int analogReading = calculateXBeeAnalog(analogMSB, analogLSB);
  float volt = ((float)analogReading / 1023) * 1.23; //Convert the analog value to a voltage value
  return volt;
}

//Function takes in the XBee address and returns the identity of the Xbee that sent the temperature data
String identifySensor(int a1, int a2, int a3, int a4) {
  int rout1[] = {64, 226, 196, 251}; //Arrays are the 32 bit address of the two XBees routers
  int rout2[] = {64, 230, 74, 29};
  if (a1 == rout1[0] && a2 == rout1[1] && a3 == rout1[2] && a4 == rout1[3]) { //Check if Sensor 1
    return "SENSOR 1";
  } //temp data is from XBee one
  else if (a1 == rout2[0] && a2 == rout2[1] && a3 == rout2[2] && a4 == rout2[3]) { //Check if Sensor 2
    return "SENSOR 2";
  } //temp data is from XBee two

  else {
    return "I don't know this sensor";  //Data is from an unknown XBee
  }
}

void setup()  {
  //Aciona o relogio
  rtc.halt(false);

  //As linhas abaixo setam a data e hora do modulo
  //e podem ser comentada apos a primeira utilizacao
  //rtc.setDOW(TUESDAY);      //Define o dia da semana
  //rtc.setTime(20, 37, 0);     //Define o horario
  //rtc.setDate(20, 7, 2016);   //Define o dia, mes e ano

  //Definicoes do pino SQW/Out
  rtc.setSQWRate(SQW_RATE_1);
  rtc.enableSQW(true);

  Serial.begin(9600); //start the serial communication
  while (!Serial);
}

void loop()  {
  if (Serial.available() >= 21) { // Wait for coordinator to recieve full XBee frame
    if (Serial.read() == 0x7E) { // Look for 7E because it is the start byte
      for (int i = 1; i < 19; i++) { // Skip through the frame to get to the unique 32 bit address
        //get each byte of the XBee address
        if (i == 8) {
          addr1 = Serial.read();
        }
        else if (i == 9) {
          addr2 = Serial.read();
        }
        else if (i == 10) {
          addr3 = Serial.read();
        }
        else if (i == 11) {
          addr4 = Serial.read();
        }
        else {
          byte discardByte = Serial.read();  //else throwout byte we don't need it
        }
      }
      int analogMSB = Serial.read(); // Read the first analog byte data
      int analogLSB = Serial.read(); // Read the second byte
      Serial.print(identifySensor(addr1, addr2, addr3, addr4)); //get identity of XBee and print it
      Serial.print(";");
      float temp = calculateTempC(calculateXBeeVolt(analogMSB, analogLSB));
      Serial.print(temp); //calculate temperature value from voltage value
      analogMSB = Serial.read(); // Read the first analog byte data
      analogLSB = Serial.read(); // Read the second byte
      Serial.print(";");
      Serial.print(calculateHumidityAir(calculateXBeeAnalog(analogMSB, analogLSB), temp));
      analogMSB = Serial.read(); // Read the first analog byte data
      analogLSB = Serial.read(); // Read the second byte
      Serial.print(";");
      Serial.print(calculateLuminosity(calculateXBeeAnalog(analogMSB, analogLSB)));
      analogMSB = Serial.read(); // Read the first analog byte data
      analogLSB = Serial.read(); // Read the second byte
      Serial.print(";");
      Serial.print(calculateHumiditySoil(calculateXBeeVolt(analogMSB, analogLSB)));
      Serial.print(";");
      //Show datas in Serial Monitor
      //Serial.print("Hora : ");
      //Serial.print(rtc.getTimeStr());
      //Serial.print(" ");
      //Serial.print("Data : ");
      //Serial.print(rtc.getDateStr(FORMAT_SHORT));
      //Serial.print(" ");
      //Serial.print(rtc.getDOWStr(FORMAT_SHORT));
    }
  }
  delay(10); //delay to allow operations to complete
}

