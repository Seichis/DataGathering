package settings;

/**
 * Created by User1 on 26/4/2015.
 */
public class SettingsObj {
    int digitOrderShowNumberTimeSpan;
    int coordRadiusOfCircle;
    float coordDistanceFromCenter;
    int longTermShowWordsTimeSpan;
    int digitSpanShowNumberTimeSpan;

    public SettingsObj() {
        this.coordDistanceFromCenter = 5.7f;
        this.coordRadiusOfCircle = 100;
        this.longTermShowWordsTimeSpan = 1000;
        this.digitOrderShowNumberTimeSpan = 1000;
        this.digitSpanShowNumberTimeSpan = 1000;
    }

    public int getDigitSpanShowNumberTimeSpan() {
        return digitSpanShowNumberTimeSpan;
    }

    public void setDigitSpanShowNumberTimeSpan(int digitSpanShowNumberTimeSpan) {
        this.digitSpanShowNumberTimeSpan = digitSpanShowNumberTimeSpan;
    }

    public int getCoordRadiusOfCircle() {
        return coordRadiusOfCircle;
    }

    public void setCoordRadiusOfCircle(int coordRadiusOfCircle) {
        this.coordRadiusOfCircle = coordRadiusOfCircle;
    }

    public float getCoordDistanceFromCenter() {
        return coordDistanceFromCenter;
    }

    public void setCoordDistanceFromCenter(float coordDistanceFromCenter) {
        this.coordDistanceFromCenter = coordDistanceFromCenter;
    }

    public int getLongTermShowWordsTimeSpan() {
        return longTermShowWordsTimeSpan;
    }

    public void setLongTermShowWordsTimeSpan(int longTermShowWordsTimeSpan) {
        this.longTermShowWordsTimeSpan = longTermShowWordsTimeSpan;
    }


    public int getDigitOrderShowNumberTimeSpan() {
        return digitOrderShowNumberTimeSpan;
    }

    public void setDigitOrderShowNumberTimeSpan(int digitOrderShowNumberTimeSpan) {
        this.digitOrderShowNumberTimeSpan = digitOrderShowNumberTimeSpan;
    }
}
