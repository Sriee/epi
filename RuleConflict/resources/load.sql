USE Rules;

# Sensor data
INSERT INTO Sensor VALUES(NULL, "Temperature1", 1, "", true, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "Temperature2", 1, "", true, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "WaterLevel1", 1, "", true, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "Temperature3", 1, "", false, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "Temperature4", 1, "F", false, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "WaterLevel2", 1, "", false, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "Temperature5", 1, "", true, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "Temperature6", 1, "", true, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "Temperature7", 1, "F", true, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "SoilMoisture1", 1, "%", true, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "WeatherApiTemperature1", 0, "F", true, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "WeatherApiHumidity1", 0, "F", true, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "WeatherApiRain1", 0, "F", true, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "SoilMoisture2", 1, "%", true, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "Temperature8", 1, "F", false, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "SoilMoisture3", 1, "%", true, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "Temperature9", 1, "F", false, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "SoilMoisture4", 1, "", false, NULL, NULL);
INSERT INTO Sensor VALUES(NULL, "Ultrasonic1", 1, "", true, NULL, NULL);

# Actuator data
INSERT INTO Actuator VALUES(NULL, "2", "Sprinkler1", false);
INSERT INTO Actuator VALUES(NULL, "3456", "LED1", true);
INSERT INTO Actuator VALUES(NULL, "5551268483084670819", "Sprinkler2", true);
INSERT INTO Actuator VALUES(NULL, "5551268483084670819", "Sprinkler3", true);
INSERT INTO Actuator VALUES(NULL, "7066147889533359447", "Sprinkler4", true);