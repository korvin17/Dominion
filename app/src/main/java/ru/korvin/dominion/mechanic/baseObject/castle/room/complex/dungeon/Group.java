package ru.korvin.dominion.mechanic.baseObject.castle.room.complex.dungeon;

import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.creature.Creature;

public class Group {
    private List<List<Creature>> creatures;//сначала идет номер по горизонтале потом по вертикале. Разделение по мобам горизонтально

    public void selectNextTarget(List<List<Creature>> targets) {
        for (int i = 0; i < creatures.size(); i++) {
            List<Creature> col = creatures.get(i);
            for (Creature creature : col) {
                creature.selectNextTarget(targets, creatures.size() - i);
            }
        }
    }

}
