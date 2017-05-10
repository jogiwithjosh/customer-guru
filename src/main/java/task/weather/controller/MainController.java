/**
 * 
 */
package task.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jogireddy Kotam
 * @date 10-May-2017
 *
 */
@Controller
@RequestMapping("/")
public class MainController {
    
    
    public String index() {
        return "webapp/index.html";
    }

}
