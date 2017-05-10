/**
 * 
 */
package task.weather.dto;

import java.io.Serializable;

/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */
public class TemperatureDTO implements Serializable {

    
    /**
     * 
     */
    private static final long serialVersionUID = -502263779444041391L;

    private Long id;
    
    private Double temp;
    
    private Double min;
    
    private Double max;
    
    private Long time;
    
    private CityDTO city;
    
    private WeatherDTO weather;

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Double getTemp() {
        return temp;
    }

    
    public void setTemp(Double temp) {
        this.temp = temp;
    }
    
    public Double getMin() {
        return min;
    }

    
    public void setMin(Double min) {
        this.min = min;
    }

    
    public Double getMax() {
        return max;
    }

    
    public void setMax(Double max) {
        this.max = max;
    }

    
    public Long getTime() {
        return time;
    }

    
    public void setTime(Long time) {
        this.time = time;
    }

    
    public CityDTO getCity() {
        return city;
    }

    
    public void setCity(CityDTO city) {
        this.city = city;
    }

    
    public WeatherDTO getWeather() {
        return weather;
    }

    
    public void setWeather(WeatherDTO weather) {
        this.weather = weather;
    }

}
