/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MCCars;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Owner
 */
public class CarHelper {
    Session session = null;
    private List ProductList;

    public CarHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public List getProduct(int startID, int endID)
    {
     List<Product> productList = null; 
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from Product as product where product.productId between '"+startID+"' and '"+endID+"'");
        productList = (List<Product>) q.list();
    } catch (Exception e) {
    }
      
    return ProductList;
    }
    
    public List getCustomerByID(int ProductId){
    List<Customer> customerList = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from Customer as customer where customer.customerId in (select productCustomer.customer.customerId from ProductCustomer as productCustomer where productCustomer.product.productId='" + ProductId + "')");
        customerList = (List<Customer>) q.list();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return customerList;
    }
    
    public Manufacturer getManufacturerByID(int ProductId){
    List<Manufacturer> manufacturerList = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Manufacturer as manufacturer where manufacturer.manufacturerId in (select productManufacturer.manufacturer.manufacturerId from ProductManufacturer as productManufacturer where productManufacturer.product.productId='" + ProductId + "')");
        manufacturerList = (List<Manufacturer>) q.list();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return manufacturerList.get(0);
    }
    
    public Product getProductByID(int ProductId){

    Product product = null;

    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Product as product where product.productId=" + ProductId);
        product = (Product) q.uniqueResult();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return product;
    }
    
    public String getProductCodeByID(int ProductCodeId){

    ProductCode productCode = null;

    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from ProductCode as productCode where productCode.ProductCodeId=" + ProductCodeId);
        ProductCode ProductCode = (ProductCode) q.uniqueResult();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return productCode.getName();
}

}
