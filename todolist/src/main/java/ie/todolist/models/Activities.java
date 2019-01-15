package ie.todolist.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.context.WebApplicationContext;

@Entity
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class Activities implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;
	
	@DateTimeFormat
	private Calendar date;
	 
	private String tasks;
	private ToDoPriority priority;
	private ToDoStatus status;
	
	@SuppressWarnings("unused")
	private Date created;
	@SuppressWarnings("unused")
	private Date updated;

	@PrePersist
	protected void onCreate() {
		created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public String getTasks() {
		return tasks;
	}
	 public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public void setTasks(String tasks) {
		this.tasks = tasks;
	}
	public ToDoPriority getPriority() {
		return priority;
	}
	public void setPriority(ToDoPriority priority) {
		this.priority = priority;
	}
	public ToDoStatus getStatus() {
		return status;
	}
	public void setStatus(ToDoStatus status) {
		this.status = status;
	}
	public Date getCreated() {
		return created;
	}
	public Date getUpdated() {
		return updated;
	}

	@Override
	public String toString() {
		return "Activities [date=" + date + ", tasks=" + tasks + ", priority=" + priority + ", status=" + status + "]";
	}

	
}
