import YrData.Yr;
import YrData.YrModel;

public class Main {

    public static void main(String[] args) {

        YrModel model = new YrModel();


        model.parse();
        model.writeToFile();



    }
}
