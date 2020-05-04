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
import javax.swing.JLabel;
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
		/*parser.getUniversityLinks();
		parser.getCountryRank();
        parser.getSchoolsInUniversity();
        parser.getFinancialAid();
        parser.getTuition();
        parser.getEnrollmentSizes();
        parser.getAdmissionRate();
        parser.getCampusSetting();
        parser.getPublicPrivate();
        parser.getAddress();
        parser.getAcademicSystem();*/
		
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
            	
            	String instructions = "Enter your preferences and select \n"
            			+ "which preferences are the most important to you to get a top 3 \n"
            			+ "list of schools that match your criteria!";
            	
            	JOptionPane.showConfirmDialog(frame, instructions, 
	    				"Instructions: ", JOptionPane.PLAIN_MESSAGE); 
	    	
            	final JComboBox<String> es = new JComboBox<String>(enrollmentSize);
            	c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 0;
        	    c.gridy = 1;
        	    final JLabel esL = new JLabel("Size Preference"); 
        	    subPanel.add(esL, c);
        	    c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 0;
        	    c.gridy = 2;
        	    subPanel.add(es, c);
            	final JComboBox<String> cs = new JComboBox<String>(campusSetting);
            	c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 1;
        	    c.gridy = 1;
        	    final JLabel csL = new JLabel("Campus Setting Preference"); 
        	    subPanel.add(csL, c);
        	    c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 1;
        	    c.gridy = 2;
        	    subPanel.add(cs, c);
            	final JComboBox<String> pp = new JComboBox<String>(publicPrivate);
            	c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 2;
        	    c.gridy = 1;
        	    final JLabel ppL = new JLabel("Public/Private Preference"); 
        	    subPanel.add(ppL, c);
        	    c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 2;
        	    c.gridy = 2;
        	    subPanel.add(pp, c);
            	final JComboBox<String> as = new JComboBox<String>(academicCalendar);
            	c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 3;
        	    c.gridy = 1;
        	    final JLabel asL = new JLabel("Academic Calendar Preference"); 
        	    subPanel.add(asL, c);
        	    c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 3;
        	    c.gridy = 2;
        	    subPanel.add(as, c);
            	final JTextField loc = new JTextField(20);
            	c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 4;
        	    c.gridy = 1;
        	    final JLabel locL = new JLabel("State Preference"); 
        	    subPanel.add(locL, c);
        	    c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 4;
        	    c.gridy = 2;
        	    subPanel.add(loc, c);
            	final JComboBox<String> r1 = new JComboBox<String>(rank1);
            	c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 0;
        	    c.gridy = 3;
        	    final JLabel r1L = new JLabel("Most Important Factor"); 
        	    subPanel.add(r1L, c);
        	    c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 0;
        	    c.gridy = 4;
        	    subPanel.add(r1, c);
            	final JComboBox<String> r2 = new JComboBox<String>(rank2);
            	c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 1;
        	    c.gridy = 3;
        	    final JLabel r2L = new JLabel("2nd Most Important Factor"); 
        	    subPanel.add(r2L, c);
        	    c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 1;
        	    c.gridy = 4;
        	    subPanel.add(r2, c);
            	final JComboBox<String> r3 = new JComboBox<String>(rank3);
            	c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 2;
        	    c.gridy = 3;
        	    final JLabel r3L = new JLabel("3rd Most Important Factor"); 
        	    subPanel.add(r3L, c);
        	    c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 2;
        	    c.gridy = 4;
        	    subPanel.add(r3, c);
            	final JComboBox<String> r4 = new JComboBox<String>(rank4);
            	c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 3;
        	    c.gridy = 3;
        	    final JLabel r4L = new JLabel("4th Most Important Factor"); 
        	    subPanel.add(r4L, c);
        	    c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 3;
        	    c.gridy = 4;
        	    subPanel.add(r4, c);
            	final JComboBox<String> r5 = new JComboBox<String>(rank5);
            	c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 4;
        	    c.gridy = 3;
        	    final JLabel r5L = new JLabel("Least Important Factor"); 
        	    subPanel.add(r5L, c);
        	    c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 4;
        	    c.gridy = 4;
        	    subPanel.add(r5, c);
            	
            	final JButton ok = new JButton("Submit Preferences");
            	c.fill = GridBagConstraints.VERTICAL;
        	    c.weightx = 0.5;
        	    c.gridx = 2;
        	    c.gridy = 8;
        	    subPanel.add(ok, c);


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

            	JFrame subFrame = new JFrame("Statistics");
            	
            	JPanel subPanel = new JPanel();
            	subFrame.add(subPanel);
            	subFrame.setLocation(300, 300);
        		subPanel.setLayout(new GridBagLayout());
            	
            	String [] questionChoices = {"Enter Choices HERE"};

            	//final JComboBox<String> es = new JComboBox<String>(choices);
            	//final JComboBox<String> cs = new JComboBox<String>(campusSetting);
            	final JButton ok = new JButton("Select Question");


        	    ok.addActionListener(new ActionListener() {
        	    	public void actionPerformed(ActionEvent f) {
        	    	}
        	    });
        	    subFrame.pack(); 
                subFrame.setVisible(true);
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
