import Clothing.ClothingData;
import YrData.YrModel;

public class Main {

    public static void main(String[] args) {

        YrModel weatherModel = new YrModel();
        weatherModel.parse();
        weatherModel.writeToFile();


        ClothingData cmodel = new ClothingData();

        cmodel.liftJSON();
        System.out.println(cmodel.getClothing());




    }
}
