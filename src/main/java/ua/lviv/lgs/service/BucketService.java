package ua.lviv.lgs.service;

import ua.lviv.lgs.domain.Bucket;

import java.util.List;

public class BucketService {
    private List<Bucket> bucketList;
    private static BucketService bucketService;

    private BucketService() {
    }

    public static BucketService getBucketService() {
        if (bucketService == null) {
            bucketService = new BucketService();
        }

        return bucketService;
    }

    public List<Bucket> getList() {
        return bucketList;
    }

    public void saveBucket(Bucket bucket) {
        bucketList.add(bucket);
    }

    public Bucket getBucket(int id) {
        return bucketList.stream().filter(x->x.getProductId().equals(id)).findFirst().get();
    }
}
