
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
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
    Map<String, Document> universityDocs;
    Map<String, String> universityRank;
    Map<String, String> universityTuition;
    Map<String, String> universitySubSchools;
    Map<String, String> universityFinancialAid;
    Map<String, String> universityAddmissionRate;
    Map<String, ArrayList<String>> calendarUniversity;
    Map<String, ArrayList<String>> universityLocation;
    Map<String, ArrayList<String>> publicPrivateUniversity;
    Map<String, ArrayList<String>> settingToUniversity;
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

    // university - link and university - document
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
        this.universityDocs = new HashMap<String, Document>();
        for (Map.Entry<String, String> entry : this.universityLinks.entrySet()) {
            String name = entry.getKey();
            String link = entry.getValue();
            try {
                Document d = Jsoup.connect(link).get();
                universityDocs.put(name, d);
            } catch (IOException e) {
                System.out.println("Could not get the university :");
            }
        }
    }

    // university - rank
    public Map<String, String> getCountryRank() {
        this.universityRank = new HashMap<String, String>();
        for (Map.Entry<String, Document> entry : universityDocs.entrySet()) {
            String name = entry.getKey();
            this.currentDoc = entry.getValue();
            Elements table = this.currentDoc.select("table[class=text-right]");
            Element tr = table.select("tr[style=vertical-align:bottom]").get(1);
            Element td = tr.selectFirst("td[style=border-bottom:thin dashed #aaa]");
            String info = td.text();
            universityRank.put(name, info);
        }
        return universityRank;
    }

    // university - schools in university
    public Map<String, String> getSchoolsInUniversity() {
        this.universitySubSchools = new HashMap<String, String>();
        for (Map.Entry<String, Document> entry : this.universityDocs.entrySet()) {
            String name = entry.getKey();
            this.currentDoc = entry.getValue();
            String info = this.currentDoc.select("table[style=margin:auto]").text();
            int index = info.indexOf("|");
            info = info.substring(index + 1);
            universitySubSchools.put(name, info);
        }
        return universitySubSchools;
    }

    // university - financial aid
    public Map<String, String> getFinancialAid() {
        this.universityFinancialAid = new HashMap<String, String>();
        for (Map.Entry<String, Document> entry : this.universityDocs.entrySet()) {
            String name = entry.getKey();
            this.currentDoc = entry.getValue();
            String info = this.currentDoc.select("table[class=table borderless]").text();
            String[] infoSplit = info.split(" ");
            ArrayList<String> arrayList = new ArrayList<String>();
            for (String x : infoSplit) {
                arrayList.add(x);
            }
            int index = arrayList.indexOf("Aids");
            String finAid = arrayList.get(index + 1);
            if (finAid.contains("Yes")) {
                universityFinancialAid.put(name, finAid);
            } else {
                universityFinancialAid.put(name, "Not Reported");
            }
        }
        return universityFinancialAid;
    }

    // university - admission rate
    public Map<String, String> getAdmissionRate() {
        this.universityAddmissionRate = new HashMap<String, String>();
        for (Map.Entry<String, Document> entry : this.universityDocs.entrySet()) {
            String name = entry.getKey();
            this.currentDoc = entry.getValue();
            String info = this.currentDoc.select("table[class=table borderless]").text();
            String[] infoSplit = info.split(" ");
            ArrayList<String> arrayList = new ArrayList<String>();
            for (String x : infoSplit) {
                arrayList.add(x);
            }
            int index = arrayList.indexOf("Rate");

            String addRate = arrayList.get(index + 1);
            if (addRate.contains("Not")) {
                universityAddmissionRate.put(name, "Not Available");
            } else {
                universityAddmissionRate.put(name, addRate);
            }
        }
        return universityAddmissionRate;
    }

    // university - tuition
    public Map<String, String> getTuition() {
        this.universityTuition = new HashMap<String, String>();
        for (Map.Entry<String, Document> entry : this.universityDocs.entrySet()) {
            String name = entry.getKey();
            this.currentDoc = entry.getValue();
            String info = this.currentDoc.select("td").text();
            String[] splitInfo = info.split(" ");
            ArrayList<String> arrayList = new ArrayList<String>();
            for (String x : splitInfo) {
                arrayList.add(x);
            }
            int index = arrayList.indexOf("US$");
            universityTuition.put(name, arrayList.get(index - 1));
        }
        return universityTuition;
    }

    // state - university
    public Map<String, ArrayList<String>> getAddress() {
        this.universityLocation = new HashMap<String, ArrayList<String>>();
        for (Map.Entry<String, Document> entry : this.universityDocs.entrySet()) {
            String name = entry.getKey();
            this.currentDoc = entry.getValue();
            String info = this.currentDoc.select("table[class=table borderless]").text();
            String[] infoSplit = info.split(" ");
            ArrayList<String> arrayList = new ArrayList<String>();
            for (String x : infoSplit) {
                arrayList.add(x);
            }

            int index = arrayList.indexOf("United");
            int index2 = arrayList.indexOf("Canada");
            String fullCampusState = "";
            if (index >= 0) {
                String campusState = arrayList.get(index - 1);
                if (campusState.equals("Hampshire") || campusState.equals("Jersey") || campusState.equals("York")
                        || campusState.equals("Mexico") || campusState.equals("Carolina")
                        || campusState.equals("Dakota") || campusState.equals("Island")
                        || campusState.equals("Virginia")) {
                    if (!arrayList.get(index - 2).equals("West") && campusState.equals("Virginia")) {
                        fullCampusState = campusState;
                    } else {
                        fullCampusState = arrayList.get(index - 2) + " " + campusState;
                    }
                } else {
                    fullCampusState = campusState;
                }
            } else {
                fullCampusState = arrayList.get(index2 - 1);
            }
            if (universityLocation.containsKey(fullCampusState)) {
                universityLocation.get(fullCampusState).add(name);
            } else {
                ArrayList<String> newStateList = new ArrayList<String>();
                newStateList.add(name);
                universityLocation.put(fullCampusState, newStateList);
            }
        }
        return universityLocation;
    }

    // Academic Calendar(Semesters, Quarters, Trimesters, 4-1-4, Other) - university
    public Map<String, ArrayList<String>> getAcademicSystem() {
        this.calendarUniversity = new HashMap<String, ArrayList<String>>();
        for (Map.Entry<String, Document> entry : this.universityDocs.entrySet()) {
            String name = entry.getKey();
            this.currentDoc = entry.getValue();
            String info = this.currentDoc.select("table[class=table borderless]").text();
            String[] infoSplit = info.split(" ");
            ArrayList<String> arrayList = new ArrayList<String>();
            for (String x : infoSplit) {
                arrayList.add(x);
            }
            int index = arrayList.indexOf("Calendar");
            String campusCalendar = arrayList.get(index + 1);
            if (campusCalendar.equals("Not")) {
                campusCalendar = campusCalendar + " reported";
            }
            if (calendarUniversity.containsKey(campusCalendar)) {
                calendarUniversity.get(campusCalendar).add(name);
            } else {
                ArrayList<String> newStateList = new ArrayList<String>();
                newStateList.add(name);
                calendarUniversity.put(campusCalendar, newStateList);
            }
        }
        return calendarUniversity;
    }

    // campus type (public/private) - university
    public Map<String, ArrayList<String>> getPublicPrivate() {
        this.publicPrivateUniversity = new HashMap<String, ArrayList<String>>();
        for (Map.Entry<String, Document> entry : this.universityDocs.entrySet()) {
            String name = entry.getKey();
            this.currentDoc = entry.getValue();
            String info = this.currentDoc.select("table[class=table borderless]").text();
            String[] infoSplit = info.split(" ");
            ArrayList<String> arrayList = new ArrayList<String>();
            for (String x : infoSplit) {
                arrayList.add(x);
            }
            int index = arrayList.indexOf("Type");
            arrayList.remove(index);
            int index2 = arrayList.indexOf("Type");
            String campusType = arrayList.get(index2 + 1);
            if (campusType.equals("Public")) {
                if (publicPrivateUniversity.containsKey("Public")) {
                    publicPrivateUniversity.get("Public").add(name);
                } else {
                    ArrayList<String> publicList = new ArrayList<String>();
                    publicList.add(name);
                    publicPrivateUniversity.put("Public", publicList);
                }
            } else {
                if (publicPrivateUniversity.containsKey("Private")) {
                    publicPrivateUniversity.get("Private").add(name);
                } else {
                    ArrayList<String> privateList = new ArrayList<String>();
                    privateList.add(name);
                    publicPrivateUniversity.put("Private", privateList);
                }
            }
        }
        return publicPrivateUniversity;
    }

    // campus setting (rural/urban/suburban) - university
    public Map<String, ArrayList<String>> getCampusSetting() {
        this.settingToUniversity = new HashMap<String, ArrayList<String>>();
        for (Map.Entry<String, Document> entry : this.universityDocs.entrySet()) {
            String name = entry.getKey();
            this.currentDoc = entry.getValue();
            String info = this.currentDoc.select("table[class=table borderless]").text();
            String[] infoSplit = info.split(" ");
            ArrayList<String> arrayList = new ArrayList<String>();
            for (String x : infoSplit) {
                arrayList.add(x);
            }
            int index = arrayList.indexOf("Setting");

            String campusSetting = arrayList.get(index + 1);
            if (campusSetting.equals("Urban")) {
                if (settingToUniversity.containsKey("Urban")) {
                    settingToUniversity.get("Urban").add(name);
                } else {
                    ArrayList<String> urbanList = new ArrayList<String>();
                    urbanList.add(name);
                    settingToUniversity.put("Urban", urbanList);
                }
            } else if (campusSetting.equals("Suburban")) {
                if (settingToUniversity.containsKey("Suburban")) {
                    settingToUniversity.get("Suburban").add(name);
                } else {
                    ArrayList<String> subList = new ArrayList<String>();
                    subList.add(name);
                    settingToUniversity.put("Suburban", subList);
                }
            } else {
                if (settingToUniversity.containsKey("Rural")) {
                    settingToUniversity.get("Rural").add(name);
                } else {
                    ArrayList<String> ruralList = new ArrayList<String>();
                    ruralList.add(name);
                    settingToUniversity.put("Rural", ruralList);
                }
            }

        }
        return settingToUniversity;
    }

    // university - size
    // small - 20,000 lower
    // medium - 40,000 lower
    // large 40,000 higher
    public Map<String, ArrayList<String>> getEnrollmentSizes() {
        this.sizeToUniversity = new HashMap<String, ArrayList<String>>();
        for (Map.Entry<String, Document> entry : this.universityDocs.entrySet()) {
            String name = entry.getKey();
            this.currentDoc = entry.getValue();
            int index = 0;
            String info = this.currentDoc.select("table[class=table borderless]").text();
            String[] splitInfo = info.split(" ");
            ArrayList<String> arrayList = new ArrayList<String>();
            for (String x : splitInfo) {
                arrayList.add(x);
            }
            if (!name.contains("Iowa State University of")) {
                index = arrayList.indexOf("Enrollment");
                index = index + 1;
            } else {
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
                if (sizeToUniversity.containsKey("Small")) {
                    sizeToUniversity.get("Small").add(name);
                } else {
                    ArrayList<String> smallList = new ArrayList<String>();
                    smallList.add(name);
                    sizeToUniversity.put("Small", smallList);
                }
            } else if (enrollment <= 40000) {
                if (sizeToUniversity.containsKey("Medium")) {
                    sizeToUniversity.get("Medium").add(name);
                } else {
                    ArrayList<String> medList = new ArrayList<String>();
                    medList.add(name);
                    sizeToUniversity.put("Medium", medList);
                }
            } else {
                if (sizeToUniversity.containsKey("Large")) {
                    sizeToUniversity.get("Large").add(name);
                } else {
                    ArrayList<String> largeList = new ArrayList<String>();
                    largeList.add(name);
                    sizeToUniversity.put("Large", largeList);
                }
            }
        }
        return sizeToUniversity;
    }

    // finds which map is associated with preference - for recommendations
    private Map<String, ArrayList<String>> findVal(String cat) {
        if (cat.equals("Size")) {
            return sizeToUniversity;
        } else if (cat.equals("Campus Setting")) {
            return settingToUniversity;
        } else if (cat.equals("Public/Private")) {
            return publicPrivateUniversity;
        } else if (cat.equals("Academic Calendar")) {
            return calendarUniversity;
        } else {
            return universityLocation;
        }
    }

    // finds which specific is associated with preference - for recommendations
    private String findSpec(String cat, String eSize, String cSetting, String publicPrivate, String aCal,
            String location) {
        if (cat.equals("Size")) {
            return eSize;
        } else if (cat.equals("Campus Setting")) {
            return cSetting;
        } else if (cat.equals("Public/Private")) {
            return publicPrivate;
        } else if (cat.equals("Academic Calendar")) {
            return aCal;
        } else {
            return location;
        }
    }

    // calculates recommendations based on various user input and weights the input
    // based on
    // the ranking of preferences
    public Map<String, ArrayList<String>> getRecommendations(String eSize, String cSetting, String publicPrivate,
            String aCal, String location, String P1, String P2, String P3, String P4, String P5) {

        Map<String, ArrayList<String>> finalRecommendations = new HashMap<String, ArrayList<String>>();

        ArrayList<String> pref1 = findVal(P1).get(findSpec(P1, eSize, cSetting, publicPrivate, aCal, location));
        ArrayList<String> pref2 = findVal(P2).get(findSpec(P2, eSize, cSetting, publicPrivate, aCal, location));
        ArrayList<String> pref3 = findVal(P3).get(findSpec(P3, eSize, cSetting, publicPrivate, aCal, location));
        ArrayList<String> pref4 = findVal(P4).get(findSpec(P4, eSize, cSetting, publicPrivate, aCal, location));
        ArrayList<String> pref5 = findVal(P5).get(findSpec(P5, eSize, cSetting, publicPrivate, aCal, location));

        ArrayList<String> recommend = new ArrayList<String>();
        pref1.retainAll(pref2);
        if (!pref1.isEmpty()) {
            recommend.addAll(pref1);
            pref1.retainAll(pref3);
            if (!pref1.isEmpty()) {
                recommend.addAll(pref1);
                pref1.retainAll(pref4);
                if (!pref1.isEmpty()) {
                    recommend.addAll(pref1);
                    pref1.retainAll(pref5);
                    if (!pref1.isEmpty()) {
                        recommend.addAll(pref1);
                    }
                }
            }
        }

        if (recommend.size() < 3) {
            for (String s : recommend) {
                String rank = universityRank.get(s);
                String tuition = universityTuition.get(s);
                String financialAid = universityFinancialAid.get(s);
                String admissionRate = universityAddmissionRate.get(s);

                ArrayList<String> facts = new ArrayList<String>();
                facts.add("School Rank: " + rank);
                facts.add("School Tuition: " + tuition);
                facts.add("Financial Aid Offering: " + financialAid);
                facts.add("Admission Rate: " + admissionRate);

                finalRecommendations.put("University: " + s, facts);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                String rank = universityRank.get(recommend.get(i));
                String tuition = universityTuition.get(recommend.get(i));
                String financialAid = universityFinancialAid.get(recommend.get(i));
                String admissionRate = universityAddmissionRate.get(recommend.get(i));

                ArrayList<String> facts = new ArrayList<String>();
                facts.add("School Rank: " + rank);
                facts.add("School Tuition: " + tuition);
                facts.add("Financial Aid Offering: " + financialAid);
                facts.add("Admission Rate: " + admissionRate);

                finalRecommendations.put("University: " + recommend.get(i), facts);
            }
        }

        return finalRecommendations;
    }

    public ArrayList<String> questionOne(String state) {
        return universityLocation.get(state);
    }

    public ArrayList<String> questionTwo(String tuition) {
        ArrayList<String> schools = new ArrayList<String>();
        for (String school : universityTuition.keySet()) {
            String range = universityTuition.get(school);
            if (range.indexOf('-') != -1) {
                int index = range.indexOf('-');
                String beg = range.substring(0, index);
                String end = range.substring(index + 1);
                // find average tuition
                double avg;
                try {
                    avg = (NumberFormat.getNumberInstance(java.util.Locale.US).parse(beg).intValue()
                            + NumberFormat.getNumberInstance(java.util.Locale.US).parse(end).intValue()) / 2;
                } catch (ParseException e1) {
                    continue;
                }
                try {
                    if (NumberFormat.getNumberInstance(java.util.Locale.US).parse(tuition).intValue() <= avg) {
                        schools.add(school);
                    }
                } catch (ParseException e) {
                    continue;
                }
            } else {
                try {
                    if (NumberFormat.getNumberInstance(java.util.Locale.US).parse(tuition).intValue() <= NumberFormat
                            .getNumberInstance(java.util.Locale.US).parse(range).intValue()) {
                        schools.add(school);
                    }
                } catch (ParseException e) {
                    continue;
                }
            }

        }

        return schools;
    }

    public ArrayList<String> questionThree(int admissionRate) {
        ArrayList<String> schools = new ArrayList<String>();
        for (String school : universityAddmissionRate.keySet()) {
            String range = universityAddmissionRate.get(school);
            if (!range.equals("Not Available") && range.indexOf('-') != -1) {
                range = range.substring(0, range.length() - 1);
                int index = range.indexOf('-');
                int beg = Integer.parseInt(range.substring(0, index));
                int end = Integer.parseInt(range.substring(index + 1));
                double avg = (beg + end) / 2;
                if (admissionRate <= avg) {
                    schools.add(school);
                }
            }

        }

        return schools;
    }

    public ArrayList<String> questionFour(String regionType) {
        return settingToUniversity.get(regionType);
    }

    public HashMap<Integer, Double> questionSix() {
        HashMap<Integer, Double> avgTuitionPerRank = new HashMap<Integer, Double>();
        for (String school : universityTuition.keySet()) {
            String tuitionString = universityTuition.get(school);
            if (tuitionString.indexOf('-') != -1) {
                int index = tuitionString.indexOf('-');
                String beg = tuitionString.substring(0, index);
                String end = tuitionString.substring(index + 1);
                try {
                    double ending = NumberFormat.getNumberInstance(java.util.Locale.US).parse(end).doubleValue();
                    int rankOfSchool = Integer.parseInt(universityRank.get(school));
                    avgTuitionPerRank.put(rankOfSchool, ending);
                } catch (ParseException e) {
                    continue;
                }
            } else {
                try {
                    double schoolTuition = NumberFormat.getNumberInstance(java.util.Locale.US).parse(tuitionString)
                            .doubleValue();
                    int rankOfSchool = Integer.parseInt(universityRank.get(school));
                    avgTuitionPerRank.put(rankOfSchool, schoolTuition);
                } catch (ParseException e) {
                    continue;
                }
            }
        }
        return avgTuitionPerRank;
    }

    public HashMap<String, Integer> questionSeven() {
        HashMap<String, Integer> statesAndCounts = new HashMap<String, Integer>();
        for (String state : universityLocation.keySet()) {
            statesAndCounts.put(state, universityLocation.get(state).size());
        }
        return statesAndCounts;
    }

    public HashMap<String, Integer> questionEight() {
        HashMap<String, Integer> statesAndCounts = new HashMap<String, Integer>();
        for (String state : settingToUniversity.keySet()) {
            statesAndCounts.put(state, settingToUniversity.get(state).size());
        }
        return statesAndCounts;
    }
}
