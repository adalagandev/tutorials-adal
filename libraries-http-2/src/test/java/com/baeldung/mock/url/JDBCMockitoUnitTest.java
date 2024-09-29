package com.baeldung.mock.url;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.baeldung.mock.jdbc.ExternalAPIService;
import com.baeldung.mock.jdbc.ProductInfo;
import com.baeldung.mock.jdbc.ProductInfoDAO;
import com.baeldung.mock.jdbc.ProductService;
import com.baeldung.mock.jdbc.ProductServiceException;

@RunWith(MockitoJUnitRunner.class)
public class JDBCMockitoUnitTest {

    @Mock
    private DataSource dataSource;


    @Mock
    private Connection conn;

    @Mock
    private PreparedStatement prepStatement;

    @Mock
    private ResultSet rs;

    @Mock
    ExternalAPIService externalAPIService;
    @Test
    public void testCreateProduct() throws ProductServiceException {
        Mockito.when(externalAPIService.isUPCValidInAustralia(anyString())).thenReturn(Boolean.TRUE);
        ProductService productService = new ProductService(new ProductInfoDAO(dataSource), externalAPIService);
        Assert.assertTrue(productService.saveNewProduct(
          "Bug Zap","Bug Repellant",10.0,"ABCXYZ"));
    }

    /**
     *  For example, we have a persistence logic that inserts 1000 records into a db and we do not want to manage all that under one db transaction.
     *  So add an extra logic to commit records in the db every 50 records, so that when an exception occurs during the entire process, we do not have to
     *  re-insert everything all over again.
     *
     *  zzzz
     *
     * ***/
    @Test
    public void testGetProductInfo() throws Exception {

        ProductInfoDAO prodInfoDAO = new ProductInfoDAO(dataSource);
//        Mockito.when( prodInfoDAO.getProductInfo(id)).thenReturn(new ProductInfo(id, "Google Pixel Hardcase", "Pixel Case Red", 40.0));
//        DriverManager mockedManager =  Mockito.mock(DriverManager.class);
//        Connection connectionMock = Mockito.mock(Connection.class);
//        connectionMock.setCatalog("Cat1");
//        ProductInfoDAO prodInfoDAOMock = Mockito.mock(ProductInfoDAO.class);
//        Mockito.when(prodInfoDAOMock.getDBConnection()).thenReturn(connectionMock);
////        Mockito.when(DriverManager.getConnection(null,null,null)).thenReturn(connection);
//        Connection conn = Mockito.mock(dataSource.getConnection());
//        Mockito.when(conn.prepareStatement(anyString()));
        Mockito.when(dataSource.getConnection()).thenReturn(conn);
        Mockito.when(conn.prepareStatement(anyString())).thenReturn(prepStatement);
        Mockito.when(prepStatement.executeQuery()).thenReturn(rs);
        Mockito.when(rs.first()).thenReturn(true);
        ProductInfo prodInfo = prodInfoDAO.getProductInfo(UUID.randomUUID());


        System.out.println(prodInfo);




    }



}
