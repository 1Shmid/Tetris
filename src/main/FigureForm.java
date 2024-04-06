package main;

public enum FigureForm {

    I_FORM (CoordMask.I_FORM, TpReadableColor.BLUE),
    J_FORM (CoordMask.J_FORM, TpReadableColor.ORANGE);

    /** Маска координат (задаёт геометрическую форму) */
    private CoordMask mask;

    /** Цвет, характерный для этой формы */
    private TpReadableColor color;

    FigureForm(CoordMask mask, TpReadableColor color){
        this.mask = mask;
        this.color = color;
    }