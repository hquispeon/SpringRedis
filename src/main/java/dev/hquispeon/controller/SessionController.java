package dev.hquispeon.controller;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * Controlador REST encargado de demostrar el uso de sesiones HTTP
 * respaldadas por Redis (cuando Spring Session está configurado).
 */
@RestController
@RequiredArgsConstructor
public class SessionController {

    /**
     * Endpoint que:
     * 1. Accede a la sesión HTTP actual (HttpSession)
     * 2. Guarda un atributo en la sesión
     * 3. Devuelve todos los atributos de la sesión como un Map
     */
	@GetMapping("/session")
	public Map<String, Object> session(HttpSession session) {

	    /*
	     * Contador de accesos:
	     * - Si no existe, empieza en 1
	     * - Si existe, se incrementa
	     */
	    Integer counter = (Integer) session.getAttribute("counter");
	    if (counter == null) {
	        counter = 1;
	    } else {
	        counter++;
	    }

	    session.setAttribute("counter", counter);

	    /*
	     * Atributo de ejemplo (usuario logueado)
	     * Se mantiene entre requests gracias a Redis
	     */
	    session.setAttribute("userId", 1L);

	    /*
	     * Respuesta enriquecida para entender el estado de la sesión
	     */
	    return Map.of(
	            "sessionId", session.getId(),                         // ID único de sesión
	            "isNew", session.isNew(),                              // ¿Recién creada?
	            "creationTime", session.getCreationTime(),            // Timestamp de creación
	            "lastAccessedTime", session.getLastAccessedTime(),    // Último acceso
	            "accessCounter", counter,                              // Número de llamadas
	            "attributes", Collections.list(session.getAttributeNames())
	                    .stream()
	                    .collect(Collectors.toMap(
	                            name -> name,
	                            session::getAttribute
	                    ))
	    );
	}

}
