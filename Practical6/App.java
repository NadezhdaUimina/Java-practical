package Practical6;

import java.util.ArrayList;

public class App {
  // создание экземляра, вывод данных
  public static void main(String[] args) {
    Infrastructure infrastructure = new Infrastructure();

    // System.out.println(infrastructure.getAllInfo(1));
    // System.out.println(infrastructure.getAllInfo(2));
    // System.out.println(infrastructure.getAllInfo(3));
    // System.out.println(infrastructure.getAllInfo(4));

    ArrayList<String> result = infrastructure.findAll("а");
    infrastructure.printResult(result);

  }
}

// класс отвечающий за создание приложения
class Infrastructure {

  // запуск создания БД
  public Infrastructure() {
    init();
  }

  Db db;

  // готовая база данных
  public Db getDb() {
    return db;
  }

  // метод вывода всех данных из БД, принимает id фильма, возвращает данные в
  // формате строки(жанр и компания обращаются к соответствующим таблицам)
  public String getAllInfo(int idCinema) {
    Cinema c = db.films.get(idCinema - 1);

    return String.format("%d %s %s %s",
        c.id,
        c.name,
        db.genres.get(c.genre - 1).name,
        db.prod.get(c.filmProd - 1).titleName);
  }

  // метод поиска по входным данным, возвращает список данных по поиску
  public ArrayList<String> findAll(String searchData) {
    ArrayList<String> findAll = new ArrayList<String>();
    for (int idCinema = 0; idCinema < db.films.size(); idCinema++) {
      Cinema c = db.films.get(idCinema);
      if (c.name.toLowerCase().contains(searchData.toLowerCase())) {
        findAll.add(String.format("%d %s %s %s",
            c.id,
            c.name,
            db.genres.get(c.genre - 1).name,
            db.prod.get(c.filmProd - 1).titleName));
      }
    }
    if (findAll.size() == 0) {
      findAll.add("Ничего не найдено");
    }
    return findAll;
  }

  // печать результатов поиска
  public void printResult(ArrayList<String> findAll) {
    for (int i = 0; i < findAll.size(); i++) {
      System.out.println(findAll.get(i));
    }
  }

  // создание БД, наполнение, добавление в БД, добавление жанров, заполнение
  // компаний
  Db init() {
    db = new Db();
    Cinema c1 = new Cinema(1, "Тьма", 1, 1);
    Cinema c2 = new Cinema(2, "Свет", 1, 2);
    Cinema c3 = new Cinema(3, "Особенности национальной рыбалки", 3, 4);
    Cinema c4 = new Cinema(4, "Человек паук", 3, 3);

    db.films.add(c1);
    db.films.add(c2);
    db.films.add(c3);
    db.films.add(c4);

    db.genres.add(new Genre(1, "Ужасы"));
    db.genres.add(new Genre(2, "Комедия"));
    db.genres.add(new Genre(3, "Драмма"));
    FilmProducerFactory pf = new FilmProducerFactory();
    db.addFilmProducer(pf.getFilmProducer("Ленфильм"));
    db.addFilmProducer(pf.getFilmProducer("Марвел"));
    db.addFilmProducer(pf.getFilmProducer("Мосфильм"));
    db.addFilmProducer(pf.getFilmProducer("DC"));

    return db;
  }

}

// база данных, создание списков: фильмы, компания, жанры
class Db {
  ArrayList<Cinema> films = new ArrayList<>();
  ArrayList<FilmProducer> prod = new ArrayList<>();
  ArrayList<Genre> genres = new ArrayList<>();

  // метод добавление компании
  public void addFilmProducer(FilmProducer producer) {
    prod.add(producer);
  }
}

// создание модели фильмов, id, компания, название, жанр
class Cinema {
  int id;
  int filmProd;
  String name;
  int genre;

  // сгенерированный конструктор(для создания выделяем строки 124-127 ПКМ ->
  // действие с исходным кодом -> Generate Constructors)
  public Cinema(int id, String name, int genre, int filmProd) {
    this.id = id;
    this.filmProd = filmProd;
    this.name = name;
    this.genre = genre;
  }
}

// модель киностудий, id, наименование
class FilmProducer {
  int id;
  String titleName;
}

// модель жанров, id, жанр
class Genre {
  int id;
  String name;

  // сгенерированный конструктор
  public Genre(int id, String name) {
    this.id = id;
    this.name = name;
  }
}

// таблица с компаниями
class FilmProducerFactory {
  int count = 1;

  // создание компании
  public FilmProducer getFilmProducer(String name) {
    FilmProducer fp = new FilmProducer();
    fp.id = count++;
    fp.titleName = name;
    return fp;
  }
}
