import Clothing.ClothingData;
import Clothing.ClothingModel;
import YrData.YrModel;

public class Main {

    public static void main(String[] args) {

        YrModel weatherModel = new YrModel();
        ClothingModel clothingModel = new ClothingModel();
        weatherModel.parse();
        weatherModel.writeToFile();

        ClothingData cmodel = new ClothingData();

        cmodel.JSONFileScraper();
        clothingModel.parseModel();
        clothingModel.writeFile();


        /*
        Git test
         */



    }
}
