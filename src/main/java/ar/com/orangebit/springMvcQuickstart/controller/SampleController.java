package ar.com.orangebit.springMvcQuickstart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.orangebit.springMvcQuickstart.domain.SampleEntity;
import ar.com.orangebit.springMvcQuickstart.service.SampleEntityService;

@Controller
public class SampleController {

  @Autowired
  private SampleEntityService sampleEntityService;

  @RequestMapping("/")
  public String loadHomePage(Model m) {
    return "home";
  }

  @RequestMapping(value = "/sample-entities", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<SampleEntity> findAll() {
    return sampleEntityService.findAll();
  }

}
