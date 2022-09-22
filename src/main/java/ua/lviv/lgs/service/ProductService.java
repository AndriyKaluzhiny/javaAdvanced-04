package ua.lviv.lgs.service;

import ua.lviv.lgs.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> productList = new ArrayList<>();
    private static ProductService productService;

    private ProductService() {
    }

    public static ProductService getProductService() {
        if (productService==null) {
            productService = new ProductService();
        }

        return productService;
    }

    public List<Product> getList() {
        return productList;
    }

    public void saveProduct(Product product) {
        productList.add(product);
    }

    public Product getProduct(String name) {
        return productList.stream().filter(x->x.getName().equals(name)).findFirst().get();
    }
}
