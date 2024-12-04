package com.todoapp.myapp.controller;

import com.todoapp.myapp.model.ToDoItem;
import com.todoapp.myapp.model.ToDoItemViewModel;
import com.todoapp.myapp.repository.ToDoItemRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class ToDoAppController {

  private ToDoItemRepository repository;

  @GetMapping("/")
  public String get(Model model) {
    Iterable<ToDoItem> list = repository.findAll();
    model.addAttribute("items", new ToDoItemViewModel(list));
    model.addAttribute("newitem", new ToDoItem());
    return "index";
  }

  @PostMapping("/add")
  public String add(@ModelAttribute("newitem") @Valid ToDoItem item, BindingResult result, Model model) {

    if(result.hasErrors()) {
      Iterable<ToDoItem> list = repository.findAll();
      model.addAttribute("items", new ToDoItemViewModel(list));
      model.addAttribute("newitem", item);
      return "index";
    }
    repository.save(item);
    return "redirect:/";
  }

//  @PostMapping("/update")
//  public String update(@ModelAttribute ToDoItemViewModel items){
//    for(ToDoItem item: items.getTodoList()){
//      ToDoItem toDoItem = ToDoItem.builder()
//          .id(item.getId())
//          .name(item.getName())
//          .category(item.getCategory())
//          .complete(item.isComplete())
//          .build();
//      repository.save(toDoItem);
//    }
//    return "redirect:/";
//  }

  @GetMapping("/delete")
  public String delete(@RequestParam("id") Integer id){
    repository.deleteById(id);
    return "redirect:/";
  }
}
