import YrData.Yr;
import YrData.YrModel;

public class Main {

    public static void main(String[] args) {

        YrModel model = new YrModel();


        model.parse();
        model.writeToFile();


        Yr yr = new Yr();

        System.out.println(yr.getObservedTag());


    }
}
