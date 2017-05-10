/**
 * 
 */
package task.weather.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task.weather.entity.Temperature;


/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */
@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    
    public List<Temperature> findByCityCityIdOrderByMaxAsc(Long cityId);

}
