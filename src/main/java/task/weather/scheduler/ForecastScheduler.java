/**
 * 
 */
package task.weather.scheduler;

import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import task.weather.exception.WeatherGuruException;
import task.weather.service.IForecastApiService;

/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */
@Component
public class ForecastScheduler {
    
    private static final Logger logger = LogManager.getLogger(ForecastScheduler.class);
    
    @Autowired
    private IForecastApiService forecastApiService;
    
    @Scheduled(cron = "${cron.expression}")
    public void processWeatherApiCalls() {
        logger.info("Entered processWeatherApiCalls at: " + new Date());
        try {
            forecastApiService.processApiCalls();
        } catch(WeatherGuruException weatherGuruException) {
            logger.error("Exception catched with message: " + weatherGuruException.getMessage(), weatherGuruException);
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
        }
    }

}
