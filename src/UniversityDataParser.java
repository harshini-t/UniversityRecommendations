
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
    Map<String, String> universitySubSchools;
    Map<String, String> universityFinAid;
    Map<String, String> universityAddRate;

    

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

    // university - link
    public void getUniversityLinks() {
        this.universityLinks = new HashMap<String, String>();
        Elements universities = this.currentDoc.select("a");
        for (Element uni : universities) {
            if (uni.attr("href").contains("reviews") && !uni.attr("href").contains("index2")) {
                String href = "https://www.4icu.org" + uni.attr("href");
                String UniversityName = uni.text();
                universityLinks.put(UniversityName, href);
            }
        }
    }

    // university - rank
    public Map<String, String> getCountryRank() {
        this.universityRank = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : this.universityLinks.entrySet()) {
            String name = entry.getKey();
            String link = entry.getValue();
            try {
                this.currentDoc = Jsoup.connect(link).get();
            } catch (IOException e) {
                System.out.println("Could not get the university :");
            }
            String info = this.currentDoc.select("td").text();
            String splitInfo[] = info.split(" ");
            universityRank.put(name, splitInfo[2]);
        }
        return universityRank;
    }

    // university - schools in university
    public Map<String, String> getSchoolsInUniversity() {
        this.universitySubSchools = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : this.universityLinks.entrySet()) {
            String name = entry.getKey();
            String link = entry.getValue();
            try {
                this.currentDoc = Jsoup.connect(link).get();
            } catch (IOException e) {
                System.out.println("Could not get the university :");
            }
            String info = this.currentDoc.select("table[style=margin:auto]").text();
            int index = info.indexOf("|");
            info = info.substring(index + 1);
            universitySubSchools.put(name, info);
        }
        return universityRank;
    }

    // university - financial aid
    public Map<String, String> getFinancialAid() {
        this.universityFinAid = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : this.universityLinks.entrySet()) {
            String name = entry.getKey();
            String link = entry.getValue();
            try {
                this.currentDoc = Jsoup.connect(link).get();
            } catch (IOException e) {
                System.out.println("Could not get the university :");
            }
            String info = this.currentDoc.select("table[class=table borderless]").text();
            String[] infoSplit = info.split(" ");
            ArrayList<String> arrayList = new ArrayList<String>();
            for (String x : infoSplit) {
                arrayList.add(x);
            }
            int index = arrayList.indexOf("Aids");
            String finAid = arrayList.get(index + 1);
            if (finAid.contains("Yes")) {
                universityFinAid.put(name, finAid);
            } else {
                universityFinAid.put(name, "Not Reported");
            }
        }
        return universityFinAid;
    }
    
    // university - admission rate
    public Map<String, String> getAdmissionRate() {
        this.universityAddRate = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : this.universityLinks.entrySet()) {
            String name = entry.getKey();
            String link = entry.getValue();
            System.out.println(name);
            try {
                this.currentDoc = Jsoup.connect(link).get();
            } catch (IOException e) {
                System.out.println("Could not get the university :");
            }
            String info = this.currentDoc.select("table[class=table borderless]").text();
            String[] infoSplit = info.split(" ");
            ArrayList<String> arrayList = new ArrayList<String>();
            for (String x : infoSplit) {
                arrayList.add(x);
            }
            int index = arrayList.indexOf("Rate");
            
            String addRate = arrayList.get(index + 1);
            if (addRate.contains("Not")) {
                universityAddRate.put(name, "Not Available");
            }
            else {
                universityAddRate.put(name, addRate);
            }           
        }
        return universityAddRate;
    }


    // university - tuition
    public Map<String, String> getTuition() {
        this.universityTuition = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : this.universityLinks.entrySet()) {
            String name = entry.getKey();
            String link = entry.getValue();
            try {
                this.currentDoc = Jsoup.connect(link).get();
            } catch (IOException e) {
                System.out.println("Could not get the university :");
            }
            String info = this.currentDoc.select("td").text();
            String[] splitInfo = info.split(" ");
            ArrayList<String> arrayList = new ArrayList<String>();
            for (String x : splitInfo) {
                arrayList.add(x);
            }
            int index = arrayList.indexOf("US$");
            universityTuition.put(name, arrayList.get(index - 1));
        }
        return universityRank;
    }

    // university - size - NOT DONE
    // small - 20,000 lower
    // medium - 40,000 lower
    // large 40,000 higher
    public Map<String, ArrayList<String>> getEnrollmentSizes() {
        this.sizeToUniversity = new HashMap<String, ArrayList<String>>();
        for (Map.Entry<String, String> entry : this.universityLinks.entrySet()) {
            String name = entry.getKey();
            String link = entry.getValue();
            int index = 0;
            try {
                this.currentDoc = Jsoup.connect(link).get();
            } catch (IOException e) {
                System.out.println("Could not get the university :");
            }
            String info = this.currentDoc.select("table[class=table borderless]").text();
            String[] splitInfo = info.split(" ");
            ArrayList<String> arrayList = new ArrayList<String>();
            for (String x : splitInfo) {
                arrayList.add(x);
            }
            if (!name.contains("Iowa State University of")) {
                index = arrayList.indexOf("Enrollment");
                index = index + 1;
            }
            else {
                index = arrayList.indexOf("Control");
                index = index - 4;
            }
            String numberAsString = arrayList.get(index);
            String str = numberAsString.replaceAll("[a-zA-Z]", "");

            int indexDash = str.indexOf("-");
            if (indexDash >= 0) {
                str = str.substring(indexDash + 1);
            }
            str = str.replaceAll(",", "");
            int enrollment = Integer.parseInt(str);
            if (enrollment <= 20000) {
                if (sizeToUniversity.containsKey("small")) {
                    sizeToUniversity.get("small").add(name);
                } else {
                    ArrayList<String> smallList = new ArrayList<String>();
                    smallList.add(name);
                    sizeToUniversity.put("small", smallList);
                }
            }
            else if (enrollment <= 40000) {
                if (sizeToUniversity.containsKey("medium")) {
                    sizeToUniversity.get("medium").add(name);
                } else {
                    ArrayList<String> medList = new ArrayList<String>();
                    medList.add(name);
                    sizeToUniversity.put("medium", medList);
                }
            } else {
                if (sizeToUniversity.containsKey("large")) {
                    sizeToUniversity.get("large").add(name);
                } else {
                    ArrayList<String> largeList = new ArrayList<String>();
                    largeList.add(name);
                    sizeToUniversity.put("large", largeList);
                }
            }
        }
        return sizeToUniversity;
    }
}
