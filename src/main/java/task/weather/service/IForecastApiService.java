/**
 * 
 */
package task.weather.service;

import task.weather.exception.WeatherGuruException;

/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */
public interface IForecastApiService {
    
    public void processApiCalls() throws WeatherGuruException;

}
