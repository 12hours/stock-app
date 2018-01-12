package com.epam.mentoring;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class TestJsonData {

    public static String productWithQuantitiesResponseJson = null;
    public static String productsJson = null;
    public static String productTypesJson = null;
    public static String suppliersJson = null;
    public static String productSavedJson = null;
    public static String productTypeSavedJson = null;
    public static String productIncomeSavedJson = null;
    public static String supplierSavedJson = null;


    static {
        try {
            productWithQuantitiesResponseJson = FileUtils.readFileToString(new ClassPathResource("productWithQuantities.json").getFile());
            productsJson = FileUtils.readFileToString(new ClassPathResource("products.json").getFile());
            productTypesJson = FileUtils.readFileToString(new ClassPathResource("productTypes.json").getFile());
            suppliersJson = FileUtils.readFileToString(new ClassPathResource("suppliers.json").getFile());

            productSavedJson = FileUtils.readFileToString(new ClassPathResource("productSaved.json").getFile());
            productTypeSavedJson = FileUtils.readFileToString(new ClassPathResource("productTypeSaved.json").getFile());
            productIncomeSavedJson = FileUtils.readFileToString(new ClassPathResource("productIncomeSaved.json").getFile());
            supplierSavedJson = FileUtils.readFileToString(new ClassPathResource("supplierSaved.json").getFile());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
