package mx.uam.tsis.homework.screens;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * Web Controller
 * 
 * @author danielgloriaflorencio
 *
 */

@Controller
@Slf4j
public class PrincipalController {
	@GetMapping("/")
	public String index() {
		log.info("index () method was invoked");
		return "index";
	}
	
	@GetMapping("/search")
    public String search(){

        log.info("register() method was invoked");
        return "register";

    }

}
