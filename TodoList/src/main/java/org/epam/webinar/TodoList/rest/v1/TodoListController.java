package org.epam.webinar.TodoList.rest.v1;

import org.epam.webinar.TodoList.rest.v1.request.TodoRequest;
import org.epam.webinar.TodoList.rest.v1.response.TodoResponse;
import org.epam.webinar.TodoList.todo.TodoEntity;
import org.epam.webinar.TodoList.todo.TodoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoListController {
    private TodoRepository todoRepository;

    public TodoListController(TodoRepository repository){
        todoRepository = repository;
    }

    @GetMapping
    public String loadTodoList(ModelMap modelMap) {

        List<TodoEntity> todoEntities = todoRepository.findAll();
        List<TodoResponse> todoResponseList = new ArrayList<>();

        for (TodoEntity entity: todoEntities) {
            TodoResponse todoResponse = new TodoResponse();
            todoResponse.setDescription(entity.getDescription());

            todoResponseList.add(todoResponse);
        }

        modelMap.addAttribute("todos", todoResponseList);
        modelMap.addAttribute("newTodo", new TodoRequest());

        return "todo";
    }
    @PostMapping("/addTodo")
    public String addNewTodo(@ModelAttribute TodoRequest request){

        TodoEntity todo = new TodoEntity();
        todo.setDescription(request.getDescription());
        todo.setStatus("created");

        todoRepository.save(todo);

        return "redirect:/todo";
    }

}
