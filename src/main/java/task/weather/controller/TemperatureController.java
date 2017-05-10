/**
 * 
 */
package task.weather.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import task.weather.exception.WeatherGuruException;
import task.weather.service.ITemparatureService;
import task.weather.util.String2MapUtil;

/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */
@RestController
@RequestMapping("/temperature")
public class TemperatureController {
    
    private static final Logger logger = LogManager.getLogger(CityController.class);
    
    @Autowired
    private ITemparatureService temparatureService;
    
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> getAll() {
        logger.info("Entered getAll");
        try {
            return new ResponseEntity<>(temparatureService.fetchAll(), HttpStatus.OK);
        } catch(WeatherGuruException weatherGuruException) {
            logger.error("Catched exception with message: " + weatherGuruException.getMessage(), weatherGuruException);
            return new ResponseEntity<>(String2MapUtil.convert("error", weatherGuruException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @RequestMapping(value = "/city/{cityId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> byCityId(@PathVariable(name = "cityId", required = true)Long cityId) {
        logger.info("Entered byCityId with cityId: " + cityId);
        try {
            return new ResponseEntity<>(temparatureService.fetchByCity(cityId), HttpStatus.OK);
        } catch(WeatherGuruException weatherGuruException) {
            logger.error("Catched exception with message: " + weatherGuruException.getMessage(), weatherGuruException);
            return new ResponseEntity<>(String2MapUtil.convert("error", weatherGuruException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception exception) {
            logger.error("Catched exception with message: " + exception.getMessage(), exception);
            return new ResponseEntity<>(String2MapUtil.convert("error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
