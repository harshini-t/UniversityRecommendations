import java.util.ArrayList;
import java.util.Map;

public class UniversityDataParserMain {

    public static void main(String[] args) {
    	 UniversityDataParser parser = new UniversityDataParser();
         

         parser.getUniversityLinks();
 		 parser.getCountryRank();
         parser.getSchoolsInUniversity();
         parser.getFinancialAid();
         parser.getTuition();
         parser.getEnrollmentSizes();
         parser.getAdmissionRate();
         parser.getCampusSetting();
         parser.getPublicPrivate();
         parser.getAddress();
         parser.getAcademicSystem();
         
         System.out.println("recommendations: " + parser.
        		 getRecommendations("Small", "Rural", "Public", 
        				 "Semesters", "New Jersey", "Size", "Public/Private", "Campus Setting", 
        				 "Location", "Academic Calendar"));
         //System.out.println("country rank: " + countryRank);
         //System.out.println("schools in uni: " + schoolsInUni);
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
