/*
 * ManuLiczekMainView.java
 */

package com.chojnacki.manufaktura.manuliczek;

import com.chojnacki.manufaktura.manuliczek.input.ShopCollector;
import com.chojnacki.manufaktura.manuliczek.model.InputDataHolder;
import com.chojnacki.manufaktura.manuliczek.model.ProcessingManager;
import com.chojnacki.manufaktura.manuliczek.model.ReportWindow;
import com.chojnacki.manufaktura.manuliczek.output.Colorer;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.PlainDocument;
import org.jdesktop.application.Task.BlockingScope;
import org.jdesktop.application.Task.InputBlocker;

/**
 * The application's main frame.
 */
public class ManuLiczekMainView extends FrameView {
    private boolean GROUND_LEVEL = false;
    private boolean FIRST_LEVEL = true;

    

    public ManuLiczekMainView(SingleFrameApplication app) {
        super(app);

        initComponents();
        try {
            UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        getFrame().setResizable(false);

        new JComponentTargetListener(mainPanel);
        new JComponentTargetListener(inputFileTextField);

        periodTextField.setDocument(new LimitedDocument(Integer.valueOf(ManuLiczekMain.getParametrizedString("periodCharsLength", ManuLiczekMainView.class))));

        //attaching listeners
        idColumnTextField.getDocument().addDocumentListener(new EnablingPropertyListener());
        companiesNameTextField.getDocument().addDocumentListener(new EnablingPropertyListener());
        sheetNameTextField.getDocument().addDocumentListener(new EnablingPropertyListener());
        outputFileTextField.getDocument().addDocumentListener(new EnablingPropertyListener());
        inputFileTextField.getDocument().addDocumentListener(new EnablingPropertyListener());
        percentageTextField.getDocument().addDocumentListener(new EnablingPropertyListener());
        periodTextField.getDocument().addDocumentListener(new EnablingPropertyListener());


        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.setAutoUpdateForegroundTask(true);
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = ManuLiczekMain.getApplication().getMainFrame();
            aboutBox = new ManuLiczekMainAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        ManuLiczekMain.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        exit = new javax.swing.JButton();
        beginProcessing = new javax.swing.JButton();
        idColumnLabel = new javax.swing.JLabel();
        companiesLabel = new javax.swing.JLabel();
        percentageLabel = new javax.swing.JLabel();
        outputFileLabel = new javax.swing.JLabel();
        inputFileLabel = new javax.swing.JLabel();
        sheetNameLabel = new javax.swing.JLabel();
        sheetNameTextField = new javax.swing.JFormattedTextField();
        percentageTextField = new javax.swing.JFormattedTextField();
        companiesNameTextField = new javax.swing.JFormattedTextField();
        idColumnTextField = new javax.swing.JFormattedTextField();
        inputFileTextField = new javax.swing.JFormattedTextField();
        outputFileTextField = new javax.swing.JFormattedTextField();
        levelLabel = new javax.swing.JLabel();
        groundRadio = new javax.swing.JRadioButton();
        levelRadio = new javax.swing.JRadioButton();
        periodLabel = new javax.swing.JLabel();
        periodTextField = new javax.swing.JFormattedTextField();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        levelSelectorRadioGroup = new javax.swing.ButtonGroup();

        mainPanel.setMaximumSize(new java.awt.Dimension(578, 280));
        mainPanel.setMinimumSize(new java.awt.Dimension(578, 280));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(578, 280));
        mainPanel.setLayout(new java.awt.GridBagLayout());

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.chojnacki.manufaktura.manuliczek.ManuLiczekMain.class).getContext().getActionMap(ManuLiczekMainView.class, this);
        exit.setAction(actionMap.get("quit")); // NOI18N
        exit.setMinimumSize(new java.awt.Dimension(0, 0));
        exit.setName("exit"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        mainPanel.add(exit, gridBagConstraints);

        beginProcessing.setAction(actionMap.get("beginProcessing")); // NOI18N
        beginProcessing.setMinimumSize(new java.awt.Dimension(0, 0));
        beginProcessing.setName("beginProcessing"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 5);
        mainPanel.add(beginProcessing, gridBagConstraints);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.chojnacki.manufaktura.manuliczek.ManuLiczekMain.class).getContext().getResourceMap(ManuLiczekMainView.class);
        idColumnLabel.setText(resourceMap.getString("idColumnLabel.text")); // NOI18N
        idColumnLabel.setName("idColumnLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 0);
        mainPanel.add(idColumnLabel, gridBagConstraints);

        companiesLabel.setText(resourceMap.getString("companiesLabel.text")); // NOI18N
        companiesLabel.setName("companiesLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 0);
        mainPanel.add(companiesLabel, gridBagConstraints);

        percentageLabel.setText(resourceMap.getString("percentageLabel.text")); // NOI18N
        percentageLabel.setName("percentageLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 0);
        mainPanel.add(percentageLabel, gridBagConstraints);

        outputFileLabel.setText(resourceMap.getString("outputFileLabel.text")); // NOI18N
        outputFileLabel.setName("outputFileLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 5, 5);
        mainPanel.add(outputFileLabel, gridBagConstraints);

        inputFileLabel.setText(resourceMap.getString("inputFileLabel.text")); // NOI18N
        inputFileLabel.setName("inputFileLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 2, 5);
        mainPanel.add(inputFileLabel, gridBagConstraints);

        sheetNameLabel.setText(resourceMap.getString("sheetNameLabel.text")); // NOI18N
        sheetNameLabel.setName("sheetNameLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 0);
        mainPanel.add(sheetNameLabel, gridBagConstraints);

        sheetNameTextField.setText(resourceMap.getString("sheetNameTextField.text")); // NOI18N
        sheetNameTextField.setName("sheetNameTextField"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 0);
        mainPanel.add(sheetNameTextField, gridBagConstraints);

        percentageTextField.setText(resourceMap.getString("percentageTextField.text")); // NOI18N
        percentageTextField.setName("percentageTextField"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 20);
        mainPanel.add(percentageTextField, gridBagConstraints);

        companiesNameTextField.setText(resourceMap.getString("companiesNameTextField.text")); // NOI18N
        companiesNameTextField.setName("companiesNameTextField"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 20);
        mainPanel.add(companiesNameTextField, gridBagConstraints);

        idColumnTextField.setText(resourceMap.getString("idColumnTextField.text")); // NOI18N
        idColumnTextField.setMaximumSize(new java.awt.Dimension(13, 20));
        idColumnTextField.setName("idColumnTextField"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 20);
        mainPanel.add(idColumnTextField, gridBagConstraints);

        inputFileTextField.setBackground(resourceMap.getColor("outputFileTextField.background")); // NOI18N
        inputFileTextField.setEditable(false);
        inputFileTextField.setText(resourceMap.getString("inputFileTextField.text")); // NOI18N
        inputFileTextField.setMaximumSize(new java.awt.Dimension(400, 20));
        inputFileTextField.setMinimumSize(new java.awt.Dimension(400, 20));
        inputFileTextField.setName("inputFileTextField"); // NOI18N
        inputFileTextField.setPreferredSize(new java.awt.Dimension(400, 20));
        inputFileTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                inputFileTextFieldMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 2, 2);
        mainPanel.add(inputFileTextField, gridBagConstraints);

        outputFileTextField.setBackground(resourceMap.getColor("outputFileTextField.background")); // NOI18N
        outputFileTextField.setEditable(false);
        outputFileTextField.setText(resourceMap.getString("outputFileTextField.text")); // NOI18N
        outputFileTextField.setAutoscrolls(false);
        outputFileTextField.setMaximumSize(new java.awt.Dimension(300, 20));
        outputFileTextField.setMinimumSize(new java.awt.Dimension(400, 20));
        outputFileTextField.setName("outputFileTextField"); // NOI18N
        outputFileTextField.setPreferredSize(new java.awt.Dimension(400, 20));
        outputFileTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                outputFileTextFieldMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 5, 2);
        mainPanel.add(outputFileTextField, gridBagConstraints);

        levelLabel.setText(resourceMap.getString("levelLabel.text")); // NOI18N
        levelLabel.setName("levelLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        mainPanel.add(levelLabel, gridBagConstraints);

        levelSelectorRadioGroup.add(groundRadio);
        groundRadio.setSelected(true);
        groundRadio.setText(resourceMap.getString("groundRadio.text")); // NOI18N
        groundRadio.setName("groundRadio"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        mainPanel.add(groundRadio, gridBagConstraints);

        levelSelectorRadioGroup.add(levelRadio);
        levelRadio.setText(resourceMap.getString("levelRadio.text")); // NOI18N
        levelRadio.setName("levelRadio"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        mainPanel.add(levelRadio, gridBagConstraints);

        periodLabel.setText(resourceMap.getString("periodLabel.text")); // NOI18N
        periodLabel.setName("periodLabel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 0);
        mainPanel.add(periodLabel, gridBagConstraints);

        periodTextField.setMaximumSize(new java.awt.Dimension(12, 20));
        periodTextField.setMinimumSize(new java.awt.Dimension(12, 20));
        periodTextField.setName("periodTextField"); // NOI18N
        periodTextField.setPreferredSize(new java.awt.Dimension(12, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 280);
        mainPanel.add(periodTextField, gridBagConstraints);

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 404, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void outputFileTextFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_outputFileTextFieldMousePressed
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (((Component)evt.getSource()).isEnabled()) {
                JFileChooser fileSaver = null;
                if (ManuLiczekMain.getApplication().getOutputFile() != null) {
                    fileSaver = new JFileChooser(ManuLiczekMain.getApplication().getOutputFile().getParentFile());
                } else {
                    fileSaver = new JFileChooser();
                }
                FileFilter fileFilter = new ParametrizedFileFilter("config.acceptedOutput", "config.acceptedOutputDesc") ;
                fileSaver.setFileFilter(fileFilter);
                int openAnswer = fileSaver.showSaveDialog((Component) evt.getSource());
                if (openAnswer != JFileChooser.CANCEL_OPTION) {
                    File outputFile = fileSaver.getSelectedFile();
                    String fileNames[] = outputFile.getName().split("\\.");
                    if (fileNames.length == 1) {
                        outputFile = new File(outputFile.getAbsolutePath() + "." + ManuLiczekMain.getParametrizedString("config.defaultOutputExtension", ManuLiczekMainView.class));
                    }
                    boolean accept = properFile(outputFile, "config.acceptedOutput");
                    if (accept) {
                        boolean overrideFile = false;
                        if (outputFile.exists()) {
                             int overrideAnswer = JOptionPane.showConfirmDialog(ManuLiczekMain.getApplication().getMainFrame(),
                                     ManuLiczekMain.getParametrizedString("questionDialog.overrideFile.message", ManuLiczekMainView.class, outputFile.getName()),
                                     ManuLiczekMain.getParametrizedString("questionDialog.overrideFile.title", ManuLiczekMainView.class),
                                     JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                             overrideFile = overrideAnswer == JOptionPane.YES_OPTION ? true : false;
                        }
                        if (overrideFile || !outputFile.exists()) {
                            outputFileTextField.setText(outputFile.getAbsolutePath());
                            new EnablingPropertyListener().handleAction();
                        }
                    } else {
                        JOptionPane.showMessageDialog(ManuLiczekMain.getApplication().getMainFrame(),
                                ManuLiczekMain.getParametrizedString("errorDialog.invalidOutputExtension.message", ManuLiczekMainView.class,
                                    ManuLiczekMain.getParametrizedString("config.acceptedOutput", ManuLiczekMainView.class)),
                                ManuLiczekMain.getParametrizedString("errorDialog.invalidOutputExtension.title", ManuLiczekMainView.class),
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_outputFileTextFieldMousePressed

    private void inputFileTextFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputFileTextFieldMousePressed
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (inputFileTextField.isEnabled()) {
                JFileChooser fileInputChooser = new JFileChooser();
                FileFilter fileFilter = new ParametrizedFileFilter("config.acceptedInput", "config.acceptedInputDesc") ;
                fileInputChooser.setFileFilter(fileFilter);
                int answer = fileInputChooser.showOpenDialog(inputFileTextField);
                if (answer != JFileChooser.CANCEL_OPTION) {
                    handleAddInputFileAction(fileInputChooser.getSelectedFile());
                }
            }
        }
    }//GEN-LAST:event_inputFileTextFieldMousePressed


    public boolean handleAddInputFileAction(File selectedFile) {
        boolean accept = properFile(selectedFile, "config.acceptedInput");
        if (accept) {
            inputFileTextField.setText(selectedFile.getAbsolutePath());
            new EnablingPropertyListener().handleAction();
        } else {
            JOptionPane.showMessageDialog(ManuLiczekMain.getApplication().getMainFrame(),
                    ManuLiczekMain.getParametrizedString("errorDialog.invalidInputExtension.message", ManuLiczekMainView.class),
                    ManuLiczekMain.getParametrizedString("errorDialog.invalidInputExtension.title", ManuLiczekMainView.class),
                    JOptionPane.ERROR_MESSAGE);
        }
        return accept;
    }
    protected boolean properFile(File file, String keyForExtensions) {
        boolean accept = false;
        if (file != null) {
            String fileNames[] = file.getName().split("\\.");
            if (fileNames.length == 2) {
                String acceptedExtensions[] = ManuLiczekMain.getParametrizedString(keyForExtensions, ManuLiczekMainView.class).split(",");
                String currentExtension;
                for (int i = 0; i < acceptedExtensions.length; i++) {
                    currentExtension = acceptedExtensions[i].trim();
                    if (currentExtension.equalsIgnoreCase(fileNames[1])) {
                        accept = true;
                        break;
                    }
                }
            } else if (fileNames.length == 1 && "config.acceptedOutput".equals(keyForExtensions)) {
                accept = true;
            }
        }
        return accept;
    }

    @Action
    public void beginProcessing() {
        InputStream levelFile, coordinatesFile, coordinatesReserveFile;
        if (getFloor() == FIRST_LEVEL) {
            levelFile = this.getClass().getClassLoader().getResourceAsStream(ManuLiczekMain.getParametrizedString("galleryLevelFilePath", Colorer.class));
            coordinatesFile = this.getClass().getClassLoader().getResourceAsStream(ManuLiczekMain.getParametrizedString("galleryCoordinatesLevelPath", Colorer.class));
            coordinatesReserveFile = this.getClass().getClassLoader().getResourceAsStream(ManuLiczekMain.getParametrizedString("galleryCoordinatesGroundPath", Colorer.class));
        } else {
            levelFile = this.getClass().getClassLoader().getResourceAsStream(ManuLiczekMain.getParametrizedString("galleryGroundFilePath", Colorer.class));
            coordinatesFile = this.getClass().getClassLoader().getResourceAsStream(ManuLiczekMain.getParametrizedString("galleryCoordinatesGroundPath", Colorer.class));
            coordinatesReserveFile = this.getClass().getClassLoader().getResourceAsStream(ManuLiczekMain.getParametrizedString("galleryCoordinatesLevelPath", Colorer.class));
        }
        ManuLiczekMain.getApplication().setIntpuFile(new File(inputFileTextField.getText()));
        ManuLiczekMain.getApplication().setOutputFile(new File(outputFileTextField.getText()));
        InputDataHolder inputDataHolder = new InputDataHolder(ManuLiczekMain.getApplication().getIntpuFile(),
                sheetNameTextField.getText(), idColumnTextField.getText(), percentageTextField.getText(),
                companiesNameTextField.getText(), periodTextField.getText());
        ManuLiczekMain.getApplication().setInputImageFile(levelFile);
        ManuLiczekMain.getApplication().setInputLocales(coordinatesFile);
        ManuLiczekMain.getApplication().setReserveInputLocales(coordinatesReserveFile);
        try {
            ProcessingManager processingManagerTask = new ProcessingManager(inputDataHolder);

            InputBlocker inputBlocker = new InputBlocker(processingManagerTask, BlockingScope.APPLICATION, ManuLiczekMain.getApplication()) {

                @Override
                protected void block() {
                    beginProcessing.setEnabled(false);
                    inputFileTextField.setEnabled(false);
                    outputFileTextField.setEnabled(false);
                    idColumnTextField.setEnabled(false);
                    companiesNameTextField.setEnabled(false);
                    percentageTextField.setEnabled(false);
                    sheetNameTextField.setEnabled(false);
                    levelRadio.setEnabled(false);
                    groundRadio.setEnabled(false);
                    periodTextField.setEnabled(false);
                }

                @Override
                protected void unblock() {
                    beginProcessing.setEnabled(true);
                    inputFileTextField.setEnabled(true);
                    outputFileTextField.setEnabled(true);
                    idColumnTextField.setEnabled(true);
                    companiesNameTextField.setEnabled(true);
                    percentageTextField.setEnabled(true);
                    sheetNameTextField.setEnabled(true);
                    levelRadio.setEnabled(true);
                    groundRadio.setEnabled(true);
                    periodTextField.setEnabled(true);
                }
            };
            processingManagerTask.setInputBlocker(inputBlocker);
            ManuLiczekMain.getApplication().executeTask(processingManagerTask);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ManuLiczekMain.getApplication().getMainFrame(),
                ManuLiczekMain.getParametrizedString("errorDialog.processingTaskInitializationError.message", ManuLiczekMainView.class),
                ManuLiczekMain.getParametrizedString("errorDialog.processingTaskInitializationError.title", ManuLiczekMainView.class),
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    public boolean getFloor() {
        boolean result;
        if(levelRadio.isSelected()) {
            result = FIRST_LEVEL;
        } else {
            result = GROUND_LEVEL;
        }
        return result;
    }
    public void showReport(ShopCollector shopCollector, long timeDifferent) {
        ReportWindow report = new ReportWindow(prepareReport(shopCollector, timeDifferent));
        report.setLocation(this.getFrame().getLocation());
        report.setVisible(true);
    }

    private String prepareReport(ShopCollector shopCollector, long timeDifferent) {
        StringBuilder result = new StringBuilder(
                ManuLiczekMain.getParametrizedString("operationSucceded", ManuLiczekMainView.class, timeDifferent/1000,
                ManuLiczekMain.getApplication().getOutputFile().getAbsolutePath()));
        result.append(
                ManuLiczekMain.getParametrizedString("shopsWithoutCoordinates", ManuLiczekMainView.class,
                shopCollector.getShopAllFloorWithoutCoordinates().size(), shopCollector.getShopAllFloorWithoutCoordinates()));
        return result.toString();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton beginProcessing;
    private javax.swing.JLabel companiesLabel;
    private javax.swing.JFormattedTextField companiesNameTextField;
    private javax.swing.JButton exit;
    private javax.swing.JRadioButton groundRadio;
    private javax.swing.JLabel idColumnLabel;
    private javax.swing.JFormattedTextField idColumnTextField;
    private javax.swing.JLabel inputFileLabel;
    private javax.swing.JFormattedTextField inputFileTextField;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JRadioButton levelRadio;
    private javax.swing.ButtonGroup levelSelectorRadioGroup;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel outputFileLabel;
    private javax.swing.JFormattedTextField outputFileTextField;
    private javax.swing.JLabel percentageLabel;
    private javax.swing.JFormattedTextField percentageTextField;
    private javax.swing.JLabel periodLabel;
    private javax.swing.JFormattedTextField periodTextField;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel sheetNameLabel;
    private javax.swing.JFormattedTextField sheetNameTextField;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;

    class EnablingPropertyListener implements DocumentListener {

        public void handleAction() {
            if (outputFileTextField.getText() != null && !"".equals(outputFileTextField.getText()) &&
                    inputFileTextField.getText() != null && !"".equals(inputFileTextField.getText()) &&
                    sheetNameTextField.getText() != null && !"".equals(sheetNameTextField.getText()) &&
                    idColumnTextField.getText() != null && !"".equals(idColumnTextField.getText()) &&
                    percentageTextField.getText() != null && !"".equals(percentageTextField.getText()) &&
                    companiesNameTextField.getText() != null && !"".equals(companiesNameTextField.getText())) {
                beginProcessing.setEnabled(true);
            } else {
                beginProcessing.setEnabled(false);
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            handleAction();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            handleAction();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            handleAction();
        }
    }

    class JComponentTargetListener extends DropTargetAdapter {

        JComponentTargetListener(Component component) {
            new DropTarget(component, DnDConstants.ACTION_MOVE, this, true);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void drop(DropTargetDropEvent dtde) {
            dtde.acceptDrop(dtde.getDropAction());
            List<File> files;
            File file;
            boolean fileAccepted = false;
            try {
                files = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                for (Iterator<File> it = files.iterator(); it.hasNext();) {
                    file = it.next();
                    fileAccepted = handleAddInputFileAction(file);
                    if (fileAccepted) {
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    class LimitedDocument extends PlainDocument {
        private int limit;

        LimitedDocument(int limit) {
            this.limit = limit;
        }

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (periodTextField.getText().length() < limit) {
                super.insertString(offs, str, a);
            }
        }

        @Override
        protected void insertUpdate(DefaultDocumentEvent chng, AttributeSet attr) {
            if (periodTextField.getText().length() < limit) {
                super.insertUpdate(chng, attr);
            }
        }

    }
}

class ParametrizedFileFilter extends FileFilter {
    private String acceptedExtensionDescKey;
    private String acceptedExtensionKey;

    public ParametrizedFileFilter(String acceptedExtensionKey, String acceptedExtensionDescKey) {
        this.acceptedExtensionKey = acceptedExtensionKey;
        this.acceptedExtensionDescKey = acceptedExtensionDescKey;
    }

    @Override
    public boolean accept(File file) {
        String fileNames[] = file.getName().split("\\.");
        boolean accept = false;

        if (file.isDirectory()) {
            accept = true;
        } else if (fileNames.length == 2) {
            String acceptedExtensions[] = ManuLiczekMain.getParametrizedString(acceptedExtensionKey, ManuLiczekMainView.class).split(",");
            String currentExtension;
            for (int i = 0; i < acceptedExtensions.length; i++) {
                currentExtension = acceptedExtensions[i].trim();
                if (currentExtension.equalsIgnoreCase(fileNames[1])) {
                    accept = true;
                    break;
                }
            }
        }
        return accept;
    }

    @Override
    public String getDescription() {
        return ManuLiczekMain.getParametrizedString(acceptedExtensionDescKey, ManuLiczekMainView.class);
    }
}