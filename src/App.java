import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class App implements Runnable {
	public void run() {
		JFrame frame = new JFrame("College Recommendations and Statistics!");
		JPanel panel = new JPanel();
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
		
        frame.add(panel);
		frame.setLocation(300, 300);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints(); 

	    JButton recommendation = new JButton("Recommendations of Schools");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 1;
	    c.gridy = 0;
	    panel.add(recommendation, c);
	    JButton graph = new JButton("Graphs based on questions");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 1;
	    c.gridy = 1;
	    panel.add(graph, c);


	    recommendation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame subFrame = new JFrame("College Recommendations");
            	
            	JPanel subPanel = new JPanel();
            	subFrame.add(subPanel);
            	subFrame.setLocation(300, 300);
        		subPanel.setLayout(new GridBagLayout());
            	
            	String [] enrollmentSize = {"Small", "Medium", "Large"};
            	String [] campusSetting = {"Rural", "Urban", "Suburban"};
            	String [] publicPrivate = {"Public", "Private"};
            	String [] academicCalendar = {"Semesters", "Trimesters", "Quarters", "4-1-4", "Other"};
            	String [] rank1 = {"Size", "Campus Setting", "Public/Private", "Academic Calendar", "Location"};
            	String [] rank2 = {"Size", "Campus Setting", "Public/Private", "Academic Calendar", "Location"};
            	String [] rank3 = {"Size", "Campus Setting", "Public/Private", "Academic Calendar", "Location"};
            	String [] rank4 = {"Size", "Campus Setting", "Public/Private", "Academic Calendar", "Location"};
            	String [] rank5 = {"Size", "Campus Setting", "Public/Private", "Academic Calendar", "Location"};
            	
            	final JComboBox<String> es = new JComboBox<String>(enrollmentSize);
            	c.fill = GridBagConstraints.HORIZONTAL;
        	    c.weightx = 0.5;
        	    c.gridx = 0;
        	    c.gridy = 0;
        	    subPanel.add(es);
            	final JComboBox<String> cs = new JComboBox<String>(campusSetting);
            	c.fill = GridBagConstraints.HORIZONTAL;
        	    c.weightx = 0.5;
        	    c.gridx = 1;
        	    c.gridy = 0;
        	    subPanel.add(cs);
            	final JComboBox<String> pp = new JComboBox<String>(publicPrivate);
            	c.fill = GridBagConstraints.HORIZONTAL;
        	    c.weightx = 0.5;
        	    c.gridx = 2;
        	    c.gridy = 0;
        	    subPanel.add(pp);
            	final JComboBox<String> as = new JComboBox<String>(academicCalendar);
            	c.fill = GridBagConstraints.HORIZONTAL;
        	    c.weightx = 0.5;
        	    c.gridx = 3;
        	    c.gridy = 0;
        	    subPanel.add(as);
            	final JTextField loc = new JTextField(20);
            	c.fill = GridBagConstraints.HORIZONTAL;
        	    c.weightx = 0.5;
        	    c.gridx = 4;
        	    c.gridy = 0;
        	    subPanel.add(loc);
            	final JComboBox<String> r1 = new JComboBox<String>(rank1);
            	c.fill = GridBagConstraints.HORIZONTAL;
        	    c.weightx = 0.5;
        	    c.gridx = 5;
        	    c.gridy = 0;
        	    subPanel.add(r1);
            	final JComboBox<String> r2 = new JComboBox<String>(rank2);
            	c.fill = GridBagConstraints.HORIZONTAL;
        	    c.weightx = 0.5;
        	    c.gridx = 0;
        	    c.gridy = 2;
        	    subPanel.add(r2);
            	final JComboBox<String> r3 = new JComboBox<String>(rank3);
            	c.fill = GridBagConstraints.HORIZONTAL;
        	    c.weightx = 0.5;
        	    c.gridx = 1;
        	    c.gridy = 2;
        	    subPanel.add(r3);
            	final JComboBox<String> r4 = new JComboBox<String>(rank4);
            	c.fill = GridBagConstraints.HORIZONTAL;
        	    c.weightx = 0.5;
        	    c.gridx = 2;
        	    c.gridy = 2;
        	    subPanel.add(r4);
            	final JComboBox<String> r5 = new JComboBox<String>(rank5);
            	c.fill = GridBagConstraints.HORIZONTAL;
        	    c.weightx = 0.5;
        	    c.gridx = 3;
        	    c.gridy = 2;
        	    subPanel.add(r5);
            	
            	final JButton ok = new JButton("OK");
            	c.fill = GridBagConstraints.HORIZONTAL;
        	    c.weightx = 0.5;
        	    c.gridx = 4;
        	    c.gridy = 2;
        	    subPanel.add(ok);


        	    ok.addActionListener(new ActionListener() {
        	    	public void actionPerformed(ActionEvent f) {
        	    		
        	    		Map<String, ArrayList<String>> finalRecommendations = parser.getRecommendations(es.getSelectedItem().toString(), 
        	    				cs.getSelectedItem().toString(), pp.getSelectedItem().toString(),
        	    				as.getSelectedItem().toString(), loc.getText(), 
        	    				r1.getSelectedItem().toString(), r2.getSelectedItem().toString(),
        	    				r3.getSelectedItem().toString(), r4.getSelectedItem().toString(), 
        	    				r5.getSelectedItem().toString());
        	    		String s = "";
        	    		for (String k : finalRecommendations.keySet()) {
        	    			s = s + k + " "; 
        	    			String e = finalRecommendations.get(k).toString();
        	    			int x = e.indexOf('[');
        	    			int y = e.indexOf(']');
        	    			s = s + "\n" + "Facts: " + e.substring(0, x) + e.substring(x+1, y) + e.substring(y+1) +  "\n"; 
        	    		}
        	    		JOptionPane.showConfirmDialog(frame, s, 
        	    				"Recommendations:", JOptionPane.PLAIN_MESSAGE); 
        	    	}
        	    });

        	    subFrame.pack();
        	    subFrame.setVisible(true);
            }
            
        });

	    graph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	//subPanel.removeAll();
            	
            	String [] questionChoices = {"Get Colleges in a Specific State: "};
            	String [] campusSetting = {"hello"};

            	//final JComboBox<String> es = new JComboBox<String>(choices);
            	final JComboBox<String> cs = new JComboBox<String>(campusSetting);
            	final JButton ok = new JButton("OK");


            	//subPanel.add(es);
            	//subPanel.add(cs);
            	//subPanel.add(ok);

        	    ok.addActionListener(new ActionListener() {
        	    	public void actionPerformed(ActionEvent f) {
        	    	//	System.out.println("es: " + es.getSelectedItem());
        	    		System.out.println("cs: " + cs.getSelectedItem());
        	    	}
        	    });

            }
        });

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new App());
    }
}
