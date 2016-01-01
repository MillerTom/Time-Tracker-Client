/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domains.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import operations.TaskOperations;
import util.DBConnection;

/**
 *
 * @author tmiller
 */
public class TaskDao implements TaskOperations {

    @Override
    public boolean saveTask(Task task) {
       Connection conn = DBConnection.connect();
        String sqlST = "insert into tasks (emp_id, task_date, task_starttime, task_endtime, task_hours, task_name, task_description, task_type)values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sqlST);
            ps.setInt(1, task.getEmpID());
            ps.setDate(2, new java.sql.Date(task.getTaskDate().getTime()));
            ps.setString(3,task.getTaskST());
            ps.setString(4, task.getTaskET());
            ps.setString(5, task.getTaskHours());
            ps.setString(6, task.getTaskName());
            ps.setString(7, task.getTaskDescription());
            ps.setString(8, task.getTaskType());
            int saveresult=ps.executeUpdate();
            if(saveresult==1){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {}
        }
    }

    @Override
    public List<Task> getEmployeeTasks(int employeeID, Date date) {
        Connection conn = DBConnection.connect();
        String sqlST = "select * from tasks where emp_id=? and task_date=?";
        List<Task> tasksList = new ArrayList<Task>();
        try {
            PreparedStatement ps = conn.prepareStatement(sqlST);
            ps.setInt(1, employeeID);
            ps.setDate(2, new java.sql.Date(date.getTime()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Task currTask = new Task();
                currTask.setTaskID(rs.getInt("task_id"));
                currTask.setEmpID(rs.getInt("emp_id"));
                currTask.setTaskDate(rs.getDate("task_date"));
                currTask.setTaskST(rs.getString("task_starttime"));
                currTask.setTaskET(rs.getString("task_endtime"));
                currTask.setTaskHours(rs.getString("task_hours"));
                currTask.setTaskName(rs.getString("task_name"));
                currTask.setTaskDescription(rs.getString("task_description"));
                currTask.setTaskType(rs.getString("task_type"));
                
                tasksList.add(currTask);
            }
            return tasksList;
        } catch (Exception e) {
            return tasksList;
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {}
        }
    }
    
     @Override
    public List<Task> getEmployeeTasksByType(int employeeID, Date date,String taskType) {
        Connection conn = DBConnection.connect();
        String sqlST = "select * from tasks where emp_id=? and task_date=? and task_type=?";
        List<Task> tasksList = new ArrayList<Task>();
        try {
            PreparedStatement ps = conn.prepareStatement(sqlST);
            ps.setInt(1, employeeID);
            ps.setDate(2, new java.sql.Date(date.getTime()));
            ps.setString(3, taskType);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Task currTask = new Task();
                currTask.setTaskID(rs.getInt("task_id"));
                currTask.setEmpID(rs.getInt("emp_id"));
                currTask.setTaskDate(rs.getDate("task_date"));
                currTask.setTaskST(rs.getString("task_starttime"));
                currTask.setTaskET(rs.getString("task_endtime"));
                currTask.setTaskHours(rs.getString("task_hours"));
                currTask.setTaskName(rs.getString("task_name"));
                currTask.setTaskDescription(rs.getString("task_description"));
                currTask.setTaskType(rs.getString("task_type"));
                
                tasksList.add(currTask);
            }
            return tasksList;
        } catch (Exception e) {
            e.printStackTrace();
            return tasksList;
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {}
        }
    }
}
