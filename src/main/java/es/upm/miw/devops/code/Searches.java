package es.upm.miw.devops.code;

import java.util.Objects;

public class Searches {

    public Fraction findFirstFractionSubtractionByUserName(String name) {
        return new UsersDatabase().findAll()
                .filter(user -> name.equals(user.getName()))
                .flatMap(user -> user.getFractions().stream())
                .filter(Objects::nonNull)
                .filter(fraction -> fraction.getNumerator() * fraction.getDenominator() < 0)
                .findFirst()
                .orElse(null);
    }

}
