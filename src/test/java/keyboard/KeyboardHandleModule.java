public interface KeyboardHandleModule {

    /**
     * Считывание последних данных из стека событий, если модулю это необходимо
     */
    void update();

    /**
     * @return Возвращает информацию о том, был ли нажат ESCAPE за последнюю итерацию
     */
    boolean wasEscPressed();

    /**
     * @return Возвращает направление, в котором пользователь хочет сдвинуть фигуру.
     * Если пользователь не пытался сдвинуть фигуру, возвращает ShiftDirection.AWAITING.
     */
    ShiftDirection getShiftDirection();

    /**
     * @return Возвращает true, если пользователь хочет повернуть фигуру.
     */
    boolean wasRotateRequested();

    /**
     * @return Возвращает true, если пользователь хочет ускорить падение фигуры.
     */
    boolean wasBoostRequested();
}