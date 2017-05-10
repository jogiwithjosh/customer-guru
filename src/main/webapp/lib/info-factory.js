/**
 * author: Jogireddy Kotam
 * date: 09-May-2017
 */
function InfoFactory($http) {
    var InfoFactory = {};
    
    InfoFactory.getAllCities = function () {
    	return $http.get("/weather-guru/city/all");
    };
    
    InfoFactory.getTemperatureByCity = function (cityId) {
    	return $http.get("/weather-guru/temperature/city/" + cityId);
    };
    
    InfoFactory.getTemperatures = function () {
    	return $http.get("/weather-guru/temperature/all");
    };
    
	return InfoFactory;	
}
angular
    .module('guruApp')
    .factory('InfoFactory', InfoFactory);