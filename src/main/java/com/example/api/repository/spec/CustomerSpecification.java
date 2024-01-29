package com.example.api.repository.spec;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class CustomerSpecification {

    public static Specification<Customer> name(String name) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Customer> email(String email) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<Customer> gender(String gender) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("gender"), gender);
    }

    public static Specification<Customer> city(String city) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Join<Address, Customer> addressCustomerJoin = root.join("addresses");
            return criteriaBuilder.equal(addressCustomerJoin.get("localidade"), city);
        };
    }

    public static Specification<Customer> uf(String state) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Join<Address, Customer> addressCustomerJoin = root.join("addresses");
            return criteriaBuilder.equal(addressCustomerJoin.get("uf"), state);
        };
    }

}
