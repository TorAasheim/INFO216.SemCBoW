package YrData;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.apache.commons.lang3.StringUtils;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Yr {

    public ArrayList<HashMap<String,String>> dataStruct = new ArrayList<HashMap<String, String>>();
    private File file = new File("varsel.xml");
    long diff = new Date().getTime() - file.lastModified();
    private ArrayList<String> weatherType = new ArrayList<String>();
    private ArrayList<String> weatherTypeEng = new ArrayList<String>();
    private ArrayList<String> dateObserved = new ArrayList<String>();
    private ArrayList<String> timeObserved = new ArrayList<>();
    private ArrayList<Integer> periodTag = new ArrayList<Integer>();
    private ArrayList<String> windspeedType = new ArrayList<String>();
    private ArrayList<String> windSpeedValue = new ArrayList<>();
    private ArrayList<String> temperature = new ArrayList<String>();
    private ArrayList<Integer> idList = new ArrayList<Integer>();




    public Yr(){
        getWeatherAPI();
        makeSymbolList();
    }

    public void getWeatherAPI(){
        System.out.println("YR API checking for updates.. ");

        if (diff > 24 * 60 * 60 * 1000){
            try {
                URL weatherAPI = new URL("http://www.yr.no/sted/Norge/Hordaland/Bergen/Bergen/varsel.xml");
                ReadableByteChannel rbc = Channels.newChannel(weatherAPI.openStream());
                FileOutputStream fos = new FileOutputStream(file);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                System.out.println("API updated from:\n" + weatherAPI + "\n");

            } catch (Exception e) {
                System.out.println("Couldn't fint URL to API");
            }
        }else{
            System.out.println("Updated");
        }
    }

    public void makeSymbolList(){
        System.out.println("Constructing list from API");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        try {
            dBuilder = dbf.newDocumentBuilder();
        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }
        Document doc = null;

        try {
            doc = dBuilder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element nodelist = (Element)doc.getElementsByTagName("tabular").item(0);

        NodeList timeNodeList = nodelist.getElementsByTagName("time");

        Element tempNodeList = (Element)doc.getElementsByTagName("temperature").item(0);



        for (int i = 0; i < timeNodeList.getLength(); i++) {


            Node node = timeNodeList.item(i);
            Element eElement = (Element) node;


            Node symbolNode = eElement.getElementsByTagName("symbol").item(0);
            Element symbolElement = (Element) symbolNode;

            Node tempNode = eElement.getElementsByTagName("temperature").item(0);
            Element tempElement = (Element) tempNode;

            Node windNode = eElement.getElementsByTagName("windSpeed").item(0);
            Element windTypeElement = (Element) windNode;

            Node windSpeedNode = eElement.getElementsByTagName("windSpeed").item(0);
            Element windspeedElement = (Element) windSpeedNode;

            weatherType.add(symbolElement.getAttribute("name"));
            dateObserved.add(StringUtils.left(eElement.getAttribute("from"), 10));
            timeObserved.add(StringUtils.right(eElement.getAttribute("from"), 8));
            periodTag.add(Integer.parseInt(eElement.getAttribute("period")));
            temperature.add(tempElement.getAttribute("value"));
            windspeedType.add(windTypeElement.getAttribute("name"));
            windSpeedValue.add(windspeedElement.getAttribute("mps"));
            idList.add(1+i);


            if (weatherType.contains("Skyet")){
                weatherTypeEng.add("Cloud");

            } else if (weatherType.contains("Delvis skyet")){
                weatherTypeEng.add("PartlyCloud");

            } else if (weatherType.contains("Regn")){
                weatherTypeEng.add("Rain");

            } else if (weatherType.contains("Kraftig Regn")){
                weatherTypeEng.add("Rain");

            } else if (weatherType.contains("Tåke")){
                weatherTypeEng.add("Fog");

            } else if (weatherType.contains("Lett regn")){
                weatherTypeEng.add("Rain");

            } else if (weatherType.contains("Sol")){
                weatherTypeEng.add("Sun");

            } else if (weatherType.contains("Snø")){
                weatherTypeEng.add("Snow");

            } else if (weatherType.contains("Sludd")){
                weatherTypeEng.add("Sleet");

            } else if (weatherType.contains("Hagl")){
                weatherTypeEng.add("Hail");

            } else if (weatherType.contains("Lyn")){
                weatherTypeEng.add("Thunder");

            } else {
                weatherTypeEng.add("Rain");
            }




        }
        System.out.println("All OK");
    }

    public ArrayList<String> getNametag() {
        return weatherType;
    }

    /**
     * the fromtab is and ArrayList
     * filled with a ceretain date.
     * The weather cast consists of two times and dates.
     * Time and date from, name of weather, time and date to.
     * to specify the duration of the weather.
     * The time and date TO is the next time and date
     * in line in this array.
     * @return
     */
    public ArrayList<String> getFromtag() {
        return dateObserved;
    }

    /**
     * The windspeed name is an arraylist filled with the
     * names of the windtypes that occur,
     * such as "Orkan", "Storm", etc.
     * @return
     */
    public ArrayList<String> getWindSpeedName() {
        return windspeedType;
    }

    public ArrayList<Integer> getDateTime() {
        return periodTag;
    }


    /**
     * Getter for the periodTag arrayList
     * which is a list containing 0-3 periods of the day.
     * It is used to limit the weather to one day on mid day.
     * @return
     */
    public ArrayList<Integer> getPeriodTag() {
        return periodTag;
    }

    /**
     * The temprature arraylist is an array
     * containting all the temeratures of the day
     * in celsius.
     * @return
     */
    public ArrayList<String> getTemprature() {
        return temperature;
    }

    /**
     * the datastruct is a cleaner way of displaying the data stored
     * in the lists if you want to see it in the terminal output.
     * @return
     */
    public ArrayList<HashMap<String, String>> getDataStruct() {
        return dataStruct;
    }

    /**
     * Getter for idList, used for giving values in table a unique values.
     * @return idList
     */
    public ArrayList<Integer> getIdList() {
        return idList;
    }

    /**
     * Getter for english version of nameTag list.
     * Contains translated names of all weather types.
     * To use in ontology.
     * @return nametagEng - ArrayList<String>
     */
    public ArrayList<String> getNametagEng() {
        return weatherTypeEng;
    }

    public ArrayList<String> getWindSpeedValue() {
        return windSpeedValue;
    }

    public ArrayList<String> getObservedTag() {
        return timeObserved;
    }
}
