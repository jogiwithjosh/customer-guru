/**
 * 
 */
package task.weather.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */
@Entity
@Table(name = "guru_city")
public class City implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6984559731796987548L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    
    @Column(name = "city_id", nullable = false, unique = true)
    private Long cityId;
    
    @Column(name = "city", nullable = false)
    private String city;
    
    @Column(name = "country", nullable = false)
    private String country;
    
    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Temperature> temperatures = new ArrayList<>();

    
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

    
    public List<Temperature> getTemperatures() {
        return temperatures;
    }

    
    public void setTemperatures(List<Temperature> temperatures) {
        this.temperatures = temperatures;
    }

}
