package co.udea.heroes.api.controller;

import co.udea.heroes.api.model.Hero;
import co.udea.heroes.api.service.HeroServiceInt;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tour-heroes")
public class HeroController {

    private final Logger log = LoggerFactory.getLogger(HeroController.class);

    private HeroServiceInt heroService;

    public HeroController (HeroServiceInt heroService){
        this.heroService = heroService;
    }

    @GetMapping
    @ApiOperation(value = "Busca todos los heroes", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los heroes fueron buscados", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> getHeroes(){
        return ResponseEntity.ok(heroService.getHeroes());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca un heroe que coincida con el ID", response = Page.class)
    public ResponseEntity<Hero> getHero(@PathVariable("id") int id){
        log.debug("REST request buscar heroe");
        return ResponseEntity.ok(heroService.getHero(id));
    }

    @GetMapping("consultar404/{id}")
    @ApiOperation(value = "Busca un heroe y devuelve 404 si no lo encuentra", response = Page.class)
    public ResponseEntity<Hero> getHeroNo404(@PathVariable("id") int id) {
        return ResponseEntity.ok(heroService.getHeroNo404(id));
    }

    @GetMapping("buscar/{name}")
    @ApiOperation(value = "Busca todos los heroes que en su nombre contenga la palabra", response = Page.class)
    public ResponseEntity <List<Hero>>searchHeroByName(@PathVariable("name") String name){
        return ResponseEntity.ok(heroService.searchHeroByNameContains(name));
    }

    @GetMapping("BuscarPorNombre/{name}")
    @ApiOperation(value = "Busca un heroe que conincida con el nombre", response = Page.class)
    public ResponseEntity<Hero> getHeroByName(@PathVariable("name") String name){
        return ResponseEntity.ok(heroService.getHeroByName(name));
    }

    @PutMapping("/actualizar")
    @ApiOperation(value = "Actualiza un heroes", response = Page.class)
    public ResponseEntity<Hero> updateHero(@RequestBody Hero hero){
        return ResponseEntity.ok(heroService.updateHero(hero));
    }

    @PostMapping("/crear")
    @ApiOperation(value = "Crea un heroes", response = Page.class)
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero){
        return ResponseEntity.ok(heroService.addHero(hero));
    }

    @DeleteMapping("/borrar")
    @ApiOperation(value = "Borra un heroe", response = Page.class)
    public void deleteHero(@RequestBody Hero hero){
       heroService.deleteHero(hero);
    }
}
