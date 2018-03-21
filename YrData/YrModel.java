package YrData;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.apache.jena.rdf.model.ModelFactory.createDefaultModel;
import static org.apache.jena.rdf.model.ModelFactory.createOntologyModel;

public class YrModel {

    Yr yr = new Yr();
    Model model = createDefaultModel();

    private ArrayList<String> temp = yr.getTemprature();
    private ArrayList<String> windSpeedValue =  yr.getWindSpeedValue();
    private ArrayList<String> windSpeedName = yr.getWindSpeedName();
    private ArrayList<String> weatherName = yr.getNametag();
    private ArrayList<String> date = yr.getFromtag();
    private ArrayList<String> observedAt = yr.getObservedTag();
    // Brukes kun for å hente størrelsen på listen
    private ArrayList<Integer> idList = yr.getIdList();
    int size = idList.size();
    // brukes kun for å sortere ut tidspunkt
    private ArrayList<Integer> period = yr.getPeriodTag();

    public YrModel(){

    }


    public void createModel(){

        String ontoURI = "https://www.auto.tuwien.ac.at/downloads/thinkhome/ontology/WeatherOntology.owl#";
        model.setNsPrefix("wo", ontoURI);

        String w3TimeResource = "http://www.w3.org/2006/time#";
        model.setNsPrefix("w3time", w3TimeResource);


        Property weatherProperty = model.createProperty(ontoURI + "hasWeatherCondition");
        Property tempProperty = model.createProperty(ontoURI + "Temperature");
        Property windSpeedProperty = model.createProperty(ontoURI + "Wind");
        Property windSpeedValueProperty = model.createProperty(ontoURI + "hasWind");
        Property observedAtProperty = model.createProperty(ontoURI + "hasObservationTime");
        Property dateProperty = model.createProperty(w3TimeResource + "inDateTime");

        Resource weatherResource = model.createResource(ontoURI + "WeatherCondition");


        for (int i = 0; i < size; i++){
            // Sjekker at tidsrommet er mellom 12 - 18 eller 18 - 00
            if (period.contains(2) || period.contains(3)){
                String temperatureItem = temp.get(i);
                String windSpeedNameItem = windSpeedName.get(i);
                String windSpeedValueItem = windSpeedValue.get(i);
                String weatherConditionItem = weatherName.get(i);
                String dateItem = date.get(i);
                String timeItem = observedAt.get(i);

                Resource weatherData = model.createResource("http://example.com/weather#" + dateItem, weatherResource)
                        .addProperty(tempProperty, temperatureItem)
                        .addProperty(windSpeedProperty, windSpeedNameItem)
                        .addProperty(windSpeedValueProperty, windSpeedValueItem)
                        .addProperty(weatherProperty, weatherConditionItem)
                        .addProperty(dateProperty, dateItem)
                        .addProperty(observedAtProperty, timeItem);


            }
        }
    }

    public void writeToFile() {
       OntModel ontmodel = createOntologyModel(OntModelSpec.OWL_MEM, model);

        try {
            ontmodel.write(new FileOutputStream("weatherModel.ttl"), "Turtle");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public Model parse(){
        createModel();
        return model;
    }



    public ArrayList<String> getTemp() {
        return temp;
    }

    public void setTemp(ArrayList<String> temp) {
        this.temp = temp;
    }

    public ArrayList<String> getWindSpeedValue() {
        return windSpeedValue;
    }

    public void setWindSpeedValue(ArrayList<String> windSpeedValue) {
        this.windSpeedValue = windSpeedValue;
    }

    public ArrayList<String> getWindSpeedName() {
        return windSpeedName;
    }

    public void setWindSpeedName(ArrayList<String> windSpeedName) {
        this.windSpeedName = windSpeedName;
    }


}
