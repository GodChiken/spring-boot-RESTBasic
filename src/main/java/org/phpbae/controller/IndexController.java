package org.phpbae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017-08-12.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexJSP(HttpSession session) {
        ModelAndView mav = new ModelAndView("/index/index");
        return mav;
    }
}
