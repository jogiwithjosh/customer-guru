/**
 * 
 */
package task.weather.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */
@Entity
@Table(name = "guru_temperature")
public class Temperature implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3096849485453839909L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    
    @Column(name = "temp", nullable = false)
    private Double temp;
    
    @Column(name = "min", nullable = false)
    private Double min;
    
    @Column(name = "max", nullable = false)
    private Double max;
    
    @Column(name = "time", nullable = false)
    private Long time;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_pk_id", nullable = false)
    private City city;
    
    @OneToOne(mappedBy = "temperature", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Weather weather;

    
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

    
    public City getCity() {
        return city;
    }

    
    public void setCity(City city) {
        this.city = city;
    }

    
    public Weather getWeather() {
        return weather;
    }

    
    public void setWeather(Weather weather) {
        this.weather = weather;
    }

}
