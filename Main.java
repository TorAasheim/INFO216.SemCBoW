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
    }
}
