package com.agripure.agripurebackend.controller;

import com.agripure.agripurebackend.entities.Plot;
import com.agripure.agripurebackend.service.IPlotService;
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
@RequestMapping("api/plots")
@Api(tags = "Plots", value = "Web Service RESTful - Plots")
public class PlotController {
    private IPlotService plotService;

    public PlotController(IPlotService plotService) {
        this.plotService = plotService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List Plots", notes = "Method for list all plots")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Plots found"),
            @ApiResponse(code = 404, message = "Plots not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Plot>>findAllPlots() {
        try {
            List<Plot> plots = plotService.getAll();
            if (plots.size() > 0)
                return new ResponseEntity<>(plots ,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find plots by Id", notes = "Method for list one plots by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Plot found"),
            @ApiResponse(code = 404, message = "Plot not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Plot>findPlotById(@PathVariable("id") Long id) {
        try {
            Optional<Plot> plot = plotService.getById(id);
            if (!plot.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(plot.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
