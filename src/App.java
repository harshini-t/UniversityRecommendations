import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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

                String[] enrollmentSize = { "Small", "Medium", "Large" };
                String[] campusSetting = { "Rural", "Urban", "Suburban" };
                String[] publicPrivate = { "Public", "Private" };
                String[] academicCalendar = { "Semesters", "Trimesters", "Quarters", "4-1-4", "Other" };
                String[] rank1 = { "Size", "Campus Setting", "Public/Private", "Academic Calendar", "Location" };
                String[] rank2 = { "Size", "Campus Setting", "Public/Private", "Academic Calendar", "Location" };
                String[] rank3 = { "Size", "Campus Setting", "Public/Private", "Academic Calendar", "Location" };
                String[] rank4 = { "Size", "Campus Setting", "Public/Private", "Academic Calendar", "Location" };
                String[] rank5 = { "Size", "Campus Setting", "Public/Private", "Academic Calendar", "Location" };

                String instructions = "Enter your preferences and select \n"
                        + "which preferences are the most important to you to get a top 3 \n"
                        + "list of schools that match your criteria!";

                JOptionPane.showConfirmDialog(frame, instructions, "Instructions: ", JOptionPane.PLAIN_MESSAGE);

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

                        Map<String, ArrayList<String>> finalRecommendations = parser.getRecommendations(
                                es.getSelectedItem().toString(), cs.getSelectedItem().toString(),
                                pp.getSelectedItem().toString(), as.getSelectedItem().toString(), loc.getText(),
                                r1.getSelectedItem().toString(), r2.getSelectedItem().toString(),
                                r3.getSelectedItem().toString(), r4.getSelectedItem().toString(),
                                r5.getSelectedItem().toString());
                        String s = "";
                        for (String k : finalRecommendations.keySet()) {
                            s = s + k + " ";
                            String e = finalRecommendations.get(k).toString();
                            int x = e.indexOf('[');
                            int y = e.indexOf(']');
                            s = s + "\n" + "Facts: " + e.substring(0, x) + e.substring(x + 1, y) + e.substring(y + 1)
                                    + "\n";
                        }
                        JOptionPane.showConfirmDialog(frame, s, "Recommendations:", JOptionPane.PLAIN_MESSAGE);
                    }
                });

                subFrame.pack();
                subFrame.setVisible(true);
            }

        });

        graph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame subFrame = new JFrame("University Statistics");

                JPanel subPanel = new JPanel();
                subFrame.add(subPanel);
                subFrame.setLocation(300, 300);
                subPanel.setLayout(new GridBagLayout());

                String[] questionChoices = { "Question 1: What universities in this state",
                        "Question 2: What universities meet my tuition goal?",
                        "Question 3: What universities meet my admission rate goal?",
                        "Question 4: What universities are in my preferred region?",
                        "Question 5: What is the relationship between university rank and tuition?",
                        "Question 6: What is the relationship between state and its number of universities?",
                        "Question 7: What is the relationship between region and its number of universities?" };

                final JComboBox<String> questions = new JComboBox<String>(questionChoices);
                c.fill = GridBagConstraints.VERTICAL;
                c.weightx = 0.5;
                c.gridx = 0;
                c.gridy = 1;
                final JLabel questionsLabel = new JLabel("Select a question!");
                subPanel.add(questionsLabel, c);
                c.fill = GridBagConstraints.VERTICAL;
                c.weightx = 0.5;
                c.gridx = 0;
                c.gridy = 2;
                subPanel.add(questions, c);

                final JButton ok = new JButton("Select Question");
                c.fill = GridBagConstraints.VERTICAL;
                c.weightx = 0.5;
                c.gridx = 0;
                c.gridy = 3;
                subPanel.add(ok, c);

                ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent f) {
                        String userChoice = questions.getSelectedItem().toString();
                        if (userChoice.substring(0, 10).equals("Question 1")) {
                            JTextField stateName = new JTextField();
                            final JComponent[] inputs = new JComponent[] { new JLabel("Enter state name: "),
                                    stateName };
                            JOptionPane.showConfirmDialog(null, inputs, "Question 1", JOptionPane.PLAIN_MESSAGE);
                            String resState = stateName.getText();
                            ArrayList<String> universities = parser.questionOne(resState);
                            String result = universities.toString().substring(1, universities.toString().length() - 1)
                                    + " are all universities in the state" + " of " + resState;
                            JOptionPane.showConfirmDialog(frame, result, "Answer: ", JOptionPane.PLAIN_MESSAGE);
                        } else if (userChoice.substring(0, 10).equals("Question 2")) {
                            JTextField tuition = new JTextField();
                            final JComponent[] inputs = new JComponent[] { new JLabel("Enter desired tuition: "),
                                    tuition };
                            JOptionPane.showConfirmDialog(null, inputs, "Question 2", JOptionPane.PLAIN_MESSAGE);
                            String resTuition = tuition.getText();
                            ArrayList<String> universities = parser.questionTwo(resTuition);
                            String result;
                            if (universities.size() > 10) {
                                result = universities.subList(0, 10).toString().substring(1,
                                        universities.subList(0, 10).toString().length() - 1) + " are some of the"
                                        + " universities that meet your desired tuition" + " of " + resTuition;
                            } else {
                                result = universities.toString().substring(1, universities.toString().length() - 1)
                                        + " are some of the" + " universities that meet your desired tuition" + " of "
                                        + resTuition;
                            }

                            JOptionPane.showConfirmDialog(frame, result, "Answer: ", JOptionPane.PLAIN_MESSAGE);
                        } else if (userChoice.substring(0, 10).equals("Question 3")) {
                            JTextField adRate = new JTextField();
                            final JComponent[] inputs = new JComponent[] { new JLabel("Enter desired admission rate: "),
                                    adRate };
                            JOptionPane.showConfirmDialog(null, inputs, "Question 3", JOptionPane.PLAIN_MESSAGE);
                            String resRate = adRate.getText();
                            ArrayList<String> universities = parser.questionThree(Integer.parseInt(resRate));
                            String result;
                            if (universities.size() > 10) {
                                result = universities.subList(0, 10).toString().substring(1,
                                        universities.subList(0, 10).toString().length() - 1) + " are some of the"
                                        + " universities that meet your desired admission rate";
                            } else {
                                result = universities.toString().substring(1, universities.toString().length() - 1)
                                        + " are some of the" + " universities that meet your admission rate";
                            }
                            JOptionPane.showConfirmDialog(frame, result, "Answer: ", JOptionPane.PLAIN_MESSAGE);
                        } else if (userChoice.substring(0, 10).equals("Question 4")) {
                            JTextField setting = new JTextField();
                            final JComponent[] inputs = new JComponent[] { new JLabel("Enter desired campus setting: "),
                                    setting };
                            JOptionPane.showConfirmDialog(null, inputs, "Question 4", JOptionPane.PLAIN_MESSAGE);
                            String settingInput = setting.getText();
                            ArrayList<String> universities = parser.questionFour(settingInput);
                            String result;
                            if (universities.size() > 10) {
                                result = universities.subList(0, 10).toString().substring(1,
                                        universities.subList(0, 10).toString().length() - 1) + " are some of the"
                                        + " universities in a " + settingInput + " setting";
                            } else {
                                result = universities.toString().substring(1, universities.toString().length() - 1)
                                        + " are some of the" + " universities in a " + settingInput + " setting";
                            }
                            JOptionPane.showConfirmDialog(null, result, "Question 4", JOptionPane.PLAIN_MESSAGE);
                        } else if (userChoice.substring(0, 10).equals("Question 5")) {
                            HashMap<Integer, Double> ranksToTuitions = parser.questionSix();
                            LineGraphPanel mainPanel = new LineGraphPanel(ranksToTuitions);
                            mainPanel.setPreferredSize(new Dimension(800, 600));
                            JFrame frame = new JFrame("Rank vs Average Tuition of Universities");
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.getContentPane().add(mainPanel);
                            frame.pack();
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);

                        } else if (userChoice.substring(0, 10).equals("Question 6")) {
                            HashMap<String, Integer> stateToCount = parser.questionSeven();
                            JFrame frame = new JFrame("Distribution of Universities Across US and Canadadian States");
                            frame.setPreferredSize(new Dimension(800, 600));
                            BarChart1 chart = new BarChart1();
                            for (String state : stateToCount.keySet()) {
                                Random rand = new Random();
                                int r = rand.nextInt(255);
                                int g = rand.nextInt(255);
                                int b = rand.nextInt(255);
                                Color randomColor = new Color(r, g, b);
                                chart.addBar(randomColor, stateToCount.get(state));
                                System.out.println(state + ": " + stateToCount.get(state) + " universities");
                            }
                            frame.getContentPane().add(chart);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.pack();
                            frame.setVisible(true);

                        } else {
                            HashMap<String, Integer> settingToCount = parser.questionEight();
                            JFrame frame = new JFrame("Distribution of University Campus Settings");
                            frame.setPreferredSize(new Dimension(800, 600));
                            BarChart1 chart = new BarChart1();
                            chart.addBar(Color.red, settingToCount.get("Rural"));
                            System.out.println("Rural: " + settingToCount.get("Rural") + " universities");
                            chart.addBar(Color.green, settingToCount.get("Suburban"));
                            System.out.println("Suburban: " + settingToCount.get("Suburban") + " universities");
                            chart.addBar(Color.blue, settingToCount.get("Urban"));
                            System.out.println("Urban: " + settingToCount.get("Urban") + " universities");
                            frame.getContentPane().add(chart);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.pack();
                            frame.setVisible(true);
                        }

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
