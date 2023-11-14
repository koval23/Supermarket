package ait.supermarket.dao;

import ait.supermarket.model.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class SupermarketImpl implements Supermarket {
    Collection<Product> products;

    public SupermarketImpl() {
        products = new ArrayList<>();
    }

    @Override
    public boolean addProduct(Product product) {
        if (products == null) {
            throw new RuntimeException("null");
        }
        if (products.contains(product)) {
            return false;
        }
        return products.add(product);
    }

    @Override
    public Product removeProduct(long barCode) {
        Product product = findByBarCode(barCode);
        if (products.contains(product)) {
            products.remove(product);
        }
        return product;
    }

    @Override
    public Product findByBarCode(long barCode) {
        for (Product p : products) {
            if (p.getBarCode() == barCode) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Iterable<Product> findByCategory(String category) {
        return findProductsByPredicate(product -> product.getCategory().equals(category));
    }

    @Override
    public Iterable<Product> findByBrand(String brand) {

        return findProductsByPredicate(product -> product.getBrand().equals(brand));
    }

    @Override
    public Iterable<Product> findProductWithExpiredDate() {
        LocalDate dataNow = LocalDate.now();
        return findProductsByPredicate(product -> product.getExpDate().isBefore(dataNow));
    }

    private List<Product> findProductsByPredicate(Predicate<Product> predicate) {

        List<Product> productsByPredicate = new ArrayList<>();
        for (Product product : products) {
            if (predicate.test(product)) {
                productsByPredicate.add(product);
            }
        }
        return productsByPredicate;


    }

    @Override
    public int skuQuantity() {
        return 0;
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
