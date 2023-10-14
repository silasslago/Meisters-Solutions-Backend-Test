package solutions.meisters.Test.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import solutions.meisters.Test.enums.TasksStatus;
import solutions.meisters.Test.models.Task;

@Getter @Setter
public class TasksRequestDTO {

	@NotNull
	private String title;
	@NotNull
	private String description;
	private TasksStatus status = TasksStatus.PENDING;
	
	public Task toTask() {
		return new Task(
				this.title,
				this.description
		);
	}
	
}
