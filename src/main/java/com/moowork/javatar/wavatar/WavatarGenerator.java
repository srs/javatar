package com.moowork.javatar.wavatar;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.google.common.hash.HashCode;
import com.moowork.javatar.Md5AvatarGenerator;

public final class WavatarGenerator
    extends Md5AvatarGenerator
{
    private final static int IMG_SIZE = 80;

    private final BufferedImage stencilImage;

    public WavatarGenerator()
        throws IOException
    {
        this.stencilImage = loadImage( "wavatar.png" );
    }

    @Override
    protected BufferedImage generate( final HashCode hash, final int size )
        throws IOException
    {
        return generate( hash.asBytes() );
    }

    protected BufferedImage generate( final byte[] hash )
        throws IOException
    {
        final BufferedImage image = new BufferedImage( IMG_SIZE, IMG_SIZE, BufferedImage.TYPE_4BYTE_ABGR );
        final Graphics2D graphics = image.createGraphics();

        applyPart( graphics, 0, ( hash[0] & 0x8f ), 4 );
        applyPart( graphics, 1, ( hash[1] & 0x8f ), 11 );
        applyPart( graphics, 2, ( hash[2] & 0x8f ), 11 );
        applyPart( graphics, 3, ( hash[3] & 0x8f ), 8 );
        applyPart( graphics, 4, ( hash[4] & 0x8f ), 13 );
        applyPart( graphics, 5, ( hash[5] & 0x8f ), 11 );
        applyPart( graphics, 6, ( hash[6] & 0x8f ), 19 );

        // TODO: We need to colorize the wavatars.

        return image;
    }

    private void applyPart( final Graphics2D graphics, final int partRow, final int hash, final int maxNum )
        throws IOException
    {
        final int num = hash % maxNum;
        final BufferedImage overlay = getImagePart( partRow, num );
        graphics.drawImage( overlay, null, 0, 0 );
    }

    private BufferedImage getImagePart( final int row, final int column )
        throws IOException
    {
        return this.stencilImage.getSubimage( column * IMG_SIZE, row * IMG_SIZE, IMG_SIZE, IMG_SIZE );
    }

    private BufferedImage loadImage( final String name )
        throws IOException
    {
        final URL url = getClass().getResource( name );
        return ImageIO.read( url );
    }

    public static void main( String... args )
        throws Exception
    {
        ImageIO.write( new WavatarGenerator().generate( "dummy@company.com", 80 ), "png", new File( "./test.png" ) );
    }
}
