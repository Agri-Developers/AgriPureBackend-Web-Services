package com.agripure.agripurebackend.controller;

import com.agripure.agripurebackend.entities.PlotsWeather;
import com.agripure.agripurebackend.service.IPlotsWeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plotsweather")
@Api(tags = "PlotsWeathers", value = "Web Service RESTful - Plants")
public class PlotsWeatherController {
    private IPlotsWeatherService plotsWeatherService;

    public PlotsWeatherController(IPlotsWeatherService plotsWeatherService) {
        this.plotsWeatherService = plotsWeatherService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List Weather of the Plots", notes = "Method for list all weathers of the plots")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Weather of Plots found"),
            @ApiResponse(code = 404, message = "Weather of Plots not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<PlotsWeather>>findAllPlotsWeather() {
        try {
            List<PlotsWeather> plotsWeathers = plotsWeatherService.getAll();
            if (plotsWeathers.size() > 0)
                return new ResponseEntity<>(plotsWeathers, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find weather of plot by Id", notes = "Method for list one weather of plots by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Weather of Plots found"),
            @ApiResponse(code = 404, message = "Weather of Plots not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<PlotsWeather>findPlotsWeatherById(@PathVariable("id") Long id) {
        try {
            Optional<PlotsWeather> plotsWeather = plotsWeatherService.getById(id);
            if (!plotsWeather.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(plotsWeather.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
