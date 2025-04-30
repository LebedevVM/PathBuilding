# PathBuilder

Проект создан с помощью фреймфорка [libGDX](https://libgdx.com/)

## Gradle

Данный проект построен с исползованием [Gradle](https://gradle.org/).
Для запуска приложения можно использовать следующие команды:

- `gradle lwjgl3:jar`: компилирует приложение в jar-файл, находящийся по адрессу `lwjgl3/build/libs`.
- `gradle lwjgl3:run`: запускает приложение.

## Работа с приложением

Для работы программы, необходимо ввести путь к текстовому файлу, в котором хранятся координаты траектории движения в формате "X:Y\n".

Нажав на кнопку "Запустить", вы запускаете самолёт, который будет следовать по траектории из файла.

Нажав на кнопку "Редактировать", вы запускаете экран редактирования, где можно создать траекторию для самолёта, путём нажатия мышей по экрану, а затем сохранить в отдельный файл.

## LWJGL3

Класс Lwjgl3Launcher - запускает приложение в виде окна.

Класс StartupHelper - помошник при запуске приложения.

## Starter

Класс Starter - отвечает за загрузку ассетов и переключение между экранами.

    Методы:

        • public void create() - метод, вызываемый при создании объекта, в котором определяются стили для экранов приложения.
        • public void changeScreen (boolean launch) - метод для изменения экранов приложения.
        • public void render() - метод, вызывемый для рендера кадров.
        • public void dispose() - метод для освобождения ресурсов.

## Экраны

Класс LaunchScreen - экран, отображающий движение самолёта.

    Методы:

        • public LaunchScreen (Starter starter) - конструктор, в котором создаются виджеты для экрана.
        • public void show () - метод, вызываемый при показе экрана.
        • public void render (float delta) - метод, вызывемый для рендера кадров.
        • public void resize (int width, int height) - метод, вызываемый при изменении размеров окна приложения.
        • public void pause () - метод паузы приложения.
        • public void resume () - метод возобновления приложения.
        • public void hide () - метод, вызываемый при скрытии экрана.
        • public void dispose () - метод для освобождения ресурсов.

Класс EditorScreen - экран для редактирования траектории полёта самолёта.

    Методы:

        • public EditorScreen (Starter starter) - конструктор, в котором создаются виджеты для экрана.
        • public void click (Vector2 position) - метод, создающий точку траектории в месте нажатия.
        • public void show () - метод, вызываемый при показе экрана.
        • public void render (float delta) - метод, вызывемый для рендера кадров.
        • public void resize (int width, int height) - метод, вызываемый при изменении размеров окна приложения.
        • public void pause () - метод паузы приложения.
        • public void resume () - метод возобновления приложения.
        • public void hide () - метод, вызываемый при скрытии экрана.
        • public void dispose () - метод для освобождения ресурсов.

## Точки траектории

Класс Point - точка траектории полёта самолёта.

    Методы:
        
        • public Point (String positionString) - конструктор, создающий объект по строке формата "X:Y", где X, Y - числа, обозначающие координаты точки.
        • public Point (Vector2 position) - конструктор, создающий объект по заданной координате.
        • public boolean checkCollision (Vector2 collisionPosition) - метод, проверяющий достижение самолётом этой точки.
        • public void render (ShapeDrawer shapeDrawer) - метод, отображающий положение точки на экране.
        • public Vector2 getPosition () - метод, возвращающий координаты точки.
        • public String toString () - переопределённый метод для возврата позиции в виде строки в формате "X:Y".

Класс PointGraph - хранилище для точек траектории.

    Методы:

        • public void addPoint (List<String> positionStrings) - метод для создания массива точек из строк формата "X:Y".
        • public void addPoint (String positionString) - метод для создания точки из строки формата "X:Y".
        • public void addPoint (Vector2 position) - метод для создания точки из координат
        • public static boolean checkPositionString (String positionString) - метод для проверки строки на соотвествие формату "X:Y"
        • private static boolean isNumeric (String str) - метод для проверки строки на соответствие числу.
        • public void render (ShapeDrawer shapeDrawer) - метод для отображения точек.
        • public Array<Point> getPoints () - метод, возвращающий массив точек.
        • public String toString () - переопределённый метод для возврата позиций точек в виде строки в формате "X:Y\n".

## Самолёт

Класс Plane - самолёт, летящий по траектории.

    Методы:

        • public Plane (PlaneController planeStorage, PointGraph pointGraph) - конструктор для самолёта.
        • public void render (ShapeDrawer shapeDrawer, Batch batch, float delta) - метод для отображения самолёта.
        • private void checkCollision () - метод для проверки достижения самолётом точки траектории.
        • private void move (float delta) - метод для передвижения самолёта до следующей точки траектории.
        • private void endJourney () - метод окончания маршрута самолёта.

Класс PlaneStorage - хранилище для самолётов.

    Методы:

        • public void addPlane (PointGraph pointGraph) - метод создания самолёта для текущей траектории.
        • public void removePlane (Plane plane) - метод удаления самолёта, закончившего свой маршрут.
        • public void render (ShapeDrawer shapeDrawer, Batch batch, float delta) - метод отрисовки самолётов.

## Адаптеры

Класс ClickAdapter - адаптер для кликов по экрану для EditorScreen.

    Методы:

        • public ClickAdapter (EditorScreen editorScreen) - коснтруктор для адаптера.
        • public boolean touchDown (int screenX, int screenY, int pointer, int button) - метод, вызываемый при нажатии мышью на экране.

Класс FileToPointGraphAdapter - адаптер для создания точек траектории из файла.

    Методы:

        • public static PointGraph getPointGraphFromFile (String fileName) - метод для получения PointGraph-а из файла с путём fileName.

Класс PointGraphToFileAdapter - адаптер для создания файла точек траектории из имеющегося PointGraph-а.

    Методы:

        • public static void uploadPointGraph (PointGraph pointGraph, String fileName) - метод для создания файла с путём fileName из PointGraph-а.

