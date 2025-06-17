package com.luisangel.jwtapi.controllers;

import com.luisangel.jwtapi.models.EReaction;
import com.luisangel.jwtapi.models.Reaccion;
import com.luisangel.jwtapi.models.User;
import com.luisangel.jwtapi.service.ReaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reacciones")
public class ReaccionController {

    @Autowired
    private ReaccionService reaccionService;

    // ✅ Agregar o cambiar reacción
    @PostMapping("/tweet/{tweetId}")
    public ResponseEntity<Reaccion> reaccionar(
            @PathVariable Long tweetId,
            @RequestParam EReaction tipo,
            @AuthenticationPrincipal User userDetails
    ) {
        Reaccion reaccion = reaccionService.reaccionarATweet(tipo, tweetId, userDetails);
        return ResponseEntity.ok(reaccion);
    }

    // ✅ Quitar reacción
    @DeleteMapping("/tweet/{tweetId}")
    public ResponseEntity<?> quitarReaccion(
            @PathVariable Long tweetId,
            @AuthenticationPrincipal User userDetails
    ) {
        reaccionService.quitarReaccion(tweetId, userDetails);
        return ResponseEntity.ok().build();
    }

    // ✅ Obtener todas las reacciones de un tweet
    @GetMapping("/tweet/{tweetId}")
    public ResponseEntity<List<Reaccion>> obtenerReacciones(@PathVariable Long tweetId) {
        return ResponseEntity.ok(reaccionService.obtenerReaccionesDeTweet(tweetId));
    }

    // ✅ Contar cuántas reacciones hay de cierto tipo en un tweet
    @GetMapping("/tweet/{tweetId}/contador")
    public ResponseEntity<?> contarPorTipo(
            @PathVariable Long tweetId,
            @RequestParam(required = false) EReaction tipo
    ) {
        if (tipo != null) {
            long count = reaccionService.contarReaccionesPorTipo(tweetId, tipo);
            return ResponseEntity.ok(count);
        } else {
            Map<EReaction, Long> conteoPorTipo = reaccionService.obtenerConteoPorTipo(tweetId);
            return ResponseEntity.ok(conteoPorTipo);
        }
    }
}
