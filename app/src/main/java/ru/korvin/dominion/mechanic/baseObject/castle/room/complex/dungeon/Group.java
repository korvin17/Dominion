package ru.korvin.dominion.mechanic.baseObject.castle.room.complex.dungeon;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.creature.Creature;

public class Group {
    private List<List<Creature>> creatures;//сначала идет номер по горизонтале потом по вертикале. Разделение по мобам горизонтально
    private Group targets;

    public Group(List<List<Creature>> creatures) {
        this.creatures = creatures;
    }

    public Group(List<Creature>... creatures) {
        this.creatures = new ArrayList<>(creatures.length);
        for (int i = 0; i < creatures.length; i++) {
            this.creatures.add(creatures[i]);
        }
    }

    public void selectNextTarget(Group targets) {
        this.targets = targets;
        for (int i = 0; i < creatures.size(); i++) {
            List<Creature> col = creatures.get(i);
            for (Creature creature : col) {
                creature.selectNextTarget(targets, i);
            }
        }
    }

    public List<List<Creature>> getCreatures() {
        return creatures;
    }

    public void fate() {
        for (List<Creature> row : creatures) {
            for (Creature creature : row) {
                creature.fate(this.targets);
            }
        }
    }

    public Creature get(Point nextTarget) {
        if (nextTarget == null)
            return null;
        if (creatures.size() <= nextTarget.x)
            return null;
        if (creatures.get(nextTarget.x).size() <= nextTarget.y)
            return null;
        return creatures.get(nextTarget.x).get(nextTarget.y);
    }

    public boolean isAlive() {
        boolean alive = false;
        for (List<Creature> row : creatures) {
            for (Creature creature : row) {
                if (!creature.isDied())
                    alive = true;
            }
        }

        return alive;
    }
}
