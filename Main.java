import RDF.RDFController;
import RDF.RDFReader;
import YrData.Yr;
import YrData.YrModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class Main {

    public static void main(String[] args) {

        YrModel weatherModel = new YrModel();
        weatherModel.parse();
        weatherModel.writeToFile();

        Model model = ModelFactory.createDefaultModel();
        model.read("./ClothingOntology/semantic-clothing.ttl");

        RDFController rdfc = new RDFController();
        rdfc.addModel(model);
        RDFReader rdfreader = new RDFReader(rdfc);

        rdfreader.queryClothingClasses();


    }
}
