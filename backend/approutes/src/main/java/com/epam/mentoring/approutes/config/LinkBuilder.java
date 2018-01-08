package com.epam.mentoring.approutes.config;

import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.view.ProductIncomeView;
import com.epam.mentoring.data.model.dto.view.ProductTypeView;
import com.epam.mentoring.data.model.dto.view.ProductView;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public class LinkBuilder {

    @Value("${host.url}")
    private String hostUrl;

    @Value("${product.path}")
    private String productPath;

    @Value("${productType.path}")
    private String productTypePath;

    @Value("${productIncome.path}")
    private String productIncomePath;

    public Map<String, Object> getLinks(Object item) {

        if (item instanceof ProductView) {
            ProductView productView = (ProductView) item;
            return getLinksForProduct(productView);
        } else if (item instanceof ProductTypeView) {
            ProductTypeView productTypeView = (ProductTypeView) item;
            return getLinksForProductType(productTypeView);
        } else if (item instanceof ProductIncomeView) {
            ProductIncomeView productIncomeView = (ProductIncomeView) item;
            return getLinksForProductIncome(productIncomeView);
        }
        return null;
    }

    private Map<String,Object> getLinksForProductIncome(ProductIncomeView productIncomeView) {
        HashMap<String, Object> linksMap = new HashMap<>();
        String path = getProductIncomePath(productIncomeView.getId());

        HashMap<String, String> selfLinksMap = new HashMap<>();
        selfLinksMap.put("href", path);
        linksMap.put("self", selfLinksMap);

        HashMap<String, String> supplierLinksMap = new HashMap<>();
        supplierLinksMap.put("href", path + "/supplier");
        linksMap.put("supplier", supplierLinksMap);

        HashMap<String, String> productLinksMap = new HashMap<>();
        productLinksMap.put("href", path + "/product");
        linksMap.put("product", productLinksMap);

        HashMap<String, String> userLinksMap = new HashMap<>();
        userLinksMap.put("href", path + "/user");
        linksMap.put("user", userLinksMap);

        return linksMap;
    }

    public Map<String,Object> getLinksForProduct(ProductView productView) {
        HashMap<String, Object> linksMap = new HashMap<>();
        String path = getProductPath(productView.getId());

        HashMap<String, String> selfLinksMap = new HashMap<>();
        selfLinksMap.put("href", path);
        linksMap.put("self", selfLinksMap);

        HashMap<String, String> productTypeLinksMap = new HashMap<>();
        productTypeLinksMap.put("href", path + "/type");
        linksMap.put("productType", productTypeLinksMap);

        HashMap<String, String> productIncomesLinksMap = new HashMap<>();
        productIncomesLinksMap.put("href", path + "/incomes");
        linksMap.put("productIncomes", productIncomesLinksMap);

        HashMap<String, String> productQuantitesLinksMap = new HashMap<>();
        productQuantitesLinksMap.put("href", path + "/quantity");
        linksMap.put("quantity", productQuantitesLinksMap);

        return linksMap;
    }

    public Map<String,Object> getLinksForProductType(ProductTypeView productTypeView) {
        HashMap<String, Object> linksMap = new HashMap<>();
        String path = getProductTypePath(productTypeView.getId());

        HashMap<String, String> selfLinksMap = new HashMap<>();
        selfLinksMap.put("href", path);
        linksMap.put("self", selfLinksMap);

        HashMap<String, String> productTypeLinksMap = new HashMap<>();
        productTypeLinksMap.put("href", path + "/products");
        linksMap.put("products", productTypeLinksMap);

        return linksMap;
    }

    private String getProductIncomePath(Integer id) {
        StringBuilder uri = new StringBuilder();
        uri.append(hostUrl);
        uri.append(productIncomePath);
        uri.append("/");
        uri.append(id);
        return uri.toString();
    }

    public String getProductPath(Integer id) {
        StringBuilder uri = new StringBuilder();
        uri.append(hostUrl);
        uri.append(productPath);
        uri.append("/");
        uri.append(id);
        return uri.toString();
    }

    public String getProductTypePath(Integer id) {
        StringBuilder uri = new StringBuilder();
        uri.append(hostUrl);
        uri.append(productTypePath);
        uri.append("/");
        uri.append(id);
        return uri.toString();
    }
}
