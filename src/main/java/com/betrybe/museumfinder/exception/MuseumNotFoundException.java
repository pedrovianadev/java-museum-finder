package com.betrybe.museumfinder.exception;


/**
 * Custom exception for museum not found.
 */
public class MuseumNotFoundException extends RuntimeException {

  public MuseumNotFoundException() {
    super("Museu não encontrado!");
  }
}