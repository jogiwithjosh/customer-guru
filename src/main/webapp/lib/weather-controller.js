/**
 * author: Jogireddy Kotam
 * date: 05-May-2017
 */

 
function WeatherCtrl($scope, $http, $q, $window, InfoFactory) {
    var that = this;
	that.cities = [];
	that.temperatures = [];
	
	function getcities() {
		return that.cities;
	}
	
	setInterval(function(){
		if(that.selectedCity) {
			onChangeCity(that.selectedCity);
		} else {
			init();
		}		
	}, 60000)
	
	
	function onChangeCity(city) {
		if(!city) {
			return;
		}
		InfoFactory.getTemperatureByCity(city.cityId).success(function(response) {
			that.temperatures = response;
			plotChart();
		}).error(function(response){
			console.log(response.error);
		});
	}
	
	
	function plotChart() {
		var dataPoints = [];
		angular.forEach(that.temperatures, function(item){
			var date = new Date(item.time);
			var dataPoint = {};
			
			dataPoint.y = item.temp;
			dataPoint.x = new Date(item.time);
			dataPoints.push(dataPoint);
		});
		
		var chart = new CanvasJS.Chart("chartContainer",{
			    title:{
			        text: "WeatherGuru Temperature-Time Chart(Trial Version, so time isn't showing properly)"
			    },
			    axisX:{
			        title: "Time of the Day",
			        labelFormatter: function (e) {
						return CanvasJS.formatDate( e.value, "hh:mm");
					}
			    },
			    axisY: {
			        title: "Temperature",
			        interval: 100
			    },
			    data: [{      
			        type: "spline",
			        dataPoints: dataPoints
			    }]
			});

			chart.render();
	}
	
	function querySearch(query, array, key) {
		return query ? that[array].filter(createFilterFor(query, key))	: that[array];
	}
	
	function searchTextChange(text) {
	   console.log('Text changed to ' + text);
	}
	
	function createFilterFor(query, propertyName) {
        var lowercaseQuery = angular.lowercase(query);
         
        return function filterFn(obj) {
        	var property = obj[propertyName].toLowerCase();
        	if (obj && propertyName && property) {
        		return (property.indexOf(lowercaseQuery) > -1);
        	}
        	else if (obj && obj.value) {
                return (obj.value.indexOf(lowercaseQuery) > -1);        		
        	}
        };
    }
	
	function init() {
		InfoFactory.getAllCities().success(function(response) {
			that.cities = response;
		}).error(function(response){
			console.log(response.error);
		});
		
		InfoFactory.getTemperatures().success(function(response) {
			that.temperatures = response;
			plotChart();
		}).error(function(response){
			console.log(response.error);
		});
	}	
	init();
	
	angular.extend(this, {
		createFilterFor: createFilterFor,
		searchTextChange: searchTextChange,
		querySearch: querySearch,
		onChangeCity: onChangeCity,
		getcities: getcities
	});
}

angular.module('guruApp').controller("WeatherController", WeatherCtrl);