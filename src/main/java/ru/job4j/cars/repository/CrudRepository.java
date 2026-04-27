package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class CrudRepository {
    private final SessionFactory sf;

    public <T> List<T> optional(String query, Class<T> cl, Map<String, Object> args) {
        var session = sf.openSession();
        var tx = session.beginTransaction();
        try {
            var sq = session.createQuery(query, cl);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sq.setParameter(arg.getKey(), arg.getValue());
            }
            List<T> result = sq.list();
            tx.commit();
            return result;
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
}
