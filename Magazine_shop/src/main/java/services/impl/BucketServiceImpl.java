package services.impl;

import dao.BucketDao;
import dao.impl.BucketDaoImpl;
import domain.Bucket;
import org.apache.log4j.Logger;
import services.BucketService;

import java.sql.SQLException;
import java.util.List;

public class BucketServiceImpl implements BucketService {
    private BucketDao bucketDao;
    private static BucketService bucketServiceImpl;
    private static Logger logger = Logger.getLogger(BucketServiceImpl.class);

    public BucketServiceImpl() {
        try {
            bucketDao = new BucketDaoImpl();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
        }
    }

    public static BucketService getBucketService() {
        if(bucketServiceImpl == null) {
            bucketServiceImpl = new BucketServiceImpl();
        }
        return bucketServiceImpl;
    }

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket read(Integer id) {
        return bucketDao.read(id);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public void delete(Integer id) {
        bucketDao.delete(id);
    }

    @Override
    public List<Bucket> readAll() {
        return bucketDao.readAll();
    }
}
