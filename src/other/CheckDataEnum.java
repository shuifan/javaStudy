package other;

/**
 * @author fandong
 * @create 2018/5/30
 */
public enum CheckDataEnum {

    /**
     * 数据类型
     * 体温 tw  ℃
     * 脉搏 mb  bpm
     * 血压 xy  千帕
     * 血糖 xt  mmol/L
     * 呼吸频率 hx  次/分
     * 身高 sg  cm
     * 体重 tz  kg
     */
    tw("℃"),
    mb("bpm"),
    xy("千帕"),
    xt("mmol/L"),
    hx("次/分"),
    sg("cm"),
    tz("kg");

    private String unit;

    CheckDataEnum(String unit) {
        this.unit = unit;
    }
}
