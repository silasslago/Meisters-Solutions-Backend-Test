package solutions.meisters.Test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import solutions.meisters.Test.models.Task;

public interface TasksRepository extends JpaRepository<Task, Long> {}
