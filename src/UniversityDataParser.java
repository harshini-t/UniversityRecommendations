
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UniversityDataParser {
    private String baseURL;
    private Document currentDoc;
    Map<String, String> universityLinks;
    Map<String, String> universityRank;
    Map<String, String> universityTuition;

    Map<String, ArrayList<String>> sizeToUniversity;
    


    public UniversityDataParser() {
        this.baseURL = "https://www.4icu.org/top-universities-north-america/"
                + "?fbclid=IwAR3mNd9z5hXoGPjyG3D5Ej9fvHpzGuAVjnr-5pngZXONWrINPtydgAAvC5I";
        try {
            this.currentDoc = Jsoup.connect(this.baseURL).get();
        } catch (IOException e) {
            System.out.println("Could not get universities :(");
        }
    }

    public void getUniversityLinks() {
        this.universityLinks = new HashMap<String, String>();
        Elements universities = this.currentDoc.select("a");
        for (Element uni : universities) {
            if (uni.attr("href").contains("reviews") && !uni.attr("href").contains("index2")) {
                String href = "https://www.4icu.org" + uni.attr("href");
                String UniversityName = uni.text();
            //    System.out.println(href);
           //     System.out.println(UniversityName);
                universityLinks.put(UniversityName, href);
            }
        }
    }
    
    //university - rank
    public Map<String, String> getCountryRank() {
        this.universityRank = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : this.universityLinks.entrySet()) {
            String name = entry.getKey();
            String link = entry.getValue();
            System.out.println(name);
            System.out.println(link);
            try {
                this.currentDoc = Jsoup.connect(link).get();
            } catch (IOException e) {
                System.out.println("Could not get the university :");
            }
            String size = this.currentDoc.select("td").text();
            String array[] = size.split(" ");
                System.out.println(array[2]); 
                universityRank.put(name, array[2]);
        }        
        return universityRank;        
    }
    
    //university - rank
    public Map<String, String> getTuition() {
        this.universityTuition = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : this.universityLinks.entrySet()) {
            String name = entry.getKey();
            String link = entry.getValue();
            System.out.println(name);
            try {
                this.currentDoc = Jsoup.connect(link).get();
            } catch (IOException e) {
                System.out.println("Could not get the university :");
            }
            String size = this.currentDoc.select("td").text();
            String[] array = size.split(" ");
             ArrayList<String> arrayList = new ArrayList<String>();
             for (String x:array) {
                 arrayList.add(x);
             }
             int index = arrayList.indexOf("US$");
                 System.out.println(arrayList.get(index-1));
                universityTuition.put(name, arrayList.get(index-1));
        }        
        return universityRank;        
    }
    
    public Map<String, ArrayList<String>> getEnrollmentSizes() {
        this.sizeToUniversity = new HashMap<String, ArrayList<String>>();
        for (Map.Entry<String, String> entry : this.universityLinks.entrySet()) {
            String name = entry.getKey();
            String link = entry.getValue();
            System.out.println(name);
            System.out.println(link);

            try {
                this.currentDoc = Jsoup.connect(link).get();
            } catch (IOException e) {
                System.out.println("Could not get the university :");
            }
            Elements size = this.currentDoc.select("td");
                System.out.println(size.text());

            
        }
        
        return sizeToUniversity;
        
    }
    
    

}
