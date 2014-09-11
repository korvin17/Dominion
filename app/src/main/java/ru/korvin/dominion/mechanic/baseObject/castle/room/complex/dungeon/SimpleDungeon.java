package ru.korvin.dominion.mechanic.baseObject.castle.room.complex.dungeon;

import ru.korvin.dominion.mechanic.baseObject.castle.room.LocationType;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;

public class SimpleDungeon extends Dungeon {
    private int name_id;
    private int image_id;

    public SimpleDungeon(int id, LocationType type, int level, int name_id, int image_id) {
        super(id, type, level);
        this.name_id = name_id;
        this.image_id = image_id;
    }

    public void init(Person person) {

    }


    @Override
    public int getNameId() {
        return name_id;
    }

    @Override
    public int getImageId() {
        return image_id;
    }
}
