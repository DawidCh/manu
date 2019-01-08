/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReportWindow.java
 *
 * Created on 2010-01-15, 17:49:41
 */

package com.chojnacki.manufaktura.manuliczek.model;

import com.chojnacki.manufaktura.manuliczek.ManuLiczekMain;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author kalosh
 */
public class ReportWindow extends JDialog {

    public ReportWindow(String reportMessage) {
        this(reportMessage, null);
    }

    
    public ReportWindow(String reportMessage, JFrame owner) {
        super(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initComponents();
        setTitle(ManuLiczekMain.getParametrizedString("reportWindowTitle", ReportWindow.class));
        reportContent.setText(reportMessage);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        String name = ManuLiczekMain.getParametrizedString("reportContent.font.name", ReportWindow.class);
        String size = ManuLiczekMain.getParametrizedString("reportContent.font.size", ReportWindow.class);
        reportContent.setFont(new Font(name, Font.PLAIN, Integer.valueOf(size)));
        scrollPanel.setBorder(BorderFactory.createTitledBorder(ManuLiczekMain.getParametrizedString("scrollPanel.border.title", ReportWindow.class)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        scrollPanel = new javax.swing.JScrollPane();
        reportContent = new javax.swing.JTextArea();
        closeReport = new javax.swing.JButton();

        setName("Form");

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/chojnacki/manufaktura/manuliczek/model/resources/ReportWindow");
        scrollPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("scrollPanel.border.title")));
        scrollPanel.setName("scrollPanel");

        reportContent.setColumns(20);
        reportContent.setEditable(false);
        reportContent.setLineWrap(true);
        reportContent.setRows(5);
        reportContent.setWrapStyleWord(true);
        reportContent.setMaximumSize(new java.awt.Dimension(142, 87));
        reportContent.setMinimumSize(new java.awt.Dimension(142, 87));
        reportContent.setName("reportContent");
        scrollPanel.setViewportView(reportContent);

        closeReport.setText(bundle.getString("close.text"));
        closeReport.setName("closeReport");
        closeReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addComponent(closeReport, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeReport))
        );

        pack();
    }// </editor-fold>
    private void closeReportActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }


    // Variables declaration - do not modify
    private javax.swing.JButton closeReport;
    private javax.swing.JTextArea reportContent;
    private javax.swing.JScrollPane scrollPanel;
    // End of variables declaration

}
