package RDF;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.FileWriter;
import java.io.IOException;

public class RDFController {

    private Model model;

    public RDFController(){
        model = ModelFactory.createDefaultModel();
    }

    public void addModel(Model model){
        model.add(model);
    }

    public ResultSet runSparql(String queryString){
        queryString = "PREFIX clo: <./Clothing Ontology/semantic-clothing.owl> " + queryString;
        queryString = "PREFIX we: <https://www.auto.tuwien.ac.at/downloads/thinkhome/ontology/WeatherOntology.owl#>" + queryString;

        Query query = null;
       try {
           query = QueryFactory.create(queryString);
       }catch(Exception e){
           System.out.println(e.toString());
           return null;
       }

        QueryExecution execution = QueryExecutionFactory.create(query, model);
        ResultSet result = execution.execSelect();
        return result;

    }

    public void saveModel(String name, Model model){
        FileWriter file = null;

        try{
            file = new FileWriter(name);
        }catch(IOException e){
            e.printStackTrace();
        }
        model.write(file, "Turtle");
    }


}
