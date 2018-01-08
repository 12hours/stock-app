package com.epam.mentoring.approutes.config;

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

    @Value(("${productType.path}"))
    private String productTypePath;

    public Map<String, Object> getLinks(Object item) {

        if (item instanceof ProductView) {
            ProductView productView = (ProductView) item;
            return getLinksForProduct(productView);
        } else if (item instanceof ProductTypeView) {
            ProductTypeView productTypeView = (ProductTypeView) item;
            return getLinksForProductType(productTypeView);
        }
        return null;
    }

    public Map<String,Object> getLinksForProduct(ProductView productView) {
        HashMap<String, Object> linksMap = new HashMap<>();
        String productPath = getProductPath(productView.getId());

        HashMap<String, String> selfLinksMap = new HashMap<>();
        selfLinksMap.put("href", productPath);
        linksMap.put("self", selfLinksMap);

        HashMap<String, String> productTypeLinksMap = new HashMap<>();
        productTypeLinksMap.put("href", productPath + "/type");
        linksMap.put("productType", productTypeLinksMap);

        HashMap<String, String> productIncomesLinksMap = new HashMap<>();
        productIncomesLinksMap.put("href", productPath + "/incomes");
        linksMap.put("productIncomes", productIncomesLinksMap);

        HashMap<String, String> productQuantitesLinksMap = new HashMap<>();
        productQuantitesLinksMap.put("href", productPath + "/quantity");
        linksMap.put("quantity", productQuantitesLinksMap);

        return linksMap;
    }

    public Map<String,Object> getLinksForProductType(ProductTypeView productTypeView) {
        HashMap<String, Object> linksMap = new HashMap<>();
        String productTypePath = getProductTypePath(productTypeView.getId());

        HashMap<String, String> selfLinksMap = new HashMap<>();
        selfLinksMap.put("href", productTypePath);
        linksMap.put("self", selfLinksMap);

        HashMap<String, String> productTypeLinksMap = new HashMap<>();
        productTypeLinksMap.put("href", productTypePath + "/products");
        linksMap.put("products", productTypeLinksMap);

        return linksMap;
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
