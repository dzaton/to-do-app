package com.backend.tareas.repo;

import com.backend.tareas.model.Tarea;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TareaRepository extends MongoRepository<Tarea, String> {

}
