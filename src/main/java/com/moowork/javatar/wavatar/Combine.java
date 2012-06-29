package com.moowork.javatar.wavatar;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

// TODO: This is just here temporary.
public class Combine
{
    public static void main( String... args )
        throws Exception
    {
        final int width = 80 * 19;
        final int height = 80 * 7;

        final BufferedImage image = new BufferedImage( width, height, BufferedImage.TYPE_4BYTE_ABGR );
        final Graphics2D g = image.createGraphics();

        combine( g, 0, loadAll( "fade", 4 ) );
        combine( g, 80, loadAll( "mask", 11 ) );
        combine( g, 2 * 80, loadAll( "shine", 11 ) );
        combine( g, 3 * 80, loadAll( "brow", 8 ) );
        combine( g, 4 * 80, loadAll( "eyes", 13 ) );
        combine( g, 5 * 80, loadAll( "pupils", 11 ) );
        combine( g, 6 * 80, loadAll( "mouth", 19 ) );

        ImageIO.write( image, "png", new File( "./wavatar.png" ) );
    }

    private static void combine( final Graphics2D g, final int y, final BufferedImage[] images )
        throws Exception
    {
        for ( int i = 0; i < images.length; i++ )
        {
            g.drawImage( images[i], null, i * 80, y );
        }
    }

    private static BufferedImage[] loadAll( final String base, final int maxNum )
        throws Exception
    {
        final BufferedImage[] list = new BufferedImage[maxNum];
        for ( int i = 0; i < maxNum; i++ )
        {
            list[i] = loadSingle( base, i + 1 );
        }

        return list;
    }

    private static BufferedImage loadSingle( final String base, final int num )
        throws Exception
    {
        final String name = String.format( base + "%d.png", num );
        final File file = new File( "/Users/srs/development/workspace/identicon/monsterid/wavatars/parts/" + name );
        System.out.println(file);
        return ImageIO.read( file );
    }
}
