package ru.korvin.dominion.mechanic.baseObject.creature;

public class Ability {
    public int hp;
    public int maxHp;

    public int mp;
    public int maxMp;

    public int energy;
    public int maxEnergy;

    public int basic_strength;//(STR) – Сила
    public int basic_dexterity;//(DEX) – Ловкость
    public int basic_constitution;//(CON) – Телосложение
    public int basic_intelligence;//(INT) – Интеллект
    public int basic_wisdom;// (WIS) – Мудрость
    public int basic_charisma;//(CHA) – Харизма

    public Ability(int maxHp, int maxMp, int maxEnergy, int basic_strength, int basic_dexterity, int basic_constitution, int basic_intelligence, int basic_wisdom, int basic_charisma) {
        this.maxHp = maxHp;
        this.maxMp = maxMp;
        this.maxEnergy = maxEnergy;
        this.basic_strength = basic_strength;
        this.basic_dexterity = basic_dexterity;
        this.basic_constitution = basic_constitution;
        this.basic_intelligence = basic_intelligence;
        this.basic_wisdom = basic_wisdom;
        this.basic_charisma = basic_charisma;

        this.hp = this.maxHp;
        this.mp = this.maxMp;
        this.energy = this.maxEnergy;
    }
}
