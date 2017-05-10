/**
 * 
 */
package task.weather.service;

import java.util.List;

import task.weather.dto.CityDTO;
import task.weather.exception.WeatherGuruException;

/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */
public interface ICityService {
        
    public List<CityDTO> fetchAll() throws WeatherGuruException;

}
