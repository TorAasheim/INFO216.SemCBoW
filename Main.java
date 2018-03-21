import YrData.Yr;
import YrData.YrModel;

public class Main {

    public static void main(String[] args) {
        Yr yr = new Yr();

        yr.getWeatherAPI();
        yr.makeSymbolList();

        System.out.println(yr.getTemprature());
        System.out.println();
        System.out.println(yr.getDateTime());
        System.out.println();
        System.out.println(yr.getNametag());
        System.out.println();
        System.out.println(yr.getWindSpeedName());
        System.out.println();
        System.out.println(yr.getWindSpeedValue());
        System.out.println();
        System.out.println(yr.getTimeTag());
        System.out.println(yr.getNametag());
        System.out.println(
        );
        System.out.println(yr.getIdList());
        System.out.println(yr.getPeriodTag());
        System.out.println(yr.getFromtag());

        YrModel model = new YrModel();
        model.createModel();
        model.writeToFile();
        System.out.println(yr.getIdList().size());


    }
}
