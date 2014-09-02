package ru.korvin.dominion.mechanic.baseObject.generator;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "creatures")
public class CreaterList {
    @ElementList(inline = true, name = "Per")
    List<Par> pars;
}
