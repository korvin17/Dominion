package ru.korvin.dominion.mechanic.baseObject.castle.room.complex.dungeon.simple;

import java.util.LinkedList;
import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.castle.room.RoomProgress;
import ru.korvin.dominion.mechanic.baseObject.castle.room.complex.dungeon.DungeonProgress;
import ru.korvin.dominion.mechanic.baseObject.castle.room.complex.dungeon.DungeonRoom;
import ru.korvin.dominion.mechanic.baseObject.creature.Creature;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.creature.gameClass.Archetype;
import ru.korvin.dominion.mechanic.baseObject.generator.Generator;
import ru.korvin.dominion.mechanic.util.Util;

public abstract class simpleDungeon extends DungeonRoom {
    private Group adventure;
    private Group mob;

    public simpleDungeon(Generator generator) {
        super(generator);

    }

    @Override
    public RoomProgress doStepForEveryOnePerson(DungeonProgress currentRoomProgress) {
        initiate();
        while (adventure.isAlive() && mob.isAlive()) {
            int adDamage = adventure.getDamage();
            int adHeal = adventure.getHeal();

            int mobDamage = adventure.getDamage();
            int mobHeal = adventure.getHeal();

            adventure.doDamage(mobDamage);
            adventure.doHeal(adHeal);

            mob.doDamage(adDamage);
            mob.doHeal(mobHeal);
        }
        return super.doStepForEveryOnePerson(currentRoomProgress);
    }

    private static final int money_start = 100;
    private static final float money_factor = 1.5f;

    @Override
    protected void win(RoomProgress progress) {
        int money = (int) (money_start * Math.pow(money_factor, level));
        progress.addMoney(money);
    }

    private void initiate() {
        initiateAdventure();
        initiateMob();
    }

    private void initiateAdventure() {
        this.adventure = new Group();
        List<Creature> creatures = new LinkedList<Creature>();
        for (Person person : persons) {
            if (person.archetype == Archetype.tank)
                creatures.add(person);
        }
        this.adventure.tank = (Creature[]) creatures.toArray();
        creatures.clear();
        for (Person person : persons) {
            if (person.archetype == Archetype.dd)
                creatures.add(person);
        }
        this.adventure.dd = (Creature[]) creatures.toArray();
        creatures.clear();
        for (Person person : persons) {
            if (person.archetype == Archetype.healer)
                creatures.add(person);
        }
        this.adventure.healers = (Creature[]) creatures.toArray();

    }

    private void initiateMob() {
        this.mob = new Group();
        List<Creature> creaturesTank = new LinkedList<Creature>();
        List<Creature> creaturesDD = new LinkedList<Creature>();
        List<Creature> creaturesHealer = new LinkedList<Creature>();
        for (int i = 0; i < level * 5; i++) {
            switch (Util.random.nextInt(5)) {
                case 0:
                case 1:
                    //creaturesTank.add(new Rat());
                case 2:
                case 3:
                    // creaturesDD.add(new Rat());
                case 4:
                    // creaturesHealer.add(new Rat());
            }
        }
    }
}
