public class Main {

    public static void main(String[] args) {
        Yr yr = new Yr();

        yr.getWeatherAPI();
        yr.makeSymbolList();

        System.out.println(yr.getTemprature());
    }
}
