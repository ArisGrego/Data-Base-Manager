package mydatabasemanager1;

//ΒΙΒΛΙΟΘΗΚΕΣ   
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;

public class ManagerApp extends javax.swing.JFrame {

    //ΜΕΘΟΔΟΣ ΓΙΑ ΔΥΝΑΜΙΚΗ ΕΝΗΜΕΡΩΣΗ ΤΟΥ COMBOBOX ΜΕ ΤΟΥΣ ΠΙΝΑΚΕΣ ΤΗΣ ΒΑΣΗΣ
    private void loadTablesIntoComboBox() {
        try {
            Connection conn = ConnectMe.getConnection(); // Σύνδεση με τη βάση
            String query = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            combo.removeAllItems();  // Καθαρίζει το ComboBox από προηγούμενα στοιχεία

            while (rs.next()) {
                String tableName = rs.getString("table_name");
                combo.addItem(tableName); // Προσθήκη νέου πίνακα στο ComboBox
            }

        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Σφάλμα κατά τη φόρτωση των πινάκων: " + ex.getMessage());
        }
    }
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------//
    
    //ΜΕΘΟΔΟΣ ΓΙΑ ΝΑ ΠΑΡΩ ΤΟ ΠΛΗΘΟΣ ΑΛΛΑ ΚΑΙ ΤΑ ΟΝΟΜΑ ΤΩΝ ΣΤΗΛΩΝ ΤΩΝ ΠΙΝΑΚΩΝ ΤΗΣ ΒΑΣΗΣ
    public List<String> getColumnNames (String tableName){      //ΣΗΜΕΙΩΣΗ : ΧΡΗΣΙΜΟΠΟΙΩ ΛΙΣΤΑ ΚΑΘΩΣ ΘΕΛΩ ΝΑ ΕΠΙΣΤΡΕΦΩ ΠΟΛΛΑ ΣΤΟΙΧΕΙΑ ΚΑΙ ΟΧΙ ΕΝΑ(ΓΙΑ ΑΥΤΟ ΟΧΙ STRING) ΚΑΙ ΤΟ ΟΡΙΣΜΑ ΠΟΥ ΔΙΝΩ ΘΑ ΕΙΝΑΙ ΤΟ ΟΝΟΜΑ ΤΟΥ ΕΚΑΣΤΟΤΕ ΠΙΝΑΚΑ ΠΟΥ ΘΑ ΘΕΛΩ ΝΑ ΕΙΣΑΓΩ ΣΤΟΙΧΕΙΑ ΚΑΙ ΘΑ ΔΙΝΕΤΑΙ ΑΠΟ ΤΗΝ ACTIONPERFORMED ΤΟΥ insertButton
        List<String> columnNames = new ArrayList<>();           //ΔΗΜΙΟΥΡΓΩ ΜΙΑ ΛΙΣΤΑ (List) ΜΕ ΟΝΟΜΑ columnNames ΠΟΥ ΑΠΟΘΗΚΕΥΕΙ ΣΥΜΒΟΛΟΣΕΙΡΕΣ. ΤΟ ArrayList ΕΙΝΑΙ ΜΙΑ ΚΛΑΣΗ ΠΟΥ ΥΛΟΠΟΙΕΙ ΤΗ ΔΟΜΗ ΔΕΔΟΜΕΝΩΝ ΛΙΣΤΑ
        
        try {
            Connection conn = ConnectMe.getConnection();
            String query = "SELECT column_name FROM information_schema.columns WHERE table_name = ?";    
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tableName); // Ορίζουμε το όνομα του πίνακα
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                columnNames.add(rs.getString("column_name")); // Παίρνουμε τα ονόματα στηλών
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return columnNames;
    }
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------//
    
    public ManagerApp() {
        initComponents();
        
        loadTablesIntoComboBox();           //ΤΗ ΚΑΛΩ ΠΡΩΤΟΝ ΕΔΩ ΩΣΤΕ ΜΕ ΤΟ ΠΟΥ ΞΕΚΙΝΗΣΕΙ ΤΟ JFRAME1 ΤΟ COMBOBOX ΝΑ ΕΧΕΙ ΕΝΗΜΕΡΩΘΕΙ ΑΝΑΛΟΓΩΣ
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------//
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tableName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        spinner = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tableDel = new javax.swing.JTextField();
        deleteButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        insertButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Επεξεργασία Βάσεων Δεδομένων");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel1.setText("ΔΗΜΙΟΥΡΓΙΑ ΠΙΝΑΚΑ ΣΤΗ ΒΑΣΗ ΔΕΔΟΜΕΝΩΝ");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel2.setText("Δώσε όνομα για τον πίνακα που θες να δημιουργήσεις : ");

        jLabel3.setText("Πόσες στήλες θέλεις να έχει ο πίνακας ; ");

        spinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabel4.setText("(Max : 10)");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel5.setText("ΔΙΑΓΡΑΦΗ ΠΙΝΑΚΑ ΑΠΟ ΤΗ ΒΑΣΗ ΔΕΔΟΜΕΝΩΝ");

        jLabel6.setText("Δώσε όνομα για τον πίνακα που θέλεις να διαγράψεις :");

        deleteButton.setText("Διαγραφή");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel7.setText("ΕΠΕΞΕΡΓΑΣΙΑ ΤΗΣ ΒΑΣΗΣ ΔΕΔΟΜΕΝΩΝ");

        jLabel8.setText("Επίλεξε τον πίνακα που θέλεις να επεξεργαστείς :");

        jLabel9.setText("(Πίνακες στη βάση live)");

        insertButton.setText("Επεξεργασία");
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        createButton.setText("Δημιουργία");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 33, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(29, 29, 29))
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tableDel, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(createButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(deleteButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tableName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(insertButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(tableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(createButton)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(tableDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteButton)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(insertButton)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //----------------------------------------------------------------------------------------------------------------------------------------------------//
    
    //ΔΗΜΙΟΡΥΓΙΑ ΠΙΝΑΚΩΝ
    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        //ΟΤΑΝ ΠΑΤΙΕΤΑΙ ΤΟ jButton ΘΑ ΔΗΜΙΟΥΡΓΕΙΤΑΙ Ο ΕΚΑΣΤΟΤΕ ΠΙΝΑΚΑΣ ΣΤΗ ΒΑΣΗ
        
        String tableNameCreation = tableName.getText().trim(); // Λήψη του ονόματος του πίνακα
    
        if (tableNameCreation.isEmpty()) { // Έλεγχος αν έχει εισαχθεί όνομα πίνακα
            JOptionPane.showMessageDialog(this, "Παρακαλώ εισάγετε όνομα για τον πίνακα");
            return;
        }

        int numColumns = (int) spinner.getValue(); // Λήψη αριθμού στηλών από το Spinner

        if (numColumns <= 0) { // Έλεγχος αν ο αριθμός στηλών είναι έγκυρος
            JOptionPane.showMessageDialog(this, "Παρακαλώ εισάγετε έγκυρο αριθμό στηλών");
            return;
        }
        
        //ΔΗΜΙΟΥΡΓΙΑ JDialog ΠΑΡΑΘΥΡΟΥ (ΧΩΡΙΣ DRAG AND DROP) ΜΕ BORDER LAYOUT ΣΤΟ ΟΠΟΙΟ ΘΑ ΟΡΙΖΟΥΜΕ ΤΑ ΟΝΟΜΑΤΑ ΤΩΝ ΣΤΗΛΩΝ ΤΩΝ ΠΙΝΑΚΩΝ ΠΟΥ ΔΗΜΙΟΥΡΓΟΥΜΕ
        
        //Δημιουργία JDialog για τα ονόματα των στηλών
        JDialog columnDialog = new JDialog(this, "Ορισμός Ονομάτων Στηλών", true);
        columnDialog.setLayout(new BorderLayout());

        // Panel για τα TextFields
        JPanel fieldsPanel = new JPanel(new GridLayout(numColumns, 2, 5, 5));

        JTextField[] columnFields = new JTextField[numColumns];
        for (int i = 0; i < numColumns; i++) {
            JLabel label = new JLabel("Στήλη " + (i + 1) + ":");
            JTextField textField = new JTextField();
            columnFields[i] = textField;
            fieldsPanel.add(label);
            fieldsPanel.add(textField);
        }

        // Κουμπιά OK και Cancel
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        columnDialog.add(fieldsPanel, BorderLayout.CENTER);
        columnDialog.add(buttonsPanel, BorderLayout.SOUTH);

        columnDialog.pack();
        columnDialog.setLocationRelativeTo(this);

        //Ενέργεια για το OK κουμπί
        okButton.addActionListener(e -> {
            // Δημιουργία query
            StringBuilder sqlBuilder = new StringBuilder("CREATE TABLE \"" + tableNameCreation + "\" (");
            for (int i = 0; i < numColumns; i++) {
                String columnName = columnFields[i].getText().trim();
                if (columnName.isEmpty()) {
                    JOptionPane.showMessageDialog(columnDialog, "Όλες οι στήλες πρέπει να έχουν όνομα");
                    return;
                }
                sqlBuilder.append("\"").append(columnName).append("\" VARCHAR(100)");
                if (i < numColumns - 1) {
                    sqlBuilder.append(", ");
                }
            }
            sqlBuilder.append(");");

            // Εκτέλεση query
            try {
                Connection conn = ConnectMe.getConnection();
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sqlBuilder.toString());
                JOptionPane.showMessageDialog(this, "Ο πίνακας '" + tableNameCreation + "' δημιουργήθηκε επιτυχώς!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Σφάλμα κατά τη δημιουργία του πίνακα: " + ex.getMessage());
            }

            columnDialog.dispose();
        });

        // Ενέργεια για το Cancel κουμπί
        cancelButton.addActionListener(e -> columnDialog.dispose());

        columnDialog.setVisible(true);
        //comboBox();
        tableName.setText("");
        spinner.setValue(1);   
        
        loadTablesIntoComboBox();           //ΤΗΝ ΚΑΛΩ ΚΑΙ ΕΔΩ ΩΣΤΕ ΚΑΘΕ ΦΟΡΑ ΠΟΥ ΘΑ ΔΗΜΙΟΥΡΓΕΙΤΑΙ ΕΝΑΣ ΠΙΝΑΚΑΣ, ΤΟ COMBOBOX ΝΑ ΕΝΗΜΕΡΩΝΕΤΑΙ
    }//GEN-LAST:event_createButtonActionPerformed
        
    //----------------------------------------------------------------------------------------------------------------------------------------------------//
    
    //ΔΙΑΓΡΑΦΗ ΠΙΝΑΚΩΝ
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        String tableNameDelete = tableDel.getText().trim(); // Λήψη του ονόματος του πίνακα από το TextField και αφαίρεση περιττών κενών

        if (tableNameDelete.isEmpty()) { // Έλεγχος αν το όνομα του πίνακα είναι κενό
            JOptionPane.showMessageDialog(this, "Παρακαλώ εισάγετε όνομα πίνακα.");
            return; // Διακοπή της μεθόδου αν το όνομα είναι κενό
        }

        // Προσθήκη εισαγωγικών στο όνομα του πίνακα
        tableNameDelete = "\"" + tableNameDelete + "\"";

        Connection conn = ConnectMe.getConnection(); // Παίρνουμε τη σύνδεση από την κλάση MyFrame
        try {
            // Έλεγχος αν υπάρχει ο πίνακας στη βάση
            String checkTableSQL = "SELECT to_regclass(?)"; // Χρήση της to_regclass για έλεγχο ύπαρξης του πίνακα
            try (PreparedStatement stmtCheck = conn.prepareStatement(checkTableSQL)) {
                stmtCheck.setString(1, tableNameDelete); // Ορισμός του ονόματος του πίνακα
                ResultSet rs = stmtCheck.executeQuery(); // Εκτέλεση του ερωτήματος
                if (rs.next() && rs.getString(1) != null) { // Αν ο πίνακας υπάρχει
                    // Διαγραφή του πίνακα
                    String deleteTableSQL = "DROP TABLE " + tableNameDelete;
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(deleteTableSQL); // Εκτέλεση της διαγραφής
                    JOptionPane.showMessageDialog(this, "Ο πίνακας '" + tableNameDelete + "' διαγράφηκε επιτυχώς!");
                } else {
                    // Αν ο πίνακας δεν υπάρχει, εμφάνιση μηνύματος
                    JOptionPane.showMessageDialog(this, "Ο πίνακας που θέλεις να διαγράψεις δεν υπάρχει.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Σφάλμα κατά τη διαγραφή του πίνακα: " + e.getMessage());
        }

        tableDel.setText(""); // Καθαρισμός του TextField μετά τη διαγραφή του πίνακα
        loadTablesIntoComboBox();  //ΤΗΝ ΚΑΛΩ ΚΑΙ ΕΔΩ ΩΣΤΕ ΚΑΘΕ ΦΟΡΑ ΠΟΥ ΘΑ ΔΙΑΓΡΑΦΕΤΑΙ ΕΝΑΣ ΠΙΝΑΚΑΣ, ΤΟ COMBOBOX ΝΑ ΕΝΗΜΕΡΩΝΕΤΑΙ
    }//GEN-LAST:event_deleteButtonActionPerformed
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------//
    
    //ΕΙΣΑΓΩΓΗ ΣΤΟΙΧΕΙΩΝ ΣΤΟΥΣ ΠΙΝΑΚΕΣ ΤΗΣ ΒΑΣΗΣ ΔΕΔΟΜΕΝΩΝ
    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed
         /*
        //ΑΝ ΑΝΤΙ ΓΙΑ COMBOBOX Ο ΧΡΗΣΤΗΣ ΕΔΙΝΕ ΟΝΟΜΑ ΤΟΥ ΠΙΝΑΚΑ ΜΕΣΩ TEXTFIELD
        String tableName = tableInsert.getText();           //ΠΑΡΕ ΤΟ ΠΕΡΙΕΧΟΜΕΝΟ ΤΟΥ TextField
        
        if (tableName.isEmpty()){                           //ΑΝ Ο ΧΡΗΣΤΗΣ ΔΕΝ ΕΔΩΣΕ ΟΝΟΜΑ ΓΙΑ ΠΙΝΑΚΑ
            JOptionPane.showMessageDialog(this, "Παρακαλώ δώσε όνομα πίνακα για εισαγωγή στοιχείων.");        //ΕΜΦΑΝΙΣΕ ΑΥΤΟ ΤΟ POP-UP MESSAGE
            return; //ΔΙΑΚΟΠΗ ΜΕΘΟΔΟΥ ΕΑΝ ΔΕΝ ΔΟΘΕΙ ΟΝΟΜΑ ΓΙΑ ΤΟΝ ΠΙΝΑΚΑ
        }
        
        tableName = "\"" + tableName + "\"";                //ΒΑΖΩ ΤΑ ΕΙΣΑΓΩΓΙΚΑ ΣΤΗ ΜΕΤΑΒΛΗΤΗ ΤΟΥ ΟΝΟΜΑΤΟΣ ΤΟΥ ΠΙΝΑΚΑ ΩΣΤΕ ΝΑ ΜΠΟΡΩ ΝΑ ΤΡΟΠΟΠΟΙΣΩ ΠΙΝΑΚΕΣ ΜΕ ΟΠΟΙΟΔΗΠΟΤΕ ΟΝΟΜΑ
        
        //ΑΡΧΗ ΕΛΕΓΧΟΥ ΥΠΑΡΞΗΣ ΤΟΥ ΠΙΝΑΚΑ ΣΤΗ ΒΑΣΗ 
                                                            //ΣΗΜΕΙΩΣΗ : ΜΕ ΤΑ PREPAREDSTATEMENTS ΠΕΡΝΑΩ ΣΤΗ ΒΑΣΗ ΔΕΔΟΜΕΝΩΝ SQL QUERYS ΕΥΚΟΛΑ ΑΛΛΑ ΚΑΙ ΜΕ ΑΣΦΑΛΕΙΑ ΚΑΤΑ ΤΑ SQL INJECTIONS
        Connection conn = MyFrame.getConnection();          //ΣΥΝΔΕΣΗ ΜΕ ΤΗ ΒΑΣΗ ΕΞΩ ΑΠΟ ΤΟ try-catch
        String checkTableSQL = "SELECT to_regclass(?)";     //Χρησιμοποίησε το to_regclass, το οποίο επιστρέφει το όνομα του πίνακα αν υπάρχει (ΕΙΝΑΙ ΕΝΤΟΛΗ SQL), ή NULL αν δεν υπάρχει
        try(PreparedStatement stmtCheck = conn.prepareStatement(checkTableSQL); ){  //ΕΠΙΧΕΙΡΩ ΣΥΝΔΕΣΗ ΜΕ ΤΗ ΒΑΣΗ ΜΕΣΩ ΤΗΣ Connection conn ΑΠΟ ΤΟ source ΜΕΡΟΣ ΤΟΥ MyFrame ΚΑΙ ΠΕΡΝΑΩ ΤΟ ΠΡΟΕΤΟΙΜΑΣΕΝΟ SQL ΕΡΩΤΗΜΑ (checkTableSQL -> to_reqclass(?) -> ελεγχος υπαρξης πινακα)
          
            stmtCheck.setString(1, tableName);              //ΚΑΘΟΡΙΖΩ ΤΗ ΤΙΜΗ ΤΗΣ ΠΡΩΤΗΣ (ΚΑΙ ΜΟΝΑΔΙΚΗΣ) ΠΑΡΑΜΕΤΡΟΥ ΣΤΟ SQL ΕΡΩΤΗΜΑ -> SELECT to_regclass(tableName)
            ResultSet rs = stmtCheck.executeQuery();        //ΕΚΤΕΛΩ ΤΟ SQL QUERY (SELECT...) ΚΑΙ ΑΠΟΘΗΚΕΥΩ ΤΑ ΑΠΟΤΕΛΕΣΜΑΤΑ ΣΤΗ ΜΕΤΑΒΛΗΤΗ rs
            
            if(rs.next() && rs.getString(1) == null){       //ΑΝ ΛΟΙΠΟΝ (ΣΥΜΦΩΝΑ ΚΑΙ ΜΕ ΤΑ ΠΑΡΑΠΑΝΩ) Ο ΠΙΝΑΚΑΣ ΔΕΝ ΥΠΑΡΧΕΙ 
                JOptionPane.showMessageDialog(this, "Ο πίνακας αυτός δεν υπάρχει στη βάση");            //ΑΝΟΙΞΕ POP UP Message ΜΕ ΚΑΤΑΛΛΛΟ ΜΗΝΥΜΑ
                return;
            }else{
                //ΕΔΩ ΘΑ ΒΑΛΩ ΤΟΝ ΚΩΔΙΚΑ ΓΙΑ ΤΗΝ ΕΙΣΑΓΩΓΗ ΣΤΟΙΧΕΙΩΝ ΣΤΙΣ ΣΤΗΛΕΣ ΤΟΥ ΠΙΝΑΚΑ ΑΦΟΥ Ο ΠΙΝΑΚΑΣ ΥΠΑΡΧΕΙ
                
            }
            
        }catch (SQLException ex){                           //ΑΝ ΓΙΑ ΚΑΠΟΙΟ ΛΟΓΟ ΔΕΝ ΠΕΤΥΧΕΙ Η ΣΥΝΔΕΣΗ ΤΟ ΠΡΟΓΡΑΜΜΑ ΔΕΝ ΠΕΦΤΕΙ ΑΛΛΑ ΡΙΧΝΕΙ ΜΙΑ ΕΞΑΙΡΕΣΗ 
            JOptionPane.showMessageDialog(this, "Σφάλμα κατά τη σύνδεση με τη βάση : " + ex.getMessage());
        }
        */
        
        //ΚΩΔΙΚΑΣ, ΓΙΑ ΠΕΡΙΠΤΩΣΗ ΧΩΡΙΣ ΔΥΝΑΜΙΚΟ ΠΙΝΑΚΑ
        //ΑΝ ΔΕΝ ΕΧΕΙ ΕΠΙΛΕΧΘΕΙ ΠΙΝΑΚΑΣ ΑΠΟ ΤΟ COMBO΄Ή ΔΕΝ ΥΠΑΡΧΕΙ ΠΙΝΑΚΑΣ ΣΤΟ COMBO 
        /*if (combo.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "Δεν έχει επιλεχθεί κάποιος πίνακας.");      //ΕΜΦΑΝΙΣΕ ΚΑΤΑΛΛΗΛΟ POP-UP MESSAGE
            return;
        }else{
            JDialog dialog = new JDialog();  //ΔΗΜΙΟΥΡΓΩ ΕΝΑ JDIALOG (ΑΝΤΙΣΤΟΙΧΑ ΘΑ ΜΠΟΡΟΥΣΑ ΝΑ ΟΡΙΣΩ ΤΙΛΤΟ ΩΣ JDialog dialog = new JDialog(this, "Titlos..");
            
            JPanel panel = new JPanel(new GridLayout(0,2));    //ΔΗΜΙΟΥΡΓΩ ΚΑΙ ΕΝΑ JPANEL (ΜΕ ΑΚΑΝΟΝΙΣΤΟ ΠΛΗΘΟΣ ΓΡΑΜΜΩΝ ΚΑΙ 2 ΣΤΗΛΩΝ) ΣΤΟ ΟΠΟΙΟ ΘΑ ΠΡΟΣΘΕΣΩ ΟΛΑ ΤΑ JCOMPONENTS ΚΑΙ ΤΟ ΟΠΟΙΟ ΘΑ ΠΡΟΣΘΕΣΩ ΣΤΟ JDIALOG
            
            //ΠΑΙΡΝΩ ΤΟ ΟΝΟΜΑ ΤΟΥ ΠΙΝΑΚΑ ΣΤΟΝ ΟΠΟΙΟ ΘΑ ΕΙΣΑΓΩ ΣΤΟΙΧΕΙΑ ΑΠΟ ΤΟ COMBOBOX
            String TableName = combo.getSelectedItem().toString();

            //Λήψη των στηλών του πίνακα μέσω της μεθόδου getColumnNames
            List<String> columnNames = getColumnNames(TableName);
            
            //ΔΗΜΙΟΥΡΓΙΑ ΤΟΥ JDIALOG ΑΝΑΛΟΓΑ ΜΕ ΤΟΝ ΕΚΑΣΤΟΤΕ ΠΙΝΑΚΑ. ΟΣΕΣ ΣΤΗΛΕΣ ΤΟΥ ΠΙΝΑΚΑ -> ΤΟΣΑ ΤΑ TEXTFIELDS ΚΑΙ ΤΑ LABELS.
            for(String columnName : columnNames){    //ΣΗΜΕΙΩΣΗ : ΕΙΔΙΚΗ ΔΥΝΑΤΟΤΗΑ ΤΗΣ ΛΙΣΤΑΣ. ΜΠΟΡΩ ΝΑ ΤΗΝ ΠΡΟΣΠΕΛΑΣΩ ΜΕ ΤΗΝ FOR ΜΕ ΤΟΝ ΤΡΟΠΟ ΠΟΥ ΔΕΙΧΝΕΤΑΙ ΕΔΩ
                JLabel label = new JLabel("Oνομα στηλης : " +columnName);
                JTextField textField = new JTextField();
                panel.add(label);
                panel.add(textField);
            }  
            
            //ΣΤΗ ΣΥΝΕΧΕΙΑ ΔΗΜΙΟΥΡΓΩ 2 BUTTONS (ΕΝΑ OK ΚΑΙ ΕΝΑ CANCEL)
            JButton okButton = new JButton("OK");
            JButton noButton = new JButton ("Cancel");
            
            //ΠΡΟΣΘΕΤΩ ΑΚΡΟΑΤΗ ΣΤΟ okButton. ΟΤΑΝ ΔΗΛΑΔΗ ΠΑΤΗΘΕΙ ΘΑ ΓΙΝΟΥΝ ΤΑ ΠΑΡΑΚΑΤΩ 
            okButton.addActionListener(e -> {
                //ΚΩΔΙΚΑΣ ΓΙΑ ΤΗΝ ΑΠΟΘΗΚΕΥΣΗ ΤΩΝ ΣΤΟΙΧΕΙΩΝ ΣΤΟΥΣ ΠΙΝΑΚΕΣ
                
            });
            
            //ΠΡΟΣΘΕΤΩ ΑΚΡΟΑΤΗ ΣΤΟ noButton. ΟΤΑΝ ΔΗΛΑΔΗ ΠΑΤΗΘΕΙ ΘΑ ΓΙΝΟΥΝ ΤΑ ΠΑΡΑΚΑΤΩ 
            noButton.addActionListener(e -> {
                dialog.setVisible(false);       //ΤΟ ΜΟΝΟ ΠΟΥ ΘΑ ΓΙΝΕΙ ΕΙΝΑΙ ΝΑ ΚΛΕΙΣΕΙ ΤΟ ΠΑΡΑΘΥΡΟ ΧΩΡΙΣ ΝΑ ΓΙΝΕΙ ΚΑΠΟΙΟ SAVE
            });
            
            //ΠΡΟΣΘΕΤΩ ΣΤΟ PANEL ΤΑ 2 JBUTTONS ΜΕ ΤΗ ΣΕΙΡΑ ΠΟΥ ΑΝΑΓΡΑΦΟΝΤΑΙ. ΑΡΑ ΣΤΗΝ ΑΡΙΣΤΕΡΗ (1Η) ΣΤΗΛΗ ΘΑ ΜΠΕΙ ΤΟ ΟΚ ΕΝΩ ΣΤΗ ΔΕΞΙΑ (2Η) ΤΟ CANCEL (ΑΦΟΥ ΕΧΩ GridLayout(0,2))
            panel.add(okButton);
            panel.add(noButton);
            
            dialog.setTitle("Εισαγωγή στοιχείων στον πίνακα : " +TableName);
            dialog.add(panel);                    //ΠΡΟΣΘΕΤΩ ΣΤΟ jDialog ΤΟ jPanel
            //dialog.setSize(300,400);              //ΡΥΘΜΙΖΩ ΤΟ JDialog ΜΕ ΑΥΤΟ ΤΟ SIZE. ΑΝ ΕΧΩ PACK ΔΕΝ ΘΑ ΕΧΩ ΑΥΤΟ.
            dialog.setLocationRelativeTo(this);   //Η ΣΥΓΚΕΚΡΙΜΕΝΗ ΕΝΤΟΛΗ ΑΦΟΡΑ ΤΟ ΠΟΥ ΘΑ ΑΝΟΙΞΕΙ ΤΟ ΠΑΡΑΘΥΡΟ. ΕΤΣΙ ΘΑ ΑΝΟΙΞΕΙ ΣΤΟ ΚΕΝΤΡΟ ΤΟΥ jFrame1(this) ΚΑΙ ΟΧΙ ΟΠΟΥ ΝΑ ΝΑΙ.
            dialog.setVisible(true);              //ΚΑΝΩ ΤΟ DIALOG ΝΑ ΦΑΙΝΕΤΑΙ (ΝΑ ΑΝΟΙΓΕΙ)
            dialog.pack();                        //Η ΣΥΓΚΕΚΡΙΜΕΝΗ ΕΝΤΟΛΗ ΕΥΘΥΝΕΤΑΙ ΓΙΑ ΤΟ ΜΕΓΕΘΟΣ ΑΛΛΑ ΚΑΙ ΤΟ LAYOUT ΚΑΤΑ ΚΑΠΟΙΟ ΤΡΟΠΟ ΤΟΥ DIALOG. ΑΝ ΥΠΑΡΧΕΙ ΑΥΤΗ Η ΕΝΤΟΛΗ ΤΟΤΕ ΤΟ SIZE ΔΕΝ ΧΡΕΙΑΖΕΤΑΙ ΚΑΘΩΣ ΤΟ ΠΑΡΑΘΥΡΟ ΘΑ ΕΜΦΑΝΙΣΤΕΙ ΜΙΚΡΟ ΚΑΙ ΘΑ ΠΕΡΙΕΛΑΜΒΑΝΕΙ ΟΛΑ ΤΑ COMPONENTS ΣΥΜΑΖΕΜΕΝΑ
        }*/
        
        //ΑΝ ΔΕΝ ΥΠΑΡΧΕΙ ΚΑΠΟΙΟΣ ΕΠΙΛΕΓΜΕΝΟΣ ΠΙΝΑΚΑΣ ΑΠΟ ΤΟ COMBOBOX
        if (combo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Δεν έχει επιλεχθεί κάποιος πίνακας.");     //ΑΝΟΙΓΕ POP-UP MESSAGE ΜΕ ΚΑΤΑΛΛΗΛΟ ΜΗΝΥΜΑ
            return;                                                                         //ΤΕΡΜΑΤΙΣΕ ΤΗΝ ΜΕΘΟΔΟ
        }

        //ΑΠΟΘΗΚΕΥΩ ΤΟ ΟΝΟΜΑ ΤΟΥ ΠΙΝΑΚΑ ΠΟΥ ΕΠΕΛΕΞΑ ΑΠΟ ΤΟ COMBO ΓΙΑ ΕΠΕΞΕΡΓΑΣΙΑ
        String tableName = combo.getSelectedItem().toString();

        //ΕΛΕΓΧΟΣ ΜΕ regex ΑΝ ΤΟ ΟΝΟΜΑ ΤΟΥ ΠΙΝΑΚΑ ΕΙΝΑΙ ΕΓΚΥΡΟ
        if (tableName.matches(".*[^a-zA-Z0-9_].*")) {
            JOptionPane.showMessageDialog(this, "Το όνομα του πίνακα περιέχει μη επιτρεπτούς χαρακτήρες."); //ΑΝ ΔΕΝ ΕΙΝΑΙ ΕΓΚΥΡΟ, POP-UP MESSAGE ΜΕ ΚΑΤΑΛΛΗΛΟ ΜΗΝΥΜΑ
            return;         //ΤΕΡΜΑΤΙΣΕ ΤΗ ΜΕΘΟΔΟ
        }

        //ΑΠΟΘΗΚΕΥΩ ΤΑ ΟΝΟΜΑΤΑ ΤΩΝ ΣΤΗΛΩΝ ΚΑΛΩΝΤΑΣ ΤΗΝ ΜΕΘΟΔΟ getColumnNames() ΠΟΥ ΔΗΜΙΟΥΡΓΗΣΑ ΠΑΡΑΠΑΝΩ
        List<String> columnNames = getColumnNames(tableName);
        if (columnNames.isEmpty()) {            //ΑΝ ΔΕΝ ΥΠΑΡΧΟΥΝ ΣΤΗΛΕΣ ΣΤΟΝ ΠΙΝΑΚΑ...
            JOptionPane.showMessageDialog(this, "Ο πίνακας δεν έχει στήλες ή υπάρχει πρόβλημα με τη βάση.");
            return;
        }

        // Δημιουργία του JDialog
        JDialog dialog = new JDialog(this, "Διαχείριση Πίνακα", true);
        dialog.setLayout(new BorderLayout());

        // Δημιουργία του JTable
        DefaultTableModel tableModel = new DefaultTableModel(columnNames.toArray(), 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Προσθήκη κουμπιών στο κάτω μέρος του JDialog
        JPanel buttonPanel = new JPanel();
        JButton addRowButton = new JButton("Προσθήκη Γραμμής");
        JButton saveButton = new JButton("Αποθήκευση");
        JButton deleteButton = new JButton("Διαγραφή Γραμμής");
        JButton cancelButton = new JButton("Ακύρωση");
        buttonPanel.add(addRowButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Φόρτωση δεδομένων από τη βάση
        Set<String> existingRows = new HashSet<>();
        try {
            Connection conn = ConnectMe.getConnection();
            String query = "SELECT * FROM \"" + tableName + "\"";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            // Εκκαθάριση του πίνακα πριν την εισαγωγή νέων δεδομένων
            tableModel.setRowCount(0);

            while (rs.next()) {
                Object[] rowData = new Object[columnNames.size()];
                for (int i = 0; i < columnNames.size(); i++) {
                    rowData[i] = rs.getObject(columnNames.get(i));
                }
                tableModel.addRow(rowData);
                existingRows.add(Arrays.toString(rowData));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Σφάλμα κατά τη φόρτωση δεδομένων: " + ex.getMessage(),
            "Σφάλμα", JOptionPane.ERROR_MESSAGE);
        }

        // Λειτουργικότητα προσθήκης νέας γραμμής
        addRowButton.addActionListener(e -> tableModel.addRow(new Object[columnNames.size()]));

        // Λειτουργικότητα διαγραφής γραμμής
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            }else {
                JOptionPane.showMessageDialog(dialog, "Παρακαλώ επιλέξτε μια γραμμή για διαγραφή.");
            }
        });

        // Λειτουργικότητα αποθήκευσης δεδομένων
        saveButton.addActionListener(e -> {
            try {
                Connection conn = ConnectMe.getConnection();

                // Διαγραφή όλων των δεδομένων από τη βάση πριν την επανεισαγωγή
                String deleteQuery = "DELETE FROM \"" + tableName + "\"";
                PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
                deleteStmt.executeUpdate();

                // Εισαγωγή δεδομένων από το JTable
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    
                    StringBuilder queryBuilder = new StringBuilder("INSERT INTO \"");
                    queryBuilder.append(tableName).append("\" (");
                    queryBuilder.append(columnNames.stream()
                    .map(name -> "\"" + name + "\"")
                    .collect(Collectors.joining(", ")));
                    queryBuilder.append(") VALUES (");
                    queryBuilder.append("?,".repeat(columnNames.size()));
                    queryBuilder.setLength(queryBuilder.length() - 1);
                    queryBuilder.append(")");
                    PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString());
                    
                    for (int j = 0; j < columnNames.size(); j++) {
                        pstmt.setObject(j + 1, tableModel.getValueAt(i, j));
                    }
                    pstmt.executeUpdate();
                }

                JOptionPane.showMessageDialog(dialog, "Τα δεδομένα αποθηκεύτηκαν επιτυχώς!");
                dialog.dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(dialog, "Σφάλμα κατά την αποθήκευση των δεδομένων: " + ex.getMessage(),
                "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Λειτουργικότητα ακύρωσης
        cancelButton.addActionListener(e -> dialog.dispose());

        // Διαμόρφωση και εμφάνιση του JDialog
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);  
    }//GEN-LAST:event_insertButtonActionPerformed

    //----------------------------------------------------------------------------------------------------------------------------------------------------//
    
    //MAIN ΜΕΘΟΔΟΣ
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JButton createButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton insertButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSpinner spinner;
    private javax.swing.JTextField tableDel;
    private javax.swing.JTextField tableName;
    // End of variables declaration//GEN-END:variables
}
