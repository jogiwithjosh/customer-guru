/**
 * 
 */
package task.weather.service;

import java.util.List;

import task.weather.dto.TemperatureDTO;
import task.weather.exception.WeatherGuruException;

/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 */
public interface ITemparatureService {
    
    public TemperatureDTO saveOrUpdate(TemperatureDTO temperature) throws WeatherGuruException;
    
    public List<TemperatureDTO> saveOrUpdate(List<TemperatureDTO> temperatures) throws WeatherGuruException;
    
    public List<TemperatureDTO> fetchByCity(Long cityId) throws WeatherGuruException;
    
    public List<TemperatureDTO> fetchAll() throws WeatherGuruException;
    
}
