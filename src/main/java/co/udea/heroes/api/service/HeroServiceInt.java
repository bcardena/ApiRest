package co.udea.heroes.api.service;

import co.udea.heroes.api.model.Hero;

import java.util.List;

public interface HeroServiceInt {

    public List<Hero> getHeroes();
    public Hero getHero(int id);
    public Hero addHero(Hero hero);
    public Hero getHeroByName(String name);
    public Hero updateHero(Hero hero);
    public void deleteHero(Hero hero);
    public List<Hero> searchHeroByNameContains(String name);
    public Hero getHeroNo404(int id);
}
