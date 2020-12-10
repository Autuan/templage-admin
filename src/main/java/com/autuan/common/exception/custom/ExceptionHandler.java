package com.autuan.common.exception.custom;

import com.autuan.project.front.entity.ReturnResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(CustomRespondException.class)
    @ResponseBody
    public ReturnResult customRespondException(CustomRespondException e) {
        return ReturnResult.error(e.getMessage());
    }

//    @org.springframework.web.bind.annotation.ExceptionHandler
//    public ModelAndView defaultException(Exception e) {
//        e.printStackTrace();
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("/error/500");
//        return mav;
//    }
}
