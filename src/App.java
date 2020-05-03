import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class App implements Runnable {
	public void run() {
		JFrame frame = new JFrame("College Recommendations and Statistics!");
		JPanel panel = new JPanel();
		frame.add(panel);
		frame.setLocation(300, 300);
		panel.setLayout(new GridLayout(20, 20));

	    JButton recommendation = new JButton("Recommendations of Schools");
	    JButton graph = new JButton("Graphs based on questions");
	    panel.add(recommendation);
	    panel.add(graph);
	    
	    JPanel subPanel = new JPanel();
    	
    	panel.add(subPanel, BorderLayout.SOUTH);
	    
	    recommendation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	subPanel.removeAll();
            	
            	String [] enrollmentSize = {"Small", "Medium", "Large"};
            	String [] campusSetting = {"Rural", "Urban", "Suburban"};
            	String [] publicPrivate = {"Public", "Private"};
            	//String [] campusLocation = {"Public", "Private"};
            	String [] academicCalendar = {"Semesters", "Trimesters", "Quarters", "4-1-4", "Other"};
            	String [] leastImportant = {"Size", "Campus Setting", "Public/Private", "Academic Calendar", "Location"};
            	
            	final JComboBox<String> es = new JComboBox<String>(enrollmentSize);
            	final JComboBox<String> cs = new JComboBox<String>(campusSetting);
            	final JComboBox<String> pp = new JComboBox<String>(publicPrivate);
            	final JComboBox<String> as = new JComboBox<String>(academicCalendar);
            	final JTextField loc = new JTextField(20);
            	final JComboBox<String> li = new JComboBox<String>(leastImportant);
            	final JButton ok = new JButton("OK");
            	
        	    subPanel.add(es);
        	    subPanel.add(cs);
        	    subPanel.add(pp);
        	    subPanel.add(as);
        	    subPanel.add(li);
        	    subPanel.add(loc);
        	    subPanel.add(ok);
        	    
        	    ok.addActionListener(new ActionListener() {
        	    	public void actionPerformed(ActionEvent f) {
        	    		System.out.println("es: " + es.getSelectedItem());
        	    		System.out.println("cs: " + cs.getSelectedItem());
        	    		System.out.println("pp: " + pp.getSelectedItem());
        	    		System.out.println("as: " + as.getSelectedItem());
        	    		System.out.println("loc: " + loc.getText());
        	    	}
        	    });
        	   
            }
        });
	    
	    graph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	subPanel.removeAll();
            	
            	String [] choices = {"sigh"};
            	String [] campusSetting = {"hello"};
            	
            	final JComboBox<String> es = new JComboBox<String>(choices);
            	final JComboBox<String> cs = new JComboBox<String>(campusSetting);
            	final JButton ok = new JButton("OK");
            	
            	
            	subPanel.add(es);
            	subPanel.add(cs);
            	subPanel.add(ok);
        	    
        	    ok.addActionListener(new ActionListener() {
        	    	public void actionPerformed(ActionEvent f) {
        	    		System.out.println("es: " + es.getSelectedItem());
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
