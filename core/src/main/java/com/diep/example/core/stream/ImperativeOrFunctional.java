package com.diep.example.core.stream;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**

 */
public class ImperativeOrFunctional {
    public static void main(String[] args) {
        Buyer b1 = new Buyer(69, "turtle");
        Buyer b2 = new Buyer(71, "dragon");
        Buyer b3 = new Buyer(2, "worm");
        Seller s1 = new Seller(10, "whale");
        Seller s2 = new Seller(50, "octopus");
        Seller s3 = new Seller(71, "shark");

        List<Transaction> transactions = Lists.newArrayList(
                new Transaction(b1, s1), new Transaction(b1, s2), new Transaction(b1, s3),
                new Transaction(b2, s1), new Transaction(b2, s2), new Transaction(b2, s3),
                new Transaction(b3, s1), new Transaction(b3, s2), new Transaction(b3, s3)
        );
        imperativeGetSellerAbove65InSortedName(transactions);
        functionalGetSellerAbove65InSortedName(transactions);
    }

    private static void imperativeGetSellerAbove65InSortedName(Collection<Transaction> transactions) {
        Set<Seller> sellers = new HashSet<>();
        for (Transaction t : transactions) {
            if (t.getBuyer().getAge() >= 65)
                sellers.add(t.getSeller());
        }
        List<Seller> sorted = new ArrayList<>(sellers);
        Collections.sort(sorted, (a, b) -> a.getName().compareTo(b.getName()));
        for (Seller s : sorted)
            System.out.println(s.getName());
    }

    private static void functionalGetSellerAbove65InSortedName(Collection<Transaction> transactions) {
        transactions.stream()
                .filter(t -> t.getBuyer().getAge() >=65 )
                .map(Transaction::getSeller)
                .distinct()
                .sorted(Comparator.comparing(Seller::getName))
                .forEach(s -> System.out.println(s.getName()));
    }

    @Getter @AllArgsConstructor
    static class Transaction {
        Buyer buyer;
        Seller seller;

    }

    static class Seller extends Person{
        Seller(int age, String name){
            super(age, name);
        }
    }
    static class Buyer extends Person{
        Buyer(int age, String name){
            super(age, name);
        }
    }

    @AllArgsConstructor
    static class Person {
        @Getter int age;
        @Getter String name;
    }
}
