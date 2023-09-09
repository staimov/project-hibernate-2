package org.staimov.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.staimov.entity.Inventory;

import java.util.List;

public class InventoryDaoImpl extends GenericDaoImpl<Inventory, Integer> implements InventoryDao {
    public InventoryDaoImpl(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }

    @Override
    public List<Inventory> getAvailableInventoryItemsForRent() {

        // Здесь нам надо получить элементы из инвентаря, которые ни разу не отдавали на прокат,
        // или выдавали на прокат, но в данный момент вернули
        // (т.е. количество событий выдачи равно количеству событий возврата)

        String sql = """
                /* запрос 1: элементы из инвентаря, которые отдавали на прокат, но в данный момент вернули */
                with returned_items as (
                    select r.inventory_id from rental r
                    group by r.inventory_id
                    having count(r.rental_date) = count(r.return_date))
                select i.* from inventory i
                    right join returned_items ri on ri.inventory_id = i.inventory_id
                union
                /* запрос 2: элементы из инвентаря, которые ни разу не отдавали на прокат */
                select i.* from inventory i
                    where i.inventory_id not in (select distinct r.inventory_id from rental r)
                order by inventory_id;
                """;
        Query<Inventory> query = getCurrentSession().createNativeQuery(sql, Inventory.class);
        return query.list();
    }
}
