package com.doorknock.features.repository.user;

import com.doorknock.features.common.enums.VolunteerRoles;
import com.doorknock.features.model.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserRepositoryImpl(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        return jpaRepository.save(user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    @Override
    public Page<User> findAllByRole(VolunteerRoles role, Pageable pageable) {
        return jpaRepository.findByRole(role, pageable);
    }

    @Override
    public Optional<User> findUserByEmail(String userEmail){
        return jpaRepository.findByEmail(userEmail);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<User> findAllOrderByDoorKnocked(
            VolunteerRoles role,
            Sort.Direction direction,
            Pageable pageable
    ) {
        String roleFilter = role != null ? "WHERE u.role = :role" : "";
        String orderDirection = direction == Sort.Direction.DESC ? "DESC" : "ASC";

        String countSql = "SELECT COUNT(*) FROM users u " + roleFilter;
        String dataSql = """
                SELECT u.* FROM users u
                %s
                ORDER BY (SELECT COUNT(*) FROM visits v WHERE v.user_id = u.user_id) %s, u.name ASC
                """.formatted(roleFilter, orderDirection);

        Query countQuery = entityManager.createNativeQuery(countSql);
        if (role != null) {
            countQuery.setParameter("role", role.name());
        }
        long total = ((Number) countQuery.getSingleResult()).longValue();

        Query dataQuery = entityManager.createNativeQuery(dataSql, User.class);
        if (role != null) {
            dataQuery.setParameter("role", role.name());
        }
        dataQuery.setFirstResult((int) pageable.getOffset());
        dataQuery.setMaxResults(pageable.getPageSize());

        List<User> users = dataQuery.getResultList();
        return new PageImpl<>(users, pageable, total);
    }

    @Override
    public void delete(User user) {
        jpaRepository.delete(user);
    }
}
