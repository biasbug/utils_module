package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.AccountService;

@Controller
public class MainController {
    @Autowired
    AccountService accountService;

    @RequestMapping("/show")
    public String showPage(){
        //accountService.transfromAccount("aaa","ccc",100);
        return "show";
    }
}
