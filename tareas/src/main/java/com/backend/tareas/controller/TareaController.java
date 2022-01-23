package com.backend.tareas.controller;

import com.backend.tareas.model.Tarea;
import com.backend.tareas.repo.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;

    /**
     * Se carga en el directorio raiz. Devuelve la lista completa de tareas guardadas.
     * @return
     */
    @GetMapping("")
     List<Tarea> index() {
        return tareaRepository.findAll();
    }

    /**
     * Retornamos el código 201 al crear una nueva tarea. Guardamos la tarea en el repositorio para que persista en la BBDD.
     * @param tarea
     * @return
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Tarea create(@RequestBody Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    /**
     * Seleccionamos la tarea desde la BBDD y la modificamos.
     * @param id
     * @param tarea
     * @return
     */
    @PutMapping("{id}")
    Tarea update(@PathVariable String id, @RequestBody Tarea tarea) {
        Tarea tareaFromDb = tareaRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
        tareaFromDb.setNombre(tarea.getNombre());
        tareaFromDb.setCompletado(tarea.isCompletado());

        return tareaRepository.save(tareaFromDb);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void delete(@PathVariable String id) {
        Tarea tarea = tareaRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
        tareaRepository.delete(tarea);
    }



}
