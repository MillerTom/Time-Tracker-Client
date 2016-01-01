/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.TaskDao;
import domains.Task;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import model.Clock;

/**
 *
 * @author tmiller
 */
public class OnlineTracker extends javax.swing.JFrame {

    /**
     * ******* Serialization ********
     */
    private static final long serialVersionUID = 1L;
    public static int rowID;
    public static int offlineRowID = 0;
    private String empName;
    private int empID;
    /**
     * ******* Private Variables ********
     */
    private Clock stopClock = new Clock();
    private Timer ticker = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            stopClock.increaseSeconds();
            timerLbl.setText(stopClock.displayTime());
        }
    });

    /**
     * Creates new form OnlineTracker
     */
    public OnlineTracker(int empID, String empName) {
        this.empID = empID;
        this.empName = empName;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        initComponents();

        //start button function
        this.start();

        //stop button function
        this.pause();

        //set render user tasks
        this.renderEmployeeTasks(todayDateChooser.getDate());
    }

    // Set properties for Start/Stop Button
    public void start() {
        startStopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startStopBtn.getLabel().equalsIgnoreCase("Start")) {
                    ticker.start();
                    startTimeValueLbl.setText(new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds());
                    startStopBtn.setLabel("Stop");
                    pauseResumeBtn.setEnabled(true);
                } else if (startStopBtn.getLabel().equalsIgnoreCase("Stop")) {

                    Object[] options = {"OK"};
                    JPanel onlineDialogPanel = new JPanel();
                    JTextArea onlineDescTextArea = new JTextArea(5, 40);
                    onlineDialogPanel.add(onlineDescTextArea);
                    JOptionPane.showOptionDialog(null, onlineDialogPanel, "Task Breif",
                            JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE,
                            null, options, options[0]);

                    if (onlineDescTextArea.getText() == null || onlineDescTextArea.getText().trim().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "Task Breif is must",
                                "Task Breif", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String taskDate = new SimpleDateFormat("MMM d,yyyy").format(todayDateChooser.getDate());
                        String taskST = startTimeValueLbl.getText();
                        String taskET = new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds();
                        String taskTime = timerLbl.getText();
                        String taskName = workingTaskNameTxtFd.getText();

                        ticker.stop();
                        stopClock.resetClock();
                        timerLbl.setText(stopClock.displayTime());
                        startStopBtn.setLabel("Start");
                        pauseResumeBtn.setEnabled(false);
                        startTimeValueLbl.setText("");

                        Task task = new Task();
                        task.setEmpID(empID);
                        task.setTaskDate(new Date(taskDate));
                        task.setTaskST(taskST);
                        task.setTaskET(taskET);
                        task.setTaskHours(taskTime);
                        task.setTaskName(taskName);
                        task.setTaskDescription(onlineDescTextArea.getText());
                        task.setTaskType("online");

                        saveEmployeeTask(task);
                        resetTableData(dataTable);
                        renderEmployeeTasksByType("online", dataTable, todayDateChooser.getDate());
                    }
                }
            }
        });
    }

    // Set properties for Pause/Resume Button
    public void pause() {
        pauseResumeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pauseResumeBtn.getLabel().equalsIgnoreCase("Pause")) {
                    ticker.stop();
                    pauseResumeBtn.setLabel("Resume");
                } else if (pauseResumeBtn.getLabel().equalsIgnoreCase("Resume")) {
                    ticker.start();
                    pauseResumeBtn.setLabel("Pause");
                }
                startStopBtn.setEnabled(true);
            }
        });
        pauseResumeBtn.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainTabbedPane = new javax.swing.JTabbedPane();
        OnlineTrackerPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        startStopBtn = new javax.swing.JButton();
        pauseResumeBtn = new javax.swing.JButton();
        dateLbl = new javax.swing.JLabel();
        todayDateChooser = new com.toedter.calendar.JDateChooser();
        timerLbl = new javax.swing.JLabel();
        taskLbl = new javax.swing.JLabel();
        workingTaskNameTxtFd = new javax.swing.JTextField();
        dataScrollPanel = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        startTimeLbl = new javax.swing.JLabel();
        startTimeValueLbl = new javax.swing.JLabel();
        offlineTrackerPanel = new javax.swing.JPanel();
        offlineDateLbl = new javax.swing.JLabel();
        offlineDateChooser = new com.toedter.calendar.JDateChooser();
        offlineStartTimeLbl = new javax.swing.JLabel();
        Format timeFormat = new SimpleDateFormat("HH:mm:ss");
        offlineStartTimeField = new javax.swing.JFormattedTextField(timeFormat);
        offlineEndTimeLbl = new javax.swing.JLabel();
        offlineEndTimeField = new javax.swing.JFormattedTextField(timeFormat);
        offlineTaskNameLbl = new javax.swing.JLabel();
        offlineTaskNameField = new javax.swing.JTextField();
        offlineSaveBtn = new javax.swing.JButton();
        offlineTableScrollPane = new javax.swing.JScrollPane();
        offlineTaskTable = new javax.swing.JTable();
        loggedinUsernameLbl = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        startStopBtn.setText("Start");
        startStopBtn.setToolTipText("");
        startStopBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startStopBtnActionPerformed(evt);
            }
        });

        pauseResumeBtn.setText("Pause");

        dateLbl.setText("Date :");

        todayDateChooser.setName("todayDateChooser"); // NOI18N

        timerLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerLbl.setText("00:00:00");

        taskLbl.setText("Task");

        workingTaskNameTxtFd.setText("Now Working Task");

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "User Name", "Date", "Start Time", "End Time", "Working Hours", "Task Name","Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        dataScrollPanel.setViewportView(dataTable);

        startTimeLbl.setText("Start Time");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dataScrollPanel)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(dateLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(todayDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(startStopBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timerLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(taskLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(pauseResumeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(workingTaskNameTxtFd, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(startTimeLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(startTimeValueLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startStopBtn)
                    .addComponent(pauseResumeBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(workingTaskNameTxtFd)
                    .addComponent(startTimeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startTimeValueLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timerLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(taskLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(todayDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(dataScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        todayDateChooser.setDate(new Date());

        todayDateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                // If the 'date' property was changed...
                if ("date".equals(evt.getPropertyName())) {
                    resetTableData(dataTable);
                    renderEmployeeTasksByType("online",dataTable,todayDateChooser.getDate());
                }
            }
        });

        javax.swing.GroupLayout OnlineTrackerPanelLayout = new javax.swing.GroupLayout(OnlineTrackerPanel);
        OnlineTrackerPanel.setLayout(OnlineTrackerPanelLayout);
        OnlineTrackerPanelLayout.setHorizontalGroup(
            OnlineTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        OnlineTrackerPanelLayout.setVerticalGroup(
            OnlineTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OnlineTrackerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Online Tracker", OnlineTrackerPanel);

        offlineDateLbl.setText("Date: ");

        offlineDateChooser.setDate(new Date());

        offlineDateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                // If the 'date' property was changed...
                if ("date".equals(evt.getPropertyName())) {
                    resetTableData(offlineTaskTable);
                    renderEmployeeTasksByType("offline",offlineTaskTable,offlineDateChooser.getDate());
                }
            }
        });

        offlineStartTimeLbl.setText("Start Time:");

        offlineStartTimeField.setValue(new Date());

        offlineEndTimeLbl.setText("End Time:");

        offlineEndTimeField.setValue(new Date());

        offlineTaskNameLbl.setText("Task: ");

        offlineTaskNameField.setText("Enter Task Name Here !");

        offlineSaveBtn.setText("Add");
        offlineSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offlineSaveBtnActionPerformed(evt);
            }
        });

        offlineTaskTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "User Name", "Date", "Start Time", "End Time", "Working Hours", "Task Name", "Description"
            }
        ));
        offlineTableScrollPane.setViewportView(offlineTaskTable);

        javax.swing.GroupLayout offlineTrackerPanelLayout = new javax.swing.GroupLayout(offlineTrackerPanel);
        offlineTrackerPanel.setLayout(offlineTrackerPanelLayout);
        offlineTrackerPanelLayout.setHorizontalGroup(
            offlineTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(offlineTrackerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(offlineTableScrollPane)
                .addContainerGap())
            .addGroup(offlineTrackerPanelLayout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(offlineTaskNameLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(offlineTaskNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, offlineTrackerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(offlineDateLbl)
                .addGap(1, 1, 1)
                .addComponent(offlineDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(offlineStartTimeLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(offlineStartTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(offlineEndTimeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(offlineTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(offlineSaveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(offlineEndTimeField, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                .addGap(63, 63, 63))
        );
        offlineTrackerPanelLayout.setVerticalGroup(
            offlineTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(offlineTrackerPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(offlineTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(offlineDateLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(offlineTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(offlineStartTimeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(offlineStartTimeField)
                        .addComponent(offlineEndTimeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(offlineEndTimeField))
                    .addComponent(offlineDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(offlineTrackerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(offlineTaskNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(offlineTaskNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(offlineSaveBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(offlineTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Offline Tracker", offlineTrackerPanel);

        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(loggedinUsernameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loggedinUsernameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoutBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTabbedPane.getAccessibleContext().setAccessibleName("Online Tracker");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startStopBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startStopBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startStopBtnActionPerformed

    private void offlineSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offlineSaveBtnActionPerformed
        boolean validdata = true;
        Object[] options = {"OK"};
        JPanel offlineDialogPanel = new JPanel();
        JTextArea offlineDescTextArea = new JTextArea(5, 40);
        offlineDialogPanel.add(offlineDescTextArea);
        JOptionPane.showOptionDialog(this, offlineDialogPanel, "Task Breif",
                JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        if (offlineDescTextArea.getText() == null || offlineDescTextArea.getText().trim().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Task Breif is must",
                    "Task Breif", JOptionPane.ERROR_MESSAGE);
        } else {
            String offlineTaskDate = new SimpleDateFormat("MMM d,yyyy").format(offlineDateChooser.getDate());
            String offlineTaskST = offlineStartTimeField.getText();
            String offlineTaskET = offlineEndTimeField.getText();
            String diffTime;
            try {
                java.text.DateFormat df = new java.text.SimpleDateFormat("HH:mm:ss");
                df.setTimeZone(TimeZone.getTimeZone("UTC+2"));
                java.util.Date date1 = df.parse(offlineTaskST);
                java.util.Date date2 = df.parse(offlineTaskET);
                if (date1.after(date2)) {
                    JOptionPane.showMessageDialog(null, "Start Time Must be Less Than End Time",
                            "Save Result", JOptionPane.ERROR_MESSAGE);
                    validdata = false;
                }
                long diff = date2.getTime() - date1.getTime();
                Date diffDate = new Date(diff);
                diffTime = df.format(diffDate);
            } catch (ParseException ex) {
                diffTime = "";
            }

            String offlineTaskTime = diffTime;
            String offlineTaskName = offlineTaskNameField.getText();

            Task task = new Task();
            task.setEmpID(empID);
            task.setTaskDate(new Date(offlineTaskDate));
            task.setTaskST(offlineTaskST);
            task.setTaskET(offlineTaskET);
            task.setTaskHours(offlineTaskTime);
            task.setTaskName(offlineTaskName);
            task.setTaskDescription(offlineDescTextArea.getText());
            task.setTaskType("offline");
            if (validdata == true) {
                saveEmployeeTask(task);
                resetTableData(offlineTaskTable);
                renderEmployeeTasksByType("offline", offlineTaskTable,offlineDateChooser.getDate());
            }

        }
    }//GEN-LAST:event_offlineSaveBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        this.setVisible(false);
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_logoutBtnActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel OnlineTrackerPanel;
    private javax.swing.JScrollPane dataScrollPanel;
    private javax.swing.JTable dataTable;
    private javax.swing.JLabel dateLbl;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel loggedinUsernameLbl;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JTabbedPane mainTabbedPane;
    private com.toedter.calendar.JDateChooser offlineDateChooser;
    private javax.swing.JLabel offlineDateLbl;
    private javax.swing.JFormattedTextField offlineEndTimeField;
    private javax.swing.JLabel offlineEndTimeLbl;
    private javax.swing.JButton offlineSaveBtn;
    private javax.swing.JFormattedTextField offlineStartTimeField;
    private javax.swing.JLabel offlineStartTimeLbl;
    private javax.swing.JScrollPane offlineTableScrollPane;
    private javax.swing.JTextField offlineTaskNameField;
    private javax.swing.JLabel offlineTaskNameLbl;
    private javax.swing.JTable offlineTaskTable;
    private javax.swing.JPanel offlineTrackerPanel;
    private javax.swing.JButton pauseResumeBtn;
    private javax.swing.JButton startStopBtn;
    private javax.swing.JLabel startTimeLbl;
    private javax.swing.JLabel startTimeValueLbl;
    private javax.swing.JLabel taskLbl;
    private javax.swing.JLabel timerLbl;
    private com.toedter.calendar.JDateChooser todayDateChooser;
    private javax.swing.JTextField workingTaskNameTxtFd;
    // End of variables declaration//GEN-END:variables

    private void renderEmployeeTasks(Date date) {
        TaskDao userTasksDao = new TaskDao();
        List<Task> userTasks = userTasksDao.getEmployeeTasks(empID, date);
        rowID = userTasks.size();

        for (int count = 0; count < userTasks.size(); count++) {
            Object[] rowData = new Object[]{userTasks.get(count).getTaskID(),
                empName,
                userTasks.get(count).getTaskDate(),
                userTasks.get(count).getTaskST(),
                userTasks.get(count).getTaskET(),
                userTasks.get(count).getTaskHours(),
                userTasks.get(count).getTaskName(),
                userTasks.get(count).getTaskDescription()};
            if (userTasks.get(count).getTaskType().equalsIgnoreCase("online")) {
                DefaultTableModel defaultTableModel = (DefaultTableModel) dataTable.getModel();
                defaultTableModel.addRow(rowData);
            } else if (userTasks.get(count).getTaskType().equalsIgnoreCase("offline")) {
                DefaultTableModel offlineDefaultTableModel = (DefaultTableModel) offlineTaskTable.getModel();
                offlineDefaultTableModel.addRow(rowData);
            }
        }
    }

    private void saveEmployeeTask(Task task) {
        TaskDao userTasksDao = new TaskDao();
        boolean saved = userTasksDao.saveTask(task);
        if (saved == false) {
            JOptionPane.showMessageDialog(null, "Task Didn't saved !",
                    "Save Result", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Task saved Successfully!",
                    "Save Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void resetTableData(JTable table) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
    }

    private void renderEmployeeTasksByType(String taskType, JTable table , Date date) {
        TaskDao userTasksDao = new TaskDao();
        List<Task> userTasks = userTasksDao.getEmployeeTasksByType(empID, date, taskType);
        rowID = userTasks.size();

        for (int count = 0; count < userTasks.size(); count++) {
            Object[] rowData = new Object[]{userTasks.get(count).getTaskID(),
                empName,
                userTasks.get(count).getTaskDate(),
                userTasks.get(count).getTaskST(),
                userTasks.get(count).getTaskET(),
                userTasks.get(count).getTaskHours(),
                userTasks.get(count).getTaskName(),
                userTasks.get(count).getTaskDescription()};

            DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
            defaultTableModel.addRow(rowData);
        }
    }
}
