package org.jb2011.lnf.beautyeye.ninepatch4j;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * @Classname NinePatch
 * @Description TODO
 * @Date 2019-12-20 17:01
 * @Created by zhoujinming
 */

public class NinePatch {
    public static final String EXTENSION_9PATCH = ".9.png";
    private BufferedImage mImage;
    private NinePatchChunk mChunk;

    public BufferedImage getImage() {
        return this.mImage;
    }

    public NinePatchChunk getChunk() {
        return this.mChunk;
    }

    public static NinePatch load(URL fileUrl, boolean convert) throws IOException {
        BufferedImage image = null;

        try {
            image = GraphicsUtilities.loadCompatibleImage(fileUrl);
        } catch (MalformedURLException var4) {
            return null;
        }

        boolean is9Patch = fileUrl.getPath().toLowerCase().endsWith(".9.png");
        return load(image, is9Patch, convert);
    }

    public static NinePatch load(InputStream stream, boolean is9Patch, boolean convert) throws IOException {
        BufferedImage image = null;

        try {
            image = GraphicsUtilities.loadCompatibleImage(stream);
        } catch (MalformedURLException var5) {
            return null;
        }

        return load(image, is9Patch, convert);
    }

    public static NinePatch load(BufferedImage image, boolean is9Patch, boolean convert) {
        if (!is9Patch) {
            if (!convert) {
                return null;
            }

            image = convertTo9Patch(image);
        } else {
            ensure9Patch(image);
        }

        return new NinePatch(image);
    }

    public int getWidth() {
        return this.mImage.getWidth();
    }

    public int getHeight() {
        return this.mImage.getHeight();
    }

    public boolean getPadding(int[] padding) {
        this.mChunk.getPadding(padding);
        return true;
    }

    public void draw(Graphics2D graphics2D, int x, int y, int scaledWidth, int scaledHeight) {
        this.mChunk.draw(this.mImage, graphics2D, x, y, scaledWidth, scaledHeight, 0, 0);
    }

    private NinePatch(BufferedImage image) {
        this.mChunk = NinePatchChunk.create(image);
        this.mImage = this.extractBitmapContent(image);
    }

    private static void ensure9Patch(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int i;
        int pixel;
        for(i = 0; i < width; ++i) {
            pixel = image.getRGB(i, 0);
            if (pixel != 0 && pixel != -16777216) {
                image.setRGB(i, 0, 0);
            }

            pixel = image.getRGB(i, height - 1);
            if (pixel != 0 && pixel != -16777216) {
                image.setRGB(i, height - 1, 0);
            }
        }

        for(i = 0; i < height; ++i) {
            pixel = image.getRGB(0, i);
            if (pixel != 0 && pixel != -16777216) {
                image.setRGB(0, i, 0);
            }

            pixel = image.getRGB(width - 1, i);
            if (pixel != 0 && pixel != -16777216) {
                image.setRGB(width - 1, i, 0);
            }
        }

    }

    private static BufferedImage convertTo9Patch(BufferedImage image) {
        BufferedImage buffer = GraphicsUtilities.createTranslucentCompatibleImage(image.getWidth() + 2, image.getHeight() + 2);
        Graphics2D g2 = buffer.createGraphics();
        g2.drawImage(image, 1, 1, (ImageObserver)null);
        g2.dispose();
        return buffer;
    }

    private BufferedImage extractBitmapContent(BufferedImage image) {
        return image.getSubimage(1, 1, image.getWidth() - 2, image.getHeight() - 2);
    }
}