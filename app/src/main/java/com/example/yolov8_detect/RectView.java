package com.example.yolov8_detect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RectView extends View {
    private final Map<RectF, String> zeroMap = new HashMap<>();
    private final Map<RectF, String> oneMap = new HashMap<>();
    private final Map<RectF, String> twoMap = new HashMap<>();
    private final Map<RectF, String> threeMap = new HashMap<>();
    private final Map<RectF, String> fourMap = new HashMap<>();
    private final Map<RectF, String> fiveMap = new HashMap<>();
    private final Map<RectF, String> sixMap = new HashMap<>();
    private final Map<RectF, String> sevenMap = new HashMap<>();
    private final Map<RectF, String> eightMap = new HashMap<>();
    private final Map<RectF, String> nineMap = new HashMap<>();
    private final Paint zeroPaint = new Paint();
    private final Paint onePaint = new Paint();
    private final Paint twoPaint = new Paint();
    private final Paint threePaint = new Paint();
    private final Paint fourPaint = new Paint();
    private final Paint fivePaint = new Paint();
    private final Paint sixPaint = new Paint();
    private final Paint sevenPaint = new Paint();
    private final Paint eightPaint = new Paint();
    private final Paint ninePaint = new Paint();
    private final Paint textPaint = new Paint();

    private String[] labels;

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        zeroPaint.setStyle(Paint.Style.STROKE);
        zeroPaint.setStrokeWidth(10.0f);
        zeroPaint.setColor(Color.RED);
        zeroPaint.setStrokeCap(Paint.Cap.ROUND);
        zeroPaint.setStrokeJoin(Paint.Join.ROUND);
        zeroPaint.setStrokeMiter(100);

        onePaint.setStyle(Paint.Style.STROKE);
        onePaint.setStrokeWidth(10.0f);
        onePaint.setColor(Color.parseColor("#FFA500"));
        onePaint.setStrokeCap(Paint.Cap.ROUND);
        onePaint.setStrokeJoin(Paint.Join.ROUND);
        onePaint.setStrokeMiter(100);

        twoPaint.setStyle(Paint.Style.STROKE);
        twoPaint.setStrokeWidth(10.0f);
        twoPaint.setColor(Color.YELLOW);
        twoPaint.setStrokeCap(Paint.Cap.ROUND);
        twoPaint.setStrokeJoin(Paint.Join.ROUND);
        twoPaint.setStrokeMiter(100);

        threePaint.setStyle(Paint.Style.STROKE);
        threePaint.setStrokeWidth(10.0f);
        threePaint.setColor(Color.GREEN);
        threePaint.setStrokeCap(Paint.Cap.ROUND);
        threePaint.setStrokeJoin(Paint.Join.ROUND);
        threePaint.setStrokeMiter(100);

        fourPaint.setStyle(Paint.Style.STROKE);
        fourPaint.setStrokeWidth(10.0f);
        fourPaint.setColor(Color.BLUE);
        fourPaint.setStrokeCap(Paint.Cap.ROUND);
        fourPaint.setStrokeJoin(Paint.Join.ROUND);
        fourPaint.setStrokeMiter(100);

        fivePaint.setStyle(Paint.Style.STROKE);
        fivePaint.setStrokeWidth(10.0f);
        fivePaint.setColor(Color.parseColor("#000080"));
        fivePaint.setStrokeCap(Paint.Cap.ROUND);
        fivePaint.setStrokeJoin(Paint.Join.ROUND);
        fivePaint.setStrokeMiter(100);

        sixPaint.setStyle(Paint.Style.STROKE);
        sixPaint.setStrokeWidth(10.0f);
        sixPaint.setColor(Color.parseColor("#800080"));
        sixPaint.setStrokeCap(Paint.Cap.ROUND);
        sixPaint.setStrokeJoin(Paint.Join.ROUND);
        sixPaint.setStrokeMiter(100);

        sevenPaint.setStyle(Paint.Style.STROKE);
        sevenPaint.setStrokeWidth(10.0f);
        sevenPaint.setColor(Color.MAGENTA);
        sevenPaint.setStrokeCap(Paint.Cap.ROUND);
        sevenPaint.setStrokeJoin(Paint.Join.ROUND);
        sevenPaint.setStrokeMiter(100);

        eightPaint.setStyle(Paint.Style.STROKE);
        eightPaint.setStrokeWidth(10.0f);
        eightPaint.setColor(Color.CYAN);
        eightPaint.setStrokeCap(Paint.Cap.ROUND);
        eightPaint.setStrokeJoin(Paint.Join.ROUND);
        eightPaint.setStrokeMiter(100);

        ninePaint.setStyle(Paint.Style.STROKE);
        ninePaint.setStrokeWidth(10.0f);
        ninePaint.setColor(Color.BLACK);
        ninePaint.setStrokeCap(Paint.Cap.ROUND);
        ninePaint.setStrokeJoin(Paint.Join.ROUND);
        ninePaint.setStrokeMiter(100);

        textPaint.setTextSize(60.0f);
        textPaint.setColor(Color.WHITE);
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    // rectF 비율 수정
    public ArrayList<Result> transFormRect(ArrayList<Result> resultArrayList) {
        float scaleX = getWidth() / (float) SupportOnnx.INPUT_SIZE;
        float scaleY = scaleX * 9f / 16f;
        float realY = getWidth() * 9f / 16f;
        float diffY = realY - getHeight();

        for (Result result : resultArrayList) {
            result.getRectF().left *= scaleX;
            result.getRectF().right *= scaleX;
            result.getRectF().top = result.getRectF().top * scaleY - (diffY / 2f);
            result.getRectF().bottom = result.getRectF().bottom * scaleY - (diffY / 2f);
        }
        return resultArrayList;
    }

    //초기화
    public void clear() {
        zeroMap.clear();
        oneMap.clear();
        twoMap.clear();
        threeMap.clear();
        fourMap.clear();
        fiveMap.clear();
        sixMap.clear();
        sevenMap.clear();
        eightMap.clear();
        nineMap.clear();
    }

    public void resultToList(ArrayList<Result> results) {
        for (Result result : results) {
            if (result.getLabel() == 0) {
                zeroMap.put(result.getRectF(), labels[0] + ", " + Math.round(result.getScore() * 100) + "%");
            } else if (result.getLabel() == 1){
                oneMap.put(result.getRectF(), labels[1] + ", " + Math.round(result.getScore() * 100) + "%");
            } else if (result.getLabel() == 2){
                twoMap.put(result.getRectF(), labels[2] + ", " + Math.round(result.getScore() * 100) + "%");
            } else if (result.getLabel() == 3){
                threeMap.put(result.getRectF(), labels[3] + ", " + Math.round(result.getScore() * 100) + "%");
            } else if (result.getLabel() == 4){
                fourMap.put(result.getRectF(), labels[4] + ", " + Math.round(result.getScore() * 100) + "%");
            } else if (result.getLabel() == 5){
                fiveMap.put(result.getRectF(), labels[5] + ", " + Math.round(result.getScore() * 100) + "%");
            } else if (result.getLabel() == 6){
                sixMap.put(result.getRectF(), labels[6] + ", " + Math.round(result.getScore() * 100) + "%");
            } else if (result.getLabel() == 7){
                sevenMap.put(result.getRectF(), labels[7] + ", " + Math.round(result.getScore() * 100) + "%");
            } else if (result.getLabel() == 8){
                eightMap.put(result.getRectF(), labels[8] + ", " + Math.round(result.getScore() * 100) + "%");
            } else{
                nineMap.put(result.getRectF(), labels[9] + ", " + Math.round(result.getScore() * 100) + "%");
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Map.Entry<RectF, String> zero : zeroMap.entrySet()) {
            canvas.drawRect(zero.getKey(), zeroPaint);
            canvas.drawText(zero.getValue(), zero.getKey().left + 10.0f, zero.getKey().top + 60.0f, textPaint);
        }
        for (Map.Entry<RectF, String> one : oneMap.entrySet()) {
            canvas.drawRect(one.getKey(), onePaint);
            canvas.drawText(one.getValue(), one.getKey().left + 10.0f, one.getKey().top + 60.0f, textPaint);
        }
        for (Map.Entry<RectF, String> two : twoMap.entrySet()) {
            canvas.drawRect(two.getKey(), twoPaint);
            canvas.drawText(two.getValue(), two.getKey().left + 10.0f, two.getKey().top + 60.0f, textPaint);
        }
        for (Map.Entry<RectF, String> three : threeMap.entrySet()) {
            canvas.drawRect(three.getKey(), threePaint);
            canvas.drawText(three.getValue(), three.getKey().left + 10.0f, three.getKey().top + 60.0f, textPaint);
        }
        for (Map.Entry<RectF, String> four : fourMap.entrySet()) {
            canvas.drawRect(four.getKey(), fourPaint);
            canvas.drawText(four.getValue(), four.getKey().left + 10.0f, four.getKey().top + 60.0f, textPaint);
        }
        for (Map.Entry<RectF, String> five : fiveMap.entrySet()) {
            canvas.drawRect(five.getKey(), fivePaint);
            canvas.drawText(five.getValue(), five.getKey().left + 10.0f, five.getKey().top + 60.0f, textPaint);
        }
        for (Map.Entry<RectF, String> six : sixMap.entrySet()) {
            canvas.drawRect(six.getKey(), sixPaint);
            canvas.drawText(six.getValue(), six.getKey().left + 10.0f, six.getKey().top + 60.0f, textPaint);
        }
        for (Map.Entry<RectF, String> seven : sevenMap.entrySet()) {
            canvas.drawRect(seven.getKey(), sevenPaint);
            canvas.drawText(seven.getValue(), seven.getKey().left + 10.0f, seven.getKey().top + 60.0f, textPaint);
        }
        for (Map.Entry<RectF, String> eight : eightMap.entrySet()) {
            canvas.drawRect(eight.getKey(), eightPaint);
            canvas.drawText(eight.getValue(), eight.getKey().left + 10.0f, eight.getKey().top + 60.0f, textPaint);
        }
        for (Map.Entry<RectF, String> nine : nineMap.entrySet()) {
            canvas.drawRect(nine.getKey(), ninePaint);
            canvas.drawText(nine.getValue(), nine.getKey().left + 10.0f, nine.getKey().top + 60.0f, textPaint);
        }
        super.onDraw(canvas);
    }
}
