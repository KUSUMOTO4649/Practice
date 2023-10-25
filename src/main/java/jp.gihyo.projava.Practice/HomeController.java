package jp.gihyo.projava.Practice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.UUID;

@Controller
public class HomeController {
    private final PracticeDAO dao;

    @RequestMapping(value="/hello")
    @ResponseBody
    String hello(){
        return """
                <html>
                    <head><title>Hello</title></head>
                    <body>
                        <h1>Hello</h1>
                        It wors!<br>
                        現在の時刻は%sです。
                    </body>
                </html>
                """ .formatted(LocalDateTime.now());
    }
   @Autowired
           HomeController(PracticeDAO dao){
       this.dao = dao;
   }
    @RequestMapping(value="/time")
    String practice(Model model){
        model.addAttribute("time",LocalDateTime.now());
        return"hello";
    }
    record TaskItem(String id,String task,String deadline,boolean done){}
    private List<TaskItem> taskItems = new ArrayList<>();
//
    @GetMapping("/list")
    String listItems( Model model){
        List<TaskItem> taskItems = this.dao.findAll();
        model.addAttribute("tasklist",taskItems);
        return "practice";//HTMLの名前を記入
    }
    @GetMapping("/add")
    String addItem(@RequestParam("task")String task,
                   @RequestParam("deadline")String deadline){
        String id = UUID.randomUUID().toString().substring(0,8);
        TaskItem item = new TaskItem(id,task,deadline,false);
        taskItems.add(item);

        return"redirect:/list";
    }
    @GetMapping("/delete")
    String deleteItem(@RequestParam("id")String id){
        dao.delete(id);
        return "redirect:/list";
    }
}

