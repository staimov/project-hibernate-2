package org.staimov.dao;

import org.staimov.entity.Film;
import org.staimov.entity.Inventory;

import java.util.List;

public interface InventoryDao extends GenericDao<Inventory, Integer> {
    List<Inventory> getAvailableInventoryItemsForRent();
}
