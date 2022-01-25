# Přístavní aplikace - real_docker 
Autoři: [Jan Dušek @dusekjan](https://github.com/dusekjan/) & [Karel Nosek @KarelNosek25](https://github.com/KarelNosek25)

## Semestrální projekt předmětu PPRO - 2021/2022

Projekt je Spring-boot aplikace s pristupem do lokalni databaze HSQLDB.

### Backend
- K databazi se pristupuje pomoci JpaRepository
  - Databaze je naplnena zkusebnimy daty pri spusteni aplikace 
- Dotazy zpracovavaji REST API Controllery
- Aplikace pro zabezpeceni vyuziva WebSecurityConfigurerAdapter
  - Vyuziva ho pro Login, Logout a urceni prav uzivatele (Uzivatel je namapovan na entitu Worker)
  - Podle pridelenych roli se definuji naroky na provadeni requestu
- Requesty na REST API od klienta zprostredkovava JavaScript metodou fetch()
- Veskere zpracovavane pozadavky (Backend, Frontend) jsou pripraveni na odchytnuti pripadne chyby

### Frontend
- Sablony a jejich styly jsou vytvoreny pomoci HTML/CSS
  - Thymeleaf 
- Pro JavaScript se nevyuziva zadny framework 

### Poznamky
- Je mozne ve vyvojovem prostredi zobrazit stav databaze v Grafickem rozhrani - ovsem pokud nebezi aplikace
  - Jinymi slovy je mozne si zobrazit obsah databaze pokud nebezi aplikace a naopak
  - Je mozne spustit aplikaci, provest zmeny a po vypnuti aplikace lze zobrazit aktualni stav databaze
- Docker - `docker-compose build` vytvori image, ale `docker-compose up` nespusti aplikaci
  - doporucuji spoustet apliakci ve vyvojovem prostredi



