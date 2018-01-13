package com.hendisantika.springbootfilter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-filter
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/01/18
 * Time: 08.21
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class HelloController {
    @RequestMapping("")
    public String hello() {
        return "redirect:/customers";
    }
}
