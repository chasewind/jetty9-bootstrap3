package com.belief.springtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PetServiceImpl implements PetService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void init() {
        logger.info("......collaborators and configuration...... ");
    }

}
