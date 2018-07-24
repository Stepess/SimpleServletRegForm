package ua.training.db;

import ua.training.model.NotUniqueValueException;
import ua.training.model.Record;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class NoteBook {

    private static final NoteBook INSTANCE = new NoteBook();
    private static int count = 0;
    private ConcurrentMap<Integer,Record> records;

    private NoteBook(){
        records = new ConcurrentHashMap<>();
    }

    public static NoteBook getInstance() {
        return INSTANCE;
    }

    public ConcurrentMap<Integer, Record> getRecords() {
        return records;
    }

    public void addRecord(Record record) throws NotUniqueValueException {
        if (records.containsValue(record))
            throw new NotUniqueValueException();
        records.put(record.getId(), record);
        count++;
    }

    public boolean isLoginUnique(String login){
        for (Record rec: records.values())
            if (rec.getLogin().equals(login))
                return false;
        return true;
    }

    public boolean isEmailUnique(String email){
        for (Record rec: records.values())
            if (rec.getEmail().equals(email))
                return false;
        return true;
    }

    public int getCount(){
        return count;
    }

}
