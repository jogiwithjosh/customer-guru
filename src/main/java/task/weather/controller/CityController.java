/**
 * 
 */
package task.weather.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import task.weather.exception.WeatherGuruException;
import task.weather.service.ICityService;
import task.weather.util.String2MapUtil;

/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */
@RestController
@RequestMapping("/city")
public class CityController {
    
    private static final Logger logger = LogManager.getLogger(CityController.class);
    
    @Autowired
    private ICityService cityService;
    
    /**
     * 
     * fetches all city data
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> getAll() {
        logger.info("Entered getAll");
        try {
            return new ResponseEntity<>(cityService.fetchAll(), HttpStatus.OK);
        } catch(WeatherGuruException weatherGuruException) {
            logger.error("Catched exception with message: " + weatherGuruException.getMessage(), weatherGuruException);
            return new ResponseEntity<>(String2MapUtil.convert("error", weatherGuruException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
