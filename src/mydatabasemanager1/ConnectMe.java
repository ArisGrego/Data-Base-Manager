package mydatabasemanager1;

//ΒΙΒΛΙΟΘΗΚΕΣ
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectMe extends javax.swing.JFrame {
    //ΜΕΤΑΒΛΗΤΗ ΣΥΝΔΕΣΗΣ
    private static Connection connection;  //Η Connection είναι μέρος της βιβλιοθήκης JDBC και λειτουργεί ως διαχειριστής για την επικοινωνία με τη βάση. static ΓΙΑ ΝΑ ΜΠΟΡΩ ΝΑ ΤΗΝ ΧΡΗΣΙΜΟΠΟΙΗΣΩ ΚΑΙ ΑΠΟ ΑΛΛΑ frames
    
    //getConnection ΓΙΑ ΝΑ ΜΠΟΡΩ ΝΑ ΠΑΡΩ ΤΙΣ ΤΙΜΕΣ ΤΙΣ (ΓΙΑ ΤΗ ΣΥΝΔΕΣΗ ΣΤΗ ΒΑΣΗ) ΚΑΙ ΑΠΟ ΑΛΛΑ frames
    public static Connection getConnection() {
        return connection;
    }
    
    public ConnectMe() {
        initComponents();
        
        // Προσθήκη ακροατη (window listener) για κλείσιμο σύνδεσης. Ο ακροατης παρακολουθει γεγονοτα παραθυρου (κλεισιμο, ανοιγμα, ελαχιστοποιηση κλπ).
        this.addWindowListener(new java.awt.event.WindowAdapter() {    //ΤΟ this ΑΝΑΦΕΡΕΤΑΙ ΣΤΟ MyFrame. Προσθέτει έναν ακροατή (listener) στο παράθυρο που ανήκει σε αυτό το αντικείμενο.
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {  //Μια απο τις μεθοδους του window listener ειναι η windowClosing (Όταν ο χρήστης προσπαθεί να κλείσει το παράθυρο).
                closeDatabaseConnection();    //Οταν λοιπον ο χρηστης κλεισει το παραθυρο καλεσε την closeDatabaseConnection() η οποια τερματιζει τη συνδεση με τη βαση.
            }
        });
    }
    
     // ΣΥΝΔΕΣΗ ΜΕ ΤΗ ΒΑΣΗ ΔΕΔΟΜΕΝΩΝ
    private void connectToDatabase() {
        String url = "jdbc:postgresql://localhost:5432/ArisData";   //URL ΒΑΣΗΣ ΔΕΔΟΜΕΝΩΝ
        String user = "postgres";				    //ΧΡΗΣΤΗΣ ΒΑΣΗΣ ΔΕΔΟΜΕΝΩΝ
        String password = "Bibikos1908.";			    //ΚΩΔΙΚΟΣ ΒΑΣΗΣ ΔΕΔΟΜΕΝΩΝ

        try {
            if (connection == null || connection.isClosed()) {      //ΑΝ ΤΟ ΑΝΤΙΚΕΙΜΕΝΟ connection ΕΧΕΙ ΜΗΔΕΝΙΚΗ ΤΙΜΗ Ή ΕΙΝΑΙ ΚΛΕΙΣΤΟ == ΔΗΛΑΔΗ ΔΕΝ ΥΠΑΡΧΕΙ ΣΥΝΔΕΣΗ ΜΕ ΤΗ ΒΑΣΗ
                connection = DriverManager.getConnection(url, user, password);          //ΜΕΘΟΔΟΣ ΜΕ ΤΗΝ ΟΠΟΙΑ ΣΥΝΔΕΩ ΤΗ JAVA ΕΦΑΡΜΟΓΗ ΜΟΥ ΜΕ ΤΗ ΒΑΣΗ ΔΕΔΟΜΕΝΩΝ ΜΕΣΩ ΤΗΣ ΒΙΒΛΙΟΘΗΚΗΣ JDBC.
                JOptionPane.showMessageDialog(this, "Σύνδεση επιτυχής!");              //ΑΝ ΣΥΝΔΕΣΗ ΕΠΙΤΥΧΗΣ ΕΜΦΑΝΙΣΕ POP-UP MESSAGE
            } else {
                JOptionPane.showMessageDialog(this, "Ήδη συνδεδεμένος με τη βάση δεδομένων.");      //ΑΝ ΕΙΣΑΙ ΗΔΗ CONNECTED ΚΑΙ ΞΑΝΑ ΠΑΤΗΣΕΙΣ CONNECT ΕΜΦΑΝΙΣΕ POP-UP MESSAGE
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Αποτυχία σύνδεσης: " + e.getMessage()); 		//ΑΝ ΣΥΝΔΕΣΗ FAIL ΠΕΤΑ ΕΞΑΙΡΕΣΗ
        }
    }

    // ΤΕΡΜΑΤΙΣΜΟΣ ΤΗΣ ΣΥΝΔΕΣΗΣ
    private void closeDatabaseConnection() {
        try {
            if (connection != null && !connection.isClosed()) {						//ΑΝ ΚΛΕΙΣΕΙ Η ΕΦΑΡΜΟΓΗ -> ΤΕΛΕΙΩΝΕΙ ΚΑΙ Η ΣΥΝΔΕΣΗ ΜΕ ΤΗ ΒΑΣΗ ΔΕΔΟΜΕΝΩΝ -> POP-UP MESSAGE
                connection.close();                                 //ΤΕΡΜΑΤΙΣΕ ΤΗ ΣΥΝΔΕΣΗ 
                System.out.println("Η σύνδεση με τη βάση δεδομένων έκλεισε.");
            }
        } catch (SQLException e) {									//ΑΝ ΕΝΩ ΚΛΕΙΣΕΙ Η ΕΦΑΜΡΜΟΓΗ, Η ΣΥΝΔΕΣΗ ΔΕΝ ΜΠΟΡΕΙ ΝΑ ΤΕΡΜΑΤΙΣΤΕΙ -> ΠΕΤΑ ΕΞΑΙΡΕΣΗ 
            System.err.println("Σφάλμα κατά το κλείσιμο της σύνδεσης: " + e.getMessage());
        }
    }
    
    // ΑΝΑΦΟΡΑ ΣΤΟ ΠΑΡΑΘΥΡΟ ManagerApp
    private ManagerApp createDBWindow;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        connectButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Επεξεργασία Βάσεων Δεδομένων");

        connectButton.setText("Σύνδεση");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(connectButton)
                .addContainerGap(162, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(connectButton)
                .addContainerGap(139, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        connectToDatabase();
        
        //ΜΕ ΤΟ ΠΑΤΗΜΑ ΤΟΥ Connect ΑΝΟΙΓΕΙ ΤΟ ΕΠΟΜΕΝΟ ΠΑΡΑΘΥΡΟ ΠΟΥ ΑΦΟΡΑ ΤΗΝ ΔΗΜΙΟΥΡΓΙΑ ΕΝΟΣ ΠΙΝΑΚΑ ΣΤΗ ΒΑΣΗ
        if (createDBWindow == null || !createDBWindow.isVisible()) {
        createDBWindow = new ManagerApp(); // Δημιουργία νέου παραθύρου
        createDBWindow.setVisible(true); // Το κάνουμε ορατό
        }
    }//GEN-LAST:event_connectButtonActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnectMe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    // End of variables declaration//GEN-END:variables
}