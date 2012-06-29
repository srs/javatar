package com.moowork.javatar;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;

public abstract class HashAvatarGenerator
    implements AvatarGenerator
{
    private final HashFunction hashFunction;

    public HashAvatarGenerator( final HashFunction hashFunction )
    {
        this.hashFunction = hashFunction;
    }

    @Override
    public final BufferedImage generate( final String seed, final int size )
        throws IOException
    {
        final HashCode hashCode = this.hashFunction.hashString( seed );
        return generate( hashCode, size );
    }

    protected abstract BufferedImage generate( HashCode hash, int size )
        throws IOException;
}
