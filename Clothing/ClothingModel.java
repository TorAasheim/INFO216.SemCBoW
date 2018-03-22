package Clothing;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import java.io.FileOutputStream;
import java.util.ArrayList;

import static org.apache.jena.rdf.model.ModelFactory.createOntologyModel;

public class ClothingModel {

    ClothingData data = new ClothingData();
    Model clothingModel = ModelFactory.createDefaultModel();

    private ArrayList<String> clothingType = data.getClothing();
    private int size = data.getClothing().size();


    public void createModel(){

        String semClothURI = "http://www.semanticweb.org/ontologies/2015/02/semcloth.owl#";
        clothingModel.setNsPrefix("clo", semClothURI);

        String dbrClothes = "http://dbpedia.org/resource/clothes";
        clothingModel.setNsPrefix("dbr", dbrClothes);

        Resource clothingResource = clothingModel.createResource(dbrClothes + "Garments");
        Property clothingProperty = clothingModel.createProperty(semClothURI + "isClothingType");

        for (int i = 0; i < this.size; i++){
            String clothingItem = clothingType.get(i);

            Resource clothingData = clothingModel.createResource("http://example.com/Clothing#" + clothingItem, clothingResource)
                    .addProperty(clothingProperty, clothingItem);
        }
    }

    public void writeFile(){

        OntModel clothingOntModel = createOntologyModel(OntModelSpec.OWL_MEM, clothingModel);

        try{
            clothingOntModel.write(new FileOutputStream("Clothing.ttl"), "Turtle");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Model parseModel(){
        createModel();
        return clothingModel;
    }

}
