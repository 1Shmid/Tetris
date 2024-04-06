package main;
/**
 * Каждая маска -- шаблон, который по мнимой координате фигуры и
 * состоянию её поворота возвращает 4 координаты реальных блоков
 * фигуры, которые должны отображаться.
 * Т.е. маска задаёт геометрическую форму фигуры.
 *
 * @author 1Shmid
 * @version 1.0
 */

public enum CoordMask {
    I_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initialCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                        case INVERT:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x , initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x, initialCoord.y - 2);
                            ret[3] = new Coord(initialCoord.x, initialCoord.y - 3);
                            break;
                        case FLIP_CCW:
                        case FLIP_CW:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[2] = new Coord(initialCoord.x + 2, initialCoord.y);
                            ret[3] = new Coord(initialCoord.x + 3, initialCoord.y);
                            break;
                    }

                    return ret;
                }
            }
    ),
    J_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initialCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                            ret[0] = new Coord(initialCoord.x + 1 , initialCoord.y);
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 2);
                            ret[3] = new Coord(initialCoord.x, initialCoord.y - 2);
                            break;
                        case INVERT:
                            ret[0] = new Coord(initialCoord.x + 1 , initialCoord.y);
                            ret[1] = initialCoord;
                            ret[2] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x, initialCoord.y - 2);
                            break;
                        case FLIP_CCW:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[2] = new Coord(initialCoord.x + 2, initialCoord.y);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y - 1);
                            break;
                        case FLIP_CW:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y - 1);
                            break;
                    }

                    return ret;
                }
            }
    ),

    /* Кирпичик     []
     *          [][][]
     */
    L_FORM(
            new GenerationDelegate() {
                @Override
                public Coord[] generateFigure(Coord initialCoord, RotationMode rotation) {
                    Coord[] ret = new Coord[4];

                    switch (rotation){
                        case NORMAL:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x, initialCoord.y - 2);
                            ret[3] = new Coord(initialCoord.x + 1, initialCoord.y - 2);
                            break;
                        case INVERT:
                            ret[0] = initialCoord;
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x + 1, initialCoord.y - 2);
                            break;
                        case FLIP_CCW:
                            ret[0] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[1] = new Coord(initialCoord.x + 1, initialCoord.y - 1);
                            ret[2] = new Coord(initialCoord.x + 2, initialCoord.y - 1);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y);
                            break;
                        case FLIP_CW:
                            ret[0] = new Coord(initialCoord.x, initialCoord.y - 1);
                            ret[1] = initialCoord;
                            ret[2] = new Coord(initialCoord.x + 1, initialCoord.y);
                            ret[3] = new Coord(initialCoord.x + 2, initialCoord.y);
                            break;
                        default:
                            ErrorCatcher.wrongParameter("rotation", "CoordMask");
                    }

                    return ret;
                }
            }
    ),



    /**
     * Делегат, содержащий метод,
     * который должен определять алгоритм для generateFigure()
     */
    private interface GenerationDelegate{

        /**
         * По мнимой координате фигуры и состоянию её поворота
         * возвращает 4 координаты реальных блоков фигуры, которые должны отображаться
         *
         * @param initialCoord Мнимая координата
         * @param rotation Состояние поворота
         * @return 4 реальные координаты
         */
        Coord[] generateFigure(Coord initialCoord,  RotationMode rotation);
    }

    private GenerationDelegate forms;

    CoordMask(GenerationDelegate forms){
        this.forms = forms;
    }

    /**
     * По мнимой координате фигуры и состоянию её поворота
     * возвращает 4 координаты реальных блоков фигуры, которые должны отображаться.
     *
     * Запрос передаётся делегату, спецефичному для каждого объекта enum'а.
     *
     * @param initialCoord Мнимая координата
     * @param rotation Состояние поворота
     * @return 4 реальные координаты
     */
    public Coord[] generateFigure(Coord initialCoord, RotationMode rotation){
        return this.forms.generateFigure(initialCoord, rotation);
    }

}