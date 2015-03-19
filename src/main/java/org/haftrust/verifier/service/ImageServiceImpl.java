package org.haftrust.verifier.service;

import org.haftrust.verifier.dao.ImageDAO;
import org.haftrust.verifier.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Miroslav
 */
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageDAO imageDao;

    @Autowired
    public ImageServiceImpl(ImageDAO imageDao) {
        this.imageDao = imageDao;
    }

    public Image find(int imageId) {
        return this.imageDao.findOne(imageId);
    }

}
