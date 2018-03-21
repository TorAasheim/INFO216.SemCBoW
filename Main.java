import YrData.Yr;
import YrData.YrModel;

public class Main {

    public static void main(String[] args) {

        YrModel weatherModel = new YrModel();
        weatherModel.parse();
        weatherModel.writeToFile();


    }
}
