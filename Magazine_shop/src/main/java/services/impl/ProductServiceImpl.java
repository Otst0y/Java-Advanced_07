package services.impl;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import domain.Product;
import org.apache.log4j.Logger;
import services.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    private static ProductService productServiceImpl;
    private static Logger logger = Logger.getLogger(ProductDaoImpl.class);

    public ProductServiceImpl() {
        try {
            productDao = new ProductDaoImpl();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
        }
    }

    public static ProductService getProductService() {
        if(productServiceImpl == null) {
            productServiceImpl = new ProductServiceImpl();
        }
        return productServiceImpl;
    }

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product read(Integer id) {
        return productDao.read(id);
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public void delete(Integer id) {
        productDao.delete(id);
    }

    @Override
    public List<Product> readAll() {
        return productDao.readAll();
    }
}
