/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domains;

import java.util.Date;

/**
 *
 * @author tmiller
 */
public class Task {
    private int taskID;
    private int empID;
    private Date taskDate;
    private String taskST;
    private String taskET;
    private String taskHours;
    private String taskName;
    private String taskDescription;   
    private String taskType;

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskST() {
        return taskST;
    }

    public void setTaskST(String taskST) {
        this.taskST = taskST;
    }

    public String getTaskET() {
        return taskET;
    }

    public void setTaskET(String taskET) {
        this.taskET = taskET;
    }

    public String getTaskHours() {
        return taskHours;
    }

    public void setTaskHours(String taskHours) {
        this.taskHours = taskHours;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
   
}
