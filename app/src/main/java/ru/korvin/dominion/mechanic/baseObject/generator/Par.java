package ru.korvin.dominion.mechanic.baseObject.generator;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "per")
public class Par {
    @Attribute(name = "name")
    public String name;
}
