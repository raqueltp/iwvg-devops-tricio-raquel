package es.upm.miw.devops.code;

import java.util.Objects;
import java.util.stream.Stream;

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

    public Fraction findFirstFractionDivisionByUserId(String id) {
        return new UsersDatabase().findAll()
                .filter(user -> id.equals(user.getId()))
                .findFirst()
                .map(User::getFractions)
                .map(list -> list.stream()
                        .filter(Objects::nonNull)
                        .limit(2)
                        .collect(java.util.stream.Collectors.toList())
                )
                .filter(fs -> fs.size() == 2)  // necesitamos 2 fracciones
                .map(fs -> {
                    Fraction f1 = fs.get(0);
                    Fraction f2 = fs.get(1);
                    // Evitamos resultados inválidos de entrada (denominadores 0)
                    if (f1.getDenominator() == 0 || f2.getDenominator() == 0) {
                        return null;
                    }
                    try {
                        return f1.divide(f2);   // usa tu Fraction.divide (sin normalizar signos)
                    } catch (ArithmeticException e) {
                        // divide lanza si el numerador del divisor es 0
                        return null;
                    }
                })
                .orElse(null);
    }

    public Stream<String> findUserFamilyNameByAllNegativeSignFractionDistinct() {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream()
                        .filter(Objects::nonNull)
                        .allMatch(f -> f.getNumerator() * f.getDenominator() < 0)
                )
                .map(User::getFamilyName)
                .distinct();
    }

    public Stream<String> findUserFamilyNameInitialByAnyProperFraction() {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream()
                        .filter(Objects::nonNull)
                        .anyMatch(Fraction::isProper)
                )
                .map(user -> user.getFamilyName().substring(0, 1));
    }

}
