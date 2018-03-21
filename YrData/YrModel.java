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
    private ArrayList<String> windDirection = yr.getNametag();
    private ArrayList<String> windSpeedValue =  yr.getWindSpeedValue();
    private ArrayList<String> windSpeedName = yr.getWindSpeedName();
    private ArrayList<String> weatherName = yr.getNametag();
    private ArrayList<String> date = yr.getFromtag();
    // Brukes kun for å hente størrelsen på lsiten
    private ArrayList<Integer> idList = yr.getIdList();
    int size = idList.size();
    // brukes kun for å sortere ut tidspunkt
    private ArrayList<Integer> period = yr.getPeriodTag();

    public YrModel(){

    }


    public void createModel(){

        String ontoURI = "https://www.auto.tuwien.ac.at/downloads/thinkhome/ontology/WeatherOntology.owl#";

        Property weatherProperty = model.createProperty(ontoURI + weatherName);
        Property tempProperty = model.createProperty(ontoURI + temp);
        Property windSpeedProperty = model.createProperty(ontoURI + windSpeedName);
        Property windSpeedValueProperty = model.createProperty(ontoURI + windSpeedValue);
        Property windDirectionProperty = model.createProperty(ontoURI + windDirection);
        Property dateProperty = model.createProperty(ontoURI + date);

        Resource weatherResource = model.createResource(ontoURI + "weatherCondition");


        for (int i = 0; i < size; i++){
            // Sjekker at tidsrommet er mellom 12 - 18 eller 18 - 00
            if (period.contains(2) || period.contains(3)){
                String temperatureItem = temp.get(i);
                String windSpeedNameItem = this.windSpeedName.get(i);
                String windSpeedValueItem = this.windSpeedValue.get(i);
                String weatherConditionItem = this.weatherName.get(i);
                String windDirectionItem = this.windDirection.get(i);
                String dateItem = date.get(i);

                Resource weatherData = model.createResource("http://example.com/weather" + dateItem, weatherResource)
                        .addProperty(tempProperty, temperatureItem)
                        .addProperty(windSpeedProperty, windSpeedNameItem)
                        .addProperty(windSpeedValueProperty, windSpeedValueItem)
                        .addProperty(weatherProperty, weatherConditionItem)
                        .addProperty(windDirectionProperty, windDirectionItem);
            }
        }
    }

    public void writeToFile() {
       // OntModel ontmodel = createOntologyModel(OntModelSpec.OWL_MEM, model);

        try {
            model.write(new FileOutputStream("testModel.ttl"), "turtle");
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


    public ArrayList<String> getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(ArrayList<String> windDirection) {
        this.windDirection = windDirection;
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
