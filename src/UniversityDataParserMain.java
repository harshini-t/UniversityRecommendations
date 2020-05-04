import java.util.ArrayList;
import java.util.Map;

public class UniversityDataParserMain {

    public static void main(String[] args) {
    	 UniversityDataParser parser = new UniversityDataParser();
         parser.getUniversityLinks();
         Map<String, String> countryRank = parser.getCountryRank();
         Map<String, String> schoolsInUni = parser.getSchoolsInUniversity();
         //Map<String, String> financialAid = parser.getFinancialAid();
         //Map<String, String> tuition = parser.getTuition();
         //Map<String, ArrayList<String>> enrollmentSize = parser.getEnrollmentSizes();
         //Map<String, String> admissionRate = parser.getAdmissionRate();
         //Map<String, ArrayList<String>> campusSetting= parser.getCampusSetting();
         //Map<String, ArrayList<String>> publicPrivate = parser.getPublicPrivate();
         //Map<String, ArrayList<String>> address = parser.getAddress();
         //Map<String, ArrayList<String>> academicSystem = parser.getAcademicSystem();

         System.out.println("country rank: " + countryRank);
         System.out.println("schools in uni: " + schoolsInUni);
         //System.out.println("financial aid: " + financialAid);
         //System.out.println("tuition: " + tuition);
         //System.out.println("enrollment size: " + enrollmentSize.keySet());
         //System.out.println("admission rate: " + admissionRate);
         //System.out.println("campus setting: " + campusSetting.keySet());
         //System.out.println("public private: " + publicPrivate);
         //System.out.println("address: " + address);
         //System.out.println("academic system: " + academicSystem.keySet());
    }
}
