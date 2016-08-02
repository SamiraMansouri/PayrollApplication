/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Business.EmployeeFactory;
import Business.IEmployee;
import Business.Payment;
import Business.TimeZones;
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Thread.sleep;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.application.Platform;

/**
 *
 * @author apple
 */
public class MainPage extends javax.swing.JFrame {

    /**
     * Creates new form MainPage
     */
    
    int timeRun=0;
    boolean setalarm=false;
    boolean setalarmManually=false;
    boolean TimeZonee=true;
     int hour=0;
     int minute=0;
     int second=0;
     int am_pm=0;
    ArrayList <IEmployee> Employees;
    Thread[] threads=new Thread[623];
    boolean[] threadsFlag=new boolean[623];
    private Object DateTimeFormat;
    int Threadindex;
    static ArrayList<TimeZones> timeZones;
    
    public MainPage() {
        initComponents();
        timeZones=new ArrayList<TimeZones>();
                jcmbTimezones.addItem("");
        	String[] ids = TimeZone.getAvailableIDs();
		for (String id : ids) {
                    
                        jcmbTimezones.addItem(displayTimeZone(TimeZone.getTimeZone(id)));
		}
                
               for (boolean flag : threadsFlag) { 
                   flag=false;
               }
        
        Employees=new ArrayList<IEmployee>();
        BufferedReader in=null;
        String thisLine=null;
        try{
            in=new BufferedReader(new FileReader("src/input.csv"));
            EmployeeFactory employeeFactory=new EmployeeFactory();
            
            while((thisLine=in.readLine())!=null){
                
                AddEmployee(thisLine, employeeFactory);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
         
         AddEmployeesInCombobx();
         
         
         // clock 
       
             new Thread()
         {
             public void run()
         {
             while(1==1 &&TimeZonee)
         {
             if (!setalarmManually) {
             Calendar cal=new GregorianCalendar();
             int hour=cal.get(GregorianCalendar.HOUR);
             int minute=cal.get(GregorianCalendar.MINUTE);
             int second=cal.get(GregorianCalendar.SECOND);
             int am_pm=cal.get(GregorianCalendar.AM_PM);
             
             String day_night="";
             if (am_pm==1) {
                 day_night="PM";
             }
             else
            {
                day_night="AM";
            }
             int rbAM_PM=0;
             String time=hour+":"+minute+":"+second+" "+day_night;
             lblClock.setText(time);
             if (setalarm) {
                 if (rbPM.isSelected()) {
                     rbAM_PM=1;
                 }
                 if ( jSpinHour.getValue()==hour&&jSpinMinute.getValue()==minute&&
                         jSpinSecond.getValue()==second && rbAM_PM==am_pm) {
                 JOptionPane.showMessageDialog(null, "Your alarm...");
                 setalarm=false;
             }
             }
             
             }
             
             else{
                 
                 second++;
             if (second==60) {
                 minute++;
                 second=0;
                
             }
             if(minute==60)
             {
                 hour++;
                 minute=0;
             }
             else if(minute==60 && hour==12 && am_pm==1)
             {
                 hour=1;
                 minute=0;
             }
             if (hour==12 && am_pm==1) {
                 hour=0;
                 am_pm=0;
             }
             else if(hour==12 && am_pm==0)
             {
                 am_pm=1;
             }
             String day_night="";
             if (am_pm==1) {
                 day_night="PM";
             }
             else
            {
                day_night="AM";
            }
             int rbAM_PM=0;
             String time=hour+":"+minute+":"+second+" "+day_night;
             lblClock.setText(time);
             if (setalarm) {
                 if (rbPM.isSelected()) {
                     rbAM_PM=1;
                 }
                 if ( jSpinHour.getValue()==hour&&jSpinMinute.getValue()==minute&&
                         jSpinSecond.getValue()==second && rbAM_PM==am_pm) {
                 JOptionPane.showMessageDialog(null, "Your alarm...");
                 setalarm=false;
             }
             }
                 try {
                     sleep(1000);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
         }
             
         }.start();
             
             
        /*
        String [] ids = TimeZone.getAvailableIDs();
        for(String id:ids) {
        TimeZone zone = TimeZone.getTimeZone(id);
        int offset = zone.getRawOffset()/1000;
        int hour = offset/3600;
        int minutes = (offset % 3600)/60;
        System.err.println(String.format("(GMT%+d:%02d) %s", hour, minutes, id));
        jcmbTimezones.addItem("(GMT%+d:%02d) %s"+String.valueOf(hour)+ String.valueOf(minutes)+ String.valueOf(id));
        }
       
       */
         
         
    
		

	}

	private static String displayTimeZone(TimeZone tz) {
                TimeZones tzs=new TimeZones();
		long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
                tzs.hour=hours;
		long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset()) 
                                  - TimeUnit.HOURS.toMinutes(hours);
                
		// avoid -4:-30 issue
		minutes = Math.abs(minutes);
                tzs.minute=minutes;

		String result = "";
		if (hours > 0) {
			result = String.format("(GMT+%d:%02d) %s", hours, minutes, tz.getID());
		} else {
			result = String.format("(GMT%d:%02d) %s", hours, minutes, tz.getID());
		}
                
                timeZones.add(tzs);

		return result;

	}

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserDialog3 = new datechooser.beans.DateChooserDialog();
        dateChooserDialog1 = new datechooser.beans.DateChooserDialog();
        dateChooserDialog2 = new datechooser.beans.DateChooserDialog();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEmployeeID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEmployeeName = new javax.swing.JTextField();
        cmbEmployees = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtHours = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        btnOpenSecondPage = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        lblClock = new javax.swing.JLabel();
        jSpinHour = new com.toedter.components.JSpinField();
        jSpinMinute = new com.toedter.components.JSpinField();
        btnMakeAlarm = new javax.swing.JButton();
        rbAM = new javax.swing.JRadioButton();
        rbPM = new javax.swing.JRadioButton();
        jSpinSecond = new com.toedter.components.JSpinField();
        btnSetAlarm = new javax.swing.JButton();
        jcmbTimezones = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Search Employee :");

        jLabel2.setText("Employee ID:");

        txtEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmployeeIDActionPerformed(evt);
            }
        });

        jLabel3.setText("Employee Name:");

        jLabel4.setText("Select Employee:");

        cmbEmployees.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEmployeesItemStateChanged(evt);
            }
        });
        cmbEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEmployeesActionPerformed(evt);
            }
        });

        jLabel5.setText("Paying :");

        jLabel6.setText("Final Date:");

        jLabel7.setText("Hours :");

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnOpenSecondPage.setText("Process Payroll");
        btnOpenSecondPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenSecondPageActionPerformed(evt);
            }
        });

        lblClock.setFont(new java.awt.Font("DS-Digital", 0, 65)); // NOI18N
        lblClock.setForeground(new java.awt.Color(0, 165, 255));
        lblClock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jSpinHour.setMaximum(12);
        jSpinHour.setMinimum(0);

        jSpinMinute.setMaximum(60);
        jSpinMinute.setMinimum(0);

        btnMakeAlarm.setText("Alarm");
        btnMakeAlarm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMakeAlarmActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbAM);
        rbAM.setSelected(true);
        rbAM.setText("AM");

        buttonGroup1.add(rbPM);
        rbPM.setText("PM");

        btnSetAlarm.setText("Set Clock");
        btnSetAlarm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetAlarmActionPerformed(evt);
            }
        });

        jcmbTimezones.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbTimezonesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblClock, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbTimezones, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnSubmit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnOpenSecondPage))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                            .addComponent(cmbEmployees, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtEmployeeName)
                                            .addComponent(txtEmployeeID)
                                            .addComponent(txtHours, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(172, 172, 172))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSpinHour, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinMinute, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rbAM, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbPM)
                                .addGap(18, 18, 18)
                                .addComponent(btnMakeAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSetAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))))
                .addGap(141, 141, 141))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(12, 12, 12)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(cmbEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblClock, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcmbTimezones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnMakeAlarm)
                                .addComponent(rbAM)
                                .addComponent(rbPM)
                                .addComponent(btnSetAlarm))
                            .addComponent(jSpinMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit)
                    .addComponent(btnOpenSecondPage))
                .addGap(7, 7, 7))
        );

        jLabel1.getAccessibleContext().setAccessibleName("Search Employee:");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmployeeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmployeeIDActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
    boolean DataIsValid=true;     
  DataIsValid=DataIsValid();
 
  //hours      
  if (DataIsValid) {
             float ID=0;
        try{
            ID= Float.parseFloat(txtHours.getText());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,
            "Hour is not in a correct format",
            "Inane warning",
            JOptionPane.WARNING_MESSAGE);
            DataIsValid=false;
        }
        }
  //Friday
        if (DataIsValid) {
              String date=GiveDate();
        boolean Friday=IsFriday(date);
         if (!Friday) {
            JOptionPane.showMessageDialog(null,
            "Your Date Should be Friday",
            "Inane warning",
            JOptionPane.WARNING_MESSAGE);
            DataIsValid=false;
        }
        }
    //Date Range
          if (DataIsValid) {
         boolean DateRangeIsOK= DateRangeIsValid();
         if (!DateRangeIsOK) {
            JOptionPane.showMessageDialog(null,
            "Your Date Range Is not OK",
            "Inane warning",
            JOptionPane.WARNING_MESSAGE);
            DataIsValid=false;
        }
          }
            if (DataIsValid) {
                AddPayment();
            }
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void cmbEmployeesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEmployeesItemStateChanged
        // TODO add your handling code here:
        if (cmbEmployees.getSelectedItem().toString().trim()!="") {
             Scanner in =new Scanner(cmbEmployees.getSelectedItem().toString());
                in.useDelimiter(",");
                double ID=in.nextDouble();
                String FullName=in.next();
                txtEmployeeID.setText(String.valueOf(ID) );
                txtEmployeeName.setText(FullName);
        }
               
    }//GEN-LAST:event_cmbEmployeesItemStateChanged

    private void cmbEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEmployeesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEmployeesActionPerformed

    private void btnOpenSecondPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenSecondPageActionPerformed

        try{
        
            EmployeeJFrame employeeJFrame=new EmployeeJFrame();
            employeeJFrame.employees=Employees;
            employeeJFrame.loadListingTable();
             employeeJFrame.setVisible(true);
             this.hide();
        
                }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_btnOpenSecondPageActionPerformed

    private void btnMakeAlarmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMakeAlarmActionPerformed
        // TODO add your handling code here:
        setalarm=true;
    }//GEN-LAST:event_btnMakeAlarmActionPerformed

    private void btnSetAlarmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetAlarmActionPerformed
        // TODO add your handling code here:
         hour=jSpinHour.getValue();
         minute=jSpinMinute.getValue();
         second=jSpinSecond.getValue();
         if (rbPM.isSelected()) {
                     am_pm=1;
                 }
         setalarmManually=true;
    }//GEN-LAST:event_btnSetAlarmActionPerformed

    private void jcmbTimezonesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbTimezonesItemStateChanged
        // TODO add your handling code here:
        if (jcmbTimezones.getSelectedItem()!="") {
            TimeZonee=false;
            threadsFlag[Threadindex]=false;
            int v=Threadindex;
           
            Threadindex=jcmbTimezones.getSelectedIndex();
            threadsFlag[Threadindex]=true;
            String timezone;
            timezone=jcmbTimezones.getSelectedItem().toString();
            SetClock(threads[Threadindex],timezone);
        
        }   
        
    }//GEN-LAST:event_jcmbTimezonesItemStateChanged

    public void SetClock(Thread t1,final String timezone)
    {
       threads[Threadindex]= new Thread()
         {
             public void run()
         {
             while(1==1 &&!TimeZonee&&threadsFlag[Threadindex])
         {
             if (!setalarmManually ) {
                 
             Calendar cal=new GregorianCalendar();
             cal.setTimeZone(TimeZone.getTimeZone(jcmbTimezones.getSelectedItem().toString()));
             int hour=cal.get(GregorianCalendar.HOUR);
             hour+=timeZones.get(Threadindex).hour;
             int minute=cal.get(GregorianCalendar.MINUTE);
             int second=cal.get(GregorianCalendar.SECOND);
             int am_pm=cal.get(GregorianCalendar.AM_PM);
            
             long x=0;
                 if (hour<0) {
                     hour+=12;
                      x=60-timeZones.get(Threadindex).minute;
                      if (am_pm==1) {
                         am_pm=0;
                     }
                      if (am_pm==0) {
                         am_pm=1;
                     }
                 }
             
             minute+=x;
                 if (minute>=60) {
                     hour++;
                     minute-=60;
                 }
             
                 
             String day_night="";
             if (am_pm==1) {
                 day_night="PM";
             }
             else
            {
                day_night="AM";
            }
             int rbAM_PM=0;
             String time=hour+":"+minute+":"+second+" "+day_night;
             lblClock.setText(time);
             if (setalarm) {
                 if (rbPM.isSelected()) {
                     rbAM_PM=1;
                 }
                 if ( jSpinHour.getValue()==hour&&jSpinMinute.getValue()==minute&&
                         jSpinSecond.getValue()==second && rbAM_PM==am_pm) {
                 JOptionPane.showMessageDialog(null, "Your alarm...");
                 setalarm=false;
             }
             }
             
             }
             
             else{
                 
                 second++;
             if (second==60) {
                 minute++;
                 second=0;
                
             }
             if(minute==60)
             {
                 hour++;
                 minute=0;
             }
             else if(minute==60 && hour==12 && am_pm==1)
             {
                 hour=1;
                 minute=0;
             }
             if (hour==12 && am_pm==1) {
                 hour=0;
                 am_pm=0;
             }
             else if(hour==12 && am_pm==0)
             {
                 am_pm=1;
             }
             String day_night="";
             if (am_pm==1) {
                 day_night="PM";
             }
             else
            {
                day_night="AM";
            }
             int rbAM_PM=0;
             String time=hour+":"+minute+":"+second+" "+day_night;
             lblClock.setText(time);
             if (setalarm) {
                 if (rbPM.isSelected()) {
                     rbAM_PM=1;
                 }
                 if ( jSpinHour.getValue()==hour&&jSpinMinute.getValue()==minute&&
                         jSpinSecond.getValue()==second && rbAM_PM==am_pm) {
                 JOptionPane.showMessageDialog(null, "Your alarm...");
                 setalarm=false;
             }
             }
                 try {
                     sleep(1000);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
         }
             
         };
       threads[Threadindex].start();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMakeAlarm;
    private javax.swing.JButton btnOpenSecondPage;
    private javax.swing.JButton btnSetAlarm;
    private javax.swing.JButton btnSubmit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cmbEmployees;
    private datechooser.beans.DateChooserDialog dateChooserDialog1;
    private datechooser.beans.DateChooserDialog dateChooserDialog2;
    private datechooser.beans.DateChooserDialog dateChooserDialog3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private com.toedter.components.JSpinField jSpinHour;
    private com.toedter.components.JSpinField jSpinMinute;
    private com.toedter.components.JSpinField jSpinSecond;
    private javax.swing.JComboBox jcmbTimezones;
    private javax.swing.JLabel lblClock;
    private javax.swing.JRadioButton rbAM;
    private javax.swing.JRadioButton rbPM;
    private javax.swing.JTextField txtEmployeeID;
    private javax.swing.JTextField txtEmployeeName;
    private javax.swing.JTextField txtHours;
    // End of variables declaration//GEN-END:variables

public void AddEmployee(String info,EmployeeFactory employeeFactory)
{
    
    try (Scanner in = new Scanner(info)) {
               
                in.useDelimiter(",");
                
                double EmployeeID=in.nextDouble();
                String FirstName=in.next();
                String LastName=in.next();
                String Address=in.next()+","+in.next()+','+in.next()+','+in.next()+','+in.next();
                String Phone=in.next();
                float SalaryWage=in.nextFloat();
                float Bonus;
                String temp=in.next();
                try{
                    
                    Bonus=Float.parseFloat(temp);
                }
                catch(Exception ex) {
                    Bonus=0;
                }
                
                String PayCategory=in.next();
                String Title=in.next();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                
                Date BonusDate=null;
               
                    try {
                       
                        BonusDate = formatter.parse(in.next());

                    }
                    catch(Exception e)
                            {
                                BonusDate=null;
                            }
               
                IEmployee employee=employeeFactory.CreateEmployee(Title);
                employee.Add(EmployeeID,FirstName,LastName,Address,Phone,SalaryWage,Bonus,
                                    PayCategory,Title,BonusDate);
                Employees.add(employee);
}

}

public void AddEmployeesInCombobx()
{
    cmbEmployees.addItem("");
    for(IEmployee employee : Employees)
    {
        cmbEmployees.addItem(employee.getEmployeeID()+","+employee.getFirstName()+" "+employee.getLastName());
    
    }
}

public boolean DataIsValid()
{
    boolean result=true;
    String Warning="";
    if (txtEmployeeID.getText().trim()!=""||txtEmployeeID.getText().trim()!=null) {
        double ID=0;
        try{
            ID= Double.parseDouble(txtEmployeeID.getText());
        }
        catch(Exception e){
            Warning="Your Employee ID is not in a correct format.\n";
            result=false;
        }
        if (SearchEmployeeID(ID)==-1 && result) {
             Warning+="This Employee does not exist.\n";
             result=false;
        }
    }
    if ((txtEmployeeName.getText().trim()!=""||txtEmployeeName.getText().trim()!=null)&&result) {
         if (SearchEmployeeFullName(txtEmployeeName.getText())==-1) {
             Warning+="This Employee does not exist.\n";
             result=false;
        }
    }
    if (((txtEmployeeName.getText().trim()!=""||txtEmployeeName.getText().trim()!=null)&&
            (txtEmployeeID.getText().trim()!=""||txtEmployeeID.getText().trim()!=null))&&result) {
        if (SearchEmployeeID(Double.parseDouble(txtEmployeeID.getText()))!=SearchEmployeeFullName(txtEmployeeName.getText())) {
            Warning+="The Employee Name and ID is mismatch.\n";
            result=false;
        }
    if ((txtEmployeeName.getText().trim()!=""||txtEmployeeName.getText().trim()!=null)&&
            (txtEmployeeID.getText().trim()!=""||txtEmployeeID.getText().trim()!=null)&&
             cmbEmployees.getSelectedItem().toString()!=""  &&result) {
        
                Scanner in =new Scanner(cmbEmployees.getSelectedItem().toString());
                in.useDelimiter(",");
                double ID=in.nextDouble();
                String FullName=in.next();
                if (ID!=Double.parseDouble(txtEmployeeID.getText())& FullName!=txtEmployeeName.getText()) {
                    Warning+="There is mismatch.\n";
                }
            
        }
    }
    if (!result) {
        JOptionPane.showMessageDialog(null,
    Warning,
    "Inane warning",
    JOptionPane.WARNING_MESSAGE);
    }
    return result;
    
}

public boolean DateRangeIsValid()
{
        Date FromDate=new Date();
        double ID=Double.parseDouble(txtEmployeeID.getText());
        IEmployee employee=Employees.get((int)ID-1);
        List <Payment> payments=employee.getPayments();
        
        if (payments.size()!=0) {
            Payment payment=payments.get(payments.size()-1);
            FromDate=payment.getToDate();
            }
        else
        {
        String myDateString ="01 11 2015";
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
            try{
            FromDate=formatter.parse(myDateString);
            }
            catch(Exception e)
            {

            }
        }
        boolean dateRange=true;
        Date Todate=jDateChooser1.getDate();
        long Days=Todate.getDate()-FromDate.getDate();
            if (Days>14) {
                dateRange=false;
            }
         
        
        Date StartDate=null;
        String myDateString ="01 11 2015";
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        try{
        StartDate=formatter.parse(myDateString);
        }
        catch(Exception e)
        {
            
        }
        
        Date EndDate=null;
        myDateString ="31 11 2015";
        try{
        EndDate=formatter.parse(myDateString);
        }
        catch(Exception e)
        {
            
        }
        if (jDateChooser1.getDate().after(EndDate)||jDateChooser1.getDate().before(StartDate)) {
        dateRange=false;
    }
            return dateRange;
        }
        



public int SearchEmployeeID(double ID)
{
    int index=-1;
    for(IEmployee employee : Employees)
    {
        if (employee.getEmployeeID()==ID) {
            index=Employees.indexOf(employee);
            break;
        }
    }
    return index;
    
}

public int SearchEmployeeFullName(String FullName)
{
    int index=-1;
    for(IEmployee employee : Employees)
    {
        String empfullName=employee.getFirstName()+" "+employee.getLastName();
        if (empfullName.equalsIgnoreCase(FullName)) {
            index=Employees.indexOf(employee);
            break;
        }
    }
    return index;
    
}


public String GiveDate() 
{
   SimpleDateFormat newDateFormat = new SimpleDateFormat("MMM DD, YYYY");
  
   Date myDate=null;
   String myDateString =null;
   try{
    myDate = jDateChooser1.getDate();}
  catch(Exception e)
  {
      
  }
newDateFormat.applyPattern("dd/MM/YYYY");
try{
myDateString = newDateFormat.format(myDate);
}
catch(Exception e)
{
    
}
return myDateString;
}

public boolean IsFriday(String datestr)
{
    Date date=null;
 try    {   
date = jDateChooser1.getDate();
 }
 catch(Exception e)
 {
     
 }
// Then get the day of week from the Date based on specific locale.
String dayOfWeek = new SimpleDateFormat("EEEE", Locale.getDefault()).format(date);
    if (dayOfWeek.trim().equalsIgnoreCase("Friday")) {
        return true;
        
    }
return false;
}

public void AddPayment()
{
    EmployeeFactory employeeFactory=new EmployeeFactory();
   IEmployee employee=null;
    for(IEmployee emp : Employees)
    {
        if (emp.getEmployeeID()==Double.parseDouble(txtEmployeeID.getText().trim())) {
           
            employee=employeeFactory.CreateEmployee(emp.getTitle());
            employee=emp;
            break;
        }
    }

        Date FromDate=new Date();
        List <Payment> payments=employee.getPayments();
        if (payments.size()!=0) {
        Payment payment=payments.get(payments.size()-1);
        FromDate=payment.getToDate();
    }
        else
        {
        String myDateString ="01 11 2015";
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        try{
        FromDate=formatter.parse(myDateString);
        }
        catch(Exception e)
        {
            
        }
        }
        
        employee.CalculateSalary(Float.parseFloat(txtHours.getText().trim()),FromDate, jDateChooser1.getDate());
        
     
}


}
