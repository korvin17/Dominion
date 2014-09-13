package ru.korvin.dominion.mechanic.baseObject.generator;

import java.util.ArrayList;
import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.castle.room.complex.dungeon.Dungeon;
import ru.korvin.dominion.mechanic.baseObject.creature.Creature;
import ru.korvin.dominion.mechanic.server.GameUtil;

public class DungeonGenerator {

    public static int incrementId =0;
    public static Dungeon generateDungeon(int level){
        int id=incrementId++;
        Dungeon dungeon=ratDungeon(id,level);
        return dungeon;
    }



    public static Dungeon ratDungeon(int id,int level) {
        Dungeon dungeon = new Dungeon(id, level);

        List<List<Creature>> mobs = new ArrayList<>();
        for (int j = 0; j < 2 * GameUtil.random.nextInt(level); j++) {
            mobs.add(new ArrayList<Creature>());
            for (int i = 0; i < 3; i++) {
                mobs.get(mobs.size()-1).add(CreatureGenerator.getRat(level));
            }
        }

        return dungeon;
    }

}
