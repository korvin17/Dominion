package ru.korvin.dominion.mechanic.server.event;

import ru.korvin.dominion.mechanic.baseObject.creature.skill.SkillDiff;
import ru.korvin.dominion.mechanic.baseObject.creature.stats.StatsDiff;
import ru.korvin.dominion.mechanic.baseObject.item.ItemDiff;

//TODO перефигачить этот класс в EVENT а тот в другое проиществие
/*Класс хранящий диф по разным событияям*/
public class EventDiff {
    public EventType type;
    public long money;
    public StatsDiff statsDiff;
    public SkillDiff skillDiff;
    public ItemDiff itemDiff;

    public EventDiff(EventType type, long money, StatsDiff statsDiff) {
        this.money = money;
        this.type = type;
        this.statsDiff = statsDiff;
    }

    public EventDiff(EventType type, long money, SkillDiff skillDiff) {
        this.type = type;
        this.money = money;
        this.skillDiff = skillDiff;
    }

    public EventDiff(EventType type, long money) {
    }

    public void add(EventDiff eventDiff) {
        this.money += eventDiff.money;
        if (this.statsDiff == null)
            this.statsDiff = eventDiff.statsDiff;
        else {
            this.statsDiff.add(statsDiff);
        }
        if (this.skillDiff == null)
            this.skillDiff = eventDiff.skillDiff;
        else {
            this.skillDiff.add(skillDiff);
        }
    }
}
