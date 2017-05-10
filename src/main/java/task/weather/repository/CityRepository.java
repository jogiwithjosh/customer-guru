/**
 * 
 */
package task.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task.weather.entity.City;


/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
