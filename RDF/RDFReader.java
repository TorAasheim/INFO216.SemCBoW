package RDF;

import org.apache.jena.atlas.iterator.Iter;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RDFReader<E> {

    Model model = ModelFactory.createDefaultModel();
    ArrayList<Resource> GarmentObject = new ArrayList<>();
    ArrayList<String> predicate = new ArrayList<>();
    ArrayList<String> ontClass = new ArrayList<>();
    RDFController rdfcont = null;


    public RDFReader(RDFController rdfcont){
        rdfcont = this.rdfcont;
    }




    public ResultSet queryClothingClasses(){
        String resultClasses = ""
                + "SELECT ?o" +
                "WHERE " + " {?o rdfs:subClassOf dbr:Clothing ." +
                "}";
       return rdfcont.runSparql(resultClasses);

    }



}
