/**
 * 
 */
package task.weather.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import task.weather.dto.CityDTO;
import task.weather.entity.City;
import task.weather.exception.WeatherGuruException;
import task.weather.repository.CityRepository;
import task.weather.util.MessageByLocaleUtils;

/**
 * @author Jogireddy Kotam
 *
 */
@Service
@Transactional
public class CityServiceImpl implements ICityService {
    
    private static final Logger logger = LogManager.getLogger(CityServiceImpl.class);
    
    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private MessageByLocaleUtils messageUtils;

    @Override
    public List<CityDTO> fetchAll() throws WeatherGuruException {
        logger.info("Entered fetchAll");
        try {
            List<City> cities = cityRepository.findAll();
            List<CityDTO> cityDTOs = new ArrayList<>();
            
            for(City city: cities) {
                CityDTO cityDTO = new CityDTO();
                cityDTO.setId(city.getId());
                cityDTO.setCityId(city.getCityId());
                cityDTO.setCity(city.getCity());
                cityDTO.setCountry(city.getCountry());
                
                cityDTOs.add(cityDTO);
            }
            return cityDTOs;
        }  catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new WeatherGuruException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new WeatherGuruException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }

}
