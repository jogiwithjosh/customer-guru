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

public class WeatherDTO implements Serializable {

    
    /**
     * 
     */
    private static final long serialVersionUID = -433640791945282795L;

    private Long id;
    
    private String main;
    
    private String description;

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getMain() {
        return main;
    }

    
    public void setMain(String main) {
        this.main = main;
    }

    
    public String getDescription() {
        return description;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }

}
