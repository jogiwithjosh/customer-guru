/**
 * 
 */
package task.weather.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import task.weather.dto.CityDTO;
import task.weather.dto.TemperatureDTO;
import task.weather.dto.WeatherDTO;
import task.weather.entity.City;
import task.weather.exception.WeatherGuruException;
import task.weather.repository.CityRepository;
import task.weather.util.MessageByLocaleUtils;

/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */
@Service
@Transactional
public class ForecastApiServiceImpl implements IForecastApiService {
    
    private static final Logger logger = LogManager.getLogger(ForecastApiServiceImpl.class);
    
    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private ITemparatureService temparatureService;
    
    @Autowired
    private MessageByLocaleUtils messageUtils;
    
    @Autowired
    private Mapper mapper;
    
    @Value("${owm.appId}")
    private String appId;
    
    @Value("${owm.baseurl}")
    private String baseURL;

    @Override
    public void processApiCalls() throws WeatherGuruException {
        logger.info("Entered processApiCalls");
        try {
            List<City> cities = cityRepository.findAll();
            for(City city : cities) {
                List<TemperatureDTO> temperatureDTOs = fecthDataFromAPI(city.getCityId());
                CityDTO cityDTO = mapper.map(city, CityDTO.class);
                
                for(TemperatureDTO temperatureDTO : temperatureDTOs) {
                    temperatureDTO.setCity(cityDTO);
                }
                
                temparatureService.saveOrUpdate(temperatureDTOs);
            }
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new WeatherGuruException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(WeatherGuruException weatherGuruException) {
            logger.error("Exception catched with message: " + weatherGuruException.getMessage(), weatherGuruException);
            throw new WeatherGuruException(messageUtils.getMessage("common.fetch.error"), weatherGuruException); 
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new WeatherGuruException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }
    
    private List<TemperatureDTO> fecthDataFromAPI(Long cityId) throws WeatherGuruException {
        logger.info("Entered fecthDataFromAPI with cityId: " + cityId);
        List<TemperatureDTO> temperatures = new ArrayList<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            //for today weather
            /*ResponseEntity<String> response = restTemplate.getForEntity(
                    baseURL + "weather?id=" + cityId + "&cnt=15&APPID=" + appId, String.class);*/
            
            //upto 16 days forecast
            ResponseEntity<String> response = restTemplate.getForEntity(
                    baseURL + "forecast/daily?id=" + cityId + "&cnt=15&APPID=" + appId, String.class);
            
            if(response.getBody() != null) {
                JsonObject jsonObject = new JsonParser().parse(response.getBody()).getAsJsonObject();
                /*TemperatureDTO temperature = new TemperatureDTO();
                WeatherDTO weather = new WeatherDTO();
                
                Double temp = jsonObject.get("main").getAsJsonObject().get("temp").getAsDouble();
                temperature.setTemp(temp);
                Double max = jsonObject.get("main").getAsJsonObject().get("temp_max").getAsDouble();
                temperature.setMax(max);
                Double min = jsonObject.get("main").getAsJsonObject().get("temp_min").getAsDouble();
                temperature.setMin(min);
                
                Long time = jsonObject.get("dt").getAsLong();
                temperature.setTime(time*1000); //getting data in sec but storing in ms
                
                String main = jsonObject.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString();
                weather.setMain(main);
                String description = jsonObject.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
                weather.setDescription(description);
                
                temperature.setWeather(weather);
                temperatures.add(temperature);  */    
                
                
                //use this block for bulk(16 day forecast acc to API) forecast
                JsonArray jsonArray = jsonObject.get("list").getAsJsonArray();
                Iterator<JsonElement> iterator = jsonArray.iterator();
                while(iterator.hasNext()) {
                    JsonElement jsonElement = iterator.next();
                    TemperatureDTO temperature = new TemperatureDTO();
                    WeatherDTO weather = new WeatherDTO();
                    
                    
                    Long time = jsonElement.getAsJsonObject().get("dt").getAsLong();
                    temperature.setTime(time*1000); //getting data in sec but storing in ms
                    
                    Double max = jsonElement.getAsJsonObject().get("temp").getAsJsonObject().get("max").getAsDouble();
                    temperature.setMax(max);
                    temperature.setTemp(max);
                    Double min = jsonElement.getAsJsonObject().get("temp").getAsJsonObject().get("min").getAsDouble();
                    temperature.setMin(min);
                    
                    String main = jsonElement.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString();
                    weather.setMain(main);
                    String description = jsonElement.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
                    weather.setDescription(description);
                    
                    temperature.setWeather(weather);
                    temperatures.add(temperature);                    
                }                
            }
            return temperatures;
        } catch (Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new WeatherGuruException(exception.getMessage(), exception);
        }
    }

}
