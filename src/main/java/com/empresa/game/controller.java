// backend/src/main/java/com/empresa/game/controller/GameController.java
@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {

    @Autowired private GameService gameService;
    @Autowired private ScoreRepository scoreRepository;

    // Avanzar un mes (Turno)
    @PostMapping("/turno/{gameId}")
    public ResponseEntity<EstadoMensual> avanzarMes(@PathVariable String gameId, @RequestBody DecisionesMes decisiones) {
        EstadoMensual resultado = gameService.procesarMes(gameId, decisiones);
        return ResponseEntity.ok(resultado);
    }

    // Registro autónomo de puntaje
    @PostMapping("/score")
    public ResponseEntity<Score> registrarPuntaje(@RequestBody ScoreRequest request) {
        Score nuevoScore = new Score(request.getJugador(), request.getPuntaje(), request.getSector());
        return ResponseEntity.ok(scoreRepository.save(nuevoScore));
    }

    @GetMapping("/leaderboard")
    public List<Score> obtenerTop() {
        return scoreRepository.findTop10ByOrderByPuntajeDesc();
    }
}
