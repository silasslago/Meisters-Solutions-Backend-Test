package solutions.meisters.Test.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import solutions.meisters.Test.dto.TasksRequestDTO;
import solutions.meisters.Test.models.Task;
import solutions.meisters.Test.repositories.TasksRepository;

@RestController
@RequestMapping("api/tasks")
public class TasksRest {

	@Autowired
	private TasksRepository tasksRepository;
	

	@GetMapping @ResponseBody @CrossOrigin
	public List<Task> listTasks() {
		return tasksRepository.findAll();
	}
	
	@RequestMapping(
		method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_JSON_VALUE
	) @ResponseBody @CrossOrigin
	public Task createTask(
			@RequestBody @Valid TasksRequestDTO taskDTO,
			Errors errors
	) {
		if(errors.hasErrors()) {
			return null;
		}
		
		Task task = taskDTO.toTask();
		tasksRepository.save(task);
		
		return task;
	}
	
	@RequestMapping(
		method = RequestMethod.PUT, 
		path = "/{id}", 
		consumes = MediaType.APPLICATION_JSON_VALUE
	) @ResponseBody @CrossOrigin
	public Task updateTask(
			@RequestBody @Valid TasksRequestDTO taskDTO,
			@PathVariable Long id,
			Errors errors
	) {
		if(errors.hasErrors()) {
			return null;
		}
		
		Optional<Task> optTask = tasksRepository.findById(id);
		if(!optTask.isEmpty()) {
			Task curTaskData = optTask.get();
			curTaskData.setDescription(taskDTO.getDescription());
			curTaskData.setTitle(taskDTO.getTitle());
			curTaskData.setStatus(taskDTO.getStatus());
			
			tasksRepository.save(curTaskData);
			return curTaskData;
		}
		
		return null;
	}
	
	@DeleteMapping("/{taskId}") @ResponseBody @CrossOrigin
	public Task deleteTask(@PathVariable Long taskId) {

		Optional<Task> optTask = tasksRepository.findById(taskId);
		
		if(!optTask.isEmpty()) {
			tasksRepository.deleteById(taskId);
			return optTask.get();
		}
		
		return null;
	}
	
}
