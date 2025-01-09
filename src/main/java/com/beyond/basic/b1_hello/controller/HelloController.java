package com.beyond.basic.b1_hello.controller;

import com.beyond.basic.domain.Hello;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//Component 어노테이션을 통해 별도의 객체를 생성할 필요가 없는, 싱글톤 객체를 생성
//Controller 어노테이션을 통해 쉽게 사용자의 요청을 처리하고, http 응답을 줄 수 있음
@Controller
//클래스 차원에서 getmapping이 /hello가 필요할때 requestmapping을 통해 url매핑
@RequestMapping("/hello")
public class HelloController {
//    case1. 서버가 사용장게 단순 string 데이터 return(get요청) - @ResponseBody 필요
//    case2. 서버가 사용자에게 화면을 return(get요청) - ResponseBody가 없을때
    @GetMapping("")
//    @ResponseBody
//    @ResponseBody가 없고, return 타입이 String인 경우 서버는 templates폴더 밑에 helloworld.html화면을 찾아 리턴
//    만약 다른 경로면 abc/helloworld처럼 경로를 잘 적어줘야함
    public String helloWorld(){
        return "helloworld";
    }
//    case3.서버가 사용자에게 json 형식의 데이터를 return(get요청)
    @GetMapping("/json")
//    메서드 차원에서도 RequestMapping 사용가능
//    @RequestMapping(value = "/json",method = RequestMethod.GET)
    @ResponseBody
    public Hello helloJson() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        Hello h1 = new Hello("hongildong","hongil@naver.com");
//        String value = objectMapper.writeValueAsString(h1);
//        직접 json으로 직렬화 할 필요 없이, return 타입을 클래스로 지정시에 자동으로 직렬화
        Hello h1 = new Hello("hongildong","hongil@naver.com");
        return h1;

    }
//    case4. 사용자가 json 데이터를 요청하되, parameter형식으로 특정객체 요청(get요청)
//        parameter형식:/hello/param1?name=hongildong
    @GetMapping("/param1")
    @ResponseBody
    public Hello param1(@RequestParam(value = "name")String inputName){
        Hello h1 = new Hello(inputName,"test@naver.com");
        return h1;
    }

//        parameter형식:/hello/param1?name=hongildong&email=hong@naver.com
    @GetMapping("/param2")
    @ResponseBody
    public Hello param2(@RequestParam(value = "name")String inputName,@RequestParam(value = "email")String inputEmail){
        Hello h1 = new Hello(inputName,inputEmail);
        return h1;
    }

//    case5. parameter가 많아질경우, 데이터 바인딩을 통해 input값 처리(get요청)
    @GetMapping("/param3")
    @ResponseBody
//    각 parameter의 값이 Hello클래스의 각변수에 mapping : new Hello(hongildong,hong@naver.com)
//    public Hello param3(Hello hello){
    public Hello param3(@ModelAttribute Hello hello){
        return hello;
    }

//    case6. 화면을 return해 주되, 특정 변수값을 동적으로 세팅
    @GetMapping("/model-param")
    public String modelParam(@RequestParam(value = "name")String inputName,Model model){
//        Model객체는 특정 데이터를 화면에 전달해주는 역할
//        modelName이라는 키값에 value를 세팅하면 modelName:값 형태로 화면에 전달
        model.addAttribute("modelName",inputName);
        return "helloWorld2";
    }
}
