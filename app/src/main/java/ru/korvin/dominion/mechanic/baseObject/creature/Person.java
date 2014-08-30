package ru.korvin.dominion.mechanic.baseObject.creature;


import java.io.Serializable;

import ru.korvin.dominion.mechanic.baseObject.skill.SkillList;
import ru.korvin.dominion.mechanic.baseObject.state.progress.Progress;

public class Person extends Creature implements Serializable {

    public Person() {
        this.skillList = new SkillList();
    }

    public Progress Rest() {
        return null;
    }


}
