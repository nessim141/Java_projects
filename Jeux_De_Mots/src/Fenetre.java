import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Fenetre extends JFrame {

    private final JPanel panel1 = new JPanel(new GridLayout(1, 8));
    private final JPanel panel2 = new JPanel(new GridLayout(3, 9, 50, 80));
    private final Proposition prop = new Proposition();
    private List<String> list_Prop = prop.generer();
    private final Dictionnaire dic = new Dictionnaire();
    List<Integer> reponse = new ArrayList<>();
    private  int nbr_aleatoire;
    private int numRonde = 0;
    private int nbr_txt_field = 0;
    JLabel score1 = new JLabel();

    private void remplirPanel(JPanel panel, int remplis, int total, int ligneIndex) {
        for (int i = 0; i < total; i++) {
            JTextField textField = new JTextField(1);
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setFont(new Font("Arial", Font.PLAIN, 16));
            if (ligneIndex == 0) {
                textField.setEditable(true);
                textField.setBackground(Color.WHITE);
            } else {
                textField.setEditable(false);
                textField.setBackground(Color.LIGHT_GRAY);
            }
            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (textField.getText().length() >= 1) {
                        e.consume();
                    }
                    if (!list_Prop.contains(String.valueOf(e.getKeyChar()))) {
                        e.consume();
                    }
                }
            });
            if (i < remplis) {
                panel.add(textField);
            } else if (i == remplis) {
                ImageIcon fleche = new ImageIcon("icons\\down-arrow_10626929 (2).png");
                JButton btn1 = new JButton(fleche);
                btn1.addActionListener(new ActionFleche(ligneIndex));
                panel.add(btn1);
            } else {
                panel.add(new JLabel());
            }
        }
    }

    private void afficheProp(JPanel panel1, List<String> list_Prop) {
        panel1.removeAll();
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        for (String carac : list_Prop) {
            JTextField textField = new JTextField(carac, 10);
            textField.setEditable(false);
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setFont(new Font("Arial", Font.BOLD, 20));
            textField.setBackground(Color.CYAN);
            panel1.add(textField);
        }
        panel1.revalidate();
        panel1.repaint();
    }

    private void afficher2Partie() {
        panel2.removeAll();
        panel2.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        nbr_aleatoire = (int) (Math.random() * 100);
        if (nbr_aleatoire % 2 == 0) {
            JPanel ligne1 = new JPanel(new GridLayout(1, 8, 20, 25));
            remplirPanel(ligne1, 3, 8, 0);
            JPanel ligne2 = new JPanel(new GridLayout(1, 8, 20, 25));
            remplirPanel(ligne2, 4, 8, 1);
            JPanel ligne3 = new JPanel(new GridLayout(1, 8, 20, 25));
            remplirPanel(ligne3, 6, 8, 2);
            panel2.add(ligne1);
            panel2.add(ligne2);
            panel2.add(ligne3);
            nbr_txt_field += 13;
        } else {
            JPanel ligne1 = new JPanel(new GridLayout(1, 8, 20, 25));
            remplirPanel(ligne1, 2, 9, 0);
            JPanel ligne2 = new JPanel(new GridLayout(1, 8, 20, 25));
            remplirPanel(ligne2, 5, 9, 1);
            JPanel ligne3 = new JPanel(new GridLayout(1, 8, 20, 25));
            remplirPanel(ligne3, 8, 9, 2);
            panel2.add(ligne1);
            panel2.add(ligne2);
            panel2.add(ligne3);
            nbr_txt_field += 15;
        }
        panel2.revalidate();
        panel2.repaint();
    }
    
    public Fenetre() {

        afficheProp(panel1, list_Prop);
        afficher2Partie();
        
        ImageIcon next = new ImageIcon("icons\\logout_10627076.png");
        JButton suivant = new JButton(next);
        suivant.addActionListener(new ActionSuivant());
        JLabel score = new JLabel("Score : ");
        JPanel panel3 = new JPanel(new GridLayout(1, 3));
        panel3.setBorder(BorderFactory.createEmptyBorder(10, 100, 5, 100));
        panel3.add(score);
        panel3.add(score1);
        panel3.add(suivant);


        ImageIcon favicon = new ImageIcon("icons\\image.png");
        this.setIconImage(favicon.getImage());
        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.SOUTH);
        this.setSize(800, 600);
        this.setTitle("Jeux De Mots");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private class ActionFleche implements ActionListener {
        private final int ligneIndex;

        public ActionFleche(int ligneIndex) {
            this.ligneIndex = ligneIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (ligneIndex + 1 < panel2.getComponentCount()) {
                JPanel nextLigne = (JPanel) panel2.getComponent(ligneIndex + 1);
                for (Component comp : nextLigne.getComponents()) {
                    if (comp instanceof JTextField jTextField) {
                        jTextField.setEditable(true);
                        jTextField.setBackground(Color.WHITE);
                    }
                }
                JPanel ligneActuelle = (JPanel) panel2.getComponent(ligneIndex);
                String mot = recupereMot(ligneActuelle);
                reponse.add( correct(mot) ? 0 : 1);
                int tailleCorreecte = 0;
                for (Component comp : ligneActuelle.getComponents()) {
                    if (comp instanceof JTextField txt) {
                        if (correct(mot)) {
                            txt.setBackground(Color.green);
                            tailleCorreecte++;
                        }
                        else {
                            txt.setBackground(Color.red);
                        }
                    } else if (comp instanceof JButton) {
                        ((JButton) comp).setEnabled(false);
                    }
                }
                reponse.add(tailleCorreecte);
            }
            if (ligneIndex == panel2.getComponentCount() - 1) {
                JPanel ligneActuelle = (JPanel) panel2.getComponent(ligneIndex);
                String mot = recupereMot(ligneActuelle);
                   
                for (Component comp : ligneActuelle.getComponents()) {
                    if (comp instanceof JTextField txt) {                    
                        if (correct(mot)) txt.setBackground(Color.green);
                        else txt.setBackground(Color.red);
                    } else if (comp instanceof JButton) {
                        ((JButton) comp).setEnabled(false);
                    }
                }
                
            }
        }

        public String recupereMot(JPanel ligneActuelle) {
            String mot = "";
            for (Component comp : ligneActuelle.getComponents()) {
                if (comp instanceof JTextField txt) {
                    mot += txt.getText();
                    txt.setEditable(false);
                }
            }
            return mot;
        }

        public Boolean correct(String mot) {
            try {
                return dic.verifier(mot);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private class ActionSuivant implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            numRonde++;
            if (numRonde >= 10) {
                afficherScore();
                return;
            }

            list_Prop = prop.generer();
            afficheProp(panel1, list_Prop);

            afficher2Partie();

            int ligneIndex = 0;
            for (Component comp : panel2.getComponents()) {
                if (comp instanceof JPanel ligne) {
                    for (Component c : ligne.getComponents()) {
                        if (c instanceof JTextField textField) {
                            textField.setText("");
                            if (ligneIndex == 0) {
                                textField.setEditable(true);
                                textField.setBackground(Color.WHITE);
                            } else {
                                textField.setEditable(false);
                                textField.setBackground(Color.LIGHT_GRAY);
                            }
                        } else if (c instanceof JButton button) {
                            button.setEnabled(true);
                        }
                    }
                    ligneIndex++;
                }
            }
        }

        private void afficherScore() {
            int tailleCorrecte = 0;
            
        
            for (int i = 0; i < reponse.size(); i += 2) {
                if (i + 1 < reponse.size() && reponse.get(i) == 0) {
                    tailleCorrecte += reponse.get(i + 1);
                }
            }
        
            if (nbr_txt_field > 0) {
                float score = (float) (tailleCorrecte *  20) / nbr_txt_field;
                score1.setText(String.format("%.2f", score));
            } else {
                score1.setText("0");
            }
            System.out.println(reponse);
        }
        
    }
}
