/**
 * 
 */
package task.weather.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */

@Entity
@Table(name = "guru_weather")
public class Weather implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3645311931313266645L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    
    @Column(name = "main")
    private String main;
    
    @Column(name = "description")
    private String description;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temperature_id", nullable = false)
    private Temperature temperature;

    
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

    
    public Temperature getTemperature() {
        return temperature;
    }

    
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

}
