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
public class CityDTO implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3309267093780567987L;

    private Long id;
    
    private Long cityId;
    
    private String city;
    
    private String country;
    
    //private List<TemperatureDTO> temperatures = new ArrayList<>();

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public Long getCityId() {
        return cityId;
    }

    
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    
    public String getCity() {
        return city;
    }

    
    public void setCity(String city) {
        this.city = city;
    }

    
    public String getCountry() {
        return country;
    }

    
    public void setCountry(String country) {
        this.country = country;
    }

    
/*    public List<TemperatureDTO> getTemperatures() {
        return temperatures;
    }

    
    public void setTemperatures(List<TemperatureDTO> temperatures) {
        this.temperatures = temperatures;
    }*/

}
