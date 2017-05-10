/**
 * 
 */
package task.weather.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import task.weather.dto.TemperatureDTO;
import task.weather.entity.Temperature;
import task.weather.exception.WeatherGuruException;
import task.weather.repository.TemperatureRepository;
import task.weather.util.MessageByLocaleUtils;

/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */
@Service
@Transactional
public class TemperatureServiceImpl implements ITemparatureService {
    
    private static final Logger logger = LogManager.getLogger(TemperatureServiceImpl.class);
    
    @Autowired
    private TemperatureRepository temperatureRepository;
    
    @Autowired
    private MessageByLocaleUtils messageUtils;
    
    @Autowired
    private Mapper mapper;
    
    @Override
    public TemperatureDTO saveOrUpdate(TemperatureDTO temperatureDTO) throws WeatherGuruException {
        logger.info("Entered saveOrUpdate");
        try {
            Temperature temperature = mapper.map(temperatureDTO, Temperature.class);
            
            temperature.getWeather().setTemperature(temperature);
            temperatureRepository.save(temperature);
            
            temperatureDTO = mapper.map(temperature, TemperatureDTO.class);            
            return temperatureDTO;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new WeatherGuruException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new WeatherGuruException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }

    @Override
    public List<TemperatureDTO> saveOrUpdate(List<TemperatureDTO> temperatureDTOs) throws WeatherGuruException {
        logger.info("Entered saveOrUpdate");
        try {
            List<Temperature> temperatures = new ArrayList<>();
            for(TemperatureDTO temperatureDTO: temperatureDTOs) {
                Temperature temperature = mapper.map(temperatureDTO, Temperature.class);
                
                temperature.getWeather().setTemperature(temperature);
                temperatures.add(temperature);
            }
            
            int batchSize = temperatures.size() / 5;
            for(int i = 0; i < temperatures.size(); i++) {
                temperatureRepository.save(temperatures.get(i));
                if(temperatures.size() >= 5) {
                    if(i % batchSize == 0) {
                        temperatureRepository.flush();
                    }   
                }
            }
            
            List<TemperatureDTO> savedTemperatureDTOs = new ArrayList<>();
            for(Temperature temperature: temperatures) {
                TemperatureDTO temperatureDTO = mapper.map(temperature, TemperatureDTO.class);
                
                savedTemperatureDTOs.add(temperatureDTO);
            }
            return savedTemperatureDTOs;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new WeatherGuruException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new WeatherGuruException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }

    @Override
    public List<TemperatureDTO> fetchByCity(Long cityId) throws WeatherGuruException {
        logger.info("Entered fetchByCity with cityId: " + cityId);
        try {
            List<Temperature> temperatures = temperatureRepository.findByCityCityIdOrderByMaxAsc(cityId);
            
            List<TemperatureDTO> savedTemperatureDTOs = new ArrayList<>();
            for(Temperature temperature: temperatures) {
                TemperatureDTO temperatureDTO = mapper.map(temperature, TemperatureDTO.class);
                
                savedTemperatureDTOs.add(temperatureDTO);
            }
            return savedTemperatureDTOs;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new WeatherGuruException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new WeatherGuruException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }

    @Override
    public List<TemperatureDTO> fetchAll() throws WeatherGuruException {
        logger.info("Entered fetchAll");
        try {
            List<Temperature> temperatures = temperatureRepository.findAll();
            
            List<TemperatureDTO> savedTemperatureDTOs = new ArrayList<>();
            for(Temperature temperature: temperatures) {
                TemperatureDTO temperatureDTO = mapper.map(temperature, TemperatureDTO.class);
                
                savedTemperatureDTOs.add(temperatureDTO);
            }
            return savedTemperatureDTOs;
        } catch(DataAccessException dataAccessException) {
            logger.error("Exception catched with message: " + dataAccessException.getMessage(), dataAccessException);
            throw new WeatherGuruException(messageUtils.getMessage("common.fetch.error"), dataAccessException); 
        } catch(Exception exception) {
            logger.error("Exception catched with message: " + exception.getMessage(), exception);
            throw new WeatherGuruException(messageUtils.getMessage("common.fetch.error"), exception); 
        }
    }

}
