package ru.korvin.dominion.mechanic.baseObject.castle.room.complex.dungeon;

import ru.korvin.dominion.mechanic.baseObject.castle.room.LocationType;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.castle.room.RoomProgress;
import ru.korvin.dominion.mechanic.baseObject.generator.Generator;
import ru.korvin.dominion.mechanic.server.GameUtil;
import ru.korvin.dominion.mechanic.server.Server;
import ru.korvin.dominion.mechanic.server.event.EventDiff;

public class DungeonRoom extends Room<DungeonProgress> {
    private int name_id;
    private int image_id;

    protected int level;
    private Dungeon dungeon;

    public DungeonRoom(Generator generator) {
        super(generator.DUNGEON_ROOM_ID, LocationType.workRoom);
    }

    @Override
    public RoomProgress doStep(RoomProgress progress) {
        return super.doStep(progress);
    }

    protected void win(RoomProgress progress) {
        level += GameUtil.nextLevel(level, true);
        EventDiff diff = GameUtil.reward(level);
        Server.getDefault().getPlayer().applyDiff(diff);
    }

    protected void lost(RoomProgress progress) {
        level += GameUtil.nextLevel(level, false);
    }

    @Override
    public int getNameId() {
        return name_id;
    }

    @Override
    public int getImageId() {
        return image_id;
    }

    @Override
    protected DungeonProgress getNewProgress() {
        return new DungeonProgress(getPersons().size());
    }
}
