/*
 * ManuLiczekMainView.java
 */

package com.chojnacki.manufaktura.manuliczek;

import com.chojnacki.manufaktura.manuliczek.input.ShopCollector;
import com.chojnacki.manufaktura.manuliczek.model.*;
import com.chojnacki.manufaktura.manuliczek.output.Colorer;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import org.jdesktop.application.Action;
import org.jdesktop.application.*;
import org.jdesktop.application.Task.BlockingScope;
import org.jdesktop.application.Task.InputBlocker;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static com.chojnacki.manufaktura.manuliczek.model.Level.*;
import static com.chojnacki.manufaktura.manuliczek.model.Place.GALLERY;
import static com.chojnacki.manufaktura.manuliczek.model.Place.PATIO;

/**
 * The application's main frame.
 */
public class ManuLiczekMainView extends FrameView {

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
        messageTimer = new Timer(messageTimeout, e -> statusMessageLabel.setText(""));
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, e -> {
            busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
            statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.setAutoUpdateForegroundTask(true);
        taskMonitor.addPropertyChangeListener(evt -> {
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
                String text = (String) (evt.getNewValue());
                statusMessageLabel.setText((text == null) ? "" : text);
                messageTimer.restart();
            } else if ("progress".equals(propertyName)) {
                int value = (Integer) (evt.getNewValue());
                progressBar.setVisible(true);
                progressBar.setIndeterminate(false);
                progressBar.setValue(value);
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

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        exit = new javax.swing.JButton();
        beginProcessing = new javax.swing.JButton();
        idColumnLabel = new javax.swing.JLabel();
        companiesLabel = new javax.swing.JLabel();
        percentageLabel = new javax.swing.JLabel();
        cousineLabel = new javax.swing.JLabel();
        outputFileLabel = new javax.swing.JLabel();
        inputFileLabel = new javax.swing.JLabel();
        sheetNameLabel = new javax.swing.JLabel();
        sheetNameTextField = new javax.swing.JFormattedTextField();
        percentageTextField = new javax.swing.JFormattedTextField();
        cousineTextField = new javax.swing.JFormattedTextField();
        companiesNameTextField = new javax.swing.JFormattedTextField();
        idColumnTextField = new javax.swing.JFormattedTextField();
        inputFileTextField = new javax.swing.JFormattedTextField();
        outputFileTextField = new javax.swing.JFormattedTextField();
        levelLabel = new javax.swing.JLabel();
        groundRadio = new javax.swing.JRadioButton();
        levelRadio = new javax.swing.JRadioButton();
        level2Radio = new javax.swing.JRadioButton();
        galleryRadio = new javax.swing.JRadioButton();
        outsideRadio = new javax.swing.JRadioButton();
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
        indoorSelectorRadioGroup = new javax.swing.ButtonGroup();

        mainPanel.setMaximumSize(new java.awt.Dimension(578, 300));
        mainPanel.setMinimumSize(new java.awt.Dimension(578, 300));
        mainPanel.setName("mainPanel");
        mainPanel.setPreferredSize(new java.awt.Dimension(578, 300));
        mainPanel.setLayout(new java.awt.GridBagLayout());

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.chojnacki.manufaktura.manuliczek.ManuLiczekMain.class).getContext().getActionMap(ManuLiczekMainView.class, this);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.chojnacki.manufaktura.manuliczek.ManuLiczekMain.class).getContext().getResourceMap(ManuLiczekMainView.class);
        int gridY = 0;

        idColumnLabel.setText(resourceMap.getString("idColumnLabel.text"));
        idColumnLabel.setName("idColumnLabel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 0);
        mainPanel.add(idColumnLabel, gridBagConstraints);

        idColumnTextField.setText(resourceMap.getString("idColumnTextField.text"));
        idColumnTextField.setMaximumSize(new java.awt.Dimension(13, 20));
        idColumnTextField.setName("idColumnTextField");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = gridY++;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 20);
        mainPanel.add(idColumnTextField, gridBagConstraints);


        companiesNameTextField.setText(resourceMap.getString("companiesNameTextField.text"));
        companiesNameTextField.setName("companiesNameTextField");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 20);
        mainPanel.add(companiesNameTextField, gridBagConstraints);

        companiesLabel.setText(resourceMap.getString("companiesLabel.text"));
        companiesLabel.setName("companiesLabel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = gridY++;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 0);
        mainPanel.add(companiesLabel, gridBagConstraints);

        percentageTextField.setText(resourceMap.getString("percentageTextField.text"));
        percentageTextField.setName("percentageTextField");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 20);
        mainPanel.add(percentageTextField, gridBagConstraints);

        percentageLabel.setText(resourceMap.getString("percentageLabel.text"));
        percentageLabel.setName("percentageLabel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = gridY++;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 0);
        mainPanel.add(percentageLabel, gridBagConstraints);

        sheetNameLabel.setText(resourceMap.getString("sheetNameLabel.text"));
        sheetNameLabel.setName("sheetNameLabel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 0);
        mainPanel.add(sheetNameLabel, gridBagConstraints);

        sheetNameTextField.setText(resourceMap.getString("sheetNameTextField.text"));
        sheetNameTextField.setName("sheetNameTextField");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = gridY++;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 0);
        mainPanel.add(sheetNameTextField, gridBagConstraints);


        periodLabel.setText(resourceMap.getString("periodLabel.text"));
        periodLabel.setName("periodLabel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 0);
        mainPanel.add(periodLabel, gridBagConstraints);

        periodTextField.setName("periodTextField");
        periodTextField.setText(resourceMap.getString("periodTextField.text"));
        periodTextField.setPreferredSize(new java.awt.Dimension(12, 20));
        periodTextField.setMaximumSize(new java.awt.Dimension(12, 20));
        periodTextField.setMinimumSize(new java.awt.Dimension(12, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = gridY++;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 280);
        mainPanel.add(periodTextField, gridBagConstraints);


        indoorSelectorRadioGroup.add(galleryRadio);
        galleryRadio.setSelected(true);
        galleryRadio.setText(resourceMap.getString("galleryRadio.text"));
        galleryRadio.setName("galleryRadio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        galleryRadio.addActionListener(x -> {
            level2Radio.setEnabled(!galleryRadio.isSelected());
            cousineTextField.setEnabled(!galleryRadio.isSelected());
            if(level2Radio.isSelected()) {
                levelRadio.setSelected(true);
            }
        });
        mainPanel.add(galleryRadio, gridBagConstraints);

        levelLabel.setText(resourceMap.getString("levelLabel.text"));
        levelLabel.setName("levelLabel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        mainPanel.add(levelLabel, gridBagConstraints);

        levelSelectorRadioGroup.add(groundRadio);
        groundRadio.setSelected(true);
        groundRadio.setText(resourceMap.getString("groundRadio.text"));
        groundRadio.setName("groundRadio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = gridY++;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        mainPanel.add(groundRadio, gridBagConstraints);

        levelSelectorRadioGroup.add(levelRadio);
        levelRadio.setText(resourceMap.getString("levelRadio.text"));
        levelRadio.setName("levelRadio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        mainPanel.add(levelRadio, gridBagConstraints);

        indoorSelectorRadioGroup.add(outsideRadio);
        outsideRadio.setText(resourceMap.getString("outsideRadio.text"));
        outsideRadio.setName("outsideRadio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = gridY++;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        outsideRadio.addActionListener(x -> {
            level2Radio.setEnabled(outsideRadio.isSelected());
            cousineTextField.setEnabled(outsideRadio.isSelected());
        });
        mainPanel.add(outsideRadio, gridBagConstraints);

        levelSelectorRadioGroup.add(level2Radio);
        level2Radio.setText(resourceMap.getString("level2Radio.text"));
        level2Radio.setName("level2Radio");
        level2Radio.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = gridY++;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        mainPanel.add(level2Radio, gridBagConstraints);

        cousineTextField.setText(resourceMap.getString("cousineTextField.text"));
        cousineTextField.setName("cousineTextField");
        cousineTextField.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 20);
        mainPanel.add(cousineTextField, gridBagConstraints);

        cousineLabel.setText(resourceMap.getString("cousineTextLabel.text"));
        cousineLabel.setName("cousineTextLabel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = gridY++;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 0);
        mainPanel.add(cousineLabel, gridBagConstraints);

        inputFileTextField.setBackground(resourceMap.getColor("outputFileTextField.background"));
        inputFileTextField.setEditable(false);
        inputFileTextField.setText(resourceMap.getString("inputFileTextField.text"));
        inputFileTextField.setMaximumSize(new java.awt.Dimension(400, 20));
        inputFileTextField.setMinimumSize(new java.awt.Dimension(400, 20));
        inputFileTextField.setName("inputFileTextField");
        inputFileTextField.setPreferredSize(new java.awt.Dimension(400, 20));
        inputFileTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                inputFileTextFieldMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 2, 2);
        mainPanel.add(inputFileTextField, gridBagConstraints);

        inputFileLabel.setText(resourceMap.getString("inputFileLabel.text"));
        inputFileLabel.setName("inputFileLabel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = gridY++;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 2, 5);
        mainPanel.add(inputFileLabel, gridBagConstraints);

        outputFileLabel.setText(resourceMap.getString("outputFileLabel.text"));
        outputFileLabel.setName("outputFileLabel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 5, 5);
        mainPanel.add(outputFileLabel, gridBagConstraints);

        outputFileTextField.setBackground(resourceMap.getColor("outputFileTextField.background"));
        outputFileTextField.setEditable(false);
        outputFileTextField.setText(resourceMap.getString("outputFileTextField.text"));
        outputFileTextField.setAutoscrolls(false);
        outputFileTextField.setMaximumSize(new java.awt.Dimension(300, 20));
        outputFileTextField.setMinimumSize(new java.awt.Dimension(400, 20));
        outputFileTextField.setName("outputFileTextField");
        outputFileTextField.setPreferredSize(new java.awt.Dimension(400, 20));
        outputFileTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                outputFileTextFieldMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = gridY++;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 5, 2);
        mainPanel.add(outputFileTextField, gridBagConstraints);

        exit.setAction(actionMap.get("quit"));
        exit.setMinimumSize(new java.awt.Dimension(0, 0));
        exit.setName("exit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        mainPanel.add(exit, gridBagConstraints);

        beginProcessing.setAction(actionMap.get("beginProcessing"));
        beginProcessing.setMinimumSize(new java.awt.Dimension(0, 0));
        beginProcessing.setName("beginProcessing");
        beginProcessing.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = gridY++;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 5);
        mainPanel.add(beginProcessing, gridBagConstraints);

        menuBar.setName("menuBar");

        fileMenu.setText(resourceMap.getString("fileMenu.text"));
        fileMenu.setName("fileMenu");

        exitMenuItem.setAction(actionMap.get("quit"));
        exitMenuItem.setName("exitMenuItem");
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text"));
        helpMenu.setName("helpMenu");

        aboutMenuItem.setAction(actionMap.get("showAboutBox"));
        aboutMenuItem.setName("aboutMenuItem");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel");

        statusPanelSeparator.setName("statusPanelSeparator");

        statusMessageLabel.setName("statusMessageLabel");

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel");

        progressBar.setName("progressBar");

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
    }

    private void outputFileTextFieldMousePressed(java.awt.event.MouseEvent evt) {
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (((Component) evt.getSource()).isEnabled()) {
                JFileChooser fileSaver;
                if (ManuLiczekMain.getApplication().getOutputFile() != null) {
                    fileSaver = new JFileChooser(ManuLiczekMain.getApplication().getOutputFile().getParentFile());
                } else {
                    fileSaver = new JFileChooser();
                }
                FileFilter fileFilter = new ParametrizedFileFilter("config.acceptedOutput", "config.acceptedOutputDesc");
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
                            overrideFile = overrideAnswer == JOptionPane.YES_OPTION;
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
    }

    private void inputFileTextFieldMousePressed(java.awt.event.MouseEvent evt) {
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (inputFileTextField.isEnabled()) {
                JFileChooser fileInputChooser = new JFileChooser();
                FileFilter fileFilter = new ParametrizedFileFilter("config.acceptedInput", "config.acceptedInputDesc");
                fileInputChooser.setFileFilter(fileFilter);
                int answer = fileInputChooser.showOpenDialog(inputFileTextField);
                if (answer != JFileChooser.CANCEL_OPTION) {
                    handleAddInputFileAction(fileInputChooser.getSelectedFile());
                }
            }
        }
    }


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
        String level = getFloor().equals(FIRST) ? "Level" : getFloor().equals(SECOND) ? "2ndLevel" : "Ground";
        String place = getPlace().isPatio() ? "patio" : "gallery";
        String reversePlace = getPlace().isGallery() ? "patio" : "gallery";

        InputStream levelFile = this.getClass().getClassLoader().getResourceAsStream(ManuLiczekMain.getParametrizedString(place + level + "FilePath", Colorer.class));
        InputStream coordinatesFile = this.getClass().getClassLoader().getResourceAsStream(ManuLiczekMain.getParametrizedString(place + "Coordinates" + level + "Path", Colorer.class));

        if (getPlace().isGallery()) {
            InputStream reserveCoordinatesFile = this.getClass().getClassLoader().getResourceAsStream(ManuLiczekMain.getParametrizedString(reversePlace + "Coordinates" + level + "Path", Colorer.class));
            ManuLiczekMain.getApplication().setReserveInputLocales(reserveCoordinatesFile);
        }

        ManuLiczekMain.getApplication().setIntpuFile(new File(inputFileTextField.getText()));
        ManuLiczekMain.getApplication().setOutputFile(new File(outputFileTextField.getText()));
        InputDataHolder inputDataHolder = new InputDataHolder(ManuLiczekMain.getApplication().getIntpuFile(),
                sheetNameTextField.getText(), idColumnTextField.getText(), percentageTextField.getText(),
                companiesNameTextField.getText(), periodTextField.getText(), cousineTextField.getText());
        ManuLiczekMain.getApplication().setInputImageFile(levelFile);
        ManuLiczekMain.getApplication().setInputLocales(coordinatesFile);
        try {
            ProcessingManager processingManagerTask = new ProcessingManager(inputDataHolder);

            InputBlocker inputBlocker = new InputBlocker(processingManagerTask, BlockingScope.APPLICATION, ManuLiczekMain.getApplication()) {

                @Override
                protected void block() {
                    Arrays.asList(beginProcessing, inputFileTextField, outputFileTextField, idColumnTextField,
                            companiesNameTextField, percentageTextField, sheetNameTextField, levelRadio, level2Radio,
                            groundRadio, outsideRadio, galleryRadio, periodTextField, cousineTextField).parallelStream()
                            .forEach(x -> x.setEnabled(false));
                }

                @Override
                protected void unblock() {
                    Arrays.asList(beginProcessing, inputFileTextField, outputFileTextField, idColumnTextField,
                            companiesNameTextField, percentageTextField, sheetNameTextField, levelRadio,
                            groundRadio, outsideRadio, galleryRadio, periodTextField, cousineTextField).parallelStream()
                            .forEach(x -> x.setEnabled(true));
                    level2Radio.setEnabled(outsideRadio.isSelected());
                    cousineTextField.setEnabled(outsideRadio.isSelected());
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

    public Level getFloor() {
        return levelRadio.isSelected() ? FIRST : level2Radio.isSelected() ? SECOND : GROUND;
    }

    public Place getPlace() {
        return outsideRadio.isSelected() ? PATIO : GALLERY;
    }

    public void showReport(ShopCollector shopCollector, long timeDifferent) {
        ReportWindow report = new ReportWindow(prepareReport(shopCollector, timeDifferent));
        report.setLocation(this.getFrame().getLocation());
        report.setVisible(true);
    }

    private String prepareReport(ShopCollector shopCollector, long timeDifferent) {
        StringBuilder result = new StringBuilder(
                ManuLiczekMain.getParametrizedString("operationSucceded", ManuLiczekMainView.class, timeDifferent / 1000,
                        ManuLiczekMain.getApplication().getOutputFile().getAbsolutePath()));
        result.append(
                ManuLiczekMain.getParametrizedString("shopsWithoutCoordinates", ManuLiczekMainView.class,
                        shopCollector.getShopAllFloorWithoutCoordinates().size(), shopCollector.getShopAllFloorWithoutCoordinates()));
        return result.toString();
    }

    private javax.swing.JButton beginProcessing;
    private javax.swing.JLabel companiesLabel;
    private javax.swing.JFormattedTextField companiesNameTextField;
    private javax.swing.JButton exit;
    private javax.swing.JRadioButton groundRadio;
    private javax.swing.JRadioButton galleryRadio;
    private javax.swing.JLabel idColumnLabel;
    private javax.swing.JFormattedTextField idColumnTextField;
    private javax.swing.JLabel inputFileLabel;
    private javax.swing.JFormattedTextField inputFileTextField;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JRadioButton levelRadio;
    private javax.swing.JRadioButton level2Radio;
    private javax.swing.JRadioButton outsideRadio;
    private javax.swing.ButtonGroup levelSelectorRadioGroup;
    private javax.swing.ButtonGroup indoorSelectorRadioGroup;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel outputFileLabel;
    private javax.swing.JFormattedTextField outputFileTextField;
    private javax.swing.JLabel percentageLabel;
    private javax.swing.JFormattedTextField percentageTextField;
    private javax.swing.JLabel cousineLabel;
    private javax.swing.JFormattedTextField cousineTextField;
    private javax.swing.JLabel periodLabel;
    private javax.swing.JFormattedTextField periodTextField;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel sheetNameLabel;
    private javax.swing.JFormattedTextField sheetNameTextField;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;

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
            boolean fileAccepted;
            try {
                files = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                for (File file1 : files) {
                    file = file1;
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