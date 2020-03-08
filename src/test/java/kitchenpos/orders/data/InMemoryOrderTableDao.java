package kitchenpos.orders.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import kitchenpos.orders.domain.entity.OrderTableDao;
import kitchenpos.orders.domain.entity.OrderTable;

public class InMemoryOrderTableDao implements OrderTableDao {

    private final Map<Long, OrderTable> entities = new HashMap<>();

    @Override
    public OrderTable save(final OrderTable entity) {
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<OrderTable> findById(final Long id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public List<OrderTable> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public List<OrderTable> findAllByIdIn(final List<Long> ids) {
        return entities.values()
            .stream()
            .filter(entity -> ids.contains(entity.getId()))
            .collect(Collectors.toList())
            ;
    }

    @Override
    public List<OrderTable> findAllByTableGroupId(final Long tableGroupId) {
        return entities.values()
            .stream()
            .filter(entity -> Objects.equals(entity.getTableGroupId(), tableGroupId))
            .collect(Collectors.toList())
            ;
    }
}