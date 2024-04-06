package graphics.lwjglmodule;

/**
 * Реализует графический модуль игры на основе LWJGL.
 *
 * @author 1Shmid
 * @version 1.0
 */
public class LwjglGraphicsModule implements GraphicsModule {

    /**
     * Конструктор.
     * Инициализирует графический движок и необходимые поля модуля.
     */
    public LwjglGraphicsModule() {
        initOpengl();
    }

    private void initOpengl() {
        try {
            /* Задаём размер будущего окна */
            Display.setDisplayMode(new DisplayMode(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));

            /* Задаём имя будущего окна */
            Display.setTitle(Constants.SCREEN_NAME);

            /* Создаём окно */
            Display.create();
        } catch (LWJGLException e) {
            ErrorCatcher.graphicsFailure(e);
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Constants.SCREEN_WIDTH,0, Constants.SCREEN_HEIGHT,1,-1);
        glMatrixMode(GL_MODELVIEW);

        /* Для поддержки текстур */
        glEnable(GL_TEXTURE_2D);

        /* Для поддержки прозрачности */
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        /* Белый фоновый цвет */
        glClearColor(1,1,1,1);
    }



    /**
     * Отрисовывает отдельную ячейку
     *
     * @param x Координата отрисовки X
     * @param y Координата отрисовки Y
     * @param color Цвет ячейки
     */
    private void drawCell(int x, int y, Color color) {
        glColor3ub(color.getRedByte(), color.getGreenByte(), color.getBlueByte());

        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(x,y+ Constants.CELL_SIZE);
        glTexCoord2f(1,0);
        glVertex2f(x+ Constants.CELL_SIZE,y+ Constants.CELL_SIZE);
        glTexCoord2f(1,1);
        glVertex2f(x+ Constants.CELL_SIZE, y);
        glTexCoord2f(0,1);
        glVertex2f(x, y);
        glEnd();
    }

    /**
     * Отрисовывает переданное игровое поле
     *
     * @param field Игровое поле, которое необходимо отрисовать
     */
    @Override
    public void draw(GameField field) {
        glClear(GL_COLOR_BUFFER_BIT);

        for(int x = 0; x < COUNT_CELLS_X; x++){
            for(int y = 0; y < COUNT_CELLS_Y; y++){
                TpReadableColor color = field.getColor(x,y);
                drawCell(x*CELL_SIZE, y*CELL_SIZE, convertColor(color));
            }
        }

        Figure figure = field.getFigure();
        TpReadableColor color = figure.getColor();
        for(Coord coord : figure.getCoords()){
            drawCell(coord.x * CELL_SIZE, coord.y * CELL_SIZE, convertColor(color));
        }

        Display.update();
    }

    /**
     * Примечание: по умолчанию метод возвращает белый цвет.
     *
     * @param color Цвет из нашего кастомного enum
     * @return Color, с которым работает LWJGL
     */
    private Color convertColor(TpReadableColor color) {
        switch (color){
            case RED:
                return new Color(ReadableColor.RED);
            case GREEN:
                return new Color(ReadableColor.GREEN);
            case BLUE:
                return new Color(ReadableColor.BLUE);
            case AQUA:
                return new Color(ReadableColor.CYAN);
            case YELLOW:
                return new Color(ReadableColor.YELLOW);
            case ORANGE:
                return new Color(ReadableColor.ORANGE);
            case PURPLE:
                return new Color(ReadableColor.PURPLE);
            case BLACK:
                return new Color(ReadableColor.BLACK);
            default:
                return new Color(ReadableColor.WHITE);
        }
    }

    /**
     * @return Возвращает true, если в окне нажат "крестик"
     */
    @Override
    public boolean isCloseRequested() {
        return Display.isCloseRequested();
    }

    /**
     * Заключительные действия.
     * Принудительно уничтожает окно.
     */
    @Override
    public void destroy() {
        Display.destroy();
    }

    /**
     * Заставляет программу немного поспать, если последний раз метод вызывался
     * менее чем 1/fps секунд назад
     */
    @Override
    public void sync(int fps) {
        Display.sync(fps);
    }
}