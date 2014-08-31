package ru.korvin.dominion.dao.storage.shell;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import ru.korvin.dominion.mechanic.baseObject.state.State;

public class SaveRecord {
    private String name;
    private Date date;
    private State state;
    private long id = -1;

    public SaveRecord(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public SaveRecord(String name, Date date, State state) {
        this(name, date);
        this.state = state;
    }

    public SaveRecord(String name, Date date, byte[] blob, int id) {
        this(name, date);
        this.id = id;
        if (blob != null) {
            try {
                ByteArrayInputStream baip = new ByteArrayInputStream(blob);
                ObjectInputStream ois = new ObjectInputStream(baip);
                this.state = (State) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getData() {
        if (state == null) return null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(state);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getName() {

        return name;
    }

    public long getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }
}
