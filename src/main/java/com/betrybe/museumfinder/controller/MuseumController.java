package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Museum Controller.
 */
@Controller
@RequestMapping("museums")
public class MuseumController {
  MuseumServiceInterface service;

  @Autowired
  public MuseumController(MuseumServiceInterface service) {
    this.service = service;
  }
  /**
   * Method that creates a new museum.
   *
   * @param newMuseumDto of type MuseumCreationDto, coming from the request.
   */

  @PostMapping
  public ResponseEntity<MuseumDto> createMuseum(@RequestBody MuseumCreationDto newMuseumDto) {
    Museum newMuseum = ModelDtoConverter.dtoToModel(newMuseumDto);
    Museum museum = service.createMuseum(newMuseum);
    MuseumDto museumDto = ModelDtoConverter.modelToDto(museum);

    return ResponseEntity.status(HttpStatus.CREATED).body(museumDto);
  }

  /**
   * Route that returns the nearest museum based on the parameters.
   *
   * @param latitude a double coming from the request.
   * @param longitude a double coming from the request.
   * @param maxDistanceKm a Double coming from the request.
   */
  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getClosestMuseum(
      @RequestParam("lat") double latitude,
      @RequestParam("lng") double longitude,
      @RequestParam("max_dist_km") Double maxDistanceKm
  ) {
    Coordinate coordinate = new Coordinate(latitude, longitude);

    Museum museum = service.getClosestMuseum(coordinate, maxDistanceKm);
    MuseumDto museumDto = ModelDtoConverter.modelToDto(museum);
    return ResponseEntity.ok(museumDto);
  }
}