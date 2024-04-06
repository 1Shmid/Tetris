package main;

import main.graphics.*;

import java.util.*;

import static main.CoordMask.*;

public enum FigureForm {

    I_FORM(CoordMask.I_FORM, TpReadableColor.BLUE),
    J_FORM(CoordMask.J_FORM, TpReadableColor.ORANGE),
    L_FORM(CoordMask.L_FORM, TpReadableColor.BLACK),
    O_FORM(CoordMask.O_FORM, TpReadableColor.RED),
    S_FORM(CoordMask.S_FORM, TpReadableColor.GREEN),
    Z_FORM(CoordMask.Z_FORM, TpReadableColor.AQUA),
    T_FORM(CoordMask.T_FORM, TpReadableColor.YELLOW);



    /**
     * Маска координат (задаёт геометрическую форму)
     */
    private CoordMask mask;

    /**
     * Цвет, характерный для этой формы
     */
    private TpReadableColor color;

    FigureForm(CoordMask mask, TpReadableColor color) {
        this.mask = mask;
        this.color = color;
    }

    /**
     * Массив со всеми объектами этого enum'а (для удобной реализации getRandomForm() )
     */
    private static final FigureForm[] formByNumber = {I_FORM, J_FORM, L_FORM, O_FORM, S_FORM, Z_FORM, T_FORM,};

    /**
     * @return Маска координат данной формы
     */
    public CoordMask getMask() {
        return this.mask;
    }

    /**
     * @return Цвет, специфичный для этой формы
     */
    public TpReadableColor getColor() {
        return this.color;
    }

    /**
     * @return Случайный объект этого enum'а, т.е. случайная форма
     */
    public static FigureForm getRandomForm() {
        int formNumber = new Random().nextInt(formByNumber.length);
        return formByNumber[formNumber];
    }
}