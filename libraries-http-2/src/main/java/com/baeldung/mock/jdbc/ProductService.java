package com.baeldung.mock.jdbc;

public class ProductService {
    ProductInfoDAO productInfoDAO;
    ExternalAPIService externalAPIService;
    public ProductService(ProductInfoDAO productInfoDAO, ExternalAPIService externalAPIService){
        this.productInfoDAO = productInfoDAO;
        this.externalAPIService = externalAPIService;
    }

    public boolean saveNewProduct(String name, String description, double price, String upc) throws ProductServiceException{
        if(!externalAPIService.isUPCValidInAustralia(upc)){
            throw new ProductServiceException("UPC is not valid in Australian Markets");
        }
        return productInfoDAO.createProduct(name, description, price, upc);
    }
}
