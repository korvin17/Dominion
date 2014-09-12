package ru.korvin.dominion.mechanic.baseObject.castle.room.complex.dungeon;

import java.util.ArrayList;
import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.castle.room.LocationType;
import ru.korvin.dominion.mechanic.baseObject.creature.Creature;
import ru.korvin.dominion.mechanic.server.GameConst;

public class Dungeon {
    private final LocationType locationType;
    private final int id;
    protected int level;

    public Dungeon(int id, LocationType type, int level) {
        this.level = level;
        this.locationType = type;
        this.id = id;

    }

    private Group adventure;
    private Group mob;


    public void init(List<Creature> adventureClose, List<Creature> adventureMid, List<Creature> adventureRange, List<Creature> mobs) {
        List<List<Creature>> mobList = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (Creature mob : mobs) {
            if (y >= GameConst.MAX_MOB_IN_ROW) {
                x++;
                y = 0;
            }
            if (mobList.size() == x) {
                mobList.add(new ArrayList<Creature>());
            }
            mobList.get(x).add(mob);
        }
        initCustom(adventureClose, adventureMid, adventureRange, mobList);
    }

    public void initCustom(List<Creature> adventureClose, List<Creature> adventureMid, List<Creature> adventureRange, List<List<Creature>> mob) {
        this.adventure = new Group(adventureClose, adventureMid, adventureRange);
        this.mob = new Group(mob);
    }

    public void SelectTarget() {
        adventure.selectNextTarget(mob);
        mob.selectNextTarget(adventure);
    }

    public boolean fate() {
        adventure.fate();
        mob.fate();
        return adventure.isAlive() && mob.isAlive();
    }

}
