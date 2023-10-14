package solutions.meisters.Test.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import solutions.meisters.Test.enums.TasksStatus;

@Entity @Table(name = "tasks")
@Getter @Setter @NoArgsConstructor
public class Task {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private TasksStatus status;
	
	public Task(String title, String description) {
		this.title = title;
		this.description = description;
		this.status = TasksStatus.PENDING;
		this.id = null;
	}
	
}
