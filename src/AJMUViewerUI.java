/*
 * AJMUDAViewer.java
 * 
 * Uses Desktop API available in Java SE 6.
 * This application must run with Java SE 6 or later.
 * Set criteria to view usability metrics form xml log in browser 
 *
 */

import java.awt.Desktop;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import java.util.Enumeration;
import java.util.List;
/**
 * AJMU(DA) - Viewer
 * 
 * @author  pi-315
 *
 */
public class AJMUViewerUI extends javax.swing.JFrame {
    
	// Variables declaration 
    ButtonGroup bgCompletionTime;
    ButtonGroup bgSuccessRunTask;
    ButtonGroup bgErrorsEvents;
    ButtonGroup bgUserSatisf;
    ButtonGroup bgCriteria;
    
    JButton btnGetUsabilityMetrics;
    
    JLabel lblApp;
    JLabel lblTask;
    
    JCheckBox taskCompletionButton;
    JCheckBox successRunTaskButton;
    JCheckBox errorsEventsButton;
    JCheckBox userSatisfButton;
    
    JRadioButton rbMax;
    JRadioButton rbAvg;
    JRadioButton rbMin;
    
    JRadioButton rbFinalized;
    JRadioButton rbNotFinalized;
    JRadioButton rbInterrupted;
    
    JRadioButton rbExceptions;
    JRadioButton rbAccessDoc;
    JRadioButton rbDialogues;
    
    JRadioButton rbVerySatisf;
    JRadioButton rbSatisf;
    JRadioButton rbNeutral;
    JRadioButton rbDissatisf;
    JRadioButton rbVeryDissatisf;
    
    JComboBox cmbTasks;
     
    private Desktop desktop;

    private String xslFileName, xmlFileName;
    
	/**
     * Creates new form AJMUViewerUI
     */
    public AJMUViewerUI() {
        // init all gui components
        initComponents();
        
        // before any Desktop APIs are used, first check whether the API is
        // supported by this particular VM on this particular host
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
            // now enable buttons for actions that are supported.
            enableSupportedActions();
        }     
    }    
       
    /**
     * Enable actions that are supported on this host.
     * The action is: open html files using their associated application
     */    
    private void enableSupportedActions() {
        
        if (desktop.isSupported(Desktop.Action.OPEN)) {
        	
            rbFinalized.setEnabled(false);
            rbNotFinalized.setEnabled(false);
            rbInterrupted.setEnabled(false);
            
            rbExceptions.setEnabled(false);
            rbAccessDoc.setEnabled(false);
            rbDialogues.setEnabled(false);
            
            rbVerySatisf.setEnabled(false);
            rbSatisf.setEnabled(false);
            rbNeutral.setEnabled(false);
            rbDissatisf.setEnabled(false);
            rbVeryDissatisf.setEnabled(false);

        	cmbTasks.setEnabled(true);             
            btnGetUsabilityMetrics.setEnabled(true);
        }
    }
    

    /** This method is called from within the constructor to
     * initialize the form. 
     */    
    private void initComponents() {
    	
    	xmlFileName = "usabilityrecord.xml";
    	java.awt.GridBagConstraints gridBagConstraints;

        bgSuccessRunTask = new javax.swing.ButtonGroup();
        bgErrorsEvents = new javax.swing.ButtonGroup();
        bgCompletionTime = new javax.swing.ButtonGroup();        
        bgUserSatisf = new javax.swing.ButtonGroup();  
        bgCriteria = new javax.swing.ButtonGroup();  
        
        rbFinalized = new javax.swing.JRadioButton("Finalized");
        rbNotFinalized = new javax.swing.JRadioButton("Not Finalized");        
        rbInterrupted = new javax.swing.JRadioButton("Interrupted");
        bgSuccessRunTask.add(rbFinalized);        
        bgSuccessRunTask.add(rbNotFinalized);
        bgSuccessRunTask.add(rbInterrupted);         
        
        rbExceptions = new javax.swing.JRadioButton("Exceptions");
        rbAccessDoc = new javax.swing.JRadioButton("Access to Help");         
        rbDialogues = new javax.swing.JRadioButton("User's Dialogues");
        bgErrorsEvents.add(rbExceptions);        
        bgErrorsEvents.add(rbAccessDoc);
        bgErrorsEvents.add(rbDialogues);         
        
        rbVerySatisf = new javax.swing.JRadioButton("Very Satisf.");
        rbSatisf = new javax.swing.JRadioButton("Satisfied");
        rbNeutral = new javax.swing.JRadioButton("Neutral");
        rbDissatisf = new javax.swing.JRadioButton("Dissatisfied");
        rbVeryDissatisf = new javax.swing.JRadioButton("Very Dissatisf.");
        bgUserSatisf.add(rbVerySatisf);        
        bgUserSatisf.add(rbSatisf);
        bgUserSatisf.add(rbNeutral);
        bgUserSatisf.add(rbDissatisf);
        bgUserSatisf.add(rbVeryDissatisf);         
                
        rbMax = new javax.swing.JRadioButton("Maximum");
        rbAvg = new javax.swing.JRadioButton("Average");        
        rbMin = new javax.swing.JRadioButton("Minimum");
        bgCompletionTime.add(rbMax);        
        bgCompletionTime.add(rbAvg);
        bgCompletionTime.add(rbMin);        
        
        btnGetUsabilityMetrics = new javax.swing.JButton();
        
        lblApp	=new javax.swing.JLabel();
        
        setAppAttributes();
        
        lblTask = new javax.swing.JLabel();
        
        taskCompletionButton = new JCheckBox("Time Task Completion",true);
        enableBg(bgCompletionTime);
        xslFileName	=  "eMaximumAll"; 
		rbMax.setSelected(true);
        successRunTaskButton= new JCheckBox("Success Run Task");
        errorsEventsButton= new JCheckBox("Errors / User's Events");
        userSatisfButton= new JCheckBox("Customer Satisfaction");
        bgCriteria.add(taskCompletionButton);
        bgCriteria.add(successRunTaskButton);
        bgCriteria.add(errorsEventsButton);
        bgCriteria.add(userSatisfButton);
       
        //Register a listener for the check boxes.
        taskCompletionButton.addItemListener(new java.awt.event.ItemListener() {
        	public void itemStateChanged(ItemEvent evt) {
                onCheckBoxAction(evt);
            }
        });
        successRunTaskButton.addItemListener(new java.awt.event.ItemListener() {
        	public void itemStateChanged(ItemEvent evt) {
                onCheckBoxAction(evt);
            }
        });
        errorsEventsButton.addItemListener(new java.awt.event.ItemListener() {
        	public void itemStateChanged(ItemEvent evt) {
                onCheckBoxAction(evt);
            }
        });
        userSatisfButton.addItemListener(new java.awt.event.ItemListener() {
        	public void itemStateChanged(ItemEvent evt) {
                onCheckBoxAction(evt);
            }
        });
        
        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("USABILITY METRICS FOR DESKTOP APPLICATION");                
        
        rbFinalized.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbFinalized.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbFinalized.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onFinalizedAction(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbFinalized, gridBagConstraints);         
        
        rbNotFinalized.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbNotFinalized.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbNotFinalized.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onNotFinalizedAction(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbNotFinalized, gridBagConstraints);
        
        rbInterrupted.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbInterrupted.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbInterrupted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onInterruptedAction(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbInterrupted, gridBagConstraints);
        /////
        
        rbExceptions.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbExceptions.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbExceptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onExceptionsAction(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbExceptions, gridBagConstraints);
        
        rbAccessDoc.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbAccessDoc.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbAccessDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAccessDocAction(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbAccessDoc, gridBagConstraints);
        
        rbDialogues.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbDialogues.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbDialogues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDialoguesAction(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbDialogues, gridBagConstraints);

        rbVerySatisf.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbVerySatisf.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbVerySatisf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onVerySatisfAction(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbVerySatisf, gridBagConstraints);
        
        rbSatisf.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbSatisf.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbSatisf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSatisfAction(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbSatisf, gridBagConstraints);
        
        rbNeutral.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbNeutral.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbNeutral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onNeutralAction(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbNeutral, gridBagConstraints);        
        
        rbDissatisf.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbDissatisf.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbDissatisf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDissatisfAction(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbDissatisf, gridBagConstraints);        
        
        rbVeryDissatisf.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbVeryDissatisf.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbVeryDissatisf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onVeryDissatisfAction(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbVeryDissatisf, gridBagConstraints);
        
        ///// 
        
        rbMax.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbMax.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onMaxAction(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbMax, gridBagConstraints);

        
        rbAvg.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbAvg.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbAvg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAvgAction(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbAvg, gridBagConstraints);
       
        rbMin.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rbMin.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rbMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onMinAction(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbMin, gridBagConstraints);
                
        populatecmbTasks();

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(cmbTasks, gridBagConstraints);

        btnGetUsabilityMetrics.setText("Get Usability Metrics");
        btnGetUsabilityMetrics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {                
					try {
						onLaunchDefaultApplication(evt);
					} catch (TransformerConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (TransformerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (TransformerFactoryConfigurationError e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(btnGetUsabilityMetrics, gridBagConstraints);
        
        lblTask.setText("Select a Task:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(lblTask, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add( taskCompletionButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(successRunTaskButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(errorsEventsButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(userSatisfButton, gridBagConstraints);        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(lblApp, gridBagConstraints);

        
        pack();
    }
    
    /** Listens to the check boxes. */
    public void onCheckBoxAction(java.awt.event.ItemEvent e) {
        int index = 0;        
        Object source = e.getItemSelectable();

        if (source == taskCompletionButton) {
            index = 0;           
        } else if (source == successRunTaskButton) {
            index = 1;            
        } else if (source == errorsEventsButton) {
            index = 2;            
        } else if (source == userSatisfButton) {
            index = 3;            
        }

        if (e.getStateChange() == ItemEvent.SELECTED) {             
        	
        	switch  (index) {
        	case 0: enableBg(bgCompletionTime);
        			xslFileName	=  "eMaximumAll"; 
        			rbMax.setSelected(true);
        			disableBg(bgSuccessRunTask);disableBg(bgErrorsEvents);disableBg(bgUserSatisf);
        			break;
        	case 1: enableBg(bgSuccessRunTask);
        			xslFileName 	="eFinalizedAll";
        			rbFinalized.setSelected(true);
        			disableBg(bgCompletionTime);disableBg(bgErrorsEvents);disableBg(bgUserSatisf);
					break;
        	case 2: enableBg(bgErrorsEvents);
        			xslFileName	= "eExceptionsAll";
        			rbExceptions.setSelected(true);
        			disableBg(bgCompletionTime);disableBg(bgSuccessRunTask);disableBg(bgUserSatisf);
	            	break;
        	case 3: enableBg(bgUserSatisf);
        			xslFileName	= "eVerySatisfAll";
        			rbVerySatisf.setSelected(true);
        			disableBg(bgCompletionTime);disableBg(bgSuccessRunTask);disableBg(bgErrorsEvents);
	            	break;
        	}
        }
    }
    
    private void enableBg(ButtonGroup bg){    	
    	
    	Enumeration<AbstractButton> buttons =  bg.getElements();
    	while(buttons.hasMoreElements()){   
    			buttons.nextElement().setEnabled(true);
    	}
    }
    
    private void disableBg(ButtonGroup bg){
    	Enumeration<AbstractButton> buttons =  bg.getElements();
    	while(buttons.hasMoreElements()){ 
    		buttons.nextElement().setEnabled(false);    		 
    	}    	
    }
    
    private void onFinalizedAction(java.awt.event.ActionEvent evt) {
        xslFileName	=  "eFinalizedAll";         
    }
    
    private void onNotFinalizedAction(java.awt.event.ActionEvent evt) {
    	xslFileName	=  "eNotFinalizedAll";         
    }
    
    private void onInterruptedAction(java.awt.event.ActionEvent evt) {
    	xslFileName	= "eInterruptedAll";         
    }
    
    private void onExceptionsAction(java.awt.event.ActionEvent evt) {
        xslFileName	=  "eExceptionsAll";         
    }
    
    private void onAccessDocAction(java.awt.event.ActionEvent evt) {
    	xslFileName	=  "eAccessDocAll";         
    }
    
    private void onDialoguesAction(java.awt.event.ActionEvent evt) {
    	xslFileName	= "eDialoguesAll";         
    }
    
    private void onMinAction(java.awt.event.ActionEvent evt) {
    	xslFileName	=  "eMinimumAll";         
    }
     
    private void onAvgAction(java.awt.event.ActionEvent evt) {
    	xslFileName	=  "eAverageAll";    	 
    }
    
    private void onMaxAction(java.awt.event.ActionEvent evt) {
    	xslFileName	=  "eMaximumAll";
         
    }    
    
    private void onVerySatisfAction(java.awt.event.ActionEvent evt){
    	xslFileName	= "eVerySatisfAll";
    }
    
    private void onSatisfAction(java.awt.event.ActionEvent evt){
    	xslFileName	= "eSatisfAll";
    }
    
    private void onNeutralAction(java.awt.event.ActionEvent evt){
    	xslFileName	= "eNeutralAll";
    }
    
    private void onDissatisfAction(java.awt.event.ActionEvent evt){
    	xslFileName	= "eDissatisfAll";
    }
    
    private void onVeryDissatisfAction(java.awt.event.ActionEvent evt){
    	xslFileName	= "eVeryDissatisfAll";
    }
    
    /**
     * Launch the default application associated with a specific
     * filename using the preset Desktop.Action.
     * @throws IOException 
     * @throws TransformerFactoryConfigurationError 
     * @throws TransformerException 
     * @throws TransformerConfigurationException 
     * 
     */
    private void onLaunchDefaultApplication(java.awt.event.ActionEvent evt) 
    										throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError, IOException {
        
    	String taskSelected = cmbTasks.getSelectedItem().toString() ;
        AJMUHandler.performStat(xslFileName);
        desktop.open(new File(xslFileName+".html"));        
    }
    
    private void populatecmbTasks(){
   	 	
        List<String> taskList = AJMUHandler.parseXML(xmlFileName);
        cmbTasks = new JComboBox( taskList.toArray());
        cmbTasks.setSelectedItem(0);
        
   }
    
    private void setAppAttributes(){
   	 	
        List<String> appAttrib = AJMUHandler.getAppAttributes(xmlFileName);
        String titleApp = "Desktop Application to evaluate: ";
        for (String element : appAttrib) {
        	titleApp = titleApp + " "+ element; 
        }
        lblApp.setText(titleApp);
   }

    public static void main(String args[]) throws FileNotFoundException, XMLStreamException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AJMUViewerUI().setVisible(true);
            }
        });       
    }
    
   
    
    
}
