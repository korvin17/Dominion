package ru.korvin.dominion.mechanic.server;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ru.korvin.dominion.mechanic.baseObject.castle.room.complex.dungeon.Group;
import ru.korvin.dominion.mechanic.baseObject.creature.Creature;
import ru.korvin.dominion.mechanic.server.event.EventDiff;
import ru.korvin.dominion.mechanic.server.event.EventType;

public class GameUtil {
    public static final Random random = new Random();

    public static int dice1D4() {
        return random.nextInt(4);
    }


    public static Point selectTargetInRow(Group targets, int indexRow) {
        if (targets.getCreatures().size() < indexRow)
            return null;
        List<Creature> row = targets.getCreatures().get(indexRow);
        List<Integer> indexs = new ArrayList<>(row.size());
        for (int i = 0; i < row.size(); i++) {
            if (!row.get(i).isDied())
                indexs.add(i);
        }
        if (indexs.size() == 0)
            return null;
        return new Point(indexRow, indexs.get(GameUtil.random.nextInt(indexs.size())));
    }

    public static Point selectTargetinRows(Group targets, int deep) {
        List<Point> points = new LinkedList<>();
        int indexRow = 0;
        for (int i = 0; (i < deep && indexRow < targets.getCreatures().size()); indexRow++) {
            Point point = selectTargetInRow(targets, indexRow);
            if (point != null) {
                points.add(point);
                i++;
            }
        }
        if (points.size() == 0)
            return null;
        else
            return points.get(random.nextInt(points.size()));
    }

    public static int nextLevel(int level, boolean win) {
        int diff = random.nextInt(3) - 1;
        level += win ? diff : -diff;
        return level;
    }

    public static EventDiff reward(int level) {
        long money = (long) (GameConst.DUNGEON_REWARD_MIN * Math.pow(level, GameConst.DUNGEON_REWARD_POW_FACTOR));
        EventDiff diff = new EventDiff(EventType.DUNGEON, money);
        return diff;
    }
}
