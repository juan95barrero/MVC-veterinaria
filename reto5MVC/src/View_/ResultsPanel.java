
package View_;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
    Esta clase es la encargada de crear la tabla donde se mostraran los dato de la BD
    con los criterios de filtracion o sin ellos
*/
class ResultsPanel extends JPanel {

    private JTable tblResults;
    private JScrollPane jspPane;

    public ResultsPanel() {
        initComponents();
    }

    private void initComponents() {
        this.tblResults = new JTable();
        this.tblResults.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        this.jspPane = new JScrollPane(this.tblResults);
        add(jspPane, BorderLayout.CENTER);

        setPreferredSize(new Dimension(650, 800));
    }

    public JTable getTblResults() {
        return tblResults;
    }

    public void setTblResults(JTable tblResults) {
        this.tblResults = tblResults;
    }
}
