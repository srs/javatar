package com.moowork.javatar;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface AvatarGenerator
{
    public BufferedImage generate( final String seed, final int size )
        throws IOException;
}
