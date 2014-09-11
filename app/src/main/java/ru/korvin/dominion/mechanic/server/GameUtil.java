package ru.korvin.dominion.mechanic.server;

import android.graphics.Point;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ru.korvin.dominion.mechanic.baseObject.creature.Creature;

public class GameUtil {
    public static final Random random = new Random();

    public static int dice1D4() {
        return random.nextInt(4);
    }


    public static Point selectTargetInRow(List<List<Creature>> targets, int indexRow) {
        if (targets.size() < indexRow)
            return null;
        List<Creature> row = targets.get(indexRow);
        if (row.size() == 0)
            return null;
        return new Point(indexRow, GameUtil.random.nextInt(row.size()));
    }

    public static Point selectTargetinRows(List<List<Creature>> targets, int indexRowFrom, int indexRowTo) {
        List<Point> points = new LinkedList<>();
        for (int i = indexRowFrom; i < indexRowTo; i++) {
            Point point = selectTargetInRow(targets, i);
            if (point != null)
                points.add(point);
        }
        if (points.size() == 0)
            return null;
        else
            return points.get(random.nextInt(points.size()));


    }
}
