package Tech.buildrun.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping(path = "/tasks")
public class ApiController {

    @Autowired
    private TaskRepository taskRepository;

   
    @GetMapping
    public List<Task> getTasks() {
        return taskRepository.findAll(); 
    }

   
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        task.setId(null); 
        Task savedTask = taskRepository.save(task);  
        return ResponseEntity.status(201).body(savedTask); 
    }

    
    @DeleteMapping
    public ResponseEntity<Void> clearTasks() {
        taskRepository.deleteAll();  
        return ResponseEntity.ok().build();
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            taskRepository.deleteById(id);  
            return ResponseEntity.ok().build(); 
        }
        return ResponseEntity.notFound().build(); 
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id, @RequestBody Task updatedTask) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task existingTask = taskOptional.get();
            
           
            if (updatedTask.getNome() != null) existingTask.setNome(updatedTask.getNome());
            if (updatedTask.getCpf() != null) existingTask.setCpf(updatedTask.getCpf());
            if (updatedTask.getEndereco() != null) existingTask.setEndereco(updatedTask.getEndereco());
            if (updatedTask.getDataNascimento() != null) existingTask.setDataNascimento(updatedTask.getDataNascimento());
            if (updatedTask.getContato() != null) existingTask.setContato(updatedTask.getContato());
            if (updatedTask.getContatoValor() != null) existingTask.setContatoValor(updatedTask.getContatoValor());
            
            // Salva a tarefa atualizada
            Task savedTask = taskRepository.save(existingTask);
            return ResponseEntity.ok(savedTask); 
        }
        return ResponseEntity.notFound().build(); 
    }

   
    @Configuration
    public class CorsConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://127.0.0.1:5500") 
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") 
                    .allowedHeaders("*") 
                    .allowCredentials(true); 
        }
    }
}
