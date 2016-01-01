/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import domains.Task;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tmiller
 */
public interface TaskOperations {
    
    public boolean saveTask(final Task task);
    
    public List<Task> getEmployeeTasks(final int employeeID,final Date date);
    
    public List<Task> getEmployeeTasksByType(final int employeeID,final Date date, final String taskType);
}   
